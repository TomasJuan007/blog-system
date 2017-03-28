<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>star</title>
<script type="text/javascript">
window.onload = function () {
    C = Math.cos; // cache Math objects
    S = Math.sin;
    U = 0;
    w = window;
    j = document;
    d = j.getElementById("c");
    c = d.getContext("2d");
    W = d.width = w.innerWidth;
    H = d.height = w.innerHeight;
    c.fillRect(0, 0, W, H); // resize <canvas> and draw black rect (default)
    c.globalCompositeOperation = "lighter"; // switch to additive color application
    c.lineWidth = 0.2;
    c.lineCap = "round";
    var bool = 0, 
        t = 0; // theta
    d.onmousemove = function (e) {
        if(window.T) {
            if(D==9) { D=Math.random()*18; f(1); }
            clearTimeout(T);
        }
        X = e.pageX; // grab mouse pixel coords
        Y = e.pageY;
        a=0; // previous coord.x
        b=0; // previous coord.y 
        A = X, // original coord.x
        B = Y; // original coord.y
        R=(e.pageX/W * 999>>0)/999;
        r=(e.pageY/H * 999>>0)/999;
        U=e.pageX/H * 360 >>0;
        D=9;
        g = 360 * Math.PI / 180;
        T = setInterval(f = function (e) { // start looping spectrum
            c.save();
            c.globalCompositeOperation = "source-over"; // switch to additive color application
            if(e!=1) {
                c.fillStyle = "rgba(0,0,0,0.02)";
                c.fillRect(0, 0, W, H); // resize <canvas> and draw black rect (default)
            }
            c.restore();
            i = 25; while(i --) {
                c.beginPath();
                if(D > 450 || bool) { // decrease diameter
                    if(!bool) { // has hit maximum
                        bool = 1;
                    }
                    if(D < 0.1) { // has hit minimum
                        bool = 0;
                    }
                    t -= g; // decrease theta
                    D -= 0.1; // decrease size
                }
                if(!bool) {
                    t += g; // increase theta
                    D += 0.1; // increase size
                }
                q = (R / r - 1) * t; // create hypotrochoid from current mouse position, and setup variables (see: http://en.wikipedia.org/wiki/Hypotrochoid)
                x = (R - r) * C(t) + D * C(q) + (A + (X - A) * (i / 25)) + (r - R); // center on xy coords
                y = (R - r) * S(t) - D * S(q) + (B + (Y - B) * (i / 25));
                if (a) { // draw once two points are set
                    c.moveTo(a, b);
                    c.lineTo(x, y)
                }
                c.strokeStyle = "hsla(" + (U % 360) + ",100%,50%,0.75)"; // draw rainbow hypotrochoid
                c.stroke();
                a = x; // set previous coord.x
                b = y; // set previous coord.y
            }
            U -= 0.5; // increment hue
            A = X; // set original coord.x
            B = Y; // set original coord.y
        }, 16);
    }
    j.onkeydown = function(e) { a=b=0; R += 0.05 }
    d.onmousemove({pageX:300, pageY:290})
}
 
</script>
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

<body style="margin:0px;padding:0px;width:100%;height:100%;overflow:hidden;">
	<canvas id="c"></canvas>
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