<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmUser" %>
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
					<th><%=TfmUser.ALIAS_ADDTIME%></th>	
					<td>
						${fmUser.addtime}							
					</td>							
					<th><%=TfmUser.ALIAS_UPDATETIME%></th>	
					<td>
						${fmUser.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUser.ALIAS_ISDELETED%></th>	
					<td>
						${fmUser.isdeleted}							
					</td>							
					<th><%=TfmUser.ALIAS_ACCOUNT%></th>	
					<td>
						${fmUser.account}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUser.ALIAS_NICK_NAME%></th>	
					<td>
						${fmUser.nickName}							
					</td>							
					<th><%=TfmUser.ALIAS_LOCAL_AREA%></th>	
					<td>
						${fmUser.localArea}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUser.ALIAS_ICON%></th>	
					<td>
						${fmUser.icon}							
					</td>							
					<th><%=TfmUser.ALIAS_PHONE%></th>	
					<td>
						${fmUser.phone}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUser.ALIAS_REAL_NAME%></th>	
					<td>
						${fmUser.realName}							
					</td>							
					<th><%=TfmUser.ALIAS_CARD_ID%></th>	
					<td>
						${fmUser.cardId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUser.ALIAS_USER_ROLE%></th>	
					<td>
						${fmUser.userRole}							
					</td>							
					<th><%=TfmUser.ALIAS_HX_PASSWORD%></th>	
					<td>
						${fmUser.hxPassword}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUser.ALIAS_HX_STATUS%></th>	
					<td>
						${fmUser.hxStatus}							
					</td>							
				</tr>		
		</table>
	</div>
</div>