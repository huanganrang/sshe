<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TdiveRegion" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveRegionController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
				<input type="hidden" name="id" value = "${diveRegion.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TdiveRegion.ALIAS_REGION_LEVEL%></th>	
					<td>
											<input class="span2" name="regionLevel" type="text" value="${diveRegion.regionLevel}"/>
					</td>							
					<th><%=TdiveRegion.ALIAS_REGION_NAME_ZH%></th>	
					<td>
											<input class="span2" name="regionNameZh" type="text" value="${diveRegion.regionNameZh}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveRegion.ALIAS_REGION_NAME_EN%></th>	
					<td>
											<input class="span2" name="regionNameEn" type="text" value="${diveRegion.regionNameEn}"/>
					</td>							
					<th><%=TdiveRegion.ALIAS_REGION_PARENT_ID%></th>	
					<td>
											<input class="span2" name="regionParentId" type="text" value="${diveRegion.regionParentId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveRegion.ALIAS_REGION_ID%></th>	
					<td colspan="3">
											<input class="span2" name="regionId" type="text" value="${diveRegion.regionId}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>