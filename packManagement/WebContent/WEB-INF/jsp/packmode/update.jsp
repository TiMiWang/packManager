<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="<%=request.getContextPath() %>/resources/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/dialog/jquery-1.4.2.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/dialog/jquery.jBox-2.3.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/dialog/jquery.jBox-zh-CN.js"></script>
<script src="<%=request.getContextPath() %>/resources/jquery.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/datetimepicker/jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/datetimepicker/jquery.datetimepicker.css" />
  <script>
     $('#datetimepicker').datetimepicker({lang:'ch'});
  </script>
    <script>
     $('#datetimepicker1').datetimepicker({lang:'ch'});
  </script>

<%@ include file="../common/common_css.jsp"%>
<script >
//$("input").change(function(){
    //var value=$(this).val();
   // if(value.length>11){
        // 内容超长了
        //alert("长度不能大于11")
       // return false;
   // }
//});
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


function Cmdusername(obj){
    var len = obj.value.replace(/[^\x00-\xff]/g, "**").length;
    if( len > 20){
        //alert("请输入：英文小于20个字符，汉字小于10个");
        return false;
    }
}

function CmdTime(obj){
    var len = obj.value.replace(/[^\x00-\xff]/g, "**").length;
    if( len == 0 ){
        //alert("请输入刷卡时间");
        return false;
    }
}

function onprogress(evt){
	var loaded = evt.loaded;                         //已经上传大小情况 
	   var tot = evt.total;                            //附件总大小 
	   var per = Math.floor(100*loaded/tot);           //已经上传的百分比  
	   $("#son").html( per +"%" );
	   $("#son").css("width" , per +"%");
	}
	
function submit_pack(){
	$('#packingForm').submit();
	}

function printFileInfo(){
	//var fileList = document.getElementById("uploadfile_id").files;
	//var pathele = document.getElementById("platformlocalPath");
	//pathele.innerHTML = fileList[0].name;
	//$("#platformlocalPath").text(fileList[0].name);
}

//上传文件
function uploadFile() {
	//将上传的多个文件放入formData中
	var fileList = document.getElementById("uploadfile_id").files;
    var formData = new FormData();
    for(var i=0; i< fileList.length; i++){
        formData.append("file" , fileList[i] );
    }

  	 var url = '${ctx }/packmode/uploadfile/'+${packmode.id}+".do";
     //监听事件
     xmlhttp.upload.addEventListener("progress", onprogress, false);
   	 xmlhttp.open("POST", url, true);
     xmlhttp.onreadystatechange = callback_upload;
     xmlhttp.send(formData);
    
}  

function callback_upload()
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
            	  jBox.tip("上传成功"); 
            	  }
              else
            	  {
            	  jBox.tip(message); 
            	  }
            } 
        }
}

</script>

