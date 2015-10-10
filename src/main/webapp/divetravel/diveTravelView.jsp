<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveTravel"%>
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
				<th>主键ID</th>
				<td colspan="3">${diveTravel.id}</td>
			</tr>
			<tr>
				<th width="10%"><%=TdiveTravel.ALIAS_NAME%></th>
				<td width="40%">${diveTravel.name}</td>
				<th width="10%"><%=TdiveTravel.ALIAS_NAME_EN%></th>
				<td width="40%">${diveTravel.nameEn}</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_COUNTRY_NAME%></th>
				<td>${diveTravel.countryName}</td>
				<th><%=TdiveTravel.ALIAS_COUNTRY_NAME_EN%></th>
				<td>${diveTravel.countryNameEn}</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_AREA_NAME%></th>
				<td>${diveTravel.areaName}</td>
				<th><%=TdiveTravel.ALIAS_AREA_NAME_EN%></th>
				<td>${diveTravel.areaNameEn}</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_PRICE%></th>
				<td>${diveTravel.price}</td>
				<!-- <th><%=TdiveTravel.ALIAS_FEATURE%></th>
				<td>${diveTravel.featureZh}</td> -->
				<th><%=TdiveTravel.ALIAS_STATUS%></th>
				<td>${diveTravel.statusZh}</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_ADDTIME%></th>
				<td colspan="3">${diveTravel.addtime}</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_ICON%></th>
				<td colspan="3"><img alt="" src="${diveTravel.icon}"> 	</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_SUMARY%></th>
				<td colspan="3">${diveTravel.sumary}</td>
			</tr>
			<tr>
				<th><%=TdiveTravel.ALIAS_DESCRIPTION%></th>
				<td colspan="3">${diveTravel.description}</td>
			</tr>
		</table>
	</div>
</div>