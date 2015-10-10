<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveLog"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveLogController/edit',
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
			<input type="hidden" name="id" value="${diveLog.id}" />
			<table class="table table-hover table-condensed">
				<tr>
					<th><%=TdiveLog.ALIAS_LOG_TYPE%></th>
					<td><input class="span2" name="logType" type="text"
						class="span2" value="${diveLog.logType}" /></td>
					<th><%=TdiveLog.ALIAS_FILE_SRC%></th>
					<td><input class="span2" name="fileSrc" type="text"
						class="span2" value="${diveLog.fileSrc}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_ACCOUNT_ID%></th>
					<td><input class="span2" name="accountId" type="text"
						class="span2" value="${diveLog.accountId}" /></td>
					<th><%=TdiveLog.ALIAS_DIVE_TYPE%></th>
					<td><input class="span2" name="diveType" type="text"
						class="span2" value="${diveLog.diveType}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_DIVE_DATE%></th>
					<td><input class="span2" name="diveDate" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveLog.FORMAT_DIVE_DATE%>'})"
						maxlength="0" value="${diveLog.diveDate}" /></td>
					<th><%=TdiveLog.ALIAS_WEATHER%></th>
					<td><input class="span2" name="weather" type="text"
						class="span2" value="${diveLog.weather}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_WATER_TEMPERATURE%></th>
					<td><input class="span2" name="waterTemperature" type="text"
						class="span2" value="${diveLog.waterTemperature}" /></td>
					<th><%=TdiveLog.ALIAS_SEEING%></th>
					<td><input class="span2" name="seeing" type="text"
						class="span2" value="${diveLog.seeing}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_IN_TIME%></th>
					<td><input class="span2" name="inTime" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveLog.FORMAT_IN_TIME%>'})"
						maxlength="0" value="${diveLog.inTime}" /></td>
					<th><%=TdiveLog.ALIAS_OUT_TIME%></th>
					<td><input class="span2" name="outTime" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveLog.FORMAT_OUT_TIME%>'})"
						maxlength="0" value="${diveLog.outTime}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_DIVE_HEIGHT%></th>
					<td><input class="span2" name="diveHeight" type="text"
						class="span2" value="${diveLog.diveHeight}" /></td>
					<th><%=TdiveLog.ALIAS_DIVE_WEITH%></th>
					<td><input class="span2" name="diveWeith" type="text"
						class="span2" value="${diveLog.diveWeith}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_WEATHER_TEMPERATURE%></th>
					<td><input class="span2" name="weatherTemperature" type="text"
						class="span2" value="${diveLog.weatherTemperature}" /></td>
					<th><%=TdiveLog.ALIAS_WIND_POWER%></th>
					<td><input class="span2" name="windPower" type="text"
						class="span2" value="${diveLog.windPower}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_GAS_START%></th>
					<td><input class="span2" name="gasStart" type="text"
						class="span2" value="${diveLog.gasStart}" /></td>
					<th><%=TdiveLog.ALIAS_GAS_END%></th>
					<td><input class="span2" name="gasEnd" type="text"
						class="span2" value="${diveLog.gasEnd}" /></td>
				</tr>
				<tr>
					<th><%=TdiveLog.ALIAS_ADDTIME%></th>
					<td><input class="span2" name="addtime" type="text"
						onclick="WdatePicker({dateFmt:'<%=TdiveLog.FORMAT_ADDTIME%>'})"
						maxlength="0" value="${diveLog.addtime}" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>