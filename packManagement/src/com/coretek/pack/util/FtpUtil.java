package com.coretek.pack.util;



import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil { 
	
	public static Deque<String> IPQueue = new LinkedBlockingDeque<String>();
    
	/**
	 * Description: 向FTP服务器上传文件
	 * @Version1.0 
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param path FTP服务器保存目录
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 
	 */
	public static int uploadFile(String ip,int port,String username, String password, String path, String filename, InputStream input) {
		int reply = 230;
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ip, port);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);//登录
			ftp.setControlEncoding("GBK");
			ftp.setBufferSize(1024);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return reply;
			}
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);			
			
			input.close();
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
			reply = 425;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return reply;
	}
}  
