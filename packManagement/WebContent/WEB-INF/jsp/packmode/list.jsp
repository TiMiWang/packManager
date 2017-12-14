<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

var timerDict = new Array();

function statusTimerFun(id)
{
  	 var url = '${ctx }/packmode/updatestatus/'+id+".do";
   	 xmlhttp.open("GET", url, true);
     xmlhttp.onreadystatechange = callback_update_status_pack;
     xmlhttp.send(null);
	}
	
function callback_update_status_pack()
{
        if(xmlhttp.readyState == 4) 
        {
            if(xmlhttp.status == 200)
            { 
              // 转换Json数据为javascript对象
              eval("var objResults =" + xmlhttp.responseText);  
              var code=objResults.header.code;
              var message=objResults.header.message;
              var id = objResults.data.id;
              if(code==200)
            	  {
                  var status = objResults.data.status;
                  var loginfo = objResults.data.loginfo;
                  var downloadPath = objResults.data.downloadPath;
                  
            	  $("#status"+id).text("打包进度信息:"+loginfo);
            	  if(status==0){
            		  $("#status"+id).text("下载:"+downloadPath);
                	  jBox.tip("打包完成"); 
                	  if(timerDict["timer"+id]!=null){
                		  clearInterval(timerDict["timer"+id]);
                	  }
                	 // window.location.href="<%=request.getContextPath() %>/resources/img/LambdaPRO.installer6.0-x86.rar"
            	  }
            	  }
              else
            	  {
            	  jBox.tip(message); 
            	  if(timerDict["timer"+id]!=null){
            		  clearInterval(timerDict["timer"+id]);
            	  }
            	  }
            } 
        }
}
//声明对象实例
var packmodeId = 0;
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
function callback_delete()
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
function callback_pack()
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
            	  jBox.tip("打包成功"); 
            	  $("#status").text("打包完成")
            	  clearInterval(statusTimer)
            	  window.location.reload();
            	  }
              else
            	  {
            	  $("#status").text("打包失败")
            	  clearInterval(statusTimer)
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
              var displaySelect = document.getElementById("packmodeId");  
              //将目标下拉列表清空  
              displaySelect.innerHTML = null;  
              displaySelect.options.add(new Option('全部',''));
              for (var i=0; i < objResults.data.length; i++)
              {
                /* displaytext += objResults.Results.computer[i].Manufacturer + " " +
                    objResults.Results.computer[i].Model + ": $" + 
                    objResults.Results.computer[i].Price + "<br>";  */
                    displaySelect.options.add(new Option(objResults.data[i].carTypename,objResults.data[i].packmodeId));
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
	 jBox("get:"+url, { title: "打包管理",width: iWidth,height: iHeight,submit: myfunc ,
         buttons: { '关闭': false }}); 
}

function windowOpen(url)
{
	var openUrl = "";//弹出窗口的url
	var iWidth=700; //弹出窗口的宽度;
	var iHeight=500; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	 jBox("get:"+url, { title: "打包管理",width: iWidth,height: iHeight,submit: myfunc ,
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
	
	//windowOpen('${ctx }/packmode/update/'+id+".do")
	window.location.href="<%=request.getContextPath() %>/packmode/update/"+id+".do"
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
    	window.location.href="<%=request.getContextPath() %>/packmode/getallpackmode.do"
    }

};
var deletesubmit = function (v, h, f) {
    if (v == 'ok')
    {
	   	 var url = '${ctx }/packmode/delete/'+packmodeId+".do";
	   	 xmlhttp.open("GET", url, true);
	     xmlhttp.onreadystatechange = callback_delete;
	     xmlhttp.send(null);
        }
    return true; //close
};

var packSubmit = function (v, h, f) {
    if (v == 'ok')
    {
    	
	   	 var url = '${ctx }/packmode/pack/'+packmodeId+".do";
	   	 xmlhttp.open("GET", url, true);
	     xmlhttp.onreadystatechange = callback_pack;
	     xmlhttp.send(formData);
	     statusTimer = setInterval(function(){statusTimerFun()},5000)
        }
    return true; //close
};
function del(id){
	packmodeId = id;
	jBox.confirm("确定吗？", "确认删除？将删除打包方案的信息", deletesubmit);
		
	}
function pack(id){
	packmodeId = id;
	jBox.confirm("确定吗？", "确认开始打包吗？", packSubmit);
		
	}

function addPackMode(){
	windowOpen('${ctx }/packmode/add.do')
}

$(function(){
	$("#createTimeDescSort").click(function(){
		$("#orderBy").val("create_time desc");
		//refreshPage();
		});

	
   	// 注册点击检索按钮事件函数
   	$("#searchButton").click(function(){
   	   	var lnameValue = $("#lname").val();
   	   	var laddressValue = $("#laddress").val();
   		
   		
   	   	var tmall= new Object();
   		tmall.lname=lnameValue;
   		tmall.laddress=laddressValue;
   		
   	   	var url = "<%=request.getContextPath() %>/laboratorymodel/selectTmallRecord.do?";
   	 	var json=stringify(tmall);
	    window.location.href=url+"data="+json;
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
function onCardlogochanged(carlogoId)
{
    // retrieve the JSON text from the local file.
    var url = "${ctx }/carType/getCartypeByLogoId/"+carlogoId;
    xmlhttp.open("GET", url, true);
    xmlhttp.onreadystatechange = GetCarTypeByCarLogo;
    xmlhttp.send(null);
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
					<a href="#">打包模块</a>
				</li> 
				<li class="active">打包配置方案</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		<div class="page-content">

			系统版本：<input type="text" name="lname" id="lname" style="width:100px" value=""/>&nbsp;&nbsp;	
			架构类型：<input type="text" name="laddress" id="laddress" style="width:100px" value=""/>&nbsp;&nbsp;	
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
										<!--<th>平台本地路径</th>-->
										<th>是否SVN签出</th>
										<th>SVN地址</th>
										<th>是否试用版</th>
										<th>有效期</th>
										<th>系统版本</th>
										<th>架构</th>
										<th>是否更新秘钥</th>
										<th>是否更新UUID</th>
										<th>svn平台路径</th>
										<th>状态</th>
									</tr>
								</thead>

								<tbody>								
									<c:forEach items="${packmodelist.datas}" var="packmode">
										<tr>
											<!--<td>${packmode.platformLocalPath }</td>-->
											<td id="svncheck${packmode.id}">${packmode.isSvnCheck }</td>
											<td>${packmode.svnNetPath }</td>
											<td id="versionInfo${packmode.id}">${packmode.versionInfo }</td>
											<td>${packmode.indate }</td>
											<td>${packmode.systemVersion }</td>
											<td>${packmode.structureType }</td>
											<td id="updatekey${packmode.id}">${packmode.isUpdateKey }</td>
											<td id="updateuuid${packmode.id}">${packmode.isUpdateUuid }</td>
											<td>${packmode.platformSvnPath}</td>
											<td id= "status${packmode.id}">${packmode.status }</td>
											<script>
											if("${packmode.status}"==0)
											{
												$("#status${packmode.id}").text("可使用");
												if(timerDict["timer${packmode.id}"]!=null){
													clearInterval(timerDict["timer${packmode.id}"]);
												}
											}else{
												$("#status${packmode.id}").text("正在使用");
												if(timerDict["timer${packmode.id}"]==null){
												    var statusTimer = setInterval(function(){statusTimerFun("${packmode.id}")},5000)
												    timerDict["timer${packmode.id}"] = statusTimer;
												}
											}
											if("${packmode.isSvnCheck}"==1)
											{
												$("#svncheck${packmode.id}").text("是");
											}else{
												$("#svncheck${packmode.id}").text("否");
											}
											if("${packmode.isUpdateUuid}"==1)
											{
												$("#updateuuid${packmode.id}").text("是");
											}else{
												$("#updateuuid${packmode.id}").text("否");
											}
											if("${packmode.isUpdateKey}"==1)
											{
												$("#updatekey${packmode.id}").text("是");
											}else{
												$("#updatekey${packmode.id}").text("否");
											}
											if("versionInfo${packmode.id}"==1)
											{
												$("#versionInfo${packmode.id}").text("是");
											}else{
												$("#versionInfo${packmode.id}").text("否");
											}
											</script>
												
											<td>																							
										 <a class="btn btn-xs btn-info" onclick="show('${packmode.id}')" title="打包">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
													</a>
													
														<a class="btn btn-xs btn-danger" onclick="del('${packmode.id }')" title="删除">
														<i class="ace-icon fa fa-trash-o bigger-120"></i>
													</a>
														<a class="btn btn-xs btn-danger" onclick="pack('${packmode.id }')" title="打包">
														<i class="ace-icon fa fa-trash-o bigger-120"></i>
													</a>
																						
											</td>		 								
										</tr>
									</c:forEach>
										
								</tbody>
							</table>
							<div class="page-header position-relative">
								<table style="width: 100%;">
									<tbody>
										<tr>
											<td style="vertical-align: top;">
										 <a class="btn btn-info fa" onclick="addPackMode()" title="添加打包配置方案">+</a>  
									
											<a href="<%=request.getContextPath() %>/packmode/getallpackmode.do" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a>
											</td>
											<td style="vertical-align: top;">
												 <c:if test="${packmodelist.total > 0}">
													<jsp:include page="/pager.jsp">
														<jsp:param value="${packmodelist.total }" name="totalRecord"/>
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