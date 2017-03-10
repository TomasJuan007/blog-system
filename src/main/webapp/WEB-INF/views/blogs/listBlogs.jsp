<%@page import="com.ykse.blogs.bean.Blogs,com.ykse.blogs.bean.User"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<ol class="breadcrumb">
	<li><a href="index">首页</a></li>
	<li class="active">所有博客</li>
</ol>


<form id="pagerForm" method="post" action="listBlogs">
	<input type="hidden" name="pageNum" value="${page.currentPage }"/>
	<input type="hidden" name="numPerPage" id="numPerPage" value="${page.numPerPage }" />
	<input type="hidden" name="totalCount" id="totalCount" value="${page.totalCount }" />

	<div class="row search-bar">
		<div class="col-sm-6">
			<a class="btn btn-primary" data-toggle="modal" data-target="#modal-dialog" href="listBlogs/addBlogs">写博客</a>
			<a class="btn btn-success" data-history="repairOrder" href="listBlogs">刷&nbsp;&nbsp;新</a>
		</div>
	</div>

</form>

<div class="row">
	<div class="col-lg-12">

		<table class="table table-hover td-relative">
			<thead>
			<tr>
				<th>题目</th>
				<th>发帖时间</th>
				<th>发帖人</th>
				<th>总评论数</th>
				<th style="width:120px;">选择功能</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.content}" var="ts">

				<tr>
					<td>${ts.blogsTitle }</td>
					<td>${ts.createTime }</td>
					<td>${ts.user.userName }</td>
					<td><span class="label label-success">${ts.commentCount }</span></td>
					<td>
						<a class="btn btn-warning" href="listComment?blogsId=${ts.blogsId}" data-ajax>查看</a>	
						<%
							User user = (User)session.getAttribute("User");
							int userId = user.getUserId();
							request.setAttribute("UserId", userId);
						%>				
						<c:choose>
						<c:when test="${ts.user.userId  == UserId}">				
							<a class="btn btn-warning" data-toggle="modal" data-target="#modal-dialog" href="listBlogs/getBlogsInfo?blogsId=${ts.blogsId}">修改</a>
						</c:when>
						</c:choose>
 					</td>					
				</tr>			
			</c:forEach>
			</tbody>
		</table>

	</div>

</div>


<div class="row search-bar">
	<div class="col-sm-4">
		当前总共 ${page.totalCount } 条数据
	</div>
	<div class="col-sm-8" style="text-align: right">
		<div class="pagination" totalCount="${page.totalCount }"  numPerPage="${page.numPerPage }" pageNumShown="${page.totalPageNum }" currentPage="${page.currentPage }"></div>
	</div>
</div>
