<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCertificateAuthority"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveCertificateAuthorityController/edit',
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
		style="overflow: auto;">
		<form id="form" method="post">
			<input type="hidden" name="id" value="${diveCertificateAuthority.id}" />
			<table class="table table-hover table-condensed">
				<tr>
					<th width="15%"><%=TdiveCertificateAuthority.ALIAS_STATUS%></th>
					<td>
						<jb:select dataType="AS" name="status" value="${diveCertificateAuthority.status}"></jb:select>		
					</td>
				</tr>
				<tr>
					<th><%=TdiveCertificateAuthority.ALIAS_RIGHT_SIDE%></th>
					<td><img alt="" src="${diveCertificateAuthority.rightSide}"> </td>
				</tr>
				<tr>
					<th><%=TdiveCertificateAuthority.ALIAS_REVERSE_SIDE%></th>
					<td><img alt="" src="${diveCertificateAuthority.reverseSide}"> </td>
				</tr>
				<!-- <tr>
					<th><%=TdiveCertificateAuthority.ALIAS_ORG_CODE%></th>
					<td><input class="span2" name="orgCode" type="text"
						class="span2" value="${diveCertificateAuthority.orgCode}" /></td>
					<th><%=TdiveCertificateAuthority.ALIAS_LEVEL_CODE%></th>
					<td><input class="span2" name="levelCode" type="text"
						class="span2" value="${diveCertificateAuthority.levelCode}" /></td>
				</tr>
				<tr>
					<th><%=TdiveCertificateAuthority.ALIAS_AUTH_DATE%></th>
					<td><input class="span2" name="authDate" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveCertificateAuthority.FORMAT_AUTH_DATE%>'})"
						maxlength="0" value="${diveCertificateAuthority.authDate}" /></td>
					<th><%=TdiveCertificateAuthority.ALIAS_REVERSE_SIDE%></th>
					<td><input class="span2" name="reverseSide" type="text"
						class="span2" value="${diveCertificateAuthority.reverseSide}" /></td>
				</tr>
				<tr>
					<th><%=TdiveCertificateAuthority.ALIAS_RIGHT_SIDE%></th>
					<td><input class="span2" name="rightSide" type="text"
						class="span2" value="${diveCertificateAuthority.rightSide}" /></td>
					<th><%=TdiveCertificateAuthority.ALIAS_AUDIT_DATE%></th>
					<td><input class="span2" name="auditDate" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveCertificateAuthority.FORMAT_AUDIT_DATE%>'})"
						maxlength="0" value="${diveCertificateAuthority.auditDate}" /></td>
				</tr>
				<tr>
					<th><%=TdiveCertificateAuthority.ALIAS_STATUS%></th>
					<td><input class="span2" name="status" type="text"
						class="span2" value="${diveCertificateAuthority.status}" /></td>
					<th><%=TdiveCertificateAuthority.ALIAS_ADDTIME%></th>
					<td><input class="span2" name="addtime" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveCertificateAuthority.FORMAT_ADDTIME%>'})"
						maxlength="0" value="${diveCertificateAuthority.addtime}" /></td>
				</tr> -->
			</table>
		</form>
	</div>
</div>