package com.coretek.pack.internal.handler.x86;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.coretek.pack.internal.handler.AbstractPluginsExportHandler;
import com.coretek.pack.util.FileUtils;

public class X86PluginsExportHandler extends AbstractPluginsExportHandler {
	
	public final String platformType = "x86";
	
	private String dspP2RepoPath = P2RepoPath+"/"+platformType;

	private String dspparentModulePath = parentModulePath+"/"+platformType;
	private String dspbuildModulePath = buildModulePath+"/"+platformType;
	private String groupName = "com.coretek.dsp.group";
	
	@Override
	public boolean MVNParentModuleModify() {
		boolean status = true;
		String dspParentPomPath = dspparentModulePath+"/pom.xml";
		String linesepar = System.getProperty("line.separator");
		File dspParentPomFile = new File(dspParentPomPath);
		try{
			if(dspParentPomFile.exists()){
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dspParentPomFile)));
				String bufferline = "";
				while((bufferline = reader.readLine())!=null){
					if(bufferline.contains("<url>")){
						bufferline = "<url>file:///"+dspP2RepoPath+"</url>";
					}
					buffer.append(bufferline);
					buffer.append(linesepar);
				}
				reader.close();
				dspParentPomFile.delete();
				dspParentPomFile.createNewFile();
				PrintWriter writer = new PrintWriter(new FileOutputStream(dspParentPomFile));
				writer.write(buffer.toString());
				writer.flush();
				writer.close();
		}else{
			status = false;
		}
		}catch(Exception ex){
			status = false;
		}
		return status;
	}
	@Override
	public boolean MVNParentModuleBuild() {
		boolean status = true;
		try {
			String[] commands = {MVNPath+"/bin/mvn.cmd","clean","install"};
			ProcessBuilder processtest = new ProcessBuilder(commands);
			processtest.directory(new File(dspparentModulePath));
			processtest.redirectErrorStream(true);
			Process process;
			process = processtest.start();
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
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public boolean pluginsBuildAndInstall(String pluginsSrcPath) {
		boolean status = true;
		FileUtils.copyFolder(dspbuildModulePath, pluginsSrcPath+"/"+platformType);
		File tmpBuildFile = new File(pluginsSrcPath+"/"+platformType);
		if(tmpBuildFile.exists()){
			try {
				String[] commands = {MVNPath+"/bin/mvn.cmd","clean","install"};
				ProcessBuilder processtest = new ProcessBuilder(commands);
				processtest.directory(tmpBuildFile);
				processtest.redirectErrorStream(true);
				Process process;
				process = processtest.start();
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
			} catch (Exception e) {
				status = false;
				e.printStackTrace();
			}
		}
		return status;
	}
	
	@Override
	public boolean redirectPLuginsToPlatform(String platformPluginsPath) {
		boolean status = true;
		try{
		String dspGroupPath = commonRepoPath+"/"+groupName.replace(".", "/");
		File dspGroupFile = new File(dspGroupPath);
		if(dspGroupFile.exists() && dspGroupFile.isDirectory()){
			List<File> jarList = FileUtils.fileFilter(dspGroupFile, ".jar");
			if(jarList.size()>0){
				for (File file : jarList)
				{
					String destFilePath = platformPluginsPath+"/"+file.getName().replace("-", "_");
					if(file.getName().contains("net.mbl.ide.project.templates") || file.getName().contains("com.windriver.jsqlite")){
						FileUtils.zipToFile(file.getAbsolutePath(), destFilePath.replace(".jar", ""));
					}else{
						FileUtils.copyFileAndReName(file.getAbsolutePath(), destFilePath);
					}
				}
				//TODO 需要对需要解压的jar进行解压操作
			}else{
				status = false;
			}
			FileUtils.delAllFile(dspGroupPath);
		}
		}catch(Exception ex){
			ex.getStackTrace();
			status = false;
		}
		return status;
	}
	
//	public static void main(String[] args){
//		try {
//			FileUtils.zipToFile("D:/xampp/tomcat/pluginsExportTools/.m2/repository/com/coretek/dsp/group/net.mbl.ide.project.templates/1.0.0/net.mbl.ide.project.templates-1.0.0.jar", "D:/xampp/tomcat/pluginsExportTools/.m2/repository/com/coretek/dsp/group/net.mbl.ide.project.templates/1.0.0/net.mbl.ide.project.templates_1.0.0");
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


}
