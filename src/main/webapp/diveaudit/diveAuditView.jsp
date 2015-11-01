<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveAudit" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TdiveAudit.ALIAS_BUSINESS_ID%></th>	
					<td>
						${diveAudit.businessId}							
					</td>							
					<th><%=TdiveAudit.ALIAS_BUSINESS_TYPE%></th>	
					<td>
						${diveAudit.businessTypeZh}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveAudit.ALIAS_USER_ID%></th>	
					<td>
						${diveAudit.userId}							
					</td>							
					<th><%=TdiveAudit.ALIAS_AUDIT_STATUS%></th>	
					<td>
						${diveAudit.auditStatusZh}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveAudit.ALIAS_AUDIT_TIME%></th>	
					<td>
						<fmt:formatDate value="${diveAudit.audittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>							
					<th><%=TdiveAudit.ALIAS_ADDTIME%></th>	
					<td>
						<fmt:formatDate value="${diveAudit.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveAudit.ALIAS_REMARK%></th>	
					<td colspan="3">
						${diveAudit.remark}							
					</td>	
				</tr>		
		</table>
	</div>
</div>