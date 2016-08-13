<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmAuthApply" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmAuthApplyController/edit',
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
				<input type="hidden" name="id" value = "${fmAuthApply.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmAuthApply.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmAuthApply.FORMAT_ADDTIME%>'})"   maxlength="0" value="${fmAuthApply.addtime}"/>
					</td>							
					<th><%=TfmAuthApply.ALIAS_UPDATETIME%></th>	
					<td>
					<input class="span2" name="updatetime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmAuthApply.FORMAT_UPDATETIME%>'})"   maxlength="0" value="${fmAuthApply.updatetime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAuthApply.ALIAS_ISDELETED%></th>	
					<td>
											<input class="span2" name="isdeleted" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fmAuthApply.isdeleted}"/>
					</td>							
					<th><%=TfmAuthApply.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" value="${fmAuthApply.userId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAuthApply.ALIAS_TYPE%></th>	
					<td>
											<jb:select dataType="AT" name="type" value="${fmAuthApply.type}"></jb:select>	
					</td>							
					<th><%=TfmAuthApply.ALIAS_USER_NAME%></th>	
					<td>
											<input class="span2" name="userName" type="text" value="${fmAuthApply.userName}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAuthApply.ALIAS_PHONE%></th>	
					<td>
											<input class="span2" name="phone" type="text" value="${fmAuthApply.phone}"/>
					</td>							
					<th><%=TfmAuthApply.ALIAS_COMPANY_TYPE%></th>	
					<td>
											<jb:select dataType="CT" name="companyType" value="${fmAuthApply.companyType}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAuthApply.ALIAS_IMAGES%></th>	
					<td>
											<input class="span2" name="images" type="text" value="${fmAuthApply.images}"/>
					</td>							
					<th><%=TfmAuthApply.ALIAS_USER_CARD%></th>	
					<td>
											<input class="span2" name="userCard" type="text" value="${fmAuthApply.userCard}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAuthApply.ALIAS_AUTH_REMARK%></th>	
					<td>
											<input class="span2" name="authRemark" type="text" value="${fmAuthApply.authRemark}"/>
					</td>							
					<th><%=TfmAuthApply.ALIAS_STATUS%></th>	
					<td>
											<jb:select dataType="AU" name="status" value="${fmAuthApply.status}"></jb:select>	
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>