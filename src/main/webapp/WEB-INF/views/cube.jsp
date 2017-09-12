<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style>
	html  
       {  
           background: -webkit-radial-gradient(center, ellipse, #430d6d 0%, #000000 100%);  
           background: radial-gradient(ellipse at center, #430d6d 0%, #000000 100%);  
           height: 100%;  
       }  
  
       .stage  
       {  
           -webkit-perspective: 1000px;  
           width: 20em;  
           height: 20em;  
           left: 50%;  
           top: 50%;  
           margin-left: -10em;  
           margin-top: -10em;  
           position: absolute;  
       }  
  
       
  
       .cube *  
       {  
           background-color: rgba(0, 0, 0, 0.5);  
           position: absolute;  
           width: 100%;  
           height: 100%;  
           border: 2px solid rgba(54, 226, 248, 0.5);  
           -webkit-box-shadow: 0 0 5em rgba(0, 128, 0, 0.4);  
       }  
  
       .font  
       {  
           -webkit-transform: translateZ(10em);  
       }  
  
       .back  
       {  
           -webkit-transform: rotateX(180deg) translateZ(10em);  
       }  
  
       .left  
       {  
           -webkit-transform: rotateY(-90deg) translateZ(10em);  
       }  
  
       .right  
       {  
           -webkit-transform: rotateY(90deg) translateZ(10em);  
       }  
  
       .top  
       {  
           -webkit-transform: rotateX(90deg) translateZ(10em);  
       }  
  
       .bottom  
       {  
           -webkit-transform: rotateX(-90deg) translateZ(10em);  
       }  
	@-webkit-keyframes spin  
       {  
           from  
           {  
               -webkit-transform: translateZ(-10em) rotateX(0) rotateY(0deg);  
               transform: translateZ(-10em) rotateX(0) rotateY(0deg);  
           }  
  
           to  
           {  
               -webkit-transform: translateZ(-10em) rotateX(360deg) rotateY(360deg);  
               transform: translateZ(-10em) rotateX(360deg) rotateY(360deg);  
           }  
       }  

.cube  
      {  
          -webkit-animation: spin linear 6s infinite;  
          position: absolute;  
          width: 100%;  
          height: 100%;  
          -webkit-transform-style: preserve-3d;  
           
      }  

	</style>
</head>
<body>
	<div class="stage">  
	    <div class="cube">  
	        <div class="font"></div>  
	        <div class="back"></div>  
	        <div class="left"></div>  
	        <div class="right"></div>  
	        <div class="top"></div>  
	        <div class="bottom"></div>  
	    </div>  
  
	</div>  
</body>
</html>