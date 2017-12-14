package com.coretek.pack.handler;

public interface IPlatformPackHandler {

	void copyPlatform2installerPath(String platformPath);
	
	boolean installPackRun();
}
