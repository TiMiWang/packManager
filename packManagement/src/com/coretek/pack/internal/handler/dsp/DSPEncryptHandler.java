package com.coretek.pack.internal.handler.dsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.coretek.pack.internal.handler.PackWorkerManager;
import com.coretek.pack.internal.ihandler.IEncryptHandler;
import com.coretek.pack.util.FileUtils;

public class DSPEncryptHandler implements IEncryptHandler{
	String platformPath = "";
	String encryptPath = PackWorkerManager.packUtilsPath + "/" + "encryption";

	public DSPEncryptHandler(String platformPath) {
		this.platformPath = platformPath;
	}

	@Override
	public boolean cleanTempFiles() {
		boolean status = true;
		File workspaceFile = new File(platformPath, "workspace");
		File spawnerFile = new File(platformPath, "LambdaIDE/eclipse/plugins/org.eclipse.cdt.core.win32.x86_5.4.0.201509131935/os/win32/x86/spawner.dat");
		File vid1DatFile = new File(platformPath,"LambdaIDE/eclipse/plugins/com.ti.ccstudio.base_6.2.1.01781/vid.dat");
		File vid22DatFile = new File(platformPath,"LambdaIDE/eclipse/plugins/com.coretek.tools.ide.configmanager_1.0.0/vid.dat");
		try {
			FileUtils.delAllFile(workspaceFile.getAbsolutePath());
			if(spawnerFile.exists()){
				FileUtils.delFile(spawnerFile.getAbsolutePath());
			}
			if(vid1DatFile.exists()){
				FileUtils.delFile(vid1DatFile.getAbsolutePath());
			}
			if(vid22DatFile.exists()){
				FileUtils.delFile(vid22DatFile.getAbsolutePath());
			}
		} catch (Exception ex) {
			ex.getStackTrace();
			status = false;
		}
		return status;
	}

