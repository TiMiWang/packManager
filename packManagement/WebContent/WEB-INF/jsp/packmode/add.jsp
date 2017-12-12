<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


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

function Cmdusername(obj){
    var len = obj.value.replace(/[^\x00-\xff]/g, "**").length;
    if( len > 20){
        alert("请输入：英文小于20个字符，汉字小于10个");
        return false;
    }
}

function CmdTime(obj){
    var len = obj.value.replace(/[^\x00-\xff]/g, "**").length;
    if( len == 0 ){
        alert("请输入刷卡时间");
        return false;
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
				<li class="active">添加</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="packmode" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->
					
						<table style="border-collapse:separate; border-spacing:10px;" >
						<tr >				
							<td width="100"><label>平台本地路径: </label></td>					
							<td>
								<div>
									<sf:input type="file" path="platformLocalPath" style="width:150px"  onblur="Cmdusername(this)" placeholder="平台本地路径"/>
									<sf:errors  path="platformLocalPath"/>
								</div>
							</td>
							</tr >
							<tr >		
							<td width="100"><label>是否SVN签出: </label></td>					
							<td>
								<div>
									<sf:input path="isSvnCheck" style="width:150px"  onblur="Cmdusername(this)" placeholder="是否SVN签出"/>
									<sf:errors  path="isSvnCheck"/>
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
									<sf:input path="platformSvnPath" style="width:150px"  onblur="Cmdusername(this)" placeholder="平台SVN路径"/>
									<sf:errors  path="platformSvnPath"/>
								</div>
							</td>
							</tr >	
							
							<tr >	
							<td width="100"><label>是否试用版: </label></td>					
							<td>
								<div>
									<sf:input path="versionInfo" style="width:150px"  onblur="Cmdusername(this)" placeholder="是否试用版"/>
									<sf:errors  path="versionInfo"/>
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
									<sf:input path="systemVersion" style="width:150px"  onblur="Cmdusername(this)" placeholder="系统版本"/>
									<sf:errors  path="systemVersion"/>
								</div>
							</td>
							</tr >
							
							<tr >		
							<td width="100"><label>架构: </label></td>					
							<td>
								<div>
									<sf:input path="structureType" style="width:150px"  onblur="Cmdusername(this)" placeholder="架构"/>
									<sf:errors  path="structureType"/>
								</div>
							</td>
							</tr >
							
							<tr >		
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
							</tr >	

					</table>

				</sf:form>
				</div>
			</div><!-- /.row -->
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>

<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script> --%>
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>

</body>
</html>