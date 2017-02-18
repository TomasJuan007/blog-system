<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>

    <head>
		<base href="<%=basePath%>">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>博客系统</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="resources/styles/bootstrap-3.3.5-dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/styles/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="resources/styles/css/form-elements.css">
        <link rel="stylesheet" href="resources/styles/css/styles.css">

        <link rel="shortcut icon" href="resources/styles/images/blogs.png" type="image/x-icon" />
        
    </head>
    
	<style type="text/css">
	html{ 
		width: 100%;
		height:100%;
	}
	body{ 
		width: 100%;
		height:100%;
		font-family: 'Open Sans', sans-serif;
		background: #092756;
		background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
		background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 );
	}
	</style>
	
    <body>
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <div class="description">
                            	<p>
	                            	简易几步，轻松加入博客大平台。
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	
                        	<form role="form" action="saveUser" method="post" class="registration-form">
                        		
                        		<fieldset>
		                        	<div class="form-top">
		                        		<div class="form-top-left">
		                        			<h3>步骤 1 / 3</h3>
		                            		<p>(个人简介)</p>
		                        		</div>
		                        		<div class="form-top-right">
		                        			<i class="fa fa-user"></i>
		                        		</div>
		                            </div>
		                            <div class="form-bottom">				               
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-last-name">姓名</label>
				                        	<input type="text" name="userName" placeholder="请输入您的姓名" class="form-last-name form-control" id="form-last-name" value = "${user.userName }">
				                        </div>
				                        <div class="form-group">			                     	
				                        	<label><input name="userSex" type="radio" value="M" checked="checked"/>&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;</label> 
											<label><input name="userSex" type="radio" value="F" />&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;</label> 
				                        </div>
				                        <button type="button" class="btn btn-next">下一步</button>
				                    </div>
			                    </fieldset>
			                    
			                    <fieldset>
		                        	<div class="form-top">
		                        		<div class="form-top-left">
		                        			<h3>步骤 2 / 3</h3>
		                            		<p>(注册信息)</p>
		                        		</div>
		                        		<div class="form-top-right">
		                        			<i class="fa fa-key"></i>
		                        		</div>
		                            </div>
		                            <div class="form-bottom">				                
				                        <div class="form-group">
				                    		<label class="sr-only" for="form-first-name">账号</label>
				                        	<input type="text" name="userAccount" placeholder="请输入账号（邮箱）" class="form-first-name form-control" id="form-first-name" value = "${user.userAccount }">
				                        </div>
				                        <div class="form-group">
				                    		<label class="sr-only" for="form-password">密码</label>
				                        	<input type="password" name="userPassword" placeholder="请输入密码" class="form-password form-control" id="form-password">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-repeat-password">重复密码</label>
				                        	<input type="password" name="" placeholder="请重复输入密码" class="form-repeat-password form-control" id="form-repeat-password">
				                        </div>
				                        <button type="button" class="btn btn-previous">上一步</button>
				                        <button type="button" class="btn btn-next">下一步</button>
				                    </div>
			                    </fieldset>
			                    
			                    <fieldset>
		                        	<div class="form-top">
		                        		<div class="form-top-left">
		                        			<h3>步骤 3 / 3</h3>
		                            		<p>(个人信息)</p>
		                        		</div>
		                        		<div class="form-top-right">
		                        			<i class="fa fa-twitter"></i>
		                        		</div>
		                            </div>
		                            <div class="form-bottom">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="form-facebook">手机</label>
				                        	<input type="text" name="userPhone" placeholder="请输入手机" class="form-facebook form-control" id="form-facebook" value = "${user.userPhone }">
				                        </div>
				                        <button type="button" class="btn btn-previous">上一步</button>
				                        <button type="submit" class="btn">提交</button>
				                    </div>
			                    </fieldset>
		                    
		                    </form>
		                    
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
        <script src="resources/styles/js/jquery-1.11.1.min.js"></script>
        <script src="resources/styles/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script src="resources/styles/js/jquery.backstretch.min.js"></script>
        <script src="resources/styles/js/retina-1.1.0.min.js"></script>
        <script src="resources/styles/js/scripts.js"></script>

    </body>

</html>