	/**
	 * dcpt.jsa文件生成
	 */
	@Override
	public boolean dcptFileGEN(int packModeId) {
		boolean status = true;
		String jarPath = platformPath
				+ "/"
				+ "LambdaIDE/eclipse/plugins/com.coretek.ide.system.core_1.0.0.jar";
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdirs();
		try {
			if(!new File(jarPath).exists()){
				status = false;
				return status;
			}
			FileUtils.zipToFile(jarPath, tempPMIdFile.getAbsolutePath());
			String filePath = tempPMIdFile.getAbsolutePath() + "/"
					+ "com/coretek/ide/system/core" + "/" + "Activator.class";
			String destPath = platformPath + "/"
					+ "LambdaIDE/eclipse/jre/bin/client";
			FileUtils.copyFile(filePath, destPath);
			File newDcptFile = new File(destPath, "dcpt.jsa");
			if (newDcptFile.exists()) {
				newDcptFile.delete();
			}
			new File(destPath, "Activator.class").renameTo(newDcptFile);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		return status;
	}

	/**
	 * 进行加密
	 * 
	 * @param arch
	 *            架构
	 * @param systemVersion
	 *            系统版本
	 * @param versionInfo
	 *            是否试用版 0：试用；1：正式
	 */
	@Override
	public boolean encryptRun(String arch, String systemVersion,
			int versionInfo, int packModeId) {
		boolean status = true;
		String fileStr = "japbin6.0" + "-" + arch;
		if (versionInfo == 0) {
			fileStr = fileStr + "-" + "demo";
		}
		if (!systemVersion.equals("")) {
			fileStr = fileStr + "-" + systemVersion;
		}
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdirs();
		FileUtils.copyFolder(encryptPath + "/" + fileStr,
				tempPMIdFile.getAbsolutePath() + "/" + fileStr);

		File encryptFile = new File(tempPMIdFile.getAbsoluteFile() + "/"
				+ fileStr);
		if (encryptFile.exists()) {
			try {
				String[] commands = {
						encryptFile.getAbsolutePath() + "/encrypt.bat",
						"key.dat", "list.txt", platformPath };
				ProcessBuilder processtest = new ProcessBuilder(commands);
				processtest.directory(encryptFile);
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
			} catch (Exception e) {
				e.printStackTrace();
				status = false;
			} finally {
				FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
			}
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * sap.jsa文件生成
	 */
	@Override
	public boolean sapFileGEN(int packModeId) {
		boolean status = true;
		String jarPath = platformPath
				+ "/"
				+ "LambdaIDE/eclipse/plugins/com.coretek.ide.system.core_1.0.0.jar";
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdirs();
		try {
			FileUtils.zipToFile(jarPath, tempPMIdFile.getAbsolutePath());
			String filePath = tempPMIdFile.getAbsolutePath() + "/"
					+ "com/coretek/ide/system/core" + "/" + "Activator.class";
			String destPath = platformPath + "/"
					+ "LambdaIDE/eclipse/jre/bin/client";
			FileUtils.copyFile(filePath, destPath);
			File newSapFile = new File(destPath, "sap.jsa");
			if (newSapFile.exists()) {
				newSapFile.delete();
			}
			new File(destPath, "Activator.class").renameTo(new File(destPath,
					"sap.jsa"));
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		return status;
	}

	/**
	 * 
	 * @param versionInfo
	 *            是否試用版
	 * @param time
	 *            有效期
	 * @param packModeId
	 */
	@Override
	public boolean vidFileGEN(String arch, String systemVersion,
			int versionInfo, int time, int packModeId) {
		boolean status = true;
		File tempPMIdFile = new File(PackWorkerManager.packUtilsPath, ""
				+ "temp" + "_" + packModeId);
		if (tempPMIdFile.exists()) {
			FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
		}
		tempPMIdFile.mkdirs();
		FileUtils.copyFolder(encryptPath + "/" + "file_encrypt",
				tempPMIdFile.getAbsolutePath() + "/" + "file_encrypt");
		File encryptFile = new File(tempPMIdFile.getAbsoluteFile() + "/"
				+ "file_encrypt");
		if (encryptFile.exists()) {
			try {
				File oldVidFile = new File(encryptFile,"vid.dat");
				if(oldVidFile.exists()){
					oldVidFile.delete();
				}
				String memoryThreadPath = encryptFile.getAbsolutePath() + "/"
						+ "memeroyThread.cfg";
				if (memeroyThreadModeify(arch, systemVersion, versionInfo,
						time, memoryThreadPath)) {
					// 執行生成命令
					String[] commands = { "java", "-jar",
							"EncrayKeyTool_NoDelother.jar", platformPath,
							memoryThreadPath };

					ProcessBuilder processtest = new ProcessBuilder(commands);
					processtest.directory(encryptFile);
					processtest.redirectErrorStream(true);
					Process process = processtest.start();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					String line = br.readLine();
					while (line != null) {
						System.out.print(line);
						line = br.readLine();
					}
					if ((process.waitFor() == 0)) {
						File vidFile = new File(encryptFile.getAbsoluteFile(),
								"vid.dat");
						if (vidFile.exists()) {
							// 拷貝vid.dat到平臺目錄
							String newVidPath = platformPath+ "/"+ "LambdaIDE/eclipse/plugins/com.ti.ccstudio.base_6.2.1.01781";
							FileUtils.copyFile(vidFile.getAbsolutePath(),
									newVidPath);
						} else {
							status = false;
						}
					} else {
						status = false;
					}
				} else {
					status = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				FileUtils.delFolder(tempPMIdFile.getAbsolutePath());
			}
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * 修改memoryThread.cfg文件
	 * 
	 * @param versionInfo
	 * @param time
	 * @param memeroyThreadpath
	 */
	private boolean memeroyThreadModeify(String arch, String systemVersion,
			int versionInfo, int time, String memeroyThreadpath) {
		boolean status = true;
		File memeroyThreadFile = new File(memeroyThreadpath);
		try {
			if (memeroyThreadFile.exists()) {
				memeroyThreadFile.delete();
			}
			memeroyThreadFile.createNewFile();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			buffer.append("<root>\n");
			if (versionInfo == 0) {
				buffer.append("<isThread>");
				buffer.append("true");
				buffer.append("</isThread>");
				buffer.append("\n");
			} else {
				buffer.append("<isThread>");
				buffer.append("false");
				buffer.append("</isThread>");
				buffer.append("\n");
			}
			buffer.append("<version>");
			if(!systemVersion.equals("")){
				buffer.append(arch + "-" + systemVersion);
			}else{
				buffer.append(arch);
			}

			buffer.append("</version>");
			buffer.append("\n");

			buffer.append("<time>");
			buffer.append(time + "");
			buffer.append("</time>");
			buffer.append("\n");

			buffer.append("</root>");

			PrintWriter out = new PrintWriter(memeroyThreadFile);
			out.print(buffer.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

}
