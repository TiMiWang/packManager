package com.coretek.pack.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.Person;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.util.FileUtils;

public class DSPPackWorker implements IPackWorker {
	

	private String LogInfo = "开始打包流程";
	private static String SVNPATH = "C:/Program Files (x86)/Subversion/bin"; 

	private boolean isRunning = false;
	private boolean isSuccess = false;

	private PackMode packmode;
	private String prePlatformPath;
	private String preSrcPath;
	private String tempoutputpackpath;
	private IPackModeService packModeService = null;
	private Person person;

	public DSPPackWorker(PackMode packmode,Person person,IPackModeService packModeService,String tempoutputpackpath) {
		this.packmode = packmode;
		this.person = person;
		this.packModeService = packModeService;
		this.tempoutputpackpath = tempoutputpackpath;
	}

	@Override
	public boolean isSuccess() {
		return isSuccess;
	}

	@Override
	public String getLogInfo() {
		return LogInfo;
	}

	@Override
	public void run() {
		try {
			isRunning = true;
			/*********************/
			if(packmode.getIsSvnCheck()==1){
				LogInfo = "开始svn下载";
				boolean status = exportPlatformFromSVN(packmode.getSvnNetPath(),packmode.getPlatformLocalPath());
				if(status){
					LogInfo = "完成svn下载";
				}else{
					LogInfo = "svn下载失败";
					isSuccess = false;
					packmode.setStatus(2);
					return;
				}
			}else{
				LogInfo = "开始解压平台";
				//此时packmode.getPlatformLocalPath()为压缩包路径
				File platformFile = new File(packmode.getPlatformLocalPath());
		        if(platformFile.exists()){
		        	FileUtils.delAllFile(packmode.getPlatformLocalPath());
		        	FileUtils.zipToFile(platformFile.getAbsolutePath(), platformFile.getParent());
		        	FileUtils.delFile(platformFile.getAbsolutePath());
		        	packmode.setPlatformLocalPath(platformFile.getParent());
		        	LogInfo = "完成平台解压";
		        }else{
		        	LogInfo = "解压包不存在，打包失败";
					isSuccess = false;
					packmode.setStatus(2);
		        	return; 
		        }
			}
			
	        prePlatformPath = packmode.getPlatformLocalPath()+"/"+"platform";
	        preSrcPath = packmode.getPlatformLocalPath()+"/"+"src";
	        if(!new File(prePlatformPath).exists()){
	        	LogInfo = "平台路径不存在";
	        	return;
	        }
	        if(!new File(preSrcPath).exists()){
	        	LogInfo = "源码路径不存在";
	        	return;
	        }
			
			LogInfo = "开始构建插件";
			boolean status = buildPlugins(preSrcPath+"/"+"tool/host/ide/src",prePlatformPath+"/LambdaIDE/eclipse",packmode.getId());
			if(status){
				LogInfo = "完成构建插件";
			}else{
				LogInfo = "构建插件失败";
				isSuccess = false;
				packmode.setStatus(2);
				return;
			}
			LogInfo = "开始构建依赖库";
			status = buildOSLibarys(preSrcPath+"/os",prePlatformPath+"/DeltaOS/lib/c66xx/little");
			if(status){
				LogInfo = "完成构建依赖库";
			}else{
				LogInfo = "构建依赖库失败";
				isSuccess = false;
				packmode.setStatus(2);
				return;
			}
			LogInfo = "开始加密平台";
			status = platformEncrypt(prePlatformPath);
			if(status){
				LogInfo = "完成平台加密";
			}else{
				LogInfo = "平台加密失败";
				isSuccess = false;
				packmode.setStatus(2);
				return;
			}
			LogInfo = "开始平台打安装包";
			status = platformPack(prePlatformPath,tempoutputpackpath,packmode.getId());
			if(status){
				LogInfo = "打安装包包完成";
				isSuccess = true;
				packmode.setStatus(2);
			}else{
				LogInfo = "打安装包失败";
				isSuccess = false;
				packmode.setStatus(2);
			}
			LogInfo = "结束平台安装包打包";
			/*********************/
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			packModeService.updateByPrimaryKey(packmode);
			isRunning = false;
		}

	}

