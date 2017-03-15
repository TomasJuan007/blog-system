<%@page import="com.ykse.blogs.bean.Blogs,com.ykse.blogs.bean.User"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<ol class="breadcrumb">
	<li><a href="index">首页</a></li>
	<li><a href="listBlogs" data-history="repairOrder">所有博客</a></li>
	<li class="active">${blogs.blogsTitle}</li>
</ol>

<form id="pagerForm" method="post" action="listComment">
	<input type="hidden" name="blogsId" value="${blogs.blogsId }"/>
	<input type="hidden" name="pageNum" value="${page.currentPage }"/>
	<input type="hidden" name="numPerPage" id="numPerPage" value="${page.numPerPage }" />
</form>

<div class="row">
	<div class="col-lg-12">
		<h1 style="text-align:center">${blogs.blogsTitle}</h1>
		<div style="text-align: right;">
			<a class="btn btn-success" href="listComment?blogsId=${blogs.blogsId}" data-ajax>刷&nbsp;&nbsp;新</a>
			<a class="btn btn-success" onclick="window.scrollTo(0,document.body.scrollHeight-comment1.scrollHeight-comment2.scrollHeight-comment3.scrollHeight-navbar.scrollHeight);">评论区</a>
			<a class="btn btn-danger" onclick="location.reload();">返回</a>
		</div>
		<pre style="margin:5px;line-height:1.4em;white-space:pre-wrap;">${blogs.blogsContent}</pre>
		
		<div class="col-sm-12" style="text-align: center;">
			<a class="btn btn-success" href="like?blogsId=${blogs.blogsId}" data-ajax>顶(${blogs.support })</a>
			<a class="btn btn-danger" href="dislike?blogsId=${blogs.blogsId}" data-ajax>踩(${blogs.nonsupport })</a>
		</div>
		
		<div class="row search-bar" id="comment1">
			<div class="col-sm-12" style="text-align: right;">
				<a class="btn btn-primary" data-toggle="modal" data-target="#modal-dialog" href="addComment?userId=${blogs.user.userId}&blogsId=${blogs.blogsId}">发表评论</a>
				<a class="btn btn-success" href="listComment?blogsId=${blogs.blogsId}" data-ajax>刷&nbsp;&nbsp;新</a>
				<a class="btn btn-success" onclick="window.scrollTo(0,0);">回顶部</a>
			</div>
		</div>
		<table class="table table-hover td-relative" id="comment2">
			<thead>
			<tr>
				<th>评论内容</th>
				<th>用户</th>
				<th>评论时间</th>
				<th style="width:120px;">选择功能</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.content}" var="cmt">
				<tr>
					<td>${cmt.commentContent }</td>
					<td>${cmt.user.userName }</td>
					<td>${cmt.createTime }</td>
					
					<%
						User user = (User)session.getAttribute("User");
						int userId = user.getUserId();
						request.setAttribute("UserId", userId);
					%>
									
					<c:choose>
					<c:when test="${cmt.user.userId  == UserId}">
						<td>
							<a class="btn btn-danger" href="deleteComment?commentId=${cmt.commentId}" onclick="location.reload();" data-ajax>删除评论</a>
						</td>
					</c:when>
					</c:choose>
				</tr>
			</c:forEach>
			</tbody>
		</table>

	</div>
	
</div>


<div class="row search-bar" id="comment3">
	<div class="col-sm-4">
		当前总共 ${page.totalCount } 条数据
	</div>
	<div class="col-sm-8" style="text-align: right">
		<div class="pagination" totalCount="${page.totalCount }"  numPerPage="${page.numPerPage }" pageNumShown="${page.totalPageNum }" currentPage="${page.currentPage }"></div>
	</div>
</div>