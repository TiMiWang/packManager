package com.coretek.pack.internal.handler.x86;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.coretek.pack.internal.handler.PackWorkerManager;
import com.coretek.pack.internal.ihandler.IPlatformPackHandler;
import com.coretek.pack.util.FileUtils;

public class X86PlatformPackHandler implements IPlatformPackHandler {
	

	private String installerEXEPath = PackWorkerManager.packBasePath+"/IS12/System/";
	private String cmdEXEName = "IsCmdBld.exe";
	private String projectName = "LambdaPRO-v12.ism";

	private String installProjectPath = "";

	public X86PlatformPackHandler(String installProjectPath) {
		this.installProjectPath = installProjectPath;
	}

	@Override
	public boolean copyPlatform2installerPath(String platformPath) {
		boolean status = true;
		try{
		String destPath = installProjectPath + "/LambdaPRO";
		FileUtils.copyFolder(platformPath, destPath);
		if(!qtCfgFileHandle(destPath)){
			status = false;
		}
		}catch(Exception ex){
			ex.getStackTrace();
			status = false;
		}
		return status;
	}
	
	/**
	 * 移动qt相关文件方便打包操作。
	 * @param platformPath
	 * @return
	 */
	private boolean qtCfgFileHandle(String platformPath){
		boolean status = true;
		try{
			File cfgFile = new File(platformPath,"host/pub/ConfigFull.cfg");
			if(cfgFile.exists()){		
				cfgFile.delete();
			}
			File noQtCfgFile = new File(platformPath,"noqtconfig/ConfigFull.cfg");
			File qtCfgFile = new File(platformPath,"qtconfig/ConfigFull.cfg");
			
			if(!noQtCfgFile.exists()){
				noQtCfgFile.getParentFile().mkdirs();
				noQtCfgFile.createNewFile();
				PrintWriter writer = new PrintWriter(noQtCfgFile);
				writer.write(genQtConfigStr(false));
				writer.flush();
				writer.close();
			}
			if(!qtCfgFile.exists()){
				qtCfgFile.getParentFile().mkdirs();
				qtCfgFile.createNewFile();
				PrintWriter writer = new PrintWriter(qtCfgFile);
				writer.write(genQtConfigStr(true));
				writer.flush();
				writer.close();
			}
			
			File qtFile = new File(platformPath,"deltaos6.0/target/qt");
			if(qtFile.exists()){
				FileUtils.moveFolder(qtFile.getAbsolutePath(), platformPath+"/qt");
			}else{
				status = false;
			}
			
		}catch(Exception ex){
			ex.getStackTrace();
			status = false;
		}
		return status;
	}
	
	private String genQtConfigStr(boolean isSupport){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffer.append("<root>\n");
		buffer.append("<configs>\n");
		buffer.append("<project name=\"dkm\">\n");
		if(isSupport){
			buffer.append("<config hasNext=\"Y\" isBuild=\"Y\" isIncludePath=\"Y\" isQt=\"Y\" isSelect=\"Y\" isSupportQt=\"Y\" name=\"支持 QT\" symbol=\"IS_SUPPORT_QT\" symbolValue=\"Y\" value=\"Y\" default=\"N\" beforeLabe=\"\" />\n");
		}
		buffer.append("</project>\n");
		buffer.append("<project name=\"rtp\">\n");
		if(isSupport){
			buffer.append("<config hasNext=\"Y\" isBuild=\"Y\" isIncludePath=\"Y\" isQt=\"Y\" isSelect=\"Y\" isSupportQt=\"Y\" name=\"支持 QT\" symbol=\"IS_SUPPORT_QT\" symbolValue=\"Y\" value=\"Y\" default=\"N\" beforeLabe=\"\" />\n");
		}
		buffer.append("</project>\n");
		buffer.append("</configs>\n");
		buffer.append("</root>\n");
		return buffer.toString();
	}

	@Override
	public boolean installPackRun() {
		boolean status = true;
		try {
			String[] commands = {installerEXEPath+"/"+cmdEXEName,"-p",projectName,"-r","SINGLE_EXE_IMAGE"};
			ProcessBuilder processtest = new ProcessBuilder(commands);
			processtest.directory(new File(installProjectPath));
			processtest.redirectErrorStream(true);
			Process process = processtest.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = br.readLine();
			while (line != null) {
				System.out.print(line);
				line = br.readLine();
			}
			if ((process.waitFor() != 0)) {
				System.out.println("error");
				status = false;
			}
			//压缩安装包到指定的路径
			
		} catch (Exception ex) {
			status = false;
			ex.getStackTrace();
		} finally {

		}
		return status;
	}
	
	@Override
	public boolean installPackageMoving(String srcInstallPackagePath,String destInstallPackagePath,String setupZipName){
		boolean status = true;
		try{
			File file = new File(srcInstallPackagePath);
			if(file.exists()){
				FileUtils.fileToZip(srcInstallPackagePath, destInstallPackagePath, setupZipName);
				file.delete();
//				FileUtils.moveFile(srcInstallPackagePath, destInstallPackagePath);
			}else{
				status = false;
			}
		}catch(Exception ex){
			status = false;
			ex.getStackTrace();
		}
		return status;
	}

}
