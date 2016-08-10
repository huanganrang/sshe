<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmGoods" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fmGoodsController/add',
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
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">

				<tr>	

					<th><%=TfmGoods.ALIAS_NAME%></th>	
					<td>
											<jb:select dataType="GN" name="name"></jb:select>	
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_PRICE%></th>	
					<td>
											<input class="span2" name="price" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_UNIT%></th>	
					<td>
											<jb:select dataType="GU" name="unit"></jb:select>	
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_MIN_BATCH%></th>	
					<td>
											<input class="span2" name="minBatch" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_STATUS%></th>	
					<td>
											<jb:select dataType="GS" name="status"></jb:select>	
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_BORN_AREA%></th>	
					<td>
											<input class="span2" name="bornArea" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_STORAGE%></th>	
					<td>
											<input class="span2" name="storage" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_OFFLINE_TIME%></th>	
					<td>
					<input class="span2" name="offlineTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmGoods.FORMAT_OFFLINE_TIME%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TfmGoods.ALIAS_CONTACTOR%></th>	
					<td>
											<input class="span2" name="contactor" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_CONTACTOR_MOBILE%></th>	
					<td>
											<input class="span2" name="contactorMobile" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_GOAL_AREA%></th>	
					<td>
											<input class="span2" name="goalArea" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_PRE_ONLINE_TIME%></th>	
					<td>
					<input class="span2" name="preOnlineTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmGoods.FORMAT_PRE_ONLINE_TIME%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TfmGoods.ALIAS_TRANSACTION_AREA%></th>	
					<td>
											<input class="span2" name="transactionArea" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_SEND_TIME%></th>	
					<td>
					<input class="span2" name="sendTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmGoods.FORMAT_SEND_TIME%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TfmGoods.ALIAS_TRANSACTION_TIME%></th>	
					<td>
					<input class="span2" name="transactionTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfmGoods.FORMAT_TRANSACTION_TIME%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_CAR_NO%></th>	
					<td>
											<input class="span2" name="carNo" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_IMAGES%></th>	
					<td>
											<input class="span2" name="images" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_DESCRIPTION%></th>	
					<td>
											<input class="span2" name="description" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_DIAMETER%></th>	
					<td>
											<input class="span2" name="diameter" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_DIAMETER_UNIT%></th>	
					<td>
											<jb:select dataType="ZU" name="diameterUnit"></jb:select>	
					</td>							
					<th><%=TfmGoods.ALIAS_COLOR%></th>	
					<td>
											<jb:select dataType="CR" name="color"></jb:select>	
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_IS_PACK%></th>	
					<td>
											<jb:select dataType="IS" name="isPack"></jb:select>	
					</td>							
					<th><%=TfmGoods.ALIAS_PACK%></th>	
					<td>
											<jb:select dataType="PK" name="pack"></jb:select>	
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_FORMAT_DESC%></th>	
					<td>
											<input class="span2" name="formatDesc" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_ONLINE_STATUS%></th>	
					<td>
											<jb:select dataType="OS" name="onlineStatus"></jb:select>	
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_PARENT_ID%></th>	
					<td>
											<input class="span2" name="parentId" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_ACCESS_NUM%></th>	
					<td>
											<input class="span2" name="accessNum" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_SOURCE%></th>	
					<td>
											<input class="span2" name="source" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_OUTER_ID%></th>	
					<td>
											<input class="span2" name="outerId" type="text"/>
					</td>							
					<th><%=TfmGoods.ALIAS_OUTER_NUMBER%></th>	
					<td>
											<input class="span2" name="outerNumber" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfmGoods.ALIAS_GRADE%></th>	
					<td>
											<jb:select dataType="GD" name="grade"></jb:select>	
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>