<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Diving Download</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<style type="text/css">
body {font-family:"微软雅黑";font-size:12px; background-color:#f8f7f5;}
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,blockquote,p{padding:0; margin:0;}

.content{ padding:5px 15px; margin:0 auto;}
.content h1{ display:block; line-height:1.8em; font-size:20px; color:#4c4c4c; font-weight:bold; text-align:left;}
</style>
<script type="text/javascript">
	function download() {
		if(isWeiXin()) {   
// 	        alert('微信扫描无法下载，请点击右上角切换其他浏览器打开.') 
	    } else {  
	        switch(getDevice()) {  
	            case 'Android':  
	                window.location.href="${pageContext.request.contextPath}/fileController/download?filePath=v/Diving.apk";  
	                break;  
	            case 'iOS':  
	               // window.location.href="http://www.goodiver.com/Download/download"; 
	               alert("暂不提供IOS下载！");
	                break;  
	            default:  
	            	alert("暂无官网地址！");
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
	 <div class="content">
		 <h1>亲爱的潜友，请点击右上角，选择"在浏览器中打开"就能下载十分有趣的潜伴APP。</h1>
	</div>
</body>
</html>