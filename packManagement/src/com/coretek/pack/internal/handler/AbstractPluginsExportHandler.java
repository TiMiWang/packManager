package com.coretek.pack.internal.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.coretek.pack.internal.handler.PackWorkerManager;
import com.coretek.pack.internal.ihandler.IPluginsExportHandler;

public abstract class AbstractPluginsExportHandler implements IPluginsExportHandler{
	
	private static boolean isModifyMVNCfg = false;
	
	protected String pluginsExportPath = PackWorkerManager.packBasePath+"/pluginsExportTools";
	protected String M2Path = pluginsExportPath+"/.m2";
	protected String P2RepoPath = M2Path+"/p2_repository";
	protected String commonRepoPath = M2Path+"/repository";
	
	protected String MVNPath = pluginsExportPath+"/apache-maven-3.5.2";
	protected String parentModulePath = pluginsExportPath+"/com.coretek.maven.parent";
	protected String buildModulePath = pluginsExportPath+"/com.coretek.maven.build";
	
	@Override
	public boolean MVNConfigModify() {
		boolean status = true;
		if(isModifyMVNCfg){
			return status;
		}
		String cfgXMLPath = MVNPath+"/conf/settings.xml";
		String linesepar = System.getProperty("line.separator");
		File cfgFile = new File(cfgXMLPath);
		try{
			if(cfgFile.exists()){
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cfgFile)));
				String bufferline = "";
				while((bufferline = reader.readLine())!=null){
					if(bufferline.contains("<localRepository>")){
						bufferline = "<localRepository>"+commonRepoPath+"</localRepository>";
					}
					buffer.append(bufferline);
					buffer.append(linesepar);
				}
				reader.close();
				cfgFile.delete();
				cfgFile.createNewFile();
				PrintWriter writer = new PrintWriter(new FileOutputStream(cfgFile));
				writer.write(buffer.toString());
				writer.flush();
				writer.close();
				isModifyMVNCfg = true;
		}else{
			status = false;
		}
		}catch(Exception ex){
			status = false;
		}
		return status;
	}


//	@Override
//	public boolean ExportRun(String buildXmlPath) {
//		boolean status = true;
//		try {
//			String[] commands = {PackWorkerManager.packBasePath+"/ant/bin/ant.bat"};
//			ProcessBuilder processtest = new ProcessBuilder(commands);
//			processtest.directory(new File(buildXmlPath));
//			processtest.redirectErrorStream(true);
//			Process process;
//			process = processtest.start();
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					process.getInputStream()));
//			String line = br.readLine();
//			while (line != null) {
//				System.out.print(line);
//				line = br.readLine();
//			}
//			if ((process.waitFor() != 0)) {
//				System.out.println("error");
//				status = false;
//			}
//		} catch (Exception e) {
//			status = false;
//			e.printStackTrace();
//		}finally{
//			FileUtils.delAllFile(pluginsSrcPath);
//		}
//		return status;
//	}


}
