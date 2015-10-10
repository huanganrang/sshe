<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/wejoin/css/login.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/wejoin/js/login.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="login_bj"></div>
		<div class="login_zt">
			<span></span>
		    <a href="#" class="a1">下载APPmade</a>
		    <a href="#" class="a2">下载客户端</a>
		    <div class="clear"></div>
		    <ol><a href="#">进入微社区</a></ol>
		</div>

		<div class="copyright">
			<span>
				<a href="javascript:void();" class="a_1">登录</a> | 
				<a href="javascript:void();" onclick="showSyldBox()">注册</a> | 
				<a href="javascript:void();">关于我们</a> | 
				<a href="javascript:void();">联系我们</a>
			</span>
		    <ol>浙ICP备13025616-1号</ol>
		</div>

		<!--登录 开始-->
		<div class="windows djcgBox" id="djcgBox" style="display:none;" >
			<div class="windowsBg"></div>
		  	<div class="windows_box">
				<div class="djcgBox_con">
					<div class="dj_top"></div>
		               <div class="dj_con">
		               	<div class="dj_title">
		                   	<span></span>
		                       <div>
		                       	<ol>用户登录</ol>
		                           <em>USER LOGIN</em>
		                       </div>
		                   </div>
		                   <ul>
		                   	<form action="" method="get">
		                   	<li><input type="text" class="pt1" onblur="if(this.value==''){this.value='请输入用户名';this.style.color='#ccc'}" onfocus="if(this.value=='请输入用户名'){this.value='';this.style.color='#333'}" value="请输入用户名" id="textfield" name="telphone" style="color:#ccc;"><b></b></li>
		                       <li><input type="password" class="pt1" onblur="if(this.value==''){this.value='请输入密码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入密码'){this.value='';this.style.color='#333'}" value="请输入密码" id="textfield" name="telphone" style="color:#ccc;"><p></p></li>
		                       <strong><a href="#" onclick="showTjcgBox()">找回密码</a></strong>
		                       <input name="" type="submit" value="登  录" class="tzam"/>
		                       </form>
		                   </ul>
		               </div>
		               <div class="dj_btn"></div>
				</div>
				<div class="close_btn"></div>
			</div>
		</div>
		<!--登录 结束-->

		<!--找回密码 开始-->
		<div class="windows tjcgBox" id="tjcgBox" style="display:none;" >
			  <div class="windowsBg"></div>
			  <div class="windows_box">
				<div class="tjcg_con">
		           	<div class="dj_top"></div>
		               <div class="tjcg_bj">
		           	<div class="dj_title">
		                   	<span></span>
		                       <div>
		                       	<ol>密码找回</ol>
		                           <em>PASSWORD RETRIEVE</em>
		                       </div>
		               </div>
		               <div class="tjcg_list">
		               	<ul>
		                   	<form action="" method="get">
		                   	<li><input type="text" class="pt2" onblur="if(this.value==''){this.value='请输入手机号获取验证码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入手机号获取验证码'){this.value='';this.style.color='#333'}" value="请输入手机号获取验证码" id="textfield" name="telphone" style="color:#ccc;"><ol><a href="#">点击获取</a></ol></li>
		                       <li><input type="text" class="pt3" onblur="if(this.value==''){this.value='请输入验证码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入验证码'){this.value='';this.style.color='#333'}" value="请输入验证码" id="textfield" name="telphone" style="color:#ccc;"></li>
		                       <li><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" class="pt2" onblur="if(this.value==''){this.value='请输入新密码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入新密码'){this.value='';this.style.color='#333'}" value="请输入新密码" id="textfield" name="telphone" style="color:#ccc;"></li>
		                       <li style="margin-bottom:40px; "><span>确认密码：</span><input type="password" class="pt2" onblur="if(this.value==''){this.value='请重复输入新密码';this.style.color='#ccc'}" onfocus="if(this.value=='请重复输入新密码'){this.value='';this.style.color='#333'}" value="请重复输入新密码" id="textfield" name="telphone" style="color:#ccc;"></li>
		                       <input name="" type="submit" value="确  定" class="tzam"/>
		                       </form>
		                   </ul>
		               </div>
		               </div>
		               <div class="dj_btn"></div>
		           </div>
				<div class="close_btn"></div>
			  </div>
		</div>
		<!--找回密码 结束-->


		<!--注册 开始-->
		<div class="windows syldBox" id="syldBox" style="display:none;" >
			  <div class="windowsBg"></div>
			  <div class="windows_box">
				<div class="tjcg_con">
		           	<div class="dj_top"></div>
		               <div class="tjcg_bj">
		           	<div class="dj_title">
		                   	<span class="zc_tb"></span>
		                       <div>
		                       	<ol>用户注册</ol>
		                           <em>USER REGISTRATION</em>
		                       </div>
		               </div>
		               <div class="tjcg_list">
		               	<ul>
		                   	<form action="" method="get">
		                   	<li><input type="text" class="pt2" onblur="if(this.value==''){this.value='请输入手机号获取验证码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入手机号获取验证码'){this.value='';this.style.color='#333'}" value="请输入手机号获取验证码" id="textfield" name="telphone" style="color:#ccc;"><ol><a href="#">点击获取</a></ol></li>
		                       <li><input type="text" class="pt3" onblur="if(this.value==''){this.value='请输入验证码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入验证码'){this.value='';this.style.color='#333'}" value="请输入验证码" id="textfield" name="telphone" style="color:#ccc;"></li>
		                       <li><span>注册帐号：</span><input type="text" class="pt2" onblur="if(this.value==''){this.value='请输入用户名';this.style.color='#ccc'}" onfocus="if(this.value=='请输入用户名'){this.value='';this.style.color='#333'}" value="请输入用户名" id="textfield" name="telphone" style="color:#ccc;"></li>
		                       <li style="margin-bottom:40px; "><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" class="pt2" onblur="if(this.value==''){this.value='请输入密码';this.style.color='#ccc'}" onfocus="if(this.value=='请输入密码'){this.value='';this.style.color='#333'}" value="请输入密码" id="textfield" name="telphone" style="color:#ccc;"></li>
		                       <input name="" type="submit" value="注  册" class="tzam"/>
		                       </form>
		                   </ul>
		               </div>
		               </div>
		               <div class="dj_btn"></div>
		           </div>
				<div class="close_btn"></div>
			  </div>
		</div>
	</body>
</html>