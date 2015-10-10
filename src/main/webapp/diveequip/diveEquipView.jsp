<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveEquip" %>
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
				<th width="8%"><%=TdiveEquip.ALIAS_EQUIP_NAME%></th>	
				<td width="42%">
					${diveEquip.equipName}							
				</td>
				<th width="8%"><%=TdiveEquip.ALIAS_PRICE%></th>	
				<td width="42%">
					${diveEquip.price}							
				</td>
			</tr>
			<tr>
				<th><%=TdiveEquip.ALIAS_EQUIP_TYPE%></th>	
				<td>
					${diveEquip.equipTypeZh}							
				</td>	
				<th><%=TdiveEquip.ALIAS_EQUIP_BRAND%></th>	
				<td>
					${diveEquip.equipBrandZh}							
				</td>
			</tr>
			<tr>
				<th><%=TdiveEquip.ALIAS_SALE_NUM%></th>	
				<td>
					${diveEquip.saleNum}							
				</td>
				<th><%=TdiveEquip.ALIAS_HOT%></th>	
				<td>
					${diveEquip.hot}							
				</td>
			</tr>
			<tr>
				<th><%=TdiveEquip.ALIAS_STATUS%></th>	
				<td>
					${diveEquip.statusZh}							
				</td>
				<th><%=TdiveEquip.ALIAS_ADDTIME%></th>	
				<td>
					${diveEquip.addtime}							
				</td>
			</tr>
			<tr>
				<th><%=TdiveEquip.ALIAS_EQUIP_ICON%></th>	
				<td colspan="3">
					<img alt="" src="${diveEquip.equipIcon}"> 							
				</td>
			</tr>
			<tr>	
				<th><%=TdiveEquip.ALIAS_EQUIP_SUMARY%></th>	
				<td colspan="3">
					${diveEquip.equipSumary}							
				</td>						
			</tr>		
			<tr>	
				<th><%=TdiveEquip.ALIAS_EQUIP_DES%></th>	
				<td colspan="3">
					${diveEquip.equipDes}							
				</td>							
			</tr>		
		</table>
	</div>
</div>