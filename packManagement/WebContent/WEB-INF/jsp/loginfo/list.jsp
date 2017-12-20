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


$(function(){
 	
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
					<a href="#">日志模块</a>
				</li> 
				<li class="active">日志信息</li>
			</ul>		
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
										<th>内容</th>
										<th>用户名</th>
										<th>架构</th>
										<th>有效期</th>
										<th>是否试用版</th>
										<th>svn路径</th>
										<th>所属项目名称</th>
										<th>所属产品名称</th>
										<th>备注</th>
										<th>日期</th>
									</tr>
								</thead>

								<tbody>								
									<c:forEach items="${loginfolist.datas}" var="logInfo">
										<tr>
											<td>${logInfo.content }</td>
											<td>${logInfo.userName }</td>
											<td>${logInfo.packModeStructure }</td>
											<td>${logInfo.indate }</td>
											<td>${logInfo.versionInfo }</td>
											<td>${logInfo.svnNetPath }</td>
											<td>${logInfo.projectName }</td>
											<td>${logInfo.productName }</td>
											<td>${logInfo.remark }</td>
											<td>${logInfo.dateTimeStr }</td>	
										</tr>
									</c:forEach>
										
								</tbody>
							</table>
							<div class="page-header position-relative">
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