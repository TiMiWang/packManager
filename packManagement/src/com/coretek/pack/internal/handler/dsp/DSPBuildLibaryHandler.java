package com.coretek.pack.internal.handler.dsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.coretek.pack.internal.handler.PackWorkerManager;

public class DSPBuildLibaryHandler
{

	// test
//	static String packBasePath = "D:/xampp/tomcat";
//	private static String BUILDOSPATH = packBasePath + "/buildOS/bin";
//	private static String CYGWINTOOLPATH = packBasePath + "/buildOS/cygwin/bin";
//	private static String GMAKEPATH = packBasePath + "/buildOS";

	 private static String BUILDOSPATH =
	 PackWorkerManager.packBasePath+"/buildOS/bin";
	 private static String CYGWINTOOLPATH =
	 PackWorkerManager.packBasePath+"/buildOS/cygwin/bin";
	 private static String GMAKEPATH =
	 PackWorkerManager.packBasePath+"/buildOS";

	private String OSSrcPath = "";
	private String targetSrcPath = "";
	private String platformPath = "";
	private CountDownLatch latch = null;
	private ThreadPoolExecutor executor = null;

//	public static void main(String[] args)
//	{
//		DSPBuildLibaryHandler handler = new DSPBuildLibaryHandler("F:/dsp/dabao/packUtils/LambdaPRO/src",
//				"C:/LambdPRO/LambdPRO6.0");
//		handler.buildLibs();
//	}

	public DSPBuildLibaryHandler(String SrcPath, String platformPath)
	{
		this.OSSrcPath = SrcPath + "/os";
		this.targetSrcPath = SrcPath + "/tool/target";
		this.platformPath = platformPath;

		File OSsrcFile = new File(this.OSSrcPath);
		File targetSrcFile = new File(this.targetSrcPath);
		if (OSsrcFile.exists() && targetSrcFile.exists())
		{

			int oslibsCount = OSsrcFile.listFiles().length;
			int targetlibsCount = targetSrcFile.listFiles().length;
			
			latch = new CountDownLatch(oslibsCount + targetlibsCount);
			executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
			executor.setKeepAliveTime(10, TimeUnit.SECONDS);
			executor.allowCoreThreadTimeOut(true);
		}
	}

	public boolean buildLibs()
	{
		boolean status = true;
		try
		{
			// /os
			File srcFile = new File(this.OSSrcPath);
			for (File destSrcFile : srcFile.listFiles())
			{
				if (destSrcFile.exists() && destSrcFile.isDirectory())
				{
					buildLibsJob job = new buildLibsJob(destSrcFile.getAbsolutePath(), platformPath, latch);
					executor.execute(job);
				}
			}
			// /tool/target
			File targetSrcFile = new File(this.targetSrcPath);
			for (File destSrcFile : targetSrcFile.listFiles())
			{
				if (destSrcFile.exists() && destSrcFile.isDirectory())
				{
					buildLibsJob job = new buildLibsJob(destSrcFile.getAbsolutePath(), platformPath, latch);
					executor.execute(job);
				}
			}
			latch.await();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	private class buildLibsJob implements Runnable
	{

		CountDownLatch latch = null;

		private String libSrcPath = "";
		private String platformPath = "";

		public buildLibsJob(String libSrcPath, String platformPath, CountDownLatch latch)
		{
			this.libSrcPath = libSrcPath;
			this.platformPath = platformPath;
			this.latch = latch;
		}

		@Override
		public void run()
		{
			try
			{
				String[] commands = { BUILDOSPATH + "/makeCopy.exe", libSrcPath, platformPath, CYGWINTOOLPATH,
						GMAKEPATH };
				ProcessBuilder processtest = new ProcessBuilder(commands);
				processtest.directory(new File(BUILDOSPATH));
				processtest.redirectErrorStream(true);
				Process process = processtest.start();
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = br.readLine();
				while (line != null)
				{
					System.out.print(line);
					line = br.readLine();
				}
				if ((process.waitFor() != 0))
				{
					System.out.println("error");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				latch.countDown();
			}
		}

	}

}
