<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmUserHobby" %>
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
					<th><%=TfmUserHobby.ALIAS_ADDTIME%></th>	
					<td>
						${fmUserHobby.addtime}							
					</td>							
					<th><%=TfmUserHobby.ALIAS_UPDATETIME%></th>	
					<td>
						${fmUserHobby.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUserHobby.ALIAS_ISDELETED%></th>	
					<td>
						${fmUserHobby.isdeleted}							
					</td>							
					<th><%=TfmUserHobby.ALIAS_USER_ID%></th>	
					<td>
						${fmUserHobby.userId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmUserHobby.ALIAS_GOOD_NAME%></th>	
					<td>
						${fmUserHobby.goodName}							
					</td>							
				</tr>		
		</table>
	</div>
</div>