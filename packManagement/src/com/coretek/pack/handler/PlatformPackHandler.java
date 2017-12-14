package com.coretek.pack.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.coretek.pack.util.FileUtils;

public class PlatformPackHandler implements IPlatformPackHandler {

	private String installerEXEPath = "F:/dsp/dabao/IS12/System/";
	private String cmdEXEName = "IsCmdBld.exe";
	private String projectName = "LambdPRO6.0-v12.ism";

	private String installProjectPath = "";

	public PlatformPackHandler(String installProjectPath) {
		this.installProjectPath = installProjectPath;
	}

	@Override
	public void copyPlatform2installerPath(String platformPath) {
		String destPath = installProjectPath + "/LambdPRO6.0";
		FileUtils.copyFolder(platformPath, destPath);
	}

	@Override
	public boolean installPackRun() {
		boolean status = true;
		try {
			String[] commands = {installerEXEPath+"/"+cmdEXEName,"-p",projectName,"-c","COMP","-a","\"build\""};
			ProcessBuilder processtest = new ProcessBuilder(commands);
			processtest.directory(new File(installProjectPath));
			processtest.redirectErrorStream(true);
			Process process = processtest.start();
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
		} catch (Exception ex) {
			status = false;
			ex.getStackTrace();
		} finally {

		}
		return status;
	}

}
