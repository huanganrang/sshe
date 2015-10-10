<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th width="10%"><%=TdiveActivity.ALIAS_NAME%></th>	
					<td width="40%">
						${diveActivity.name}							
					</td>
					<th width="10%"><%=TdiveActivity.ALIAS_ROOM_TYPE%></th>	
					<td width="40%">
						${diveActivity.roomTypeZh}							
					</td>							
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_BUSINESS_TYPE%></th>	
					<td>
						${diveActivity.businessTypeZh}	
					</td>	
					<th>业务ID</th>	
					<td>
						${diveActivity.businessId}	
					</td>	
				</tr>
				<tr>	
					<th><%=TdiveActivity.ALIAS_DIVER_PRICE%></th>	
					<td>
						${diveActivity.diverPrice}	
					</td>	
					<th><%=TdiveActivity.ALIAS_NON_DRIVE_PRICE%></th>	
					<td>
						${diveActivity.nonDrivePrice}	
					</td>													
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_SINGLE_ROOM_PRICE%></th>	
					<td>
						${diveActivity.singleRoomPrice}	
					</td>	
					<th><%=TdiveActivity.ALIAS_PEER_NAME%></th>	
					<td>
						${diveActivity.peerName}	
					</td>													
				</tr>		
				<tr>	
					<th><%=TdiveActivity.ALIAS_START_DATE%></th>	
					<td>
						<fmt:formatDate value="${diveActivity.startDate}" pattern="yyyy-MM-dd"/>	
					</td>		
					<th><%=TdiveActivity.ALIAS_END_DATE%></th>	
					<td>
						<fmt:formatDate value="${diveActivity.endDate}" pattern="yyyy-MM-dd"/>
					</td>						
				</tr>
				<tr>	
					<th><%=TdiveActivity.ALIAS_START_ADDR%></th>	
					<td colspan="3">
						${diveActivity.startAddr}							
					</td>					
				</tr>
				<tr>	
					<th><%=TdiveActivity.ALIAS_END_ADDR%></th>	
					<td colspan="3">
						${diveActivity.endAddr}							
					</td>					
				</tr>
				<tr>	
					<th><%=TdiveActivity.ALIAS_DESCRIPTION%></th>	
					<td colspan="3">
						${diveActivity.description}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveActivity.ALIAS_ADDTIME%></th>	
					<td colspan="3">
						<fmt:formatDate value="${diveActivity.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>						
					</td>							
				</tr>		
		</table>
	</div>
</div>