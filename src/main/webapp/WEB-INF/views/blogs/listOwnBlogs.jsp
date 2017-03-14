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
	<li class="active">我的博客</li>
</ol>


<form id="pagerForm" method="post" action="listBlogs">
	<input type="hidden" name="pageNum" value="${page.currentPage }"/>
	<input type="hidden" name="numPerPage" id="numPerPage" value="${page.numPerPage }" />
	<input type="hidden" name="totalCount" id="totalCount" value="${page.totalCount }" />

	<div class="row search-bar">
		<div class="col-sm-6">
			<a class="btn btn-primary" data-toggle="modal" data-target="#modal-dialog" href="addBlogs">写博客</a>
			<a class="btn btn-success" data-history="repairOrder" href="listOwnBlogs">刷&nbsp;&nbsp;新</a>
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
				<th>评论 / 赞</th>
				<th style="width:120px;">选择功能</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.content}" var="ts">
				<tr>
					<td>${ts.blogsTitle }</td>
					<td>${ts.createTime }</td>
					<td>
						<span class="label label-warning">${ts.commentCount }</span>
						<span>/</span>
						<span class="label label-danger">${ts.commentCount }</span>
					</td>
					<td>
						<a class="btn btn-success" href="listComment?blogsId=${ts.blogsId}" data-ajax>查看</a>	
						<a class="btn btn-warning" data-toggle="modal" data-target="#modal-dialog" href="getBlogsInfo?blogsId=${ts.blogsId}">修改</a>
						<a class="btn btn-danger" href="deleteBlog?blogsId=${ts.blogsId}" onclick="location.reload();" data-ajax>删除</a>
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
