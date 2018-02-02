package com.coretek.pack.internal.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.coretek.pack.model.LogInfo;
import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.Person;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.service.IlogInfoService;

public class LogInfoHandler {
	
	private IlogInfoService loginfoservice = null;
	private IPackModeService packModeService = null;
	private IPersonSerivce personservice = null;
	
	
	public void setLoginfoservice(IlogInfoService loginfoservice) {
		this.loginfoservice = loginfoservice;
	}

	public void setPackModeService(IPackModeService packModeService) {
		this.packModeService = packModeService;
	}

	public void setPersonservice(IPersonSerivce personservice) {
		this.personservice = personservice;
	}

	public int insert(int userId,int packModeId,String content){
		PackMode packmode = packModeService.selectByPrimaryKey(packModeId);
		Person person = personservice.selectByPrimaryKey(userId);
		if(packmode!=null && person!=null){
			LogInfo loginfo = new LogInfo();
			loginfo.setContent(content);
			loginfo.setIndate(packmode.getIndate());
			loginfo.setPackModeStructure(packmode.getStructureType());
			loginfo.setProductName(packmode.getProductName());
			loginfo.setProjectName(packmode.getProjectName());
			loginfo.setRemark(packmode.getRemark());
			loginfo.setSvnNetPath(packmode.getSvnNetPath());
			loginfo.setUserName(person.getName());
			if(packmode.getVersionInfo()==0){
				loginfo.setVersionInfo("试用版");
			}else{
				loginfo.setVersionInfo("正式版");
			}
			Date currentdate = new Date();
			DateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			loginfo.setDateTime(currentdate);
			loginfo.setDateTimeStr(fd.format(currentdate));
			return loginfoservice.insert(loginfo);
		}
		return -1;
	}
}
