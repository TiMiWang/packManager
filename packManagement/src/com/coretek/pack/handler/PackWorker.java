package com.coretek.pack.handler;

import java.io.File;

import com.coretek.pack.model.PackMode;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.util.EncryptHandler;

public class PackWorker implements IPackWorker {

	private String LogInfo;

	private boolean isRunning = false;

	private PackMode packmode;
	private String prePlatformPath;
	private String tempoutputpackpath;
	public IPackModeService packModeService = null;

	public PackWorker(PackMode packmode,IPackModeService packModeService,String tempoutputpackpath) {
		this.packmode = packmode;
		this.packModeService = packModeService;
		this.prePlatformPath = packmode.getPlatformLocalPath();
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
				exportPlatformFromSVN(packmode.getPlatformSvnPath(),prePlatformPath);
			}
			LogInfo = "完成svn下载";
			LogInfo = "开始构建插件";
//			buildPlugins();
			LogInfo = "完成构建插件";
			LogInfo = "开始构建依赖库";
//			buildOSLibarys();
			LogInfo = "完成构建依赖库";
			LogInfo = "开始加密平台";
			platformEncrypt(prePlatformPath);
			LogInfo = "完成平台加密";
			LogInfo = "开始平台打安装包";
			platformPack(prePlatformPath,tempoutputpackpath);
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
			String pluginsBuildOutPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildOSLibarys(String srcPath, String libarysBuildOutPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean platformEncrypt(String platformPath) {
		EncryptHandler handler = new EncryptHandler(platformPath);
		handler.cleanTempFiles();
		handler.dcptFileGEN(packmode.getId());
		handler.encryptRun(packmode.getStructureType(), packmode.getSystemVersion(), packmode.getVersionInfo(), packmode.getId());
		handler.sapFileGEN(packmode.getId());
		handler.vidFileGEN(packmode.getStructureType(), packmode.getSystemVersion(),packmode.getVersionInfo(), packmode.getIndate(), packmode.getId());
		return true;
	}

	@Override
	public boolean platformPack(String platformPath, String platfomPackPath) {
		// TODO Auto-generated method stub
		return false;
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
