<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmPurchaseUser" %>
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
					<th><%=TfmPurchaseUser.ALIAS_ADDTIME%></th>	
					<td>
						${fmPurchaseUser.addtime}							
					</td>							
					<th><%=TfmPurchaseUser.ALIAS_UPDATETIME%></th>	
					<td>
						${fmPurchaseUser.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchaseUser.ALIAS_ISDELETED%></th>	
					<td>
						${fmPurchaseUser.isdeleted}							
					</td>							
					<th><%=TfmPurchaseUser.ALIAS_PURCHASE_ID%></th>	
					<td>
						${fmPurchaseUser.purchaseId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchaseUser.ALIAS_USER_ID%></th>	
					<td>
						${fmPurchaseUser.userId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>