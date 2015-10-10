<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveShopCart" %>
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
					<th><%=TdiveShopCart.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${diveShopCart.accountId}							
					</td>							
					<th><%=TdiveShopCart.ALIAS_BUSINESS_ID%></th>	
					<td>
						${diveShopCart.businessId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveShopCart.ALIAS_BUSINESS_TYPE%></th>	
					<td>
						${diveShopCart.businessType}							
					</td>							
					<th><%=TdiveShopCart.ALIAS_NUMBER%></th>	
					<td>
						${diveShopCart.number}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveShopCart.ALIAS_PRICE%></th>	
					<td>
						${diveShopCart.price}							
					</td>							
					<th><%=TdiveShopCart.ALIAS_ADDTIME%></th>	
					<td>
						${diveShopCart.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>