package com.coretek.pack.internal.handler.dsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.coretek.pack.internal.handler.AbstractPluginsExportHandler;
import com.coretek.pack.util.FileUtils;

public class DSPPluginsExportHandler extends AbstractPluginsExportHandler{
	
	private String dspP2RepoPath = P2RepoPath+"/dsp";

	private String dspparentModulePath = parentModulePath+"/dsp";
	private String dspbuildModulePath = buildModulePath+"/dsp";
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
						bufferline = "<url>"+dspP2RepoPath+"</url>";
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
			String[] commands = {MVNPath+"/bin/mvn.bat","clean","install"};
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
		FileUtils.copyFolder(dspbuildModulePath, pluginsSrcPath+"/dsp");
		File tmpBuildFile = new File(pluginsSrcPath+"/dsp");
		if(tmpBuildFile.exists()){
			try {
				String[] commands = {MVNPath+"/bin/mvn.bat","clean","install"};
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
		String dspGroupPath = commonRepoPath+"/"+groupName.replace(".", "/");
		File dspGroupFile = new File(dspGroupPath);
		if(dspGroupFile.exists() && dspGroupFile.isDirectory()){
			List<File> jarList = FileUtils.fileFilter(dspGroupFile, ".jar");
			if(jarList.size()>0){
				for (File file : jarList)
				{
					FileUtils.copyFile(file.getAbsolutePath(), platformPluginsPath);
				}
				//TODO 需要对需要解压的jar进行解压操作
			}else{
				status = false;
			}
		}
		return status;
	}


}
