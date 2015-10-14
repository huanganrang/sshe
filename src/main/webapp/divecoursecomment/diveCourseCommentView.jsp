<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCourseComment" %>
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
					<th><%=TdiveCourseComment.ALIAS_COURSE_ID%></th>	
					<td>
						${diveCourseComment.courseId}							
					</td>							
					<th><%=TdiveCourseComment.ALIAS_COMMENT%></th>	
					<td>
						${diveCourseComment.comment}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveCourseComment.ALIAS_PID%></th>	
					<td>
						${diveCourseComment.pid}							
					</td>							
					<th><%=TdiveCourseComment.ALIAS_USER_ID%></th>	
					<td>
						${diveCourseComment.userId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveCourseComment.ALIAS_ADDTIME%></th>	
					<td>
						${diveCourseComment.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>