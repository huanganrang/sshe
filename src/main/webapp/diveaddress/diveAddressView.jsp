<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveAddress"%>
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
				<td colspan="3">${diveAddress.id}</td>
			</tr>
			<tr>
				<th width="8%"><%=TdiveAddress.ALIAS_NAME%></th>
				<td width="42%">${diveAddress.name}</td>
				<th width="8%"><%=TdiveAddress.ALIAS_STATUS%></th>
				<td width="42%">${diveAddress.statusZh}</td>
			</tr>
			<tr>
				<th><%=TdiveAddress.ALIAS_AREA%></th>
				<td>${diveAddress.areaZh}</td>
				<th><%=TdiveAddress.ALIAS_FEATURE%></th>
				<td>${diveAddress.featureZh}</td>
			</tr>
			<tr>
				<th><%=TdiveAddress.ALIAS_ADDTIME%></th>
				<td colspan="3">${diveAddress.addtime}</td>
			</tr>
			<tr>
				<th><%=TdiveAddress.ALIAS_ICON%></th>
				<td colspan="3"><img alt="" src="${diveAddress.icon}"> 	</td>
			</tr>
			<tr>
				<th><%=TdiveAddress.ALIAS_SUMARY%></th>
				<td colspan="3">${diveAddress.sumary}</td>
			</tr>
			<tr>
				<th><%=TdiveAddress.ALIAS_DESCRIPTION%></th>
				<td colspan="3">${diveAddress.description}</td>
			</tr>
		</table>
	</div>
</div>