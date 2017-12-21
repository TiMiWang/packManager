package com.coretek.pack.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.coretek.pack.model.Person;
import com.coretek.pack.model.PersonExample;
import com.coretek.pack.page.Pager;
import com.coretek.pack.page.SystemContext;
import com.coretek.pack.service.IPersonSerivce;
import com.coretek.pack.util.AjaxMsg;
import com.coretek.pack.util.ConstantEnum.MsgType;
import com.coretek.pack.util.FrontHelper;
import com.coretek.pack.util.ParameterConstants;
import com.coretek.pack.util.sessionContexts;
@Controller
@RequestMapping("person")
public class PersonController {
	private AjaxMsg ajaxMsg = AjaxMsg.newInstance();
	public static Person TeacherStatic=new Person();
	
	private IPersonSerivce personService;

	public IPersonSerivce getPersonService() {
		return personService;
	}
	@Inject
	public void setPersonService(IPersonSerivce personService) {
		this.personService = personService;
	}

	private PersonExample personExample=new PersonExample();
	
	
	@RequestMapping(value = "getallperson", method = RequestMethod.GET)
	public String getAA(Model model) {
		
		personExample.clear();
		personExample.setPageNumber(ParameterConstants.ZERO);
		personExample.setPageSize(ParameterConstants.PageSizeConstantMax);
		//总数
		int count = personService.countByExample(personExample);
		
		personExample.clear();
		personExample.setPageNumber(SystemContext.getPageOffset());
		personExample.setPageSize(SystemContext.getPageSize());

		List<Person> peronlists = personService
				.selectByExample(personExample);
		Pager<Person> peronlist = new Pager<Person>(count,
				peronlists);
		personExample.clear();
		model.addAttribute("peronlist", peronlist);		
		return "person/list";
	}
	
	@RequestMapping(value="getcurrentperon",method = RequestMethod.GET)
	public String getPerson(Model model,HttpSession session)
	{
		session = sessionContexts.getSession(session.getId());
		if(session!=null){
			String userid = (Integer)session.getAttribute("userid")+"";
			Person person = personService.selectByPrimaryKey(Integer.parseInt(userid));
			List<Person> personlist = new ArrayList<Person>();
			personlist.add(person);
			Pager<Person> listperson = new Pager<Person>(1,
					personlist);
			model.addAttribute("personlist",listperson);
		}
		return "person/list";
	}

//	@RequestMapping(value = "add", method = RequestMethod.GET)
//	public String addTCustomerGet(Model model) {
//		model.addAttribute("TCustomer", new TeacherModel());
//		return "teacherModel/add";
//	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addTCustomerPost(@Valid Person person,
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "teacherModel/add";
		}
		personService.insert(person);
		return "redirect:/teacherModelController/teachers.do";
	}

	@RequestMapping(value = "update/{tid}", method = RequestMethod.GET)
	public String updateTCustomerGet(@PathVariable int tid, Model model) {
		Person Person;
		if (tid > 0) {
			Person = personService.selectByPrimaryKey(tid);
		} else {
			Person = new Person();
		}
		model.addAttribute("person", Person);
		return "person/update";
	}

	@RequestMapping(value = "update/{tid}", method = RequestMethod.POST)
	public String updateTCustomerPost(@PathVariable("tid") int tid,
			@Valid Person tperson, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "person/update";
		}

		if (tid > 0) {
			Person peron= personService.selectByPrimaryKey(tid);
			tperson.setId(tid);
			tperson.setPermission(peron.getPermission());
			personService.updateByPrimaryKey(tperson);
		}
		return "redirect:/person/getcurrentperon.do";
	}

	@RequestMapping(value = "delete/{Id}", method = RequestMethod.GET)
	public @ResponseBody Object delete(@PathVariable int Id) {
		ajaxMsg.clear();
		if (Id > ParameterConstants.ZERO) {
			personService.deleteByPrimaryKey(Id);
			ajaxMsg.addHeader(MsgType.SUCCESS, "删除成功");
		} else {
			ajaxMsg.addHeader(MsgType.ERROR, "该用户信息有误,不能删除");
		}
		return ajaxMsg;
	}

	@RequestMapping(value = "selectTCustomer", method = RequestMethod.GET)
	// 查询
	public String selectredpacket(HttpServletRequest request, Model model) {
//
//		try {
//			Map<String, Object> params = FrontHelper.transfParams(request);
//
//			TeacherExample.clear();
//			if (params.get(ParameterConstants.DATA) != null) {
//				String data = params.get(ParameterConstants.DATA).toString();
//				TeacherModel tCustomerRequest = (TeacherModel) JsonUtilTool
//						.json2Object(data, TeacherModel.class);
//				//String setting = Kit.ISOToUTF8(tCustomerRequest.getName());
//				String setting = Kit.ISOToUTF8(tCustomerRequest.getTname());
//				TeacherStatic = tCustomerRequest;
//				if (tCustomerRequest != null) {
//					TeacherExample.clear();
//					TeacherExample.setPageNumber(ParameterConstants.ZERO);
//					TeacherExample
//							.setPageSize(ParameterConstants.PageSizeConstantMax);
//					TeacherModelExample.Criteria criteria = TeacherExample
//							.createCriteria();
//					if (setting != null && setting.length() > 0) {
//						criteria.andTnameLike("%" + setting + "%");
//					}
//					
//				}
//			}
//			if (TeacherStatic != null) {
//				String setting = Kit.ISOToUTF8(TeacherStatic.getTname());
//				TeacherModelExample.Criteria criteria = TeacherExample
//						.createCriteria();
//
//				if (setting != null && setting.length() > 0) {
//					criteria.andTnameLike("%" + setting + "%");
//				}
//			
//			}
////			TeacherExample.setOrderByClause(orderBy);
//			TeacherExample.setPageNumber(SystemContext.getPageOffset());
//			TeacherExample.setPageSize(SystemContext.getPageSize());
//			List<TeacherModel> redpacketList = null;
//			int count = TeacherService.countByExample(TeacherExample);
//			redpacketList = TeacherService.selectByExample(TeacherExample);
//
//			Pager<TeacherModel> listredpacket = new Pager<TeacherModel>(count,
//					redpacketList);
//			TeacherExample.clear();
//			model.addAttribute("listTCustomer", listredpacket);
//
//			return "teacherModel/list";
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			FrontHelper.isAbbr = true;
//		}
		return "teacherModel/list";
	}
	
	
	
}
