package com.coretek.pack.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coretek.pack.model.LogInfo;
import com.coretek.pack.model.LogInfoExample;
import com.coretek.pack.page.Pager;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.service.IlogInfoService;

@Controller
@RequestMapping("loginfo")
public class LogInfoController {
	
	IlogInfoService loginfoService;
	IPackModeService packmodeService;
	IPersonSerivce personService;
	
	public IPackModeService getPackmodeService() {
		return packmodeService;
	}

	@Inject
	public void setPackmodeService(IPackModeService packmodeService) {
		this.packmodeService = packmodeService;
	}

	public IPersonSerivce getPersonService() {
		return personService;
	}

	@Inject
	public void setPersonService(IPersonSerivce personService) {
		this.personService = personService;
	}

	public IlogInfoService getLoginfoService() {
		return loginfoService;
	}

	@Inject
	public void setLoginfoService(IlogInfoService loginfoService) {
		this.loginfoService = loginfoService;
	}


	@RequestMapping(value="getallloginfo",method = RequestMethod.GET)
	public String getallloginfo(HttpSession session,Model model){
		LogInfoExample loginfoexample = new LogInfoExample();
		loginfoexample.clear();
		List<LogInfo> loginfolist =  loginfoService.selectByExample(loginfoexample);
		Pager<LogInfo> listloginfo = new Pager<LogInfo>(loginfolist.size(),
				loginfolist);
		model.addAttribute("loginfolist",listloginfo);
		return "loginfo/list";
	}
}
