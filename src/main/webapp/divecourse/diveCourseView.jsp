<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCourse"%>
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
				<th width="8%"><%=TdiveCourse.ALIAS_TITLE%></th>
				<td width="42%">${diveCourse.title}</td>
				<th width="8%"><%=TdiveCourse.ALIAS_PRICE%></th>
				<td width="42%">${diveCourse.price}</td>
			</tr>
			<tr>
				<th><%=TdiveCourse.ALIAS_COURSE_TYPE%></th>
				<td>${diveCourse.courseTypeZh}</td>
				<th><%=TdiveCourse.ALIAS_STATUS%></th>
				<td>${diveCourse.statusZh}</td>
			</tr>
			<tr>
				<th><%=TdiveCourse.ALIAS_FILE_PATH%></th>
				<td><a href="${pageContext.request.contextPath}/fileController/download?filePath=${diveCourse.filePath}">点击下载</a> </td>
				<th><%=TdiveCourse.ALIAS_ADDTIME%></th>
				<td>${diveCourse.addtime}</td>
			</tr>
			<tr>
				<th><%=TdiveCourse.ALIAS_ICON%></th>
				<td colspan="3"><img alt="" src="${diveCourse.icon}"> </td>
			</tr>
			<tr>
				<th><%=TdiveCourse.ALIAS_CONTENT%></th>
				<td colspan="3">${diveCourse.content}</td>
			</tr>
			<tr>
				<th><%=TdiveCourse.ALIAS_INTRODUCE%></th>
				<td colspan="3">${diveCourse.introduce}</td>
			</tr>
			
		</table>
	</div>
</div>