package com.coretek.pack.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coretek.pack.model.Person;
import com.coretek.pack.model.PersonExample;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.util.AjaxMsg;
import com.coretek.pack.util.Captcha;
import com.coretek.pack.util.sessionContexts;
@Controller
@RequestMapping("loginController")

public class LoginController extends BaseController{
	private AjaxMsg ajaxMsg = AjaxMsg.newInstance();
	
	private IPersonSerivce personService;
	private PersonExample personexample = new PersonExample();
	
	
	/*@RequestMapping(value="index",method=RequestMethod.GET)
	public String getLogin(){
		return "redirect:../index.jsp";
	}*/
	
	
	public IPersonSerivce getPersonService() {
		return personService;
	}
	@Inject
	public void setPersonService(IPersonSerivce personService) {
		this.personService = personService;
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		sessionContexts.removeSession(session);
		session.invalidate();
		return "redirect:/loginController/index.do";
	}
	@RequestMapping("/wenjuanlogin")
	public String index(HttpSession session) {
		return "redirect:/";
	}
	@RequestMapping("/question")
	public String quesion(HttpSession session) {
		return "redirect:/question.jsp";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String login(String username, String password, String checkcode,
			Model model, HttpSession session) {
		String cc = (String) session.getAttribute("cc");
		session.removeAttribute("cc");
		if (!checkcode.equals(cc)) {
			model.addAttribute("error", "验证码出错，请重新输入");
			return "admin/login";
		}
		personexample.createCriteria().andNameEqualTo(username).andPasswordEqualTo(password);
		List<Person> personlist = personService.selectByExample(personexample);
		personexample.clear();
		if(personlist != null && personlist.size()>0){
			session.setAttribute("userid", personlist.get(0).getId());
			model.addAttribute("username", personlist.get(0).getName());
			sessionContexts.addSessoin(session);
			return "admin/index";
//			return "redirect:../laboratorymodel/login.do";
		}else {
			model.addAttribute("error", "用户名或密码错误");
			return "admin/login";
		}


	}
	@RequestMapping("/drawCheckCode")
	public void drawCheckCode(HttpServletResponse resp, HttpSession session)
			throws IOException {
		resp.setContentType("image/jpg");
		int width = 200;
		int height = 30;
		Captcha c = Captcha.getInstance();
		c.set(width, height);
		String checkcode = c.generateCheckcode();
		session.setAttribute("cc", checkcode);
		OutputStream os = resp.getOutputStream();
		ImageIO.write(c.generateCheckImg(checkcode), "jpg", os);
	}
	

	
	
	private static final int SEND_MAX_Count=10;
	//
	private static final int SEND_MAX_TIME=5;
	public static class SendModel
	{
		private String validateVode; 
		public String getValidateVode() {
			return validateVode;
		}
		public void setValidateVode(String validateVode) {
			this.validateVode = validateVode;
		}
		public int getSendCount() {
			return sendCount;
		}
		public void setSendCount(int sendCount) {
			this.sendCount = sendCount;
		}
		public Date getSendTime() {
			return sendTime;
		}
		public void setSendTime(Date sendTime) {
			this.sendTime = sendTime;
		}
		private int sendCount;
		private Date sendTime;
	}
	
	/***
	 * //生成随机字符串
	 * @return
	 */
		public String randString()
		{
		   String result="";
		   Random r = new Random();
			  while(true){
			  	int x = r.nextInt(999999);
			  if(x > 99999){
				  result=String.valueOf(x);
				  break;
			  }
			  }
			  return result;
		}

	
	
}
