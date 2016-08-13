<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmFeedback" %>
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
					<th><%=TfmFeedback.ALIAS_ADDTIME%></th>	
					<td>
						${fmFeedback.addtime}							
					</td>							
					<th><%=TfmFeedback.ALIAS_UPDATETIME%></th>	
					<td>
						${fmFeedback.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmFeedback.ALIAS_ISDELETED%></th>	
					<td>
						${fmFeedback.isdeleted}							
					</td>							
					<th><%=TfmFeedback.ALIAS_CONTENT%></th>	
					<td>
						${fmFeedback.content}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmFeedback.ALIAS_USER_ID%></th>	
					<td>
						${fmFeedback.userId}							
					</td>							
					<th><%=TfmFeedback.ALIAS_STATUS%></th>	
					<td>
						${fmFeedback.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmFeedback.ALIAS_REPLY%></th>	
					<td>
						${fmFeedback.reply}							
					</td>							
				</tr>		
		</table>
	</div>
</div>