<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmPropertiesController/edit',
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
				<input type="hidden" name="id" value = "${fmProperties.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmProperties.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmProperties.FORMAT_ADDTIME%>'})"   maxlength="0" value="${fmProperties.addtime}"/>
					</td>							
					<th><%=TfmProperties.ALIAS_UPDATETIME%></th>	
					<td>
					<input class="span2" name="updatetime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmProperties.FORMAT_UPDATETIME%>'})"   maxlength="0" value="${fmProperties.updatetime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmProperties.ALIAS_ISDELETED%></th>	
					<td>
											<input class="span2" name="isdeleted" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fmProperties.isdeleted}"/>
					</td>							
					<th><%=TfmProperties.ALIAS_NAME%></th>	
					<td>
											<input class="span2" name="name" type="text" value="${fmProperties.name}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmProperties.ALIAS_GOOD_NAME%></th>	
					<td>
											<jb:select dataType="GN" name="goodName" value="${fmProperties.goodName}"></jb:select>	
					</td>							
					<th><%=TfmProperties.ALIAS_DESCRIPTION%></th>	
					<td>
											<input class="span2" name="description" type="text" value="${fmProperties.description}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmProperties.ALIAS_FIELD_TYPE%></th>	
					<td>
											<jb:select dataType="FT" name="fieldType" value="${fmProperties.fieldType}"></jb:select>	
					</td>							
					<th><%=TfmProperties.ALIAS_REQUIRE%></th>	
					<td>
											<input class="span2" name="require" type="text" value="${fmProperties.require}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmProperties.ALIAS_DEFAULT_VALUE%></th>	
					<td>
											<input class="span2" name="defaultValue" type="text" value="${fmProperties.defaultValue}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>