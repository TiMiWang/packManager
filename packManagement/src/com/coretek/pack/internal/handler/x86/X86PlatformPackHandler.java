package com.coretek.pack.internal.handler.x86;

import com.coretek.pack.internal.handler.PackWorkerManager;
import com.coretek.pack.internal.ihandler.IPlatformPackHandler;

public class X86PlatformPackHandler implements IPlatformPackHandler {

	private String installerEXEPath = PackWorkerManager.packBasePath+"/IS12/System/";
	private String cmdEXEName = "IsCmdBld.exe";
	private String projectName = "LambdaPRO-v12.ism";

	private String installProjectPath = "";

	public X86PlatformPackHandler(String installProjectPath) {
		this.installProjectPath = installProjectPath;
	}
	
	@Override
	public void copyPlatform2installerPath(String platformPath) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean installPackRun() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean installPackageMoving(String srcInstallPackagePath,
			String destInstallPackagePath) {
		// TODO Auto-generated method stub
		return false;
	}

}
