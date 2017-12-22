                <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>

<script src="<%=request.getContextPath() %>/resources/js/jquery-1.9.1.js"></script>
<%-- <script src="<%=request.getContextPath() %>/resources/js/jquery.ui.dialog.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.dialog.js"></script> --%>
<script src="<%=request.getContextPath() %>/resources/js/dialog/jquery-1.4.2.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/dialog/jquery.jBox-2.3.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/dialog/jquery.jBox-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/dialog/jbox.css" /> 

<script type="text/javascript">

//声明对象实例
var cartypeid = 0;
var xmlhttp = false;
//产生一个XMLHttpRequest对象实例
getHTTPRequestObject();

function getHTTPRequestObject()
{
    try
    {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch(e)
    {
        try
        {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(E)
        {
            xmlhttp = false;
        }
    }
    if(!xmlhttp && typeof XMLHttpRequest!= 'undefined')
    {
        xmlhttp = new XMLHttpRequest();
    }
}
function callback1()
{
        if(xmlhttp.readyState == 4) 
        {
            if(xmlhttp.status == 200)
            { 
              // 转换Json数据为javascript对象
              eval("var objResults =" + xmlhttp.responseText);  
              var code=objResults.header.code;
              var message=objResults.header.message;
              if(code==200)
            	  {
            	  jBox.tip("删除成功");   
            	  window.location.reload();
            	  }
              else
            	  {
            	  jBox.tip(message); 
            	  }
            } 
        }
}
//回调处理
function callback()
{
        if(xmlhttp.readyState == 4) 
        {
            if(xmlhttp.status == 200)
            { 
              // 转换Json数据为javascript对象
              eval("var objResults =" + xmlhttp.responseText);            
              var displaytext = "";
              //获取用于显示菜单的下拉列表  
              var displaySelect = document.getElementById("cartypeid");  
              //将目标下拉列表清空  
              displaySelect.innerHTML = null;  
              displaySelect.options.add(new Option('全部',''));
              for (var i=0; i < objResults.data.length; i++)
              {
                /* displaytext += objResults.Results.computer[i].Manufacturer + " " +
                    objResults.Results.computer[i].Model + ": $" + 
                    objResults.Results.computer[i].Price + "<br>";  */
                    displaySelect.options.add(new Option(objResults.data[i].carTypename,objResults.data[i].cartypeid));
              } 
                //var finddiv = document.getElementById("divResponse");//寻找显示容器
                //finddiv.innerHTML = displaytext;//引用解析好了的数据
            } 
        }
}


function windowOpen2(url)
{
	var openUrl = "";//弹出窗口的url
	var iWidth=700; //弹出窗口的宽度;
	var iHeight=500; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	 jBox("get:"+url, { title: "人员管理",width: iWidth,height: iHeight,submit: myfunc ,
         buttons: { '关闭': false }}); 
}

function windowOpen(url)
{
	var openUrl = "";//弹出窗口的url
	var iWidth=700; //弹出窗口的宽度;
	var iHeight=500; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	 jBox("get:"+url, { title: "人员管理",width: iWidth,height: iHeight,submit: myfunc ,
         buttons: { '确定': true ,'关闭': false }}); 
}
function windowOpen1(url)
{
	var openUrl = "";//弹出窗口的url
	var iWidth=300; //弹出窗口的宽度;
	var iHeight=150; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	 jBox("iframe:"+url, { title: "上传",width: iWidth,height: iHeight,submit: myfunc ,
         buttons: {}}); 
}
function show(id){
	
	windowOpen('${ctx }/person/update/'+id+".do")
}
/* function del(customerId){	
	if(confirm('确认删除？')){
		windowOpen('${ctx }/carType/delete/'+customerId);
		alert("删除成功");
	}
} */
var myfunc = function (v, h, f) {

    if (v== true ){    //选择清空时，返回false.然后清空。
    	h.find("form").submit();
    	
    }else{
    	window.location.href="<%=request.getContextPath() %>/person/getcurrentperon.do"
    }

};
var submit1 = function (v, h, f) {
    if (v == 'ok')
    {
	   	 var url = '${ctx }/person/delete/'+cartypeid+".do";
	   	 xmlhttp.open("GET", url, true);
	     xmlhttp.onreadystatechange = callback1;
	     xmlhttp.send(null);
        }
    return true; //close
};
function del(carTypeId){
	cartypeid = carTypeId;
	jBox.confirm("确定吗？", "确认删除？将删除相关信息", submit1);
		
	}


$(function(){


	
   	// 注册点击检索按钮事件函数
   	$("#searchButton").click(function(){

   	   //	$.get(url, {data :stringify(car)}, function(result){
   	   		
   	   //	});
   	   
   	});
   	
    function stringify(json,space)
   	{
   		if(typeof(space)=='undefined')
   		{
   			return JSON.stringify(json);
   		}
   		else
   		{
   			return JSON.stringify(json,undefined,2);
   		}
   	}
});
function loaded()
{
	if (window.parent && !window.parent.closed) {
		//window.parent.jBox.close();
	}
	
}

</script>
<script src="<%=request.getContextPath() %>/resources/jquery.js"></script>
<script src="<%=request.getContextPath() %>/resources/jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/jquery.datetimepicker.css" /> 
<script type="text/javascript">
$(function(){
        $("#startTime").datetimepicker({ format:"Y-m-d",      
            timepicker:false,    //关闭时间选项
            yearEnd:2050,        //设置最大年份 
            lang:'ch',
            });
        $("#endTime").datetimepicker({ format:"Y-m-d",      
            timepicker:false,    //关闭时间选项
            yearEnd:2050,        //设置最大年份 
            lang:'ch',
            });
        //$( "#endTime" ).datepicker( "option", "dateFormat", "yy/mm/dd" });
        
});
</script>
</head>
<body class="no-skin" onload="loaded()">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">用户管理</a>
				</li> 
				<li class="active">人员信息</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		<div class="page-content">

			姓名：<input type="text" name="lname" id="lname" style="width:100px" value="${laboratoryModel.lname}"/>&nbsp;&nbsp;	
			权限：<input type="text" name="laddress" id="laddress" style="width:100px" value="${laboratoryModel.laddress}"/>&nbsp;&nbsp;	
			 <img src="<%=request.getContextPath() %>/resources/upload/chaxun.png" onclick=" submit()" id="searchButton" width="60" height="35">
	
	    </div>
		<div class="clear"></div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>姓名</th>
										<th>密码</th>
										<th>权限</th>
										<th>svn账户名</th>
										<th>svn密码</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>								
									<c:forEach items="${personlist.datas}" var="person">
										<tr>
											<td>${person.name }</td>
											<td>${person.password }</td>
											<td id="permission${person.id}">${person.permission }</td>
											<td>${person.svnUsername }</td>
											<td>${person.svnPassword }</td>
											<td>																							
										 <a class="btn btn-xs btn-info" onclick="show('${person.id}')" title="编辑">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
													</a>										
											</td>
											<script type="text/javascript">
											if("${person.permission}"==0)
											{
												$("#permission${person.id}").text("开发人员");
											}else if("${person.permission}"==1){
												$("#permission${person.id}").text("管理人员");
											}
											</script>		 								
										</tr>
									</c:forEach>
										
								</tbody>
							</table>
							<div class="page-header position-relative">
								<table style="width: 100%;">
									<tbody>
										<tr>
											<td style="vertical-align: top;">
										<!--  <a class="btn btn-info fa" onclick="addLaboratoryModel()" title="添加客户">+</a> -->  
									
											<%-- <a href="<%=request.getContextPath() %>/laboratorymodel/laboratorymodels.do" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a> --%>
											</td>
											<td style="vertical-align: top;">
												 <c:if test="${personlist.total > 0}">
													<jsp:include page="/pager.jsp">
														<jsp:param value="${personlist.total }" name="totalRecord"/>
														<jsp:param value="selectTmallRecord.do" name="url"/>
													</jsp:include>
												</c:if> 
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>

<script src="<%=request.getContextPath() %>/resources/ace/assets/js/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/jquery.dataTables.bootstrap.js"></script>

</body>
</html>