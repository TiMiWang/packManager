package com.coretek.pack.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.coretek.pack.util.FileUtils;

public class DSPPlatformPackHandler implements IPlatformPackHandler {

	private String installerEXEPath = "F:/dsp/dabao/IS12/System/";
	private String cmdEXEName = "IsCmdBld.exe";
	private String projectName = "LambdPRO6.0-v12.ism";

	private String installProjectPath = "";

	public DSPPlatformPackHandler(String installProjectPath) {
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
			String[] commands = {installerEXEPath+"/"+cmdEXEName,"-p",projectName,"-r","SINGLE_EXE_IMAGE"};
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
			//压缩安装包到指定的路径
			
		} catch (Exception ex) {
			status = false;
			ex.getStackTrace();
		} finally {

		}
		return status;
	}
	
	@Override
	public boolean installPackageMoving(String srcInstallPackagePath,String destInstallPackagePath){
		boolean status = true;
		try{
			//test

			File file = new File(srcInstallPackagePath);
			if(file.exists()){
				FileUtils.moveFile(srcInstallPackagePath, destInstallPackagePath);
			}else{
				status = false;
			}
		}catch(Exception ex){
			status = false;
			ex.getStackTrace();
		}
		return status;
	}


}
