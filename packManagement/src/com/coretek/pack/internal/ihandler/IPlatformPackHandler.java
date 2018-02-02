package com.coretek.pack.internal.ihandler;

public interface IPlatformPackHandler {

	/**
	 * 拷贝平台到打包项目路径下
	 * @param platformPath
	 */
	boolean copyPlatform2installerPath(String platformPath);
	
	/**
	 * 开始运行打安装包操作
	 * @return
	 */
	boolean installPackRun();
	
	/**
	 * 将安装包移动到指定的可下载路径下
	 * @param srcInstallPackagePath
	 * @param destInstallPackagePath
	 * @return
	 */
	boolean installPackageMoving(String srcInstallPackagePath,String destInstallPackagePath,String setupZipName);
	
}
