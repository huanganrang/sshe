<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivityComment"%>
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
				<th><%=TdiveActivityComment.ALIAS_ACTIVITY_ID%></th>
				<td>${diveActivityComment.activityId}</td>
				<th><%=TdiveActivityComment.ALIAS_COMMENT%></th>
				<td>${diveActivityComment.comment}</td>
			</tr>
			<tr>
				<th><%=TdiveActivityComment.ALIAS_PID%></th>
				<td>${diveActivityComment.pid}</td>
				<th><%=TdiveActivityComment.ALIAS_USER_ID%></th>
				<td>${diveActivityComment.userId}</td>
			</tr>
			<tr>
				<th><%=TdiveActivityComment.ALIAS_ADDTIME%></th>
				<td>${diveActivityComment.addtime}</td>
			</tr>
		</table>
	</div>
</div>