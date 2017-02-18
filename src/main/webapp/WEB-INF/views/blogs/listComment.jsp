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
	<li><a href="<%=basePath%>listBlogs" data-history="repairOrder">所有博客</a></li>
	<li class="active">${blogsTitle}</li>
</ol>


<form id="pagerForm" method="post" action="listBlogs/listComment">
	<input type="hidden" name="pageNum" value="${page.currentPage }"/>
	<input type="hidden" name="numPerPage" id="numPerPage" value="${page.numPerPage }" />
	<input type="hidden" name="totalCount" id="totalCount" value="${page.totalCount }" />

</form>

<div class="row">
	<div class="col-lg-12">
		<h1 style="text-align:center">${blogsTitle}</h1>
		<pre style="margin:5px;line-height:1.4em;">${blogsContent}</pre>
		
		<div class="row search-bar">
			<div class="col-sm-11" style="text-align: right;">
				<a class="btn btn-success"  data-toggle="modal" data-target="#modal-dialog" href="listBlogs/addComment?userId=${userId}&blogsId=${blogsId}">发表评论</a>									
			</div>
		</div>
		<table class="table table-hover td-relative">
			<thead>
			<tr>
				<th>用户</th>
				<th>评论内容</th>
				<th>评论时间</th>
				<th style="width:120px;">选择功能</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.content}" var="cmt">
				<tr>
					<td>${cmt.user.userName }</td>
					<td>${cmt.commentContent }</td>
					<td>${cmt.createTime }</td>
					<td>
						<a class="btn btn-danger" data-todo="ajaxTodo" title="确认该评论？" href="javascript:void(0);" onclick="deleteComment(${cmt.commentId})">删除评论</a>	
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
<script type="text/javascript">
function deleteComment(id){
	$.ajax({
			type: 'delete',
			url: '<%=basePath%>listBlogs/deleteComment?commentId='+id,
			dataType: 'text',
			success:function(data){
				if(data=="suc"){
					alert("删除成功");
					location.reload();
				}
			},
			error:function(data){
			}
		});
}
</script>