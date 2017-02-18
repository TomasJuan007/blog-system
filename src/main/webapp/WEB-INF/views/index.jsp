<jsp:include page="../../resources/styles/default.jsp"></jsp:include>
<%@ page language="java" import="java.util.*, com.ykse.blogs.bean.User" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html >
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<base href="<%=basePath%>">
	<title>博客系统</title>
</head>

<body>

<%
 	if(session.getAttribute("User") instanceof User){
 		User user = (User) session.getAttribute("User");
 	if(user != null){
 		((User) session.getAttribute("User")).getUserAccount();%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand " href="index" >我的主页</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">				
				<li><a href="listBlogs" data-history>博客列表</a></li>
				<li class="dropdown">
					<a data-toggle="dropdown">帮助<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="help" data-history>查看帮助</a></li>
						<li><a href="contact" data-history>联系我们</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="chpwd" data-toggle="modal" data-target="#modal-dialog">修改密码</a></li>
				<li><a href="logout">退出</a></li>
			</ul>
		</div>
	</div>
</div>




<div class="container dwz-unit-box" id="container">

	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h1>Hello, <%= user.getUserName() %>！</h1>
		<p>欢迎来到博客世界，让您更加热爱这妙不可言的时光与生活。</p>
		<p>
			<a class="btn btn-lg btn-primary" href="listBlogs" data-history="repairOrder" role="button">查看博客</a>
		</p>
	</div>
	
	<div class="bs-glyphicons">
		<a href="userInfo" data-toggle="modal" data-target="#modal-dialog">
			<span class="glyphicon glyphicon-user"></span>
			<span class="glyphicon-class">我的信息</span>
		</a>
	
		<a href="listBlogs/addBlogs" data-toggle="modal" data-target="#modal-dialog">
			<span class="glyphicon glyphicon-edit"></span>
			<span class="glyphicon-class">写博客</span>
		</a>
				
	</div>
	
</div>

<% }} %>


<div id="background" style="display: none"></div>
<div id="progressBar" style="display: none">数据加载中，请稍等...</div>

<!-- Modal -->
<div class="modal fade" id="modal-dialog" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content dwz-unit-box">

		</div>
	</div>
</div>

</body>
</html>
