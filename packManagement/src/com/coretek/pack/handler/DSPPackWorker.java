package com.coretek.pack.handler;

import java.io.File;

import com.coretek.pack.model.PackMode;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.util.FileUtils;

public class DSPPackWorker implements IPackWorker {
	

	private String LogInfo = "开始打包流程";

	private boolean isRunning = false;

	private PackMode packmode;
	private String prePlatformPath;
	private String preSrcPath;
	private String tempoutputpackpath;
	public IPackModeService packModeService = null;

	public DSPPackWorker(PackMode packmode,IPackModeService packModeService,String tempoutputpackpath) {
		this.packmode = packmode;
		this.packModeService = packModeService;
		this.tempoutputpackpath = tempoutputpackpath;
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
				exportPlatformFromSVN(packmode.getPlatformSvnPath(),packmode.getPlatformLocalPath());
				LogInfo = "完成svn下载";
			}else{
				LogInfo = "开始解压平台";
				//此时packmode.getPlatformLocalPath()为压缩包路径
				File platformFile = new File(packmode.getPlatformLocalPath());
		        if(platformFile.exists()){
//		        	FileUtils.zipToFile(platformFile.getAbsolutePath(), platformFile.getParent());
//		        	FileUtils.delFile(platformFile.getAbsolutePath());
		        	packmode.setPlatformLocalPath(platformFile.getParent());
		        }else{
		        	LogInfo = "解压包不存在，打包失败";
		        	return; 
		        }
		        prePlatformPath = packmode.getPlatformLocalPath()+"/"+"platform";
		        preSrcPath = packmode.getPlatformLocalPath()+"/"+"src";
		        LogInfo = "完成平台解压";
			}
			
			LogInfo = "开始构建插件";
			buildPlugins(preSrcPath+"/"+"tool/host/ide/src",prePlatformPath+"/LambdaIDE/eclipse/plugins",packmode.getId());
			LogInfo = "完成构建插件";
			LogInfo = "开始构建依赖库";
			buildOSLibarys(preSrcPath+"/os",prePlatformPath+"/DeltaOS/lib/c66xx/little");
			LogInfo = "完成构建依赖库";
			LogInfo = "开始加密平台";
			platformEncrypt(prePlatformPath);
			LogInfo = "完成平台加密";
			LogInfo = "开始平台打安装包";
			platformPack(prePlatformPath,tempoutputpackpath,packmode.getId());
			LogInfo = "结束平台打安装包";
			/*********************/
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			LogInfo = "完成打包";
			packmode.setStatus(0);
			packModeService.updateByPrimaryKey(packmode);
			isRunning = false;
		}

	}

	@Override
	public boolean exportPlatformFromSVN(String svnNetPath,
			String exportLocalPath) {
		String commandStr = "svn export "+svnNetPath;
		File exportFile = new File(exportLocalPath);
		try {
			Process process = Runtime.getRuntime().exec(commandStr, new String[]{System.getenv("SVNPATH")}, exportFile);
			if(process.waitFor()==0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		tempPMIdFile.mkdir();
		try{
		IPluginsExportHandler handler = new DSPPluginsExportHandler();
		LogInfo = "开始生成配置文件";
		String tempPluginXMLPath = tempPMIdFile+"/"+"pluginexport.xml";
		String tempBuildXMLPath = tempPMIdFile+"/"+"build.xml";
		
		String xmlStr = handler.pluginXmlGen(pluginsBuildOutPath);
		FileUtils.contentToFile(tempPluginXMLPath, xmlStr);
		
		xmlStr = handler.buildXmlGen(tempPluginXMLPath);
		FileUtils.contentToFile(tempBuildXMLPath, xmlStr);
		handler.PluginSrcRedirectePath(pluginsSrcPath);
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
		return false;
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
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdir();
		try{
		String templatInstallProjectPath = PackWorkerManager.packUtilsPath+"/installPackage"+"/LambdPRO6.0-v12-DSP";
		String destInstallProjectPath = tempPMIdFile+"/"+"LambdPRO6.0-v12-DSP";
		FileUtils.copyFolder(templatInstallProjectPath, destInstallProjectPath);
		
		PlatformPackHandler handler = new PlatformPackHandler(destInstallProjectPath);
		handler.copyPlatform2installerPath(platformPath);
		handler.installPackRun();
		
		}catch(Exception ex){
			ex.getStackTrace();
		}finally{
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
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
