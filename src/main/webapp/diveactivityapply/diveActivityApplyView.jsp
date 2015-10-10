<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivityApply"%>
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
				<th><%=TdiveActivityApply.ALIAS_ACTIVITY_ID%></th>
				<td>${diveActivityApply.activityId}</td>
				<th><%=TdiveActivityApply.ALIAS_REMARK%></th>
				<td>${diveActivityApply.remark}</td>
			</tr>
			<tr>
				<th><%=TdiveActivityApply.ALIAS_USER_ID%></th>
				<td>${diveActivityApply.userId}</td>
				<th><%=TdiveActivityApply.ALIAS_ADDTIME%></th>
				<td>${diveActivityApply.addtime}</td>
			</tr>
		</table>
	</div>
</div>