<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivity" %>
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
					<th><%=TdiveActivity.ALIAS_NAME%></th>	
					<td>
						${diveActivity.name}							
					</td>							
					<th><%=TdiveActivity.ALIAS_START_DATE%></th>	
					<td>
						${diveActivity.startDate}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveActivity.ALIAS_END_DATE%></th>	
					<td>
						${diveActivity.endDate}							
					</td>							
					<th><%=TdiveActivity.ALIAS_START_ADDR%></th>	
					<td>
						${diveActivity.startAddr}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveActivity.ALIAS_ADDR_ID%></th>	
					<td>
						${diveActivity.addrId}							
					</td>							
					<th><%=TdiveActivity.ALIAS_END_ADDR%></th>	
					<td>
						${diveActivity.endAddr}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveActivity.ALIAS_DESCRIPTION%></th>	
					<td>
						${diveActivity.description}							
					</td>							
					<th><%=TdiveActivity.ALIAS_ADDTIME%></th>	
					<td>
						${diveActivity.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>