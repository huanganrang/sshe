<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmGoodsUser" %>
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
					<th><%=TfmGoodsUser.ALIAS_ADDTIME%></th>	
					<td>
						${fmGoodsUser.addtime}							
					</td>							
					<th><%=TfmGoodsUser.ALIAS_UPDATETIME%></th>	
					<td>
						${fmGoodsUser.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoodsUser.ALIAS_ISDELETED%></th>	
					<td>
						${fmGoodsUser.isdeleted}							
					</td>							
					<th><%=TfmGoodsUser.ALIAS_GOODS_ID%></th>	
					<td>
						${fmGoodsUser.goodsId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoodsUser.ALIAS_USER_ID%></th>	
					<td>
						${fmGoodsUser.userId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>