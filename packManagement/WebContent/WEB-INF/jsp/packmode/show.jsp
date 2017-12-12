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
					<a href="#">考勤管理</a>
				</li>
				<li class="active">考勤信息管理</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="showTmall" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->
					
					<table style="border-collapse:separate; border-spacing:10px;" >
						<tr >				
							<td width="100"><label>刷卡人猫号: </label></td>					
							<td>
								<div>
									<sf:input path="mailid" style="width:150px" readonly="true" placeholder="刷卡人猫号"/>
									<sf:errors  path="mailid"/>
								</div>
							</td>
							</tr>
						<tr >				
							<td width="100"><label>二维码: </label></td>					
							<td>
								<div>
									<sf:input path="cardno" style="width:150px" readonly="true" placeholder="二维码"/>
									<sf:errors  path="cardno"/>
								</div>
							</td>
							<td ><label>是否通过: </label></td>					
							<td>
								<div>
									<select path="ispass" style="width:150px" readonly="true" name="isused">
											<c:forEach items="${showIsPass }" var="list" >
												<option value="${list.index}">${list.name}</option>
											</c:forEach>								
									</select>
								</div>
							</td>
						</tr>
						<tr >				
							<td width="100"><label>进出标志位: </label></td>					
							<td>
								<div>
									<select path="enterintag" style="width:150px" readonly="true" name="usertype">
											<c:forEach items="${showEnter}" var="list" >
												<option value="${list.index}">${list.name}</option>
											</c:forEach>								
									</select>
								</div>
							</td>
							<td width="100"><label>打卡标示: </label></td>					
							<td>
								<div>
									<sf:input path="recindex" style="width:150px" readonly="true" placeholder="打卡标示"/>
									<sf:errors  path="recindex"/>
								</div>
							</td>
							
						</tr>
						
						<tr >				
							<td width="100"><label>类型: </label></td>					
							<td>
								<div>
									<sf:input path="type" style="width:150px" readonly="true" placeholder="类型"/>
									<sf:errors  path="type"/>
								</div>
							</td>
							<td width="100"><label>有效值: </label></td>					
							<td>
								<div>
									<sf:input path="valid" style="width:150px" readonly="true" placeholder="有效值"/>
									<sf:errors  path="valid"/>
								</div>
							</td>
							
						</tr>
						<tr >				
							<td width="100"><label>门号: </label></td>					
							<td>
								<div>
									<sf:input path="doorno" style="width:150px"  readonly="true" placeholder="门号"/>
									<sf:errors  path="doorno"/>
								</div>
							</td>
							<td width="100"><label>刷卡失败原因: </label></td>					
							<td>
								<div>
									<sf:input path="reasoncode" style="width:150px" readonly="true" placeholder="刷卡失败原因"/>
									<sf:errors  path="reasoncode"/>
								</div>
							</td>
							
						</tr>
						
						
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