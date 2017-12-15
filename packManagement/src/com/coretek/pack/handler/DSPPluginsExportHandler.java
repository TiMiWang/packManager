package com.coretek.pack.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.coretek.pack.util.FileUtils;

public class DSPPluginsExportHandler implements IPluginsExportHandler{

	private String workspacePath = "F:/dsp/workspace_dsp_new";
	private String pluginsSrcPath = "F:/dsp/dabao/packUtils/singlePluginSrc";
	private String eclipseSDKPath = "F:/dsp/eclipse4_5_1";
	
	private String exportPlugins = "cn.edu.buaa.ui.nattable,com.coretek.analysis.common.core,com.coretek.analysis.core,com.coretek.analysis.launcher,com.coretek.analysis.logging,com.coretek.analysis.messagebroadcast,com.coretek.analysis.metric,com.coretek.analysis.overview,com.coretek.analysis.sqlite,com.coretek.analysis.sqlvirtualtable,com.coretek.analysis.ui,com.coretek.common.utils,com.coretek.core.runtime,com.coretek.core.runtime.jni,com.coretek.core.runtime.logging,com.coretek.core.runtime.utils,com.coretek.debug.gdbremote.core,com.coretek.debug.gdbremote.ui,com.coretek.dsp.ide.util,com.coretek.eventanalyzer,com.coretek.eventanalyzer.agnostic,com.coretek.eventanalyzer.agnostic.eventreceive,com.coretek.eventanalyzer.analysis.cpuusage,com.coretek.eventanalyzer.analysis.memusage,com.coretek.eventanalyzer.analysis.systemload,com.coretek.eventanalyzer.analysis.task,com.coretek.eventanalyzer.common.core,com.coretek.eventanalyzer.common.ui,com.coretek.eventanalyzer.eventgraph,com.coretek.eventanalyzer.eventtable,com.coretek.eventanalyzer.overview,com.coretek.eventanalyzer.plugins.deltaos,com.coretek.eventanalyzer.plugins.deltaos6,com.coretek.eventanalyzer.plugins.targetconnection,com.coretek.eventanalyzer.plugins.timtargetconnection,com.coretek.eventanalyzer.wvboot,com.coretek.ide.boot,com.coretek.ide.common.command.core,com.coretek.ide.common.core,com.coretek.ide.common.ui,com.coretek.ide.dyload,com.coretek.ide.eventanalyzer,com.coretek.ide.jfreechart,com.coretek.ide.jnetpcap,com.coretek.ide.jnetpcap.ui,com.coretek.ide.loggers,com.coretek.ide.memoryrendering,com.coretek.ide.memorytransport.floattext,com.coretek.ide.monitor,com.coretek.ide.multidspapp,com.coretek.ide.multidspapp.core,com.coretek.ide.nativeLib,com.coretek.ide.netassist,com.coretek.ide.nettransmission,com.coretek.ide.rcp.supportManager,com.coretek.ide.system.core,com.coretek.ide.system.ui,com.coretek.ide.tftpsvc,com.coretek.ide.tftpsvc.global.ui,com.coretek.ide.ui,com.coretek.ide.ui.swt.core,com.coretek.ide.utils,com.coretek.tim,com.coretek.tom,com.coretek.tools.dsp.boxmonitor,com.coretek.tools.dsp.terminal.console,com.coretek.tools.dsp.terminal.console.ui,com.coretek.tools.externaltools,com.coretek.tools.ide.configmanager,com.coretek.tools.ide.ftpServer,com.coretek.tools.ide.wftp,com.coretek.tools.syscfg,com.coretek.ui.swt.core,com.google.gson,com.rtos.chart,com.ti.ccstudio.core,com.windriver.jsqlite";
	@Override
	public String buildXmlGen(String pluginXmlPath) {
		StringBuffer buffer = new StringBuffer(); 
		pluginXmlPath = pluginXmlPath.replace("\\", "/");
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffer.append("<project basedir=\".\" default=\"build\">\n");
		buffer.append("    <target name=\"build\">\n");
		buffer.append("    	<java jar=\"");
		buffer.append(eclipseSDKPath);
		buffer.append("/plugins/org.eclipse.equinox.launcher_1.3.100.v20150511-1540.jar\" fork=\"true\">\n");
		buffer.append("			<arg value=\"-application\"/>\n");
		buffer.append("			<arg value=\"org.eclipse.ant.core.antRunner\"/>\n");
		buffer.append("    		<arg value=\"-buildfile\"/>\n");
		buffer.append("    		<arg value=\"");
		buffer.append(pluginXmlPath);
		buffer.append("\"/>\n");
		buffer.append("    		<arg value=\"-data\"/>\n");
		buffer.append("    		<arg value=\"");
		buffer.append(workspacePath);
		buffer.append("\"/>\n");
		buffer.append("    	</java>\n");
		buffer.append("    </target>\n");
		buffer.append("</project>\n");
		return buffer.toString();
	}

	@Override
	public String pluginXmlGen(String pluginsBuildOutPath) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffer.append("<project default=\"plugin_export\" name=\"build\">\n");
		buffer.append("	<target name=\"plugin_export\">\n");
		buffer.append("<pde.exportPlugins destination=\"");
		buffer.append(pluginsBuildOutPath);
		buffer.append("\" exportSource=\"false\" exportType=\"directory\" plugins=\"");
		buffer.append(exportPlugins);
		buffer.append("\" useJARFormat=\"true\"/>\n");
		buffer.append("	</target>\n");
		buffer.append("</project>\n");
		return buffer.toString();
	}

	@Override
	public boolean ExportRun(String buildXmlPath) {
		boolean status = true;
		try {
			String[] commands = {System.getenv("ANT_PATH")+"/bin/ant.bat"};
			ProcessBuilder processtest = new ProcessBuilder(commands);
			processtest.directory(new File(buildXmlPath));
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
	public boolean PluginSrcRedirectePath(String pluginSrcPath) {
		try{
		FileUtils.delAllFile(pluginsSrcPath);
		FileUtils.copyFolder(pluginSrcPath, pluginsSrcPath);
		}catch(Exception ex){
			ex.getStackTrace();
			return false;
		}
		return true;
	}

}
