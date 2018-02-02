package com.coretek.pack.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coretek.pack.internal.handler.LogInfoHandler;
import com.coretek.pack.internal.handler.PackWorkerManager;
import com.coretek.pack.internal.ihandler.IPackWorker;
import com.coretek.pack.model.InstallPack;
import com.coretek.pack.model.InstallPackExample;
import com.coretek.pack.model.InstallPackView;
import com.coretek.pack.model.InstallPackViewExample;
import com.coretek.pack.model.LogInfoExample;
import com.coretek.pack.model.PackModeExample;
import com.coretek.pack.model.Person;
import com.coretek.pack.model.PersonExample;
import com.coretek.pack.page.Pager;
import com.coretek.pack.page.SystemContext;
import com.coretek.pack.service.IInstallPackService;
import com.coretek.pack.service.IInstallPackViewService;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.service.IlogInfoService;
import com.coretek.pack.util.AjaxMsg;
import com.coretek.pack.util.ConstantEnum.MsgType;
import com.coretek.pack.util.ParameterConstants;


@Controller
@RequestMapping("installpack")
public class InstallPackController {

	IlogInfoService logInfoService;
	IPackModeService packModeService;
	IPersonSerivce personService;
	IInstallPackService installPackService;
	IInstallPackViewService installPackViewService;

	LogInfoExample loginfoexample = new LogInfoExample();
	PackModeExample packmodeexample = new PackModeExample();
	PersonExample personexample = new PersonExample();
	InstallPackExample installpackexample = new InstallPackExample();
	InstallPackViewExample installPackViewExample = new InstallPackViewExample();
	
	private LogInfoHandler loginfoHandler = new LogInfoHandler();
	private AjaxMsg ajaxMsg = AjaxMsg.newInstance();
	
	@Inject
	public void setInstallPackViewService(
			IInstallPackViewService installPackViewService) {
		this.installPackViewService = installPackViewService;
	}

	@Inject
	public void setInstallPackService(IInstallPackService installPackService) {
		this.installPackService = installPackService;
	}

	@Inject
	public void setPackmodeService(IPackModeService packmodeService) {
		this.packModeService = packmodeService;
		loginfoHandler.setPackModeService(packmodeService);
	}

	@Inject
	public void setPersonService(IPersonSerivce personService) {
		this.personService = personService;
		loginfoHandler.setPersonservice(personService);
	}

	@Inject
	public void setLoginfoService(IlogInfoService loginfoService) {
		this.logInfoService = loginfoService;
		loginfoHandler.setLoginfoservice(loginfoService);
	}
	
	@RequestMapping(value="delete/{id}",method = RequestMethod.GET)
	public @ResponseBody Object delete(@PathVariable int id, HttpSession session){
		
		ajaxMsg.clear();
//		int userid = (int)session.getAttribute("userid");
//		Person person = personService.selectByPrimaryKey(userid);
//		int permission = person.getPermission();
//		if(permission!=1){
//			ajaxMsg.addHeader(MsgType.ERROR, "权限不足");
//			return ajaxMsg;
//		}
		if(id > ParameterConstants.ZERO){
			installPackService.deleteByPrimaryKey(id);
			ajaxMsg.addHeader(MsgType.SUCCESS, "删除成功");
		}else {
			ajaxMsg.addHeader(MsgType.ERROR, "该用户信息有误,不能删除");
		}
		return ajaxMsg;
		
//		return "redirect:/installpack/getinstallpack/all.do";
	}
	
	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
	public String downInstallPackFile(@PathVariable("id") int id,HttpSession session){
		String visitPath = "";
		InstallPack installpack = installPackService.selectByPrimaryKey(id);
		File file = new File(installpack.getInstallPackPath());  
		if(file.exists()){
		String resourcesPath = file.getAbsolutePath();
		visitPath = resourcesPath.replace("\\", "/");
		//新建日志
		String userid = session.getAttribute("userid")+"";
		if(userid!=""){
			loginfoHandler.insert(Integer.parseInt(userid), id, "下载了安装包文件");
		}
		}else{
			visitPath = "installpack/getinstallpack/all.do";
		}
		return "redirect:/"+visitPath;  
	}
	
	@RequestMapping(value="getinstallpack/all",method = RequestMethod.GET)
	public String getInstallPackAll(HttpSession session,Model model){
		installPackViewExample.clear();
		installPackViewExample.setPageNumber(ParameterConstants.ZERO);
		installPackViewExample.setPageSize(ParameterConstants.PageSizeConstantMax);
		//总数
		int count = installPackViewService.countByExample(installPackViewExample);
		
		installPackViewExample.clear();
		installPackViewExample.setPageNumber(SystemContext.getPageOffset());
		installPackViewExample.setPageSize(SystemContext.getPageSize());
		installPackViewExample.setOrderByClause("date_time desc");
		List<InstallPackView> installpacklist =  installPackViewService.selectByExample(installPackViewExample);
		Pager<InstallPackView> installpackPages = new Pager<InstallPackView>(count,
				installpacklist);
		model.addAttribute("installpackviews",installpackPages);
		return "installpack/list";
	}
	
}


