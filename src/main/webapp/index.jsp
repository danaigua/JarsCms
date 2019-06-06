<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页_Jar包下载网</title>
<meta name="keywords" content="Jar包下载,JavaDoc下载,JavaSouce下载,Java文档下载,Java源码下载" />
<meta name="description" content="Jar包下载网是一家Jar包资源整合下载网站." />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jar.css">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<style type="text/css">
	  body {
        padding-bottom: 40px;
      }
</style>
<script type="text/javascript">

	$(function(){
		
		
		 
		 $("#search").click(function(){  // 给span注册点击事件
			 search();
		 });
		 
		 $("#sInput").keydown(function(e) {  // 给搜索文本框注册enter回车事件
	          if (e.keyCode == 13) {  
	        	  search();
	           }
		 });    
		 
		 function search(){
			 var q=$("#sInput").val();
			 if(q==""){
				 $("#sInput").focus();
				 return;
			 }
			 window.location.href="${pageContext.request.contextPath}/jar/q.do?q="+q;
		 }
		 
		 
	
	});
	
</script>
</head>
<body>

<jsp:include page="/foreground/common/top.jsp"/>

<div class="container">

	<div class="row">
			<div class="col-md-12" align="center" style="padding-top: 50px;">
				<a href="http://jar.open1111.com" target="_blank"><img alt="jar包下载网" src="${pageContext.request.contextPath}/static/images/logo.png"></a>
			</div>
	</div>

	<div class="row">
	    <div class="col-md-3"></div>
		<div class="col-md-6" align="center" style="padding-top: 20px;">
			<div class="input-group">
			    <input type="text" class="form-control input-lg" id="sInput" placeholder="请输入英文jar包名称，多个关键字之间用空格隔开"><span class="input-group-addon btn btn-info" id="search">搜索一下</span>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
	
	<div class="row">
 		<div class="col-md-2"></div>
		<div class="col-md-8">
			<ul id="jars" class="indexJars">
				<font color=red>&nbsp;&nbsp;猜你喜欢：</font><hr style='margin-top: 2px; margin-bottom: 10px;'></hr>
				<c:forEach var="tag" items="${tagList}">
					<li><a href='${pageContext.request.contextPath}/jar/q.do?q=${tag.name}' target='_blank' title='${tag.name}.jar下载'>${tag.name}.jar</a></li>
				</c:forEach>
	       	</ul>
		</div>
		<div class="col-md-2"></div>
    </div>
	       
	
	
<jsp:include page="/foreground/common/foot.jsp"/>
	
	
</div>

</body>
</html>