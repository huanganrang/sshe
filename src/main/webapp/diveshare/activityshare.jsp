<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>活动:<fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd"/> 从${activity.startAddr}出发,目的地:${activity.endAddr} </title>
<style type="text/css">
body {font-family: Arial,"冬青黑体简体中文","微软雅黑";font-size:28px;}
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,blockquote,p{padding:0; margin:0;}
table,td,tr,th{font-size:12px;}
li{list-style-type:none; margin:0; padding:0;}
table{ margin:0 auto;}
img{border:0; line-height:0;}
ol,ul {list-style:none;}   
h1,h2,h3,h4,h5,h6 {font-size:12px; font-weight:normal;}   
a { color: #1354b5; text-decoration: none; outline: none;}
a:visited {text-decoration:none;}
a:hover {text-decoration:underline;}
a:active { }
a img { border: none; }
dd,dl,dt{ margin:0 ; padding:0;}

.left{ float:left;}
.right{ float:right;}
/*分享模板*/
.fx{ padding-left:30px; padding-right:30px; padding-top:32px; position:relative; max-width:740px; overflow:hidden; margin:0 auto;}
.fx .fub{ position:absolute; width:120px; height:78px; overflow:hidden; background:url(http://www.goodiver.com/skins/share/images/fub_bg.png) no-repeat; text-align:center; font-size:28px; color:#fff; top:0; right:30px; line-height:68px;}
/* .fx .fub2{ position:absolute; width:120px; height:78px; overflow:hidden; background:url(###fub_bg2###) no-repeat; text-align:center; font-size:28px; color:#fff; top:0; right:30px; line-height:68px;} */
.fx .content{ color:4c4c4c;font-size:28px;}
/*top*/
.fx .content .head { overflow:hidden; margin-bottom:30px; min-width:420px;}
.fx .content .head .wd{ width:70%; overflow:hidden;}
.fx .content .head .wd dt img{ width:100%;}
.fx .content .head dt{border-radius:50px; width:70px; height:70px; overflow:hidden; float:left;}
.fx .content .head ul{ margin-left:90px; margin-top:8px;}
.fx .content .head h1{ font-size:24px; font-weight:bold;}
.fx .content .head h2{ font-size:18px; margin-top:8px; color:#999;}
.fx .content .head h3{ font-size:18px; margin-top:12px; color:#999;}
.fx .content .head span{font-weight:bold;}
.fx .content .con1{ width:100%; overflow: hidden; min-width:420px;}
.fx .content .con1 img{ width:100%;}
.fx .content .con1 {font-size:20px;  color:#333;}
.fx .content .con1 dt{ margin-top:20px; margin-bottom:20px;line-height:30px;}
.fx .content .con1 .xxjh{ color:#666; margin-top:20px;}
.fx .content .con1 dd{ margin-top:10px; margin-bottom:10px;}
.pl { width:100%; overflow:hidden; min-width:480px; max-width:740px; overflow:hidden; margin:0 auto;}
.pl .zan { margin-top:20px; background-color:#f5f5f5;height:81px; padding-left:30px;padding-right:30px; border-bottom:#ccc solid 1px; overflow:hidden;}
.pl .zan ul{ overflow:hidden; height:82px;}
.pl .zan li{ border-radius:40px; width:48px; height:48px; overflow:hidden; float:left; margin-right:18px; margin-top:17px;}
.pl .zan dt{ font-size:18px; color:#1f8fe4; line-height:82px;}
.pl .zxpl .bt{border-bottom:#ccc solid 1px;padding-left:30px;padding-right:30px; height:47px; margin-top:10px;}
.pl .zxpl h1{ width:200px; height:48px; line-height:48px; font-size:24px; color:#4c4c4c;}
.pl .zxpl ul{border-bottom:#ccc solid 1px;padding-left:30px;padding-right:30px; padding-top:24px; padding-bottom:23px; }
.pl .zxpl dt{border-radius:40px; width:48px; height:48px;overflow:hidden; float:left;}
.pl .zxpl li h2{ font-size:24px; color:#222; margin-left:68px;}
.pl .zxpl li h3{ float:right; color:#999999; font-size:18px;}
.pl .zxpl li h4{ margin-left:68px; color:#666666; font-size:20px; line-height:35px; margin-top:14px;}
.pl i{border-bottom:#ccc solid 1px; line-height:80px; height:80px; color:#4c4c4c; text-align:center;display:block; width:100%; font-style:normal;}
.pl i a:hover{color:#0070c6; text-decoration:none;}
.pl .zxpl span{ color:#0070c6;}
.content .nr{ margin-top:15px; overflow:hidden;}
.content .nr p{ text-indent:2em; font-size:14px; color:#666; line-height:1.8em; margin:.8em 0;}
.content .nr img{ width:100%;}
</style>
<meta name="viewport" content="width=device-width, initial-scale=0.66, minimum-scale=0.66, maximum-scale=0.66, user-scalable=yes">   
</head>
<body>
  	<div class="fx">
    	<div class="fub">
    		<c:choose>
    			<c:when test="${activity.status == 'AT01'}">
    				报名中
    			</c:when>
    			<c:when test="${activity.status == 'AT02'}">
    				进行中
    			</c:when>
    			<c:when test="${activity.status == 'AT03'}">
    				已结束
    			</c:when>
    		</c:choose>
    	</div>
 		<div class="content">
    		<div class="head">
        		<div class="left wd">
        			<dt><img src="${pageContext.request.contextPath}/diveshare/logo.png"></dt>
           			<ul>
		              <h1>${activity.name}</h1>
		              <h2><span></span> &nbsp;</h2>
		              <h3><fmt:formatDate value="${activity.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
           			</ul>
        		</div>
        <!-- 
        <div class="left">
        	<ul>
        		<h2><a href="http://www.goodiver.com/">潜伴-全球潜水爱好者集中营</a></h2>
        	</ul>
        </div> -->
  		 	</div>
   			<div class="con1">
       			<dt><fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${activity.endDate}" pattern="yyyy-MM-dd"/> 从${activity.startAddr}出发,目的地:${activity.endAddr}</dt><dt></dt>
       			<dt class="xxjh">${activity.description}</dt>
   			</div>
 		</div>
	</div>
  	<div class="pl">
    	<div class="zan">
      		<dt class="right"><c:out value="${fn:length(activity.applies)}"></c:out>  人报名</dt>
      		<ul>
      			<c:forEach items="${activity.applies}" var="z" varStatus="s">
        			<li><img src="${pageContext.request.contextPath}/<c:out value="${z.icon}" />" width="48" height="48"
        				onerror="javascript:this.src='${pageContext.request.contextPath}/diveshare/default.png'"></li>
        		</c:forEach>
      		</ul>
    	</div>
    	<!-- 
    	<div class="zxpl">
      		<div class="bt">
          		<h1>最新评论</h1>
      		</div>
    	</div> -->
  	</div>
  	<jsp:include page="bottom.jsp"></jsp:include>
  	<!-- <div class="wm">
   		<div class="down_wm"><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/diveshare/20150724_down_wm.png"></a></div>
	    <div class="down_wen">点击或长按二维码下载</div>
	    <div class="top"><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/diveshare/logo2.png" width="395" height="72"></a></div>
	    <div class="bottom"></div>
	</div> -->
</body>
</html>