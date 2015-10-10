<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.cont_ft{ overflow:hidden; border-top:2px dashed #ccc; margin-top:15px; padding-top:20px; font-size:26px; color:#4c4c4c; text-align:center; padding-bottom:2em;}
.cont_ft h3{ display:block; font-size:14px; line-height:2em;}
.cont_ft h3 span{}
.cont_ft .gw{ width:61%; overflow:hidden; margin:0 auto;}
.cont_ft .gw img{ width:100%;}
.cont_ft .ft_ew{ overflow:hidden; margin-top:1.5em;}
.cont_ft .ft_ew .ew{ float:left; width:49.5%; text-align:center;}
.cont_ft .ft_ew .ew dt{ margin:0 auto; width:70%; overflow:hidden; text-align:center; position:relative;}
.cont_ft .ft_ew .ew dt i{ display:block; position:absolute; width:97%; overflow:hidden; top:1px; left:1.5%; font-style:normal;}
.cont_ft .ft_ew .ew p{ font-size:14px; line-height:1.5em;}

.wm { min-width:480px; max-width:740px; overflow:hidden; margin:0 auto; margin-top:30px;}
.wm .top{padding-left:35px;padding-right:35px; padding-bottom:35px; text-align:center; margin-top:6%;}
.wm .down_wm{ overflow:hidden; text-align:center; width:40%; margin:0 auto;}
.wm .down_wm img{ width:100%; margin-bottom:9%;}
.wm .down_wen{ font-size:20px; color:#999; text-align:center; overflow:hidden;}
.wm .bottom{ background-image:url(${pageContext.request.contextPath}/diveshare/bottom.png); height:100px; margin-top:1%;}
</style>
</head>
<body>
	 <div class="cont_ft">
   		<h3>潜伴，全球潜水爱好者集中营 </h3>
       	<div class="gw"><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/diveshare/xz.png" alt=""></a></div>
       	<h3 style="line-height:1em;">扫码下载</h3>
       	<div class="wm">
       		<div class="down_wm"><a href="${pageContext.request.contextPath}/diveshare/download.jsp"><img src="${pageContext.request.contextPath}/diveshare/20150910__down_wm.png"></a></div>
	    	<div class="down_wen">点击或长按二维码下载</div>
	    	<div class="top"><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/diveshare/logo2.png" width="395" height="72"></a></div>
	    	<div class="bottom"></div>
       	</div>
   	</div>
</body>
</html>