<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload Photo</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="panel panel-primary" style="margin-bottom: 0px;">
            <div class="panel-heading">
                Form Upload Photo
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-md-6">
                        <form role="form" method="POST" action="/uploadimgctlr" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>File input</label>
                                <input type="file" method="POST" accept=".jpg" name="file"/>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Upload</button>
                            </div>
                        </form>
                    </div>
                </div>

                <c:if test="${ (photo != null) && !(photo eq '')}">
                    <div class="row">
                        <div class="col-md-12">
                            <img src="/resources/img/${ photo }" class="col-md-12"/>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</div>
</body>
</html>