	@Override
	public boolean exportPlatformFromSVN(String svnNetPath,
			String exportLocalPath) {
		boolean status = true;
		File exportFile = new File(exportLocalPath);
		if(!exportFile.exists()){
			exportFile.mkdirs();
		}
		FileUtils.delAllFile(exportLocalPath);
		try {
			//导出src
			LogInfo = "从SVN导出src目录";
			String[] commandSrc = {SVNPATH+"/svn.exe","export",svnNetPath+"/src","--username",person.getSvnUsername(),"--password",person.getSvnPassword()};
			ProcessBuilder processtest = new ProcessBuilder(commandSrc);
			processtest.directory(exportFile);
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
				return status;
			}
			//导出src
			LogInfo = "从SVN导出platform目录";
			String[] commandPlatform = {SVNPATH+"/svn.exe","export",svnNetPath+"/platform","--username",person.getSvnUsername(),"--password",person.getSvnPassword()};
			processtest = new ProcessBuilder(commandPlatform);
			processtest.directory(exportFile);
			processtest.redirectErrorStream(true);
			process = processtest.start();
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			line = br.readLine();
			while (line != null) {
				System.out.print(line);
				line = br.readLine();
			}
			if ((process.waitFor() != 0)) {
				System.out.println("error");
				status = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean buildPlugins(String pluginsSrcPath,
			String pluginsBuildOutPath,int packModeId) {
		boolean status = true;
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdirs();
		try{
		IPluginsExportHandler handler = new DSPPluginsExportHandler();
		LogInfo = "开始生成配置文件";
		String tempPluginXMLPath = tempPMIdFile+"/"+"pluginexport.xml";
		String tempBuildXMLPath = tempPMIdFile+"/"+"build.xml";
		
		String xmlStr = handler.pluginXmlGen(pluginsBuildOutPath);
		FileUtils.contentToFile(tempPluginXMLPath, xmlStr);
		
		xmlStr = handler.buildXmlGen(tempPluginXMLPath);
		FileUtils.contentToFile(tempBuildXMLPath, xmlStr);
		LogInfo = "插件导出前准备";
		status = handler.PluginSrcRedirectePath(pluginsSrcPath);
		if(!status){
			LogInfo = "重定向插件失败";
			status = false;
			return status;
		}
		LogInfo = "开始导出插件";
		status = handler.ExportRun(tempPMIdFile.getAbsolutePath());
		
		}catch(Exception ex){
			status = false;
			ex.getStackTrace();
		}finally{
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		return status;
	}

	@Override
	public boolean buildOSLibarys(String srcPath, String libarysBuildOutPath) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean platformEncrypt(String platformPath) {
		DSPEncryptHandler handler = new DSPEncryptHandler(platformPath);
		boolean status = handler.cleanTempFiles();
		if(status){
			LogInfo = "清理完成";
			System.out.println();
		}else{
			LogInfo = "清理临时文件失败";
			return false;
		}
		LogInfo = "开始.dcpt文件生成";
		status = handler.dcptFileGEN(packmode.getId());
		if(status){
			LogInfo = ".dcpt文件生成成功";
		}else{
			LogInfo = ".dcpt文件生成失败";
			return false;
		}
		LogInfo = "开始加密平台";
		status = handler.encryptRun(packmode.getStructureType(), packmode.getSystemVersion(), packmode.getVersionInfo(), packmode.getId());
		if(status){
			LogInfo = "加密成功";
		}else{
			LogInfo = "加密失败";
			return false;
		}
		status = handler.sapFileGEN(packmode.getId());
		if(status){
			LogInfo = ".sap文件生成成功";
		}else{
			LogInfo = ".sap文件生成失败";
			return false;
		}
		status = handler.vidFileGEN(packmode.getStructureType(), packmode.getSystemVersion(),packmode.getVersionInfo(), packmode.getIndate(), packmode.getId());
		if(status){
			LogInfo = "vid文件生成成功";
		}else{
			LogInfo = "vid文件生成失败";
			return false;
		}
		return true;
	}

	@Override
	public boolean platformPack(String platformPath, String platfomPackPath,int packModeId) {
		boolean status = true;
		final File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdirs();
		try{
		String templatInstallProjectPath = PackWorkerManager.packUtilsPath+"/installPackage"+"/LambdPRO6.0-v12-DSP";
		String destInstallProjectPath = tempPMIdFile+"/"+"LambdPRO6.0-v12-DSP";
		if(!new File(destInstallProjectPath).exists()){
			new File(destInstallProjectPath).mkdirs();
		}
		if(!new File(templatInstallProjectPath).exists()){
			status = false;
			return status;
		}
		FileUtils.copyFolder(templatInstallProjectPath, destInstallProjectPath);
		
		DSPPlatformPackHandler handler = new DSPPlatformPackHandler(destInstallProjectPath);
		LogInfo = "拷贝平台到打包项目路径下";
		handler.copyPlatform2installerPath(platformPath);
		LogInfo = "开始生成安装包操作";
		status = handler.installPackRun();
		if(status){
			LogInfo = "打安装包成功";
		}else{
			LogInfo = "打安装包失败";
			return status;
		}
		status = handler.installPackageMoving(destInstallProjectPath+"/Media/SINGLE_EXE_IMAGE/Package/Setup.exe", platfomPackPath);
		if(status){
			LogInfo = "安装包移动成功";
		}else{
			LogInfo = "安装包移动失败";
			return status;
		}
		
		}catch(Exception ex){
			ex.getStackTrace();
			status = false;
		}finally{
			//异步清除临时文件
			new Thread(new Runnable() {
				@Override
				public void run() {
					FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
				}
			}).start();
		}
		return status;
	}

	@Override
	public boolean isRunning() {

		return isRunning;
	}

	@Override
	public String getoutputpackpath() {
		return tempoutputpackpath;
	}

}
