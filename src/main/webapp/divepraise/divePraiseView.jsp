<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdivePraise" %>
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
					<th><%=TdivePraise.ALIAS_BUSINESS_ID%></th>	
					<td>
						${divePraise.businessId}							
					</td>							
					<th><%=TdivePraise.ALIAS_BUSINESS_TYPE%></th>	
					<td>
						${divePraise.businessType}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdivePraise.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${divePraise.accountId}							
					</td>							
					<th><%=TdivePraise.ALIAS_ADDTIME%></th>	
					<td>
						${divePraise.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>