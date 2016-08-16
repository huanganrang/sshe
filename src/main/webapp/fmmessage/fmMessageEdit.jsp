<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmMessageController/edit',
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
				<input type="hidden" name="id" value = "${fmMessage.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmMessage.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmMessage.FORMAT_ADDTIME%>'})"   maxlength="0" value="${fmMessage.addtime}"/>
					</td>							
					<th><%=TfmMessage.ALIAS_UPDATETIME%></th>	
					<td>
					<input class="span2" name="updatetime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmMessage.FORMAT_UPDATETIME%>'})"   maxlength="0" value="${fmMessage.updatetime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmMessage.ALIAS_ISDELETED%></th>	
					<td>
											<input class="span2" name="isdeleted" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fmMessage.isdeleted}"/>
					</td>							
					<th><%=TfmMessage.ALIAS_CONTENT%></th>	
					<td>
											<input class="span2" name="content" type="text" value="${fmMessage.content}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmMessage.ALIAS_TITLE%></th>	
					<td>
											<input class="span2" name="title" type="text" value="${fmMessage.title}"/>
					</td>							
					<th><%=TfmMessage.ALIAS_SUB_TITLE%></th>	
					<td>
											<input class="span2" name="subTitle" type="text" value="${fmMessage.subTitle}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmMessage.ALIAS_SEND_TYPE%></th>	
					<td>
											<jb:select dataType="ST" name="sendType" value="${fmMessage.sendType}"></jb:select>	
					</td>							
					<th><%=TfmMessage.ALIAS_URL%></th>	
					<td>
											<input class="span2" name="url" type="text" value="${fmMessage.url}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmMessage.ALIAS_SEND_TIME%></th>	
					<td>
					<input class="span2" name="sendTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmMessage.FORMAT_SEND_TIME%>'})"   maxlength="0" value="${fmMessage.sendTime}"/>
					</td>							
					<th><%=TfmMessage.ALIAS_LOGIN_ID%></th>	
					<td>
											<input class="span2" name="loginId" type="text" value="${fmMessage.loginId}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>