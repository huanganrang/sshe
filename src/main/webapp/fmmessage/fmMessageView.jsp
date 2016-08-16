<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmMessage.ALIAS_ADDTIME%></th>	
					<td>
						${fmMessage.addtime}							
					</td>							
					<th><%=TfmMessage.ALIAS_UPDATETIME%></th>	
					<td>
						${fmMessage.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMessage.ALIAS_ISDELETED%></th>	
					<td>
						${fmMessage.isdeleted}							
					</td>							
					<th><%=TfmMessage.ALIAS_CONTENT%></th>	
					<td>
						${fmMessage.content}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMessage.ALIAS_TITLE%></th>	
					<td>
						${fmMessage.title}							
					</td>							
					<th><%=TfmMessage.ALIAS_SUB_TITLE%></th>	
					<td>
						${fmMessage.subTitle}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMessage.ALIAS_SEND_TYPE%></th>	
					<td>
						${fmMessage.sendType}							
					</td>							
					<th><%=TfmMessage.ALIAS_URL%></th>	
					<td>
						${fmMessage.url}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMessage.ALIAS_SEND_TIME%></th>	
					<td>
						${fmMessage.sendTime}							
					</td>							
					<th><%=TfmMessage.ALIAS_LOGIN_ID%></th>	
					<td>
						${fmMessage.loginId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>