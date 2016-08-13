<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmAuthApply" %>
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
					<th><%=TfmAuthApply.ALIAS_ADDTIME%></th>	
					<td>
						${fmAuthApply.addtime}							
					</td>							
					<th><%=TfmAuthApply.ALIAS_UPDATETIME%></th>	
					<td>
						${fmAuthApply.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAuthApply.ALIAS_ISDELETED%></th>	
					<td>
						${fmAuthApply.isdeleted}							
					</td>							
					<th><%=TfmAuthApply.ALIAS_USER_ID%></th>	
					<td>
						${fmAuthApply.userId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAuthApply.ALIAS_TYPE%></th>	
					<td>
						${fmAuthApply.type}							
					</td>							
					<th><%=TfmAuthApply.ALIAS_USER_NAME%></th>	
					<td>
						${fmAuthApply.userName}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAuthApply.ALIAS_PHONE%></th>	
					<td>
						${fmAuthApply.phone}							
					</td>							
					<th><%=TfmAuthApply.ALIAS_COMPANY_TYPE%></th>	
					<td>
						${fmAuthApply.companyType}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAuthApply.ALIAS_IMAGES%></th>	
					<td>
						${fmAuthApply.images}							
					</td>							
					<th><%=TfmAuthApply.ALIAS_USER_CARD%></th>	
					<td>
						${fmAuthApply.userCard}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmAuthApply.ALIAS_AUTH_REMARK%></th>	
					<td>
						${fmAuthApply.authRemark}							
					</td>							
					<th><%=TfmAuthApply.ALIAS_STATUS%></th>	
					<td>
						${fmAuthApply.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>