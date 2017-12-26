package com.coretek.pack.internal.handler.x86;

import com.coretek.pack.internal.handler.PackWorkerManager;
import com.coretek.pack.internal.ihandler.IEncryptHandler;

public class X86EncryptHandler implements IEncryptHandler {

	String platformPath = "";
	String encryptPath = PackWorkerManager.packUtilsPath + "/" + "encryption";

	public X86EncryptHandler(String platformPath) {
		this.platformPath = platformPath;
	}
	
	@Override
	public boolean cleanTempFiles() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dcptFileGEN(int packModeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean encryptRun(String arch, String systemVersion,
			int versionInfo, int packModeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sapFileGEN(int packModeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vidFileGEN(String arch, String systemVersion,
			int versionInfo, int time, int packModeId) {
		// TODO Auto-generated method stub
		return false;
	}

}
