<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveAccount"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveAccountController/edit',
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
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input type="hidden" name="id" value="${diveAccount.id}" />
			<table class="table table-hover table-condensed">
				<tr>
					<th><%=TdiveAccount.ALIAS_USER_NAME%></th>
					<td><input class="span2" name="userName" type="text"
						class="span2" value="${diveAccount.userName}" /></td>
					<th><%=TdiveAccount.ALIAS_PASSWORD%></th>
					<td><input class="span2" name="password" type="text"
						class="span2" value="${diveAccount.password}" /></td>
				</tr>
				<tr>
					<th><%=TdiveAccount.ALIAS_ICON%></th>
					<td><input class="span2" name="icon" type="text" class="span2"
						value="${diveAccount.icon}" /></td>
					<th><%=TdiveAccount.ALIAS_NICKNAME%></th>
					<td><input class="span2" name="nickname" type="text"
						class="span2" value="${diveAccount.nickname}" /></td>
				</tr>
				<tr>
					<th><%=TdiveAccount.ALIAS_SEX%></th>
					<td><input class="span2" name="sex" type="text" class="span2"
						value="${diveAccount.sex}" /></td>
					<th><%=TdiveAccount.ALIAS_CITY%></th>
					<td><input class="span2" name="city" type="text" class="span2"
						value="${diveAccount.city}" /></td>
				</tr>
				<tr>
					<th><%=TdiveAccount.ALIAS_PERSONALITY%></th>
					<td><input class="span2" name="personality" type="text"
						class="span2" value="${diveAccount.personality}" /></td>
					<th><%=TdiveAccount.ALIAS_EMAIL%></th>
					<td><input class="span2" name="email" type="text"
						class="span2" value="${diveAccount.email}" /></td>
				</tr>
				<tr>
					<th><%=TdiveAccount.ALIAS_ADDTIME%></th>
					<td><input class="span2" name="addtime" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveAccount.FORMAT_ADDTIME%>'})"
						maxlength="0" value="${diveAccount.addtime}" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>