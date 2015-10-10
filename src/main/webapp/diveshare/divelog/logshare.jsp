<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>${account.nickname}的潜水日志</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/diveshare/divelog/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/diveshare/divelog/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/diveshare/divelog/ImgMagnify.css">
</head>
<body>
	<div id="main">
    	<div class="main-frame">
        	<div class="main_viewport">
            	<div class="record_hd">
                	<div class="cover" <c:if test="${log.logType == 'LT02'}">style="height: 160px;"</c:if>>
                  		<img class="cover_img" 
                  			style="<c:if test="${log.logType == 'LT02'}">height: 160px;</c:if> background-image:url(${pageContext.request.contextPath}/diveshare/divelog/cover_img.jpg)" 
                  			src="${pageContext.request.contextPath}/diveshare/divelog/space.png" alt="">
                  		<div class="cover_header">
					 		<!-- <span class="user_header" style="background-image:url(http://godive.cn/seaguest_server/diveres/headimgs/20150522/470-20150522093503110.jpg)"></span> -->
					 		<img class="user_header" src="${pageContext.request.contextPath}/${account.icon}" width="60" height="60"
        							onerror="javascript:this.src='${pageContext.request.contextPath}/diveshare/default.png'">
							<p class="user_text">
                        		<span class="user_name">${account.nickname}</span>
                     		</p>
                     		<c:if test="${log.logType == 'LT01'}">
	                     		<table class="record_info some_info" >
	                     			<tr>
	                     				<td colspan="2"><i>潜水地点：${log.diveAddress}</i></td>
	                     			</tr>
	                     			<tr>
	                     				<td colspan="2"><i>潜水时间：<fmt:formatDate value="${log.diveDate}" pattern="yyyy-MM-dd HH:mm"/></i></td>
	                     			</tr>
	                     			<tr>
	                     				<td><i>潜水深度：<fmt:formatNumber value="${log.diveHeight}" pattern="#m"/></i></td>
	                     				<td><i>潜水时长：<fmt:formatNumber value="${duration}" pattern="#min"/></i></td>
	                     			</tr>
	                     			<tr>
	                     				<td><i>天气：${log.weatherZh}</i></td>
	                     				<td><i>风力：${log.windPowerZh}</i></td>
	                     			</tr>
	                     			<tr>
	                     				<td><i>气温：<fmt:formatNumber value="${log.weatherTemperature}" pattern="#°C"/></i></td>
	                     				<td><i>水温：<fmt:formatNumber value="${log.waterTemperature}" pattern="#°C"/></i></td>
	                     			</tr>
	                     			<tr>
	                     				<td><i>能见度：<fmt:formatNumber value="${log.seeing}" pattern="#m"/></i></td>
	                     				<td><i>潜水类型：${log.diveTypeZh}</i></td>
	                     			</tr>
	                     			<tr>
	                     				<td><i>开始氧量：<fmt:formatNumber value="${log.gasStart}" pattern="#Bar"/></i></td>
	                     				<td><i>结束氧量：<fmt:formatNumber value="${log.gasEnd}" pattern="#Bar"/></i></td>
	                     			</tr>
	                     		</table>
	                     	</c:if>
                  		</div>
                	</div>
            	</div>
	            <div class="record_details">
	            	<div class="part_bg">
	            		<input type="hidden" value="${fn:length(imageList)}" id="imageLength">
	                	<p class="record_text">${log.sumary}</p>
	                	<div class="dive_imgs_item follow_img_item clearfix">
	                   		<div class="note_img clearfix ">
	                   			<c:forEach items="${imageList}" var="image" varStatus="s">
		                   			<a href="javascript:void(0);">
										<img original="${pageContext.request.contextPath}/<c:out value="${image}" />" 
												simage="${pageContext.request.contextPath}/<c:out value="${image}" />" 
												src="${pageContext.request.contextPath}/<c:out value="${image}" />"  
											/>
									</a>
								</c:forEach>
							</div>
	                	</div>
	                	<div class="write_time t_r">发布时间：<fmt:formatDate value="${log.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
						<div class="dive_tags clearfix">
							<ul>
								<li><span class="tag_img iconfont"></span></li>
							</ul>
						</div>
	               	</div>
	               	<!-- 
	               	<div class="part_bg" style="padding:0;">
		          		<div class="buddys_animals">
			           		<div class="wave_bg">
			                   	<img class="wave_img" src="${pageContext.request.contextPath}/diveshare/bottom.png" width="750" height="100" alt="">
			           		</div>
		       			</div>
		    		</div> -->
		    		<jsp:include page="../bottom.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<!-- 
		<div id="gotoDownload" style="display: none;">
			<div class="download-close" id="closeDownload"></div>
			<a href="http://www.godive.cn/"><img src="./Cathy-优潜的潜水日志_files/down_app.jpg" alt=""></a>
		</div> -->
	</div>


	<script type="text/javascript" src="${pageContext.request.contextPath}/diveshare/divelog/touchSwipe.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/diveshare/divelog/scrollBox.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"></script>
	<script language="javascript">
		$(function() {
			var length = $("#imageLength").val();
			if(length > 1) {
				var l = 3;
				var m = 10;
				var w = $(window).width() / l - (m * (l - 1));
				$('.dive_imgs_item img').css({
					width : (w + 'px'),
					height : (w + 'px')
				});
			} else {
				$('.dive_imgs_item img').css({
					width : '100%',
					height : 'inherit'
				});
			}
			
			//console.log(w);
			
			$('.download-close').click(function(){
				$(this).parent('#gotoDownload').hide();
			});
		});
	</script>

	</body>
</html>