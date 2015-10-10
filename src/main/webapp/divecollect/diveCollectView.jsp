<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCollect"%>
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
				<th><%=TdiveCollect.ALIAS_BUSINESS_ID%></th>
				<td>${diveCollect.businessId}</td>
				<th><%=TdiveCollect.ALIAS_BUSINESS_TYPE%></th>
				<td>${diveCollect.businessType}</td>
			</tr>
			<tr>
				<th><%=TdiveCollect.ALIAS_ADDTIME%></th>
				<td>${diveCollect.addtime}</td>
			</tr>
		</table>
	</div>
</div>