<jsp:include page="../../resources/styles/default.jsp"></jsp:include>
<%@page import="com.ykse.blogs.bean.Blogs,com.ykse.blogs.bean.User"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar">
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

            <div class="col-md-8">
                <iframe style="float: right;" width="420" scrolling="no" height="45" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=4&num=3"></iframe>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="center" data-history="repairOrder">个人中心</a></li>
                <li><a href="logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>

<ol class="breadcrumb">
    <li><a href="index">首页</a></li>
    <li class="active">所有文件</li>
</ol>


<form id="pagerForm" method="post" action="listBlogs">
    <input type="hidden" name="pageNum" value="${page.currentPage }"/>
    <input type="hidden" name="numPerPage" id="numPerPage" value="${page.numPerPage }" />
    <input type="hidden" name="totalCount" id="totalCount" value="${page.totalCount }" />

    <div class="row search-bar">
        <div class="col-sm-6">
            <a class="btn btn-success" data-history="repairOrder" href="listFiles">刷&nbsp;&nbsp;新</a>
        </div>
    </div>

</form>

<div class="row">
    <div class="col-lg-12">

        <table class="table table-hover td-relative">
            <thead>
            <tr>
                <th>文件ID</th>
                <th>用户</th>
                <th>文件名称</th>
                <th>时间</th>
                <th style="width:120px;">选择功能</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.content}" var="ts">

                <tr>
                    <td>${ts.filesId }</td>
                    <td>${ts.user.userName }</td>
                    <td>${ts.fileName }</td>
                    <td>${ts.createTime }</td>
                    <td>
                        <%
                            User user = (User)session.getAttribute("User");
                            int userId = user.getUserId();
                            request.setAttribute("UserId", userId);
                        %>
                        <a class="btn btn-success" href="viewFile?filesId=${ts.filesId}&userId=UserId" data-ajax>查看文件（图片）</a>
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
