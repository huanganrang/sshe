<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveLog"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
			<tr>
				<th><%=TdiveLog.ALIAS_ACCOUNT_ID%></th>
				<td colspan="3">${diveLog.accountId}</td>
			</tr>
			<tr>
				<th width="8%"><%=TdiveLog.ALIAS_LOG_TYPE%></th>
				<td width="42%">${diveLog.logTypeZh}</td>
				<th width="8%"><%=TdiveLog.ALIAS_DIVE_TYPE%></th>
				<td width="42%">${diveLog.diveTypeZh}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_DIVE_DATE%></th>
				<td>${diveLog.diveDate}</td>
				<th><%=TdiveLog.ALIAS_DIVE_ADDRESS%></th>
				<td>${diveLog.diveAddress}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_WEATHER%></th>
				<td>${diveLog.weatherZh}</td>
				<th><%=TdiveLog.ALIAS_WEATHER_TEMPERATURE%></th>
				<td>${diveLog.weatherTemperature}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_WATER_TEMPERATURE%></th>
				<td>${diveLog.waterTemperature}</td>
				<th><%=TdiveLog.ALIAS_SEEING%></th>
				<td>${diveLog.seeing}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_WIND_POWER%></th>
				<td>${diveLog.windPower}</td>
				<th><%=TdiveLog.ALIAS_DIVE_HEIGHT%></th>
				<td>${diveLog.diveHeight}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_IN_TIME%></th>
				<td>${diveLog.inTime}</td>
				<th><%=TdiveLog.ALIAS_OUT_TIME%></th>
				<td>${diveLog.outTime}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_GAS_START%></th>
				<td>${diveLog.gasStart}</td>
				<th><%=TdiveLog.ALIAS_GAS_END%></th>
				<td>${diveLog.gasEnd}</td>
			</tr>	
			<tr>
				<th><%=TdiveLog.ALIAS_HIGH_GAS%></th>
				<td>
					<c:choose>
						<c:when test="${diveLog.highGas == 1}">是</c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</td>
				<th><%=TdiveLog.ALIAS_ADDTIME%></th>
				<td>${diveLog.addtime}</td>
			</tr>
			<tr>
				<th><%=TdiveLog.ALIAS_FILE_SRC%></th>
				<td colspan="3">
					<img alt="" src="${diveLog.fileSrc}">
				</td>
			</tr>
		</table>
	</div>
</div>