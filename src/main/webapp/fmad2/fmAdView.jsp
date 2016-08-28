<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmAd" %>
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
					<th><%=TfmAd.ALIAS_ADDTIME%></th>	
					<td>
						${fmAd.addtime}							
					</td>							
					<th><%=TfmAd.ALIAS_UPDATETIME%></th>	
					<td>
						${fmAd.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAd.ALIAS_ISDELETED%></th>	
					<td>
						${fmAd.isdeleted}							
					</td>							
					<th><%=TfmAd.ALIAS_GOODS_NAME%></th>	
					<td>
						${fmAd.goodsName}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAd.ALIAS_URL%></th>	
					<td>
						${fmAd.url}							
					</td>							
					<th><%=TfmAd.ALIAS_LOCAL%></th>	
					<td>
						${fmAd.local}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAd.ALIAS_STATUS%></th>	
					<td>
						${fmAd.status}							
					</td>							
					<th><%=TfmAd.ALIAS_CHANNEL%></th>	
					<td>
						${fmAd.channel}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAd.ALIAS_TYPE%></th>	
					<td>
						${fmAd.type}							
					</td>							
					<th><%=TfmAd.ALIAS_GOODS_ID%></th>	
					<td>
						${fmAd.goodsId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAd.ALIAS_LOGIN_ID%></th>	
					<td>
						${fmAd.loginId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>