</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">打包配置方案</a>
				</li>
				<li class="active">修改</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="packmode" id="packingForm" cssClass="form-horizontal" role="form" action="/packmode/updateandpack/${packmode.id}.do">
					<!-- #section:elements.form -->
					
					<table style="border-collapse:separate; border-spacing:10px;" >
						<tr >				
							<td width="100"><label>平台本地路径: </label></td>
							<td>
							<input type="file" id="uploadfile_id" onchange="printFileInfo()"/>
							</td>					
							<td>
							<div id="son"/>
							</td>
							<td>
							<a class="btn btn-info fa" type="button" value="上传文件" onclick="uploadFile()" >上传</a>
							</td>
							</tr >
							<tr >		
							<td width="100"><label>是否SVN签出: </label></td>					
							<td>
								<div>
									<%-- <sf:input id="testttt" path="isSvnCheck" onblur="Cmdusername(this)" placeholder="是否SVN签出"/> --%>
									<!--<sf:errors  path="isSvnCheck"/>-->
									<select id="issvncheckout" style="width:150px" onchange="svnCheck_change('parent',this)"> 
									<option value="1">是</option> 
									<option value="0">否</option> 
									</select> 
								</div>

							</td>
							</tr >	
							
							<tr >	
							<td width="100"><label>SVN地址: </label></td>					
							<td>
								<div>
									<sf:input path="svnNetPath" style="width:150px"  onblur="Cmdusername(this)" placeholder="SVN地址"/>
									<sf:errors  path="svnNetPath"/>
								</div>
							</td>
							</tr >	
							<tr >	
							<td width="100"><label>平台SVN路径: </label></td>					
							<td>
								<div>
									<sf:input id="svnpathid" path="platformSvnPath" style="width:150px"  onblur="Cmdusername(this)" placeholder="平台SVN路径"/>
									<sf:errors  path="platformSvnPath"/>
								</div>
							</td>
							</tr >	
							
							<tr >	
							<td width="100"><label>是否试用版: </label></td>					
							<td>
								<div>
									<!--<sf:input path="versionInfo" style="width:150px"  onblur="Cmdusername(this)" placeholder="是否试用版"/>
									<sf:errors  path="versionInfo"/>-->
									<select id="isthread" style="width:150px" onchange="versioninfo_change('parent',this)"> 
									<option value="0">是</option> 
									<option value="1">否</option> 
									</select> 
								</div>
							</td>
							</tr >	
							
							<tr >	
							<td width="100"><label>有效期: </label></td>					
							<td>
								<div>
									<sf:input path="indate" style="width:150px"  onblur="Cmdusername(this)" placeholder="有效期"/>
									<sf:errors  path="indate"/>
								</div>
							</td>
							</tr >	
							
							<tr >	
							<td width="100"><label>系统版本: </label></td>					
							<td>
								<div>
									<!--<sf:input path="systemVersion" style="width:150px"  onblur="Cmdusername(this)" placeholder="系统版本"/>
									<sf:errors  path="systemVersion"/>-->
									<select id="systemVersion" style="width:150px" onchange="systemversion('parent',this)"> 
									</select> 
								</div>
							</td>
							</tr >
							
							<tr >		
							<td width="100"><label>架构: </label></td>					
							<td>
								<div>
									<!--<sf:input path="structureType" style="width:150px"  onblur="Cmdusername(this)" placeholder="架构"/>
									<sf:errors  path="structureType"/>-->
									<select id=structuretypeid style="width:150px" onchange="structuretype_change('parent',this,0)"> 
									<option value="dsp">DSP</option> 
									<option value="x86">X86</option> 
									</select> 
								</div>
							</td>
							</tr >
							<tr> </tr>
							<tr> </tr>
							<tr> </tr>
							<tr> </tr>
							<tr> </tr>
							
							<!--<tr >		
							 <td width="100"><label>是否更新秘钥: </label></td>					
							<td>
								<div>
									<sf:input path="isUpdateKey" style="width:150px"  onblur="Cmdusername(this)" placeholder="是否更新秘钥"/>
									<sf:errors  path="isUpdateKey"/>
								</div>
							</td>
							</tr >	
							
							<tr >	
							<td width="100"><label>是否更新UUID: </label></td>					
							<td>
								<div>
									<sf:input path="isUpdateUuid" style="width:150px"  onblur="Cmdusername(this)" placeholder="是否更新UUID"/>
									<sf:errors  path="isUpdateUuid"/>
								</div>
							</td>
							</tr >	-->

					</table>
				</sf:form>
				
								<div class="page-header position-relative">
								<table style="width: 100%;">
									<tbody>
										<tr>
											<td style="vertical-align: top;">
										 <a class="btn btn-info fa" onclick="submit_pack()" title="打包">打包</a>  
									
											<a href="<%=request.getContextPath() %>/packmode/getallpackmode.do" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="取消"></a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
				
				</div>
			</div><!-- /.row -->
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>




<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script> --%>
<script type="text/javascript">

$("#structuretypeid").val("${packmode.structureType}");
$("#isthread").val("${packmode.versionInfo}");
$("#issvncheckout").val("${packmode.isSvnCheck}");

var svnCheck_change = function(parent,object) {
	$("#testttt").val(object.find("option:selected").val());
}
var versioninfo_change = function(parent,object) {
	
}
var systemversion = function(parent,object) {
	
}

$(function(){
	
	$("#addForm").cmsvalidate();
});
</script>

</body>
</html>