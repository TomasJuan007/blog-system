<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<pre align="center" style="border:none;font-size:20px;font-family:楷体"><b><<用户中心>></b></pre>
	<div class="bs-glyphicons">
		<a href="userInfo" data-toggle="modal" data-target="#modal-dialog">
			<span class="glyphicon glyphicon-user"></span>
			<span class="glyphicon-class">个人信息</span>
		</a>
		<a href="chpwd" data-toggle="modal" data-target="#modal-dialog">
			<span class="glyphicon glyphicon-edit"></span>
			<span class="glyphicon-class">修改密码</span>
		</a>
	</div>
	
	<pre align="center" style="border:none;font-size:20px;font-family:楷体"><b><<博客中心>></b></pre>
	<div class="bs-glyphicons">
		<a href="listOwnBlogs" data-history>
			<span class="glyphicon glyphicon-file"></span>
			<span class="glyphicon-class">我的博客</span>
		</a>
		<a href="listBlogs" data-history>
			<span class="glyphicon glyphicon-briefcase"></span>
			<span class="glyphicon-class">所有博客</span>
		</a>
		<a href="/center/editor/editBlogs">
			<span class="glyphicon glyphicon-edit"></span>
			<span class="glyphicon-class">编辑器</span>
		</a>
		<a href="/center/heat/listHeatBlogs" data-history>
			<span class="glyphicon glyphicon-fire"></span>
			<span class="glyphicon-class">热门博客</span>
		</a>
	</div>

	<pre align="center" style="border:none;font-size:20px;font-family:楷体"><b><<工具中心>></b></pre>
	<div class="bs-glyphicons">
		<a href="uploadFile">
			<span class="glyphicon glyphicon-file"></span>
			<span class="glyphicon-class">上传文件</span>
		</a>
		<a href="javascript:void(0)" onclick="setVisible()">
			<span id="icon" class="glyphicon glyphicon-eye-close"></span>
			<span class="glyphicon-class">天气预报</span>
		</a>
		<script>
            function setVisible() {
                if(getComputedStyle(document.getElementById("weather")).visibility === "visible") {
                    document.getElementById("weather").style.visibility = "hidden";
                    document.getElementById("icon").setAttribute("class", "glyphicon glyphicon-eye-open");
                } else {
                    document.getElementById("weather").style.visibility = "visible";
                    document.getElementById("icon").setAttribute("class", "glyphicon glyphicon-eye-close");
                }
            }
		</script>
	</div>

	<pre align="center" style="border:none;font-size:20px;font-family:楷体"><b><<旋转中心>></b></pre>
	<div class="bs-glyphicons">
		<a href="cube">
			<span class="glyphicon glyphicon-stop"></span>
			<span class="glyphicon-class">旋转的方块</span>
		</a>
		<a href="star">
			<span class="glyphicon glyphicon-asterisk"></span>
			<span class="glyphicon-class">放射的星火</span>
		</a>
		<a href="starcube">
			<span class="glyphicon glyphicon-fire"></span>
			<span class="glyphicon-class">火星方块</span>
		</a>
	</div>