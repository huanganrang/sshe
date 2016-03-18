<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.listener.Application" %>
<%@ page import="jb.pageModel.BaseData" %>
<%
	String androidFilePath = Application.get("VM01").getIcon();
	String iosDownloadUrl = Application.get("VM02").getDescription();
%>
<!DOCTYPE html>
<html>
<head>
<title>Diving Download</title>
<meta name="viewport" content="width=device-width, initial-scale=0.66, minimum-scale=0.66, maximum-scale=0.66, user-scalable=yes">
<style type="text/css">
	body {
		background-image: URL(${pageContext.request.contextPath}/diveshare/download_bg.png);
		background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-size:100% 100%;
	}
</style>
<script type="text/javascript">
	function download() {
		if(isWeiXin()) {   
// 	        alert('微信扫描无法下载，请点击右上角切换其他浏览器打开.') 
	    } else {  
	        switch(getDevice()) {  
	            case 'Android':  
	            	window.location.href="${pageContext.request.contextPath}/<%=androidFilePath %>";  
	                break;  
	            case 'iOS':  
	               window.location.href="<%=iosDownloadUrl %>"; 
// 	               alert("暂不提供IOS下载！");
	                break;  
	            default:  
// 	            	alert("暂无官网地址！");
	            	window.location.href="${pageContext.request.contextPath}/";
	                break;  
	        }  
	    }  
	}
	
	function isWeiXin() {   
	    var ua = window.navigator.userAgent.toLowerCase();  
	    if(ua.match(/MicroMessenger/i) == 'micromessenger') {   
	        return true;  
	    } else {   
	        return false;  
	    }   
	}  
	  
	function getDevice() {  
	    var u = navigator.userAgent;  
	    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {  
	        return 'Android';  
	    } else if (u.indexOf('iPhone') > -1) {  
	        return 'iOS';  
	    } else {  
	        return 'none';  
	    }  
	}  
</script>
</head>
<body onload="download();">
</body>
</html>