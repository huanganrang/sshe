<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivityComment"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveActivityCommentController/edit',
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
			<input type="hidden" name="id" value="${diveActivityComment.id}" />
			<table class="table table-hover table-condensed">
				<tr>
					<th><%=TdiveActivityComment.ALIAS_ACTIVITY_ID%></th>
					<td><input class="span2" name="activityId" type="text"
						class="span2" value="${diveActivityComment.activityId}" /></td>
					<th><%=TdiveActivityComment.ALIAS_COMMENT%></th>
					<td><input class="span2" name="comment" type="text"
						class="span2" value="${diveActivityComment.comment}" /></td>
				</tr>
				<tr>
					<th><%=TdiveActivityComment.ALIAS_PID%></th>
					<td><input class="span2" name="pid" type="text" class="span2"
						value="${diveActivityComment.pid}" /></td>
					<th><%=TdiveActivityComment.ALIAS_USER_ID%></th>
					<td><input class="span2" name="userId" type="text"
						class="span2" value="${diveActivityComment.userId}" /></td>
				</tr>
				<tr>
					<th><%=TdiveActivityComment.ALIAS_ADDTIME%></th>
					<td><input class="span2" name="addtime" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveActivityComment.FORMAT_ADDTIME%>'})"
						maxlength="0" value="${diveActivityComment.addtime}" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>