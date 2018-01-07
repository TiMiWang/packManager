package com.coretek.pack.internal.ihandler;

public interface IPluginsExportHandler {
	
	
	/**
	 * 修改setting localRepository为绝对路径下的.m2
	 * 只修改一次通过isM判断odifyMVNCfg
	 * @return
	 */
	boolean MVNConfigModify();
	
	/**
	 * 修改父pom的本地P2仓库路径
	 * @return
	 */
	boolean MVNParentModuleModify();
	
	/**
	 * 编译安装父module到仓库
	 * @return
	 */
	boolean MVNParentModuleBuild();
	
	/**
	 * 
	 * @param pluginsSrcPath
	 * @return
	 */
	boolean pluginsBuildAndInstall(String pluginsSrcPath);
	
	boolean redirectPLuginsToPlatform(String platformPluginsPath);
	
}
