<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="post" action="listBlogs/saveComment" class="required-validate">
<input type="hidden" name="blogsId" value=${blogsId } />
<input type="hidden" name="userId" value=${userId } />
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		<h4 class="modal-title">写评论</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="input-nickname">内容：</label>
			<textarea type="textarea" name="commentContent" style="width:100%;height:10%;" required minlength="2" maxlength="30000"></textarea>
			<div class="help-block with-errors"></div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="submit" class="btn btn-primary" data-loading-text="Loading...">发表</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	</div>
</form>

