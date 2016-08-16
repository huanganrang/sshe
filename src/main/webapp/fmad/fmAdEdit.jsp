<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmAd" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmAdController/edit',
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
				<input type="hidden" name="id" value = "${fmAd.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmAd.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmAd.FORMAT_ADDTIME%>'})"   maxlength="0" value="${fmAd.addtime}"/>
					</td>							
					<th><%=TfmAd.ALIAS_UPDATETIME%></th>	
					<td>
					<input class="span2" name="updatetime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmAd.FORMAT_UPDATETIME%>'})"   maxlength="0" value="${fmAd.updatetime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAd.ALIAS_ISDELETED%></th>	
					<td>
											<input class="span2" name="isdeleted" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fmAd.isdeleted}"/>
					</td>							
					<th><%=TfmAd.ALIAS_GOODS_NAME%></th>	
					<td>
											<jb:select dataType="GN" name="goodsName" value="${fmAd.goodsName}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAd.ALIAS_URL%></th>	
					<td>
											<input class="span2" name="url" type="text" value="${fmAd.url}"/>
					</td>							
					<th><%=TfmAd.ALIAS_LOCAL%></th>	
					<td>
											<input class="span2" name="local" type="text" value="${fmAd.local}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAd.ALIAS_STATUS%></th>	
					<td>
											<jb:select dataType="FS" name="status" value="${fmAd.status}"></jb:select>	
					</td>							
					<th><%=TfmAd.ALIAS_CHANNEL%></th>	
					<td>
											<input class="span2" name="channel" type="text" value="${fmAd.channel}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAd.ALIAS_TYPE%></th>	
					<td>
											<jb:select dataType="GS" name="type" value="${fmAd.type}"></jb:select>	
					</td>							
					<th><%=TfmAd.ALIAS_GOODS_ID%></th>	
					<td>
											<input class="span2" name="goodsId" type="text" value="${fmAd.goodsId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmAd.ALIAS_LOGIN_ID%></th>	
					<td>
											<input class="span2" name="loginId" type="text" value="${fmAd.loginId}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>