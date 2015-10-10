<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveActivityController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				var businessId;
				if($("input:radio[name=businessType]:checked").val() == 'BT01') {
					businessId = $('[name=travelId]').val();
				} else {
					businessId = $('[name=storeId]').val();
				}
				$("#businessId").val(businessId);
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
		
		$("input:radio[name=businessType]").bind('click', function(){
			if($(this).val() == 'BT01') {
				$("#travel").show();
				$("#store").hide();
			} else {
				$("#travel").hide();
				$("#store").show();
			}
		});
		
		$('[name=travelId]').val($("#businessId").val());
		$('[name=storeId]').val($("#businessId").val());
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">
		<form id="form" method="post">
				<input type="hidden" name="id" value = "${diveActivity.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th width="10%"><%=TdiveActivity.ALIAS_NAME%></th>	
					<td width="40%">
					<input class="span2" name="name" type="text" value="${diveActivity.name}"/>
					</td>
					<th width="10%"><%=TdiveActivity.ALIAS_ROOM_TYPE%></th>	
					<td width="40%">
						<jb:select dataType="RT" name="roomType" value="${diveActivity.roomType}"></jb:select>	
					</td>		
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_BUSINESS_TYPE%></th>	
					<td>
						<input type="radio" name="businessType" value="BT01" <c:if test="${diveActivity.businessType=='BT01'}">checked</c:if>/> 船宿
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="businessType" value="BT05" <c:if test="${diveActivity.businessType=='BT05'}">checked</c:if>/> 度假村
					</td>	
					<th>选择业务</th>	
					<td>
						<div id="travel" <c:if test="${diveActivity.businessType=='BT05'}">style="display: none;"</c:if>>
							<jb:selectSql dataType="SL003" name="travelId"></jb:selectSql>
						</div>
						<div id="store" <c:if test="${diveActivity.businessType=='BT01'}">style="display: none;"</c:if>>
							<jb:selectSql dataType="SL004" name="storeId"></jb:selectSql>
						</div>
						<input type="hidden" name="businessId" id="businessId"  value ="${diveActivity.businessId}"/>
					</td>	
				</tr>
				<tr>	
					<th><%=TdiveActivity.ALIAS_DIVER_PRICE%></th>	
					<td>
						<input class="span2" name="diverPrice" type="text" value="${diveActivity.diverPrice}"/>
					</td>	
					<th><%=TdiveActivity.ALIAS_NON_DRIVE_PRICE%></th>	
					<td>
						<input class="span2" name="nonDrivePrice" type="text" value="${diveActivity.nonDrivePrice}"/>
					</td>													
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_SINGLE_ROOM_PRICE%></th>	
					<td>
						<input class="span2" name="singleRoomPrice" type="text" value="${diveActivity.singleRoomPrice}"/>
					</td>	
					<th><%=TdiveActivity.ALIAS_PEER_NAME%></th>	
					<td>
						<input class="span2" name="peerName" type="text" value="${diveActivity.peerName}"/>
					</td>													
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_START_DATE%></th>	
					<td>
					<input class="span2" name="startDateStr" type="text" onclick="WdatePicker({dateFmt:'<%=TdiveActivity.FORMAT_START_DATE%>'})"  
							maxlength="0" class="" value="<fmt:formatDate value="${diveActivity.startDate}" pattern="yyyy-MM-dd"/>"/>
					</td>	
					<th><%=TdiveActivity.ALIAS_END_DATE%></th>	
					<td>
					<input class="span2" name="endDateStr" type="text" onclick="WdatePicker({dateFmt:'<%=TdiveActivity.FORMAT_END_DATE%>'})"  
							maxlength="0" class=""  value="<fmt:formatDate value="${diveActivity.endDate}" pattern="yyyy-MM-dd"/>"/>
					</td>							
											
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_START_ADDR%></th>	
					<td colspan="3">
						<input class="span2" name="startAddr" type="text" style="width: 500px;" value="${diveActivity.startAddr}"/>
					</td>	
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_END_ADDR%></th>	
					<td colspan="3">
						<input class="span2" name="endAddr" type="text"  style="width: 500px;" value="${diveActivity.endAddr}"/>
					</td>								
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_DESCRIPTION%></th>	
					<td colspan="3">
						<textarea style="width: 500px;" name="description">${diveActivity.description}</textarea>
					</td>							
				</tr>
			</table>				
		</form>
	</div>
</div>