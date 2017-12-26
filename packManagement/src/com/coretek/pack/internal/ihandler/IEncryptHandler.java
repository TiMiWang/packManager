package com.coretek.pack.internal.ihandler;

public interface IEncryptHandler {

	boolean cleanTempFiles();
	boolean dcptFileGEN(int packModeId);
	boolean encryptRun(String arch, String systemVersion,int versionInfo, int packModeId);
	boolean sapFileGEN(int packModeId);
	boolean vidFileGEN(String arch, String systemVersion,int versionInfo, int time, int packModeId);
}
