<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmUserController/edit',
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
				<input type="hidden" name="id" value = "${fmUser.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmUser.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmUser.FORMAT_ADDTIME%>'})"   maxlength="0" value="${fmUser.addtime}"/>
					</td>							
					<th><%=TfmUser.ALIAS_UPDATETIME%></th>	
					<td>
					<input class="span2" name="updatetime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmUser.FORMAT_UPDATETIME%>'})"   maxlength="0" value="${fmUser.updatetime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_ISDELETED%></th>	
					<td>
											<input class="span2" name="isdeleted" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fmUser.isdeleted}"/>
					</td>							
					<th><%=TfmUser.ALIAS_ACCOUNT%></th>	
					<td>
											<input class="span2" name="account" type="text" value="${fmUser.account}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_NICK_NAME%></th>	
					<td>
											<input class="span2" name="nickName" type="text" value="${fmUser.nickName}"/>
					</td>							
					<th><%=TfmUser.ALIAS_LOCAL_AREA%></th>	
					<td>
											<input class="span2" name="localArea" type="text" value="${fmUser.localArea}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_ICON%></th>	
					<td>
											<input class="span2" name="icon" type="text" value="${fmUser.icon}"/>
					</td>							
					<th><%=TfmUser.ALIAS_PHONE%></th>	
					<td>
											<input class="span2" name="phone" type="text" value="${fmUser.phone}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_REAL_NAME%></th>	
					<td>
											<input class="span2" name="realName" type="text" value="${fmUser.realName}"/>
					</td>							
					<th><%=TfmUser.ALIAS_CARD_ID%></th>	
					<td>
											<input class="span2" name="cardId" type="text" value="${fmUser.cardId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_USER_ROLE%></th>	
					<td>
											<jb:select dataType="UR" name="userRole" value="${fmUser.userRole}"></jb:select>	
					</td>							
					<th><%=TfmUser.ALIAS_HX_PASSWORD%></th>	
					<td>
											<input class="span2" name="hxPassword" type="text" value="${fmUser.hxPassword}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_HX_STATUS%></th>	
					<td>
											<jb:select dataType="IS" name="hxStatus" value="${fmUser.hxStatus}"></jb:select>	
					</td>							
					<th><%=TfmUser.ALIAS_AUTH_STATUS%></th>	
					<td>
											<jb:select dataType="AU" name="authStatus" value="${fmUser.authStatus}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmUser.ALIAS_STATUS%></th>	
					<td>
											<jb:select dataType="AS" name="status" value="${fmUser.status}"></jb:select>	
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>