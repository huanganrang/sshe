<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveOrder" %>
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
					<th><%=TdiveOrder.ALIAS_ACCOUNT_ID%></th>	
					<td>
						${diveOrder.accountId}							
					</td>							
					<th><%=TdiveOrder.ALIAS_ADDRESS%></th>	
					<td>
						${diveOrder.address}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_EXPRESS_NAME%></th>	
					<td>
						${diveOrder.expressName}							
					</td>							
					<th><%=TdiveOrder.ALIAS_EXPRESS_NO%></th>	
					<td>
						${diveOrder.expressNo}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_PAY_WAY%></th>	
					<td>
						${diveOrder.payWay}							
					</td>							
					<th><%=TdiveOrder.ALIAS_REMARK%></th>	
					<td>
						${diveOrder.remark}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_PAY_STATUS%></th>	
					<td>
						${diveOrder.payStatus}							
					</td>							
					<th><%=TdiveOrder.ALIAS_ORDER_STATUS%></th>	
					<td>
						${diveOrder.orderStatus}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_SEND_STATUS%></th>	
					<td>
						${diveOrder.sendStatus}							
					</td>							
					<th><%=TdiveOrder.ALIAS_PAYTIME%></th>	
					<td>
						${diveOrder.paytime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_ADDTIME%></th>	
					<td>
						${diveOrder.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>