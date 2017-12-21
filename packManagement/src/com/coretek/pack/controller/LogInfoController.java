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
import com.coretek.pack.page.SystemContext;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.service.IlogInfoService;
import com.coretek.pack.util.ParameterConstants;

@Controller
@RequestMapping("loginfo")
public class LogInfoController {
	
	IlogInfoService loginfoService;
	IPackModeService packmodeService;
	IPersonSerivce personService;
	
	LogInfoExample loginfoexample = new LogInfoExample();
	
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

	/**
	 * 分页已实现
	 * 要修改 Mapping.xml文件， 增加    <if test="pageNumber != null" >
     * 						limit ${pageNumber},${pageSize}
     *						</if>
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getallloginfo",method = RequestMethod.GET)
	public String getallloginfo(HttpSession session,Model model){
		loginfoexample.clear();
		loginfoexample.setPageNumber(ParameterConstants.ZERO);
		loginfoexample.setPageSize(ParameterConstants.PageSizeConstantMax);
		//总数
		int count = loginfoService.countByExample(loginfoexample);
		
		loginfoexample.clear();
		loginfoexample.setPageNumber(SystemContext.getPageOffset());
		loginfoexample.setPageSize(SystemContext.getPageSize());
		loginfoexample.setOrderByClause("date_time desc");
		List<LogInfo> loginfolist =  loginfoService.selectByExample(loginfoexample);
		Pager<LogInfo> listloginfo = new Pager<LogInfo>(count,
				loginfolist);
		model.addAttribute("loginfolist",listloginfo);
		return "loginfo/list";
	}
}
