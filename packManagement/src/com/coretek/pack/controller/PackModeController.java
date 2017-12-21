package com.coretek.pack.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coretek.pack.handler.IPackWorker;
import com.coretek.pack.handler.LogInfoHandler;
import com.coretek.pack.handler.PackWorkerManager;
import com.coretek.pack.model.LogInfoExample;
import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.PackModeExample;
import com.coretek.pack.model.Person;
import com.coretek.pack.model.PersonExample;
import com.coretek.pack.page.Pager;
import com.coretek.pack.page.SystemContext;
import com.coretek.pack.service.IPackModeService;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.service.IlogInfoService;
import com.coretek.pack.util.AjaxMsg;
import com.coretek.pack.util.ConstantEnum.MsgType;
import com.coretek.pack.util.FileUtils;
import com.coretek.pack.util.ParameterConstants;
import com.coretek.pack.util.sessionContexts;

@Controller
@RequestMapping("packmode")
public class PackModeController {
	
	private IPackModeService packModeService;
	private IPersonSerivce personservice;
	private IlogInfoService loginfoservice;
	
	private PackWorkerManager packWorkerManager = PackWorkerManager.getInstance();
	private LogInfoHandler loginfoHandler = new LogInfoHandler();
	
	private PackModeExample packmodeexample = new PackModeExample();
	private PersonExample personexample = new PersonExample();
	private LogInfoExample loginfo = new LogInfoExample();
	
	private String statusInfo = "";
	
	private AjaxMsg ajaxMsg = AjaxMsg.newInstance();
	
	public IPackModeService getPackModeService() {
		return packModeService;
	}
	
	@Inject
	public void setPackModeService(IPackModeService packModeService) {
		this.packModeService = packModeService;
		loginfoHandler.setPackModeService(packModeService);
	}
	public IPersonSerivce getPersonservice() {
		return personservice;
	}
	@Inject
	public void setPersonservice(IPersonSerivce personservice) {
		this.personservice = personservice;
		loginfoHandler.setPersonservice(personservice);
	}
	public IlogInfoService getLoginfoservice() {
		return loginfoservice;
	}
	@Inject
	public void setLoginfoservice(IlogInfoService loginfoservice) {
		this.loginfoservice = loginfoservice;
		loginfoHandler.setLoginfoservice(loginfoservice);
	}
	
	@RequestMapping(value="getallpackmode",method = RequestMethod.GET)
	public String getAllPackMode(Model model,HttpSession session){
		session = sessionContexts.getSession(session.getId());
		if(session!=null){
			
		PackWorkerManager.packBasePath = PackWorkerManager.getpackUtilsPath(session);
		PackWorkerManager.packUtilsPath = PackWorkerManager.packBasePath+"/packUtils";
		
		packmodeexample.clear();
		packmodeexample.setPageNumber(ParameterConstants.ZERO);
		packmodeexample.setPageSize(ParameterConstants.PageSizeConstantMax);
		//总数
		int count = packModeService.countByExample(packmodeexample);
		
		packmodeexample.clear();
		packmodeexample.setPageNumber(SystemContext.getPageOffset());
		packmodeexample.setPageSize(SystemContext.getPageSize());
		
		packmodeexample.clear();
		List<PackMode> packmodelist = packModeService.selectByExample(packmodeexample);
		Pager<PackMode> listpackmode = new Pager<PackMode>(count,
				packmodelist);
		model.addAttribute("packmodelist", listpackmode);
		if(statusInfo!=""){
			model.addAttribute("errorinfo", statusInfo);
			statusInfo = "";
		}
		}
		return "packmode/list";
	}
	
	@RequestMapping(value="uploadfile/{id}",method = RequestMethod.POST)
	public @ResponseBody Object uploadfile(Model model, @PathVariable int id, HttpSession session,@RequestParam MultipartFile file,HttpServletRequest request){
			ajaxMsg.clear();
			PackMode packmode = packModeService.selectByPrimaryKey(id);
			if(packmode==null){
				ajaxMsg.addHeader(MsgType.ERROR, "当前打包配置有问题");
				return ajaxMsg;
			}
			
	      	String path = PackWorkerManager.packUtilsPath+"/"+"LambdaPRO_"+id;
	      	if(!new File(path).exists()){
	      		FileUtils.delAllFile(path);
	      	}
	      	new File(path).mkdir();
	        String fileName = file.getOriginalFilename();    
	        File dir = new File(path,fileName);          
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }  
	        //MultipartFile自带的解析方法  
	        try {
				file.transferTo(dir);
			} catch (Exception e) {
				e.printStackTrace();
			}
			packmode.setPlatformLocalPath(dir.getAbsolutePath());
			packModeService.updateByPrimaryKey(packmode);
			ajaxMsg.addHeader(MsgType.SUCCESS, "上传成功");
			
			//新建日志
			loginfoHandler.insert((int)session.getAttribute("userid"), id, "成功将文件："+fileName+"上传到服务器");
			
