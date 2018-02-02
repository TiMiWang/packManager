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

var installpackId;
function del(id){
	installpackId = id;
	jBox.confirm("确定吗？", "确认删除？将删除安装包", deletesubmit);
		
	}
	
var deletesubmit = function (v, h, f) {
    if (v == 'ok')
    {
	   	 var url = '${ctx }/installpack/delete/'+installpackId+".do";
	   	 xmlhttp.open("GET", url, true);
	     xmlhttp.onreadystatechange = callback_delete;
	     xmlhttp.send(null);
        }
    return true; //close
};

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
	    <div class="page-content">
			系统版本：<input type="text" name="username" id="username" style="width:100px" value=""/>&nbsp;&nbsp;	
			架构类型：<input type="text" name="structuretype" id="structuretype" style="width:100px" value=""/>&nbsp;&nbsp;	
			<img src="<%=request.getContextPath() %>/resources/upload/chaxun.png" onclick="query()" id="searchButton" width="60" height="35">
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
										<th>打包用户</th>
										<th>架构</th>
										<th>有效期(天)</th>
										<th>版本</th>
										<th>所属项目名称</th>
										<th>所属产品名称</th>
										<th>日期</th>
										<th>下载</th>
										<th>删除</th>
									</tr>
								</thead>

								<tbody>								
									<c:forEach items="${installpackviews.datas}" var="installpackview">
										<tr>
											<td>${installpackview.userName }</td>
											<td>${installpackview.packModeStructure }</td>
											<td>${installpackview.indate }</td>
											<td>${installpackview.versionInfo }</td>
											<td>${installpackview.projectName }</td>
											<td>${installpackview.productName }</td>
											<td>${installpackview.dateTimeStr }</td>																		
											<td><a href="<%=request.getContextPath() %>/installpack/download/${installpackview.id }.do"> 点击下载      </a></td>
											<td>																																	
											<a class="btn btn-xs btn-danger" onclick="del('${installpackview.id }')" title="删除">
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
											<a href="<%=request.getContextPath() %>/installpack/getinstallpack/all.do" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a>
											</td>
											<td style="vertical-align: top;">
												 <c:if test="${loginfolist.total > 0}">
													<jsp:include page="/pager.jsp">
														<jsp:param value="${loginfolist.total }" name="totalRecord"/>
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