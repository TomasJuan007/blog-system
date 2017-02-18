<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<form method="post" action="savePsw" class="required-validate">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		<h4 class="modal-title">修改密码</h4>
	</div>
	<div class="modal-body">

		<div class="form-group">
			<label for="inputPassword1">旧密码</label>
			<input type="password" name="oldPwd" class="form-control" id="inputPassword1" placeholder="Password" required="required" minlength="1" maxlength="20">
		</div>

		<div class="form-group">
			<label for="inputPassword2">新密码</label>
			<input type="password" name="newPwd" class="form-control" id="inputPassword2" placeholder="Password" required="required" minlength="1" maxlength="20">
		</div>

		<div class="form-group">
			<label for="inputPassword3">重复输入新密码</label>
			<input type="password" name="rnewPassword" class="form-control" id="inputPassword3" placeholder="Password" required="required" equalTo="#inputPassword2">
		</div>

	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		<button type="submit" class="btn btn-primary">提交</button>
	</div>
</form>