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
		$('.img-preview').each(function(){
			var $this = $(this);
			$this.css('height',$this.parent().attr('height'));
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
				<input type="hidden" name="id" value = "${fmAuthApply.id}"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th>昵称</th>
					<td>
						${fmAuthApply.fmUser.nickName}
					</td>

					<th>注册手机号</th>
					<td>
						${fmAuthApply.fmUser.account}
					</td>
				</tr>
				<tr>	
					<th>提交认证时间</th>
					<td>
						${fmAuthApply.addtime}
					</td>

					<th>认证处理时间</th>
					<td>
						${fmAuthApply.updatetime}
					</td>
				</tr>
				<tr>
					<th><%=TfmAuthApply.ALIAS_STATUS%></th>
					<td colspan="3">
						<input type="hidden" name="status" value = "${fmAuthApply.status}"/>
						${fmAuthApply.statusName}
					</td>
				</tr>

				<tr>	
					<th><%=TfmAuthApply.ALIAS_TYPE%></th>	
					<td>
						${fmAuthApply.typeName}
					</td>							

					<th>身份类型</th>
					<td>
						${fmAuthApply.fmUser.userRoleName}
					</td>
				</tr>

				<tr>
					<th><%=TfmAuthApply.ALIAS_USER_NAME%></th>
					<td>
						${fmAuthApply.userName}
					</td>
					<th><%=TfmAuthApply.ALIAS_USER_CARD%></th>	
					<td>
						${fmAuthApply.userCard}
					</td>							
				</tr>
				<tr>
					<th><%=TfmAuthApply.ALIAS_COMPANY_TYPE%></th>
					<td colspan="3">
						${fmAuthApply.companyTypeName}
					</td>

				</tr>
				<tr>

					<td colspan="4" height="155">
						<c:forEach items="${fmAuthApply.imageList}" var="image">
							<img class="img-preview" src="${image}" width="200" height="150"/>
						</c:forEach>
					</td>
				</tr>
				<tr>	
					<th><%=TfmAuthApply.ALIAS_AUTH_REMARK%></th>	
					<td colspan="3">
						<textarea class="span2" name="authRemark" style="width: 90%">${fmAuthApply.authRemark}</textarea>
					</td>							

				</tr>
			</table>				
		</form>
	</div>
</div>