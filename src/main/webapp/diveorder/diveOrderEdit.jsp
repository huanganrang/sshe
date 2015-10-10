<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveOrder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveOrderController/edit',
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
				<input type="hidden" name="id" value = "${diveOrder.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TdiveOrder.ALIAS_ACCOUNT_ID%></th>	
					<td>
											<input class="span2" name="accountId" type="text" value="${diveOrder.accountId}"/>
					</td>							
					<th><%=TdiveOrder.ALIAS_ADDRESS%></th>	
					<td>
											<input class="span2" name="address" type="text" value="${diveOrder.address}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_EXPRESS_NAME%></th>	
					<td>
											<input class="span2" name="expressName" type="text" value="${diveOrder.expressName}"/>
					</td>							
					<th><%=TdiveOrder.ALIAS_EXPRESS_NO%></th>	
					<td>
											<input class="span2" name="expressNo" type="text" value="${diveOrder.expressNo}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_PAY_WAY%></th>	
					<td>
											<input class="span2" name="payWay" type="text" value="${diveOrder.payWay}"/>
					</td>							
					<th><%=TdiveOrder.ALIAS_REMARK%></th>	
					<td>
											<input class="span2" name="remark" type="text" value="${diveOrder.remark}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_PAY_STATUS%></th>	
					<td>
											<jb:select dataType="PS" name="payStatus" value="${diveOrder.payStatus}"></jb:select>	
					</td>							
					<th><%=TdiveOrder.ALIAS_ORDER_STATUS%></th>	
					<td>
											<jb:select dataType="OS" name="orderStatus" value="${diveOrder.orderStatus}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_SEND_STATUS%></th>	
					<td>
											<jb:select dataType="SS" name="sendStatus" value="${diveOrder.sendStatus}"></jb:select>	
					</td>							
					<th><%=TdiveOrder.ALIAS_PAYTIME%></th>	
					<td>
					<input class="span2" name="paytime" type="text" onclick="WdatePicker({dateFmt:'<%=TdiveOrder.FORMAT_PAYTIME%>'})"   maxlength="0" value="${diveOrder.paytime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TdiveOrder.FORMAT_ADDTIME%>'})"   maxlength="0" value="${diveOrder.addtime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>