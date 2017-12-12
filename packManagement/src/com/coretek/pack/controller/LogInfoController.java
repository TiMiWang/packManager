package com.coretek.pack.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.logInfo;
import com.coretek.pack.model.logInfoExample;
import com.coretek.pack.page.Pager;
import com.coretek.pack.service.IlogInfoService;

@Controller
@RequestMapping("loginfo")
public class LogInfoController {
	
	IlogInfoService loginfoService;
	logInfoExample loginfoexample = new logInfoExample();
	

	public IlogInfoService getLoginfoService() {
		return loginfoService;
	}

	@Inject
	public void setLoginfoService(IlogInfoService loginfoService) {
		this.loginfoService = loginfoService;
	}


	@RequestMapping(value="getallloginfo",method = RequestMethod.GET)
	public String getallloginfo(HttpSession session,Model model){
		loginfoexample.clear();
		List<logInfo> loginfolist =  loginfoService.selectByExample(loginfoexample);
		Pager<logInfo> listloginfo = new Pager<logInfo>(loginfolist.size(),
				loginfolist);
		model.addAttribute("loginfolist",listloginfo);
		return "loginfo/list";
	}
}
