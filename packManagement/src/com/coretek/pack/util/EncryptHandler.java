package com.coretek.pack.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.coretek.pack.handler.PackWorkerManager;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class EncryptHandler {
	String platformPath = "";
	String encryptPath = PackWorkerManager.packUtilsPath+"/"+"encryption";
	
	public EncryptHandler(String platformPath){
		this.platformPath = platformPath;
	}
	
	public void cleanTempFiles(){
		File workspaceFile = new File(platformPath,"workspace");
		File tsFile = new File(platformPath,"host/pub/ts");
		File configureFile = new File(platformPath,"host/ide/platform/eclipse/configuration");
		File vidDatFile = new File(platformPath,"host/ide/LambdaPRO/eclipse/plugins/com.coretek.tools.ide.configManager_1.0.0/vid.dat");
		File bochsConnectionsFile = new File(platformPath,"host/pub/bochsConnections");
		File spawnerFile = new File(platformPath,"host/ide/tools/eclipse/plugins/org.eclipse.cdt.core.win32_5.2.0.201102110609/os/win32/x86/spawner.dat");
	
		FileUtils.delAllFile(workspaceFile.getAbsolutePath());
		FileUtils.delAllFile(tsFile.getAbsolutePath());
		FileUtils.delAllFile(configureFile.getAbsolutePath());
		FileUtils.delAllFile(bochsConnectionsFile.getAbsolutePath());
		FileUtils.delFile(vidDatFile.getAbsolutePath());
		FileUtils.delFile(spawnerFile.getAbsolutePath());

	}
	
	/**
	 * dcpt.jsa文件生成
	 */
	public void dcptFileGEN(int packModeId){
		String jarPath = platformPath+"/"+"LambdaIDE/eclipse/plugins/com.coretek.ide.system.core_1.0.0.jar";
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath,""+"temp"+"_"+packModeId);
		if(!tempPMIdFile.exists()){
			tempPMIdFile.mkdir();
		}
		try {
			FileUtils.zipToFile(jarPath, tempPMIdFile.getAbsolutePath());
			String filePath = tempPMIdFile.getAbsolutePath()+"/"+"com/coretek/ide/system/core"+"/"+"Activator.class";
			String destPath = platformPath+"/"+"LambdaIDE/eclipse/jre/bin/client";
			FileUtils.copyFile(filePath, destPath);
			new File(destPath,"Activator.class").renameTo(new File(destPath,"dcpt.jsa"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
	}
	
	/**
	 * 进行加密
	 * @param arch 架构
	 * @param systemVersion 系统版本
	 * @param versionInfo 是否试用版 0：试用；1：正式
	 */
	public void encryptRun(String arch,String systemVersion,int versionInfo,int packModeId){
		String fileStr = "japbin6.0"+"-"+arch+"-"+systemVersion;
		if(versionInfo==0){
			fileStr = fileStr+"-"+"demo";
		}
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath,""+"temp"+"_"+packModeId);
		if(!tempPMIdFile.exists()){
			tempPMIdFile.mkdir();
		}
		FileUtils.copyFolder(encryptPath+"/"+fileStr, tempPMIdFile.getAbsolutePath());
		
		File encryptFile = new File(tempPMIdFile.getAbsoluteFile()+"/"+fileStr);
		if(encryptFile.exists()){
		try {
			String command = "encrypt key.dat list.txt"+" "+platformPath;
			Process process = Runtime.getRuntime().exec(command, new String[]{}, encryptFile);
			if((process.waitFor()!=0)){
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		}
	}
	
	/**
	 * sap.jsa文件生成
	 */
	public void sapFileGEN(int packModeId){
		String jarPath = platformPath+"/"+"LambdaIDE/eclipse/plugins/com.coretek.ide.system.core_1.0.0.jar";
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath,""+"temp"+"_"+packModeId);
		if(!tempPMIdFile.exists()){
			tempPMIdFile.mkdir();
		}
		try {
			FileUtils.zipToFile(jarPath, tempPMIdFile.getAbsolutePath());
			String filePath = tempPMIdFile.getAbsolutePath()+"/"+"com/coretek/ide/system/core"+"/"+"Activator.class";
			String destPath = platformPath+"/"+"LambdaIDE/eclipse/jre/bin/client";
			FileUtils.copyFile(filePath, destPath);
			new File(destPath,"Activator.class").renameTo(new File(destPath,"sap.jsa"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
	
	}

	/**
	 * 
	 * @param versionInfo 是否試用版
	 * @param time 有效期
	 * @param packModeId
	 */
	public void vidFileGEN(String arch,String systemVersion,int versionInfo,int time,int packModeId){
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath,""+"temp"+"_"+packModeId);
		if(!tempPMIdFile.exists()){
			tempPMIdFile.mkdir();
		}
		FileUtils.copyFolder(encryptPath+"/"+"file_encrypt", tempPMIdFile.getAbsolutePath());
		File encryptFile = new File(tempPMIdFile.getAbsoluteFile()+"/"+"file_encrypt");
		if(encryptFile.exists()){
		try {
			String memoryThreadPath = encryptFile.getAbsolutePath()+"/"+"memoryThread.cfg";
			if(memeroyThreadModeify(arch,systemVersion,versionInfo,time,memoryThreadPath)){
				//執行生成命令
				String command = "java -jar EncrayKeyTool_NoDelother.jar"+" "+platformPath+" "+memoryThreadPath;
				Process process = Runtime.getRuntime().exec(command, new String[]{}, encryptFile);
				if((process.waitFor()==0)){
					File vidFile = new File(encryptFile.getAbsoluteFile(),"vid.dat");
					 if(vidFile.exists()){
						 //拷貝vid.dat到平臺目錄
						 String newVidPath = platformPath+"/"+"LambdaIDE/eclipse/plugins/com.coretek.tools.ide.configmanager_1.0.0";
						 FileUtils.moveFile(vidFile.getAbsolutePath(), newVidPath); 
					 }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		}
	}
	
	/**
	 * 修改memoryThread.cfg文件
	 * @param versionInfo
	 * @param time
	 * @param memeroyThreadpath
	 */
	private boolean memeroyThreadModeify(String arch,String systemVersion,int versionInfo,int time,String memeroyThreadpath){
		File memeroyThreadFile = new File(memeroyThreadpath);
		if(memeroyThreadFile.exists()){
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc =  db.parse(memeroyThreadFile);
				Element rootEle = doc.getDocumentElement();
				NodeList nodelist = rootEle.getChildNodes();
				for(int i=0;i<nodelist.getLength();i++){
					Node node = nodelist.item(i);
					String name = node.getNodeName();
					if(name.equals("isThread")){
						if(versionInfo==0){
							node.setNodeValue("true");
						}else{
							node.setNodeValue("false");
						}
					}else if(name.equals("version")){
						node.setNodeValue(arch+"-"+systemVersion);
					}else if(name.equals("time")){
						node.setNodeValue(time+"");
					}
				}
				OutputFormat outformat = new OutputFormat(doc);
				OutputStream os = new FileOutputStream(memeroyThreadFile);
				XMLSerializer xmlserilizer = new XMLSerializer(os, outformat);
				xmlserilizer.serialize(doc);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}
	

}