		return ajaxMsg;
	}
	
	@RequestMapping(value="updatestatus/{id}",method = RequestMethod.GET)
	public @ResponseBody Object updatestatus(Model model, @PathVariable int id, HttpSession session){
		ajaxMsg.clear();
		IPackWorker packworker = packWorkerManager.getPackWorker(id);
		PackMode packmode = packModeService.selectByPrimaryKey(id);
		if(packmode!=null){
		if(packworker!=null){
			ajaxMsg.addHeader(MsgType.SUCCESS, "请求成功");
			ajaxMsg.addData("id", id);
			ajaxMsg.addData("loginfo", packworker.getLogInfo());
			ajaxMsg.addData("status", packmode.getStatus());
			ajaxMsg.addData("issuccess",packworker.isSuccess());
		}else{
			packmode.setStatus(0);
			packModeService.updateByPrimaryKey(packmode);
			ajaxMsg.addData("id", id);
			ajaxMsg.addHeader(MsgType.ERROR, "打包失败");
		}
		}else{
			ajaxMsg.addHeader(MsgType.ERROR, "打包失败");
		}
		return ajaxMsg;
	}
	
	@RequestMapping(value="delete/{id}",method = RequestMethod.GET)
	public @ResponseBody Object delete(Model model, @PathVariable int id, HttpSession session){
		ajaxMsg.clear();
		int userid = (int)session.getAttribute("userid");
		Person person = personservice.selectByPrimaryKey(userid);
		int permission = person.getPermission();
		if(permission!=1){
			ajaxMsg.addHeader(MsgType.ERROR, "权限不足");
			return ajaxMsg;
		}
		if(id > ParameterConstants.ZERO){
			packModeService.deleteByPrimaryKey(id);
			ajaxMsg.addHeader(MsgType.SUCCESS, "删除成功");
		}else {
			ajaxMsg.addHeader(MsgType.ERROR, "该用户信息有误,不能删除");
		}
		return ajaxMsg;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addGet(Model model) {
		PackMode packmode = new PackMode();
		model.addAttribute("packmode",packmode );
		return "packmode/add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addPost(@Valid PackMode packmode,
			BindingResult br, Model model,HttpSession session) {
		if (br.hasErrors()) {
			statusInfo = "发生未知错误";
			return "redirect:/packmode/getallpackmode.do";
		}	
		int userid = (int)session.getAttribute("userid");
		Person person = personservice.selectByPrimaryKey(userid);
		int permission = person.getPermission();
		if(permission!=1){
			statusInfo = "权限不足";
			return "redirect:/packmode/getallpackmode.do";
		}
		packModeService.insert(packmode);
		return "redirect:/packmode/getallpackmode.do";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateGet(@PathVariable int id, Model model) {
		PackMode packmode;
		if (id > 0) {
			packmode = packModeService.selectByPrimaryKey(id);
		} else {
			packmode = new PackMode();
		}
		model.addAttribute("packmode", packmode);
		return "packmode/update";
	}

	@RequestMapping(value = "updateandpack/{id}", method = RequestMethod.POST)
	public String updatePost(@PathVariable("id") int id,
			@Valid PackMode packmode, BindingResult br, Model model,HttpServletRequest request, HttpSession session) {
		if (br.hasErrors()) {
			return "packmode/update";
		}
		String userid = (Integer)session.getAttribute("userid")+"";
		if(userid==""){
			return "packmode/update";
		}
		Person person = personservice.selectByPrimaryKey(Integer.parseInt(userid));
		if(person==null){
			return "packmode/update";
		}
		if (id > 0) {
			PackMode tpackmode= packModeService.selectByPrimaryKey(id);
			packmode.setId(id);
			packmode.setStatus(1);
			if(packmode.getIsSvnCheck()==1){
		      	final String path = PackWorkerManager.packUtilsPath+"/"+"LambdaPRO_"+id;   
		        packmode.setPlatformLocalPath(path);
			}else{
				packmode.setPlatformLocalPath(tpackmode.getPlatformLocalPath());
			}
			packModeService.updateByPrimaryKey(packmode);
			
			IPackWorker packworker = packWorkerManager.getPackWorker(id);
			if(packworker!=null){
				File outputPath = new File(packworker.getoutputpackpath());
				if(outputPath.exists()){
					FileUtils.delFolder(outputPath.getAbsolutePath());
				}
				packWorkerManager.removePackWorker(id);
			}
			
			//開始调用异步打包
			packworker = packWorkerManager.createPackWorker(packmode,person,packModeService,session.getServletContext().getRealPath("resources/platform"));
			packWorkerManager.packWorkerWorking(packworker);
			//新建日志
			loginfoHandler.insert((int)session.getAttribute("userid"), id, "进行打安装包操作");
		}
		return "redirect:/packmode/getallpackmode.do";
	}
	
	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
	public String downInstallPackFile(@PathVariable("id") int id,HttpSession session){
		String visitPath = "";
		IPackWorker packwork = PackWorkerManager.getInstance().getPackWorker(id);
		if(packwork==null){
			return null;
		}
		String installPackPath = packwork.getoutputpackpath();
		File file = new File(installPackPath,"Setup.exe");  
		if(file.exists()){	
		String resourcesPath = file.getAbsolutePath();
		resourcesPath = resourcesPath.replace("\\", "/");
		visitPath = resourcesPath.substring(resourcesPath.indexOf("resources/platform"),resourcesPath.length()); 
		//新建日志
		String userid = session.getAttribute("userid")+"";
		if(userid!=""){
			loginfoHandler.insert(Integer.parseInt(userid), id, "下载了安装包文件");
		}
		}
		System.out.println("输出路径："+visitPath);
		return "redirect:/"+visitPath;  
		
	} 
	
//	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<byte[]> downInstallPackFile(@PathVariable("id") int id,HttpSession session){
//		IPackWorker packwork = PackWorkerManager.getInstance().getPackWorker(id);
//		if(packwork==null){
//			return null;
//		}
//		String installPackPath = packwork.getoutputpackpath();
//		ResponseEntity<byte[]> entity = null;
//		try {
//			File file = new File(installPackPath,"Setup.exe");  
//			if(file.exists()){
//				String dfileName = new String(file.getName().getBytes("gb2312"), "iso8859-1");
//				HttpHeaders headers = new HttpHeaders();  
//				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//				headers.setContentDispositionFormData("attachment", dfileName);
//				entity = new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);  
//				//新建日志
//				loginfoHandler.insert((int)session.getAttribute("userid"), id, "下载了安装包文件");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
//		return entity;  
//		
//	} 
	

}
