<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveStore"%>
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
				<th>主键ID</th>
				<td colspan="3">${diveStore.id}</td>
			</tr>
			<tr>
				<th width="8%"><%=TdiveStore.ALIAS_NAME%></th>
				<td width="42%">${diveStore.name}</td>
				<th width="8%"><%=TdiveStore.ALIAS_SERVER_SCOPE%></th>
				<td width="42%">${diveStore.serverScope}</td>
			</tr>
			<tr>
				<th><%=TdiveStore.ALIAS_AREA%></th>
				<td>${diveStore.area}</td>
				<th><%=TdiveStore.ALIAS_STATUS%></th>
				<td>${diveStore.statusZh}</td>
			</tr>
			<tr>
				<th><%=TdiveStore.ALIAS_ADDTIME%></th>
				<td colspan="3">${diveStore.addtime}</td>
			</tr>
			<tr>
				<th><%=TdiveStore.ALIAS_ICON%></th>
				<td colspan="3"><img alt="" src="${diveStore.icon}"></td>
			</tr>
			<tr>
				<th><%=TdiveStore.ALIAS_SUMARY%></th>
				<td colspan="3">${diveStore.sumary}</td>
			</tr>
			<tr>
				<th><%=TdiveStore.ALIAS_DESCRIPTION%></th>
				<td colspan="3">${diveStore.description}</td>
			</tr>
		</table>
	</div>
</div>