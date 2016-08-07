<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmPurchase" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmPurchaseController/edit',
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
				<input type="hidden" name="id" value = "${fmPurchase.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfmPurchase.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_ADDTIME%>'})"   maxlength="0" value="${fmPurchase.addtime}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_UPDATETIME%></th>	
					<td>
					<input class="span2" name="updatetime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_UPDATETIME%>'})"   maxlength="0" value="${fmPurchase.updatetime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_ISDELETED%></th>	
					<td>
											<input class="span2" name="isdeleted" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fmPurchase.isdeleted}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_NAME%></th>	
					<td>
											<jb:select dataType="GN" name="name" value="${fmPurchase.name}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_START_PRICE%></th>	
					<td>
											<input class="span2" name="startPrice" type="text" value="${fmPurchase.startPrice}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_END_PRICE%></th>	
					<td>
											<input class="span2" name="endPrice" type="text" value="${fmPurchase.endPrice}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_UNIT%></th>	
					<td>
											<jb:select dataType="GU" name="unit" value="${fmPurchase.unit}"></jb:select>	
					</td>							
					<th><%=TfmPurchase.ALIAS_MIN_NUM%></th>	
					<td>
											<input class="span2" name="minNum" type="text" value="${fmPurchase.minNum}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_MAX_NUM%></th>	
					<td>
											<input class="span2" name="maxNum" type="text" value="${fmPurchase.maxNum}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_STATUS%></th>	
					<td>
											<jb:select dataType="GS" name="status" value="${fmPurchase.status}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_BORN_AREA%></th>	
					<td>
											<input class="span2" name="bornArea" type="text" value="${fmPurchase.bornArea}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_TRANSACTION_AREA%></th>	
					<td>
											<input class="span2" name="transactionArea" type="text" value="${fmPurchase.transactionArea}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_START_TIME%></th>	
					<td>
					<input class="span2" name="startTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_START_TIME%>'})"   maxlength="0" value="${fmPurchase.startTime}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_END_TIME%></th>	
					<td>
					<input class="span2" name="endTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_END_TIME%>'})"   maxlength="0" value="${fmPurchase.endTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_IMAGES%></th>	
					<td>
											<input class="span2" name="images" type="text" value="${fmPurchase.images}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_REQUIRE%></th>	
					<td>
											<input class="span2" name="require" type="text" value="${fmPurchase.require}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_DIAMETER%></th>	
					<td>
											<input class="span2" name="diameter" type="text" value="${fmPurchase.diameter}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_DIAMETER_UNIT%></th>	
					<td>
											<jb:select dataType="ZU" name="diameterUnit" value="${fmPurchase.diameterUnit}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_COLOR%></th>	
					<td>
											<jb:select dataType="CR" name="color" value="${fmPurchase.color}"></jb:select>	
					</td>							
					<th><%=TfmPurchase.ALIAS_IS_PACK%></th>	
					<td>
											<jb:select dataType="IS" name="isPack" value="${fmPurchase.isPack}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_PACK%></th>	
					<td>
											<jb:select dataType="PK" name="pack" value="${fmPurchase.pack}"></jb:select>	
					</td>							
					<th><%=TfmPurchase.ALIAS_FORMAT_DESC%></th>	
					<td>
											<input class="span2" name="formatDesc" type="text" value="${fmPurchase.formatDesc}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_VOICE_URL%></th>	
					<td>
											<input class="span2" name="voiceUrl" type="text" value="${fmPurchase.voiceUrl}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_VOICE_DURATION%></th>	
					<td>
											<input class="span2" name="voiceDuration" type="text" value="${fmPurchase.voiceDuration}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfmPurchase.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" value="${fmPurchase.userId}"/>
					</td>							
					<th><%=TfmPurchase.ALIAS_ONLINE_STATUS%></th>	
					<td>
											<jb:select dataType="OS" name="onlineStatus" value="${fmPurchase.onlineStatus}"></jb:select>	
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>