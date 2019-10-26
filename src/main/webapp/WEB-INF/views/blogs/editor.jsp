<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>博客系统</title>
<style type="text/css">

[class*="fontawesome-"]:before {
  font-family: 'FontAwesome', sans-serif;
  font-size:21px;
  position: absolute;
  margin-top: 15px;
  margin-left: 24px;
}

body{
  margin:0;
  font-family: Arial, Helvetica, sans-serif;
  background:#E5EBEF;
}

div .header{
  background:rgba(57, 89, 123, 0.8);
  width:100%;
  height:80px;
  position:fixed;
  z-index:1;
  padding-left:160px;
}

div .sidebar .circles{
  border-radius:50%;
  border: 5px solid white;
  width:10px;
  height:10px;
  margin-top:30px;
  margin-left:15px;
  float:left;
  
  box-shadow: 16px 0 0 -5px #29343A,
              16px 0 0 0 white,
              32px 0 0 -5px #29343A,
              32px 0 0 0 white;
  
  position:relative;
}

div .sidebar .exitMenu input{
  display:none;
}

div .sidebar .exitMenu label{
  border-radius:50%;
  border: 5px solid white;
  width:20px;
  height:20px;
  margin-left:-10px;
  float:left;
  
  position:absolute;
  bottom:35px;
}

div .sidebar .exitMenu input:checked ~ label{
  border-style:double;
}

div .sidebar .exitMenu input:checked ~ .finishButton{
  bottom:-100px;
}

div .sidebar label:not(:first-child):hover{
  opacity:.8;
  cursor:pointer;
}

div .header .path{
   margin-top:15px;
}

div .header .path p{
  color:rgba(255,255,255,.7);
  float:left;
}
div .header .path p:hover{
  color:rgba(255,255,255,1);
  cursor:pointer;
}

div .header .path p:not(:first-child):before{
  content: ">";
  margin:0 10px;
  display:inline-block;
  color:#456;
  font-weight:bold;
}

div .sidebar{
  background:#29343A;
  width:80px;
  position:fixed;
  height:100%;
}

div .content{
  margin-left:80px;
  
  padding-top:110px;
  padding-left:80px;
  padding-right:80px;
}

.content input{
  font-size:40px;
  color: rgba(150,150,150, 0.8);
  padding:5px;
  outline: none;
  transition:.2s;
}

input:focus{
  background:white;
  border-radius:5px;
  box-shadow:inset 0 0 5px 0 grey;
  text-shadow: none;
  color:#444;
}

input:hover{
  background:white;
  border-radius:5px;
  box-shadow:inset 0 0 5px 0 grey;
  text-shadow: none;
  color:#444;
}

::selection {
background-color: #444;
color: #ffffff;
}

.content #contentText{
  width:100%;
  border:none;
  min-height:200px;
  
  border-radius:10px;
  
  padding: 20px;
  outline: none; 
  resize: none;
  
  font-family: Arial, Helvetica, sans-serif;
  font-size:16px;
  
  line-height:180%;
  margin-bottom:30px;
  margin-top:30px;
  transition:.2s;
}

.content #contentText:focus{
  background:white;
  box-shadow: inset 0 0 4px -1px black;
}
.content #contentText:hover{
  background:white;
  box-shadow: inset 0 0 4px -1px black;
}

.textEditing input{
  display:none;
}

.textEditing span{
  margin: 0 0 0 -5px;
  cursor:pointer;
}

.textEditing input:first-child + label span:after {
  border-top-left-radius: 7px;
  border-bottom-left-radius: 7px;
}

.textEditing input:nth-last-child(2) + label span:after {
  border-top-right-radius: 7px;
  border-bottom-right-radius: 7px;
}

.textEditing span:after {
  content: "";
  display:inline-block;

  width: 65px;
  height: 50px;

  border: 1px solid #B8C3CB;
  background-color:#F2F6F8;
}

.textEditing #linkLable span:after{
  background:#29343A;
  transition:.4s;
  width:0;
}

.textEditing input:checked + label span:after {
  background-color:#29343A;
  color:#fff;
}

.textEditing input:checked + label span {
  color:#fff;
}

.textEditing #attachment:checked ~ #linkLable span:after {
  width:320px;
}

.textEditing span:hover:after {
  background-color:white;
}

div .finishButton {
  padding-top:20px;
  padding-bottom:20px;
  width:calc(100% - 80px);
  margin-left:80px;
  position:fixed;
  bottom:0;
  background:#E5EBEF;
  transition:.3s ease;
  
  box-shadow: 0 0 0 #fff, 0 0 5px #888;
}

.finishButton a{
    background-color: #999;
    border-radius: 7px;
  border: 1px solid #eee;

  color: #fff;
    display: inline-block;
    margin-right: 20px;
    padding: 15px 0;
    width:130px;
    text-align:center;
    text-decoration: none;
    vertical-align: middle;
}

.finishButton a:hover{
   box-shadow:inset 0 0 3px black;
}

.finishButton button{
    background-color: #999;
    border-radius: 7px;
  border: 1px solid #eee;

  color: #fff;
    display: inline-block;
    margin-right: 20px;
    padding: 15px 0;
    width:130px;
    text-align:center;
    text-decoration: none;
    vertical-align: middle;
}

.finishButton button:hover{
   box-shadow:inset 0 0 3px black;
}

#publish{
    background-color: #57D36B;
    margin-left:80px;
}

#delete{
    background-color: #E37668;
    position:absolute;
    right:50px;
}

input{
   outline: none; 
}
</style>
</head>

<body>
<form method="post" action="submitBlogs">
<div class="main"> 
  <div class="header">
    <div class="path">
      <p onclick="history.go(-1);">个人中心</p>
      <p id="articleHeaderName">编辑器</p>
    </div>
  </div>
  <div class="sidebar">
    <div class="circles"></div>
    <div class="exitMenu">
      <input type="checkbox" id="exitMenuCheckbox"/> 
      <label for="exitMenuCheckbox"></label>
      <div class="finishButton">
		<button type="submit" id="publish">提交</button>
        <a href="index" id="delete">关闭</a>
      </div>
    </div>
  </div>
  <div class="content">
    <input type="text" name="blogTitle" placeholder="标题" required minlength="2" maxlength="50">
  	<textarea id="contentText" contenteditable="true" name="blogContent" required minlength="2" maxlength="30000"></textarea>
  </div>
</div>
</form>
</body>
</html>