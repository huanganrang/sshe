<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveLogDetail" %>
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
					<th><%=TdiveLogDetail.ALIAS_LOG_ID%></th>	
					<td>
						${diveLogDetail.logId}							
					</td>							
					<th><%=TdiveLogDetail.ALIAS_SUMARY%></th>	
					<td>
						${diveLogDetail.sumary}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveLogDetail.ALIAS_FILE_SRC%></th>	
					<td>
						${diveLogDetail.fileSrc}							
					</td>							
					<th><%=TdiveLogDetail.ALIAS_LG_X%></th>	
					<td>
						${diveLogDetail.lgX}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveLogDetail.ALIAS_LG_Y%></th>	
					<td>
						${diveLogDetail.lgY}							
					</td>							
					<th><%=TdiveLogDetail.ALIAS_ADDTIME%></th>	
					<td>
						${diveLogDetail.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>