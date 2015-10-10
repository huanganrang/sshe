<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveAccount"%>
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
				<th width="8%"><%=TdiveAccount.ALIAS_USER_NAME%></th>
				<td width="42%">${diveAccount.userName}</td>
				<th width="8%"><%=TdiveAccount.ALIAS_NICKNAME%></th>
				<td width="42%">${diveAccount.nickname}</td>
			</tr>
			<tr>
				<th><%=TdiveAccount.ALIAS_SEX%></th>
				<td>${diveAccount.sexZh}</td>
				<th><%=TdiveAccount.ALIAS_CITY%></th>
				<td>${diveAccount.city}</td>
			</tr>
			<tr>
				<th><%=TdiveAccount.ALIAS_EMAIL%></th>
				<td>${diveAccount.email}</td>
				<th><%=TdiveAccount.ALIAS_ADDTIME%></th>
				<td>${diveAccount.addtime}</td>
			</tr>
			<tr>
				<th><%=TdiveAccount.ALIAS_PERSONALITY%></th>
				<td colspan="3">${diveAccount.personality}</td>
			</tr>
			
			<tr>
				<th>头像</th>
				<td colspan="3">
					<img alt="" src="${diveAccount.icon}">
				</td>
			</tr>
		</table>
	</div>
</div>