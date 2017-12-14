package com.coretek.pack.handler;

public interface IPluginsExportHandler {
	
	/**
	 * 将插件源码复制到工作空间指定的路径下
	 * @param pluginSrcPath
	 * @return
	 */
	void PluginSrcRedirectePath(String pluginSrcPath);
	
	String buildXmlGen(String pluginXmlPath);
	String pluginXmlGen(String pluginsBuildOutPath);
	
	boolean ExportRun(String buildXmlPath);
}
