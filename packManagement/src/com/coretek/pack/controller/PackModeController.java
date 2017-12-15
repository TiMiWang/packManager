package com.coretek.pack.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.coretek.pack.handler.PackWorkerManager;
import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.PackModeExample;
import com.coretek.pack.model.PersonExample;
import com.coretek.pack.model.logInfoExample;
import com.coretek.pack.page.Pager;
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
	
	private PackModeExample packmodeexample = new PackModeExample();
	private PersonExample personexample = new PersonExample();
	private logInfoExample loginfo = new logInfoExample();
	
	private String statusInfo = "";
	
	private AjaxMsg ajaxMsg = AjaxMsg.newInstance();
	
	public IPackModeService getPackModeService() {
		return packModeService;
	}
	
	@Inject
	public void setPackModeService(IPackModeService packModeService) {
		this.packModeService = packModeService;
	}
	public IPersonSerivce getPersonservice() {
		return personservice;
	}
	@Inject
	public void setPersonservice(IPersonSerivce personservice) {
		this.personservice = personservice;
	}
	public IlogInfoService getLoginfoservice() {
		return loginfoservice;
	}
	@Inject
	public void setLoginfoservice(IlogInfoService loginfoservice) {
		this.loginfoservice = loginfoservice;
	}
	
	@RequestMapping(value="getallpackmode",method = RequestMethod.GET)
	public String getAllPackMode(Model model,HttpSession session){
		session = sessionContexts.getSession(session.getId());
		if(session!=null){
		packmodeexample.clear();
		List<PackMode> packmodelist = packModeService.selectByExample(packmodeexample);
		Pager<PackMode> listpackmode = new Pager<PackMode>(1,
				packmodelist);
		model.addAttribute("packmodelist", listpackmode);
		}
		return "packmode/list";
	}
	
	@RequestMapping(value="uploadfile/{id}",method = RequestMethod.POST)
	public @ResponseBody Object pack(Model model, @PathVariable int id, HttpSession session,@RequestParam MultipartFile file,HttpServletRequest request){
			ajaxMsg.clear();
			PackMode packmode = packModeService.selectByPrimaryKey(id);
			if(packmode==null){
				ajaxMsg.addHeader(MsgType.ERROR, "当前打包配置有问题");
				return ajaxMsg;
			}
			
	      	String path = PackWorkerManager.packUtilsPath+"/"+"LambdaPRO_"+id;
	      	if(!new File(path).exists()){
	      		new File(path).mkdir();
	      	}
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
			
		return ajaxMsg;
	}
	
	@RequestMapping(value="updatestatus/{id}",method = RequestMethod.GET)
	public @ResponseBody Object updatestatus(Model model, @PathVariable int id, HttpSession session){
		ajaxMsg.clear();
		PackMode packmode = packModeService.selectByPrimaryKey(id);
		IPackWorker packworker = packWorkerManager.getPackWorker(id);
		if(packmode!=null && packworker!=null){
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
		return ajaxMsg;
	}
	
	@RequestMapping(value="delete/{id}",method = RequestMethod.GET)
	public @ResponseBody Object delete(Model model, @PathVariable int id, HttpSession session){
		ajaxMsg.clear();
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
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "packmode/add";
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
			@Valid PackMode packmode, BindingResult br, Model model,HttpServletRequest request) {
		if (br.hasErrors()) {
			return "packmode/update";
		}
		if (id > 0) {
			PackMode tpackmode= packModeService.selectByPrimaryKey(id);
			packmode.setId(id);
			packmode.setStatus(1);
			if(packmode.getIsSvnCheck()==1){
		      	String path = request.getSession().getServletContext().getRealPath("upload")+"/"+"temp_"+id;     
		        File dir = new File(path);          
		        if(dir.exists()){  
		            FileUtils.delFolder(path); 
		        }
		        dir.mkdir();
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
			packworker = packWorkerManager.createPackWorker(packmode,packModeService,"F:/dsp/dabao/web/packManagement/WebContent/resources/platfrom");
			packWorkerManager.packWorkerWorking(packworker);
		}
		return "redirect:/packmode/getallpackmode.do";
	}
	
	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> downInstallPackFile(@PathVariable("id") int id){
		IPackWorker packwork = PackWorkerManager.getInstance().getPackWorker(id);
		if(packwork==null){
			return null;
		}
		String installPackPath = packwork.getoutputpackpath();
        ResponseEntity<byte[]> entity = null;
        try {
    		File file = new File(installPackPath);  
    		if(file.exists()){
        		String dfileName = new String(file.getName().getBytes("gb2312"), "iso8859-1");
    	        HttpHeaders headers = new HttpHeaders();  
    	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	        headers.setContentDispositionFormData("attachment", dfileName);
    	        entity = new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);  
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return entity;  
		
	} 
	

}
