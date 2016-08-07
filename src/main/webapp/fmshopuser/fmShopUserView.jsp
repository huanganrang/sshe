<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmShopUser" %>
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
					<th><%=TfmShopUser.ALIAS_ADDTIME%></th>	
					<td>
						${fmShopUser.addtime}							
					</td>							
					<th><%=TfmShopUser.ALIAS_UPDATETIME%></th>	
					<td>
						${fmShopUser.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmShopUser.ALIAS_ISDELETED%></th>	
					<td>
						${fmShopUser.isdeleted}							
					</td>							
					<th><%=TfmShopUser.ALIAS_SHOP_ID%></th>	
					<td>
						${fmShopUser.shopId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmShopUser.ALIAS_USER_ID%></th>	
					<td>
						${fmShopUser.userId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>