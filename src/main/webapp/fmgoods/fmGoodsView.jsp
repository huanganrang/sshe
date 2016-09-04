<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmGoods" %>
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
					<th><%=TfmGoods.ALIAS_NAME%></th>	
					<td>
						${fmGoods.nameName}
					</td>
					<th>状态</th>
					<td>
						${fmGoods.statusName}
					</td>
				</tr>		

				<tr>
					<th>规格</th>
					<td colspan="3">
						${fmGoods.extFields}
					</td>

				</tr>
				<tr>
					<th>描述</th>
					<td colspan="3">
						${fmGoods.description}
					</td>

				</tr>
				<tr>
					<th><%=TfmGoods.ALIAS_PRICE%></th>
					<td>
						${fmGoods.price}/${fmGoods.unitName}
					</td>
					<th><%=TfmGoods.ALIAS_MIN_BATCH%></th>
					<td>
						${fmGoods.minBatch}
					</td>
				</tr>
				<tr>	


				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_BORN_AREA%></th>	
					<td>
						${fmGoods.bornArea}							
					</td>							
					<th><%=TfmGoods.ALIAS_STORAGE%></th>	
					<td>
						${fmGoods.storage}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_OFFLINE_TIME%></th>	
					<td>
						${fmGoods.offlineTime}							
					</td>							
					<th><%=TfmGoods.ALIAS_CONTACTOR%></th>	
					<td>
						${fmGoods.contactor}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_CONTACTOR_MOBILE%></th>	
					<td>
						${fmGoods.contactorMobile}							
					</td>							
					<th><%=TfmGoods.ALIAS_GOAL_AREA%></th>	
					<td>
						${fmGoods.goalArea}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_PRE_ONLINE_TIME%></th>	
					<td>
						${fmGoods.preOnlineTime}							
					</td>							
					<th><%=TfmGoods.ALIAS_TRANSACTION_AREA%></th>	
					<td>
						${fmGoods.transactionArea}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_SEND_TIME%></th>	
					<td>
						${fmGoods.sendTime}							
					</td>							
					<th><%=TfmGoods.ALIAS_TRANSACTION_TIME%></th>	
					<td>
						${fmGoods.transactionTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_CAR_NO%></th>	
					<td>
						${fmGoods.carNo}							
					</td>
					<th>发布时间</th>
					<td>
						${fmGoods.addtime}
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<c:forEach items="${fmGoods.imageList}" var="image">
							<img class="img-preview" src="${image}" width="200" height="150"/>
						</c:forEach>
					</td>
				</tr>
				<tr>	
					<th><%=TfmGoods.ALIAS_USER_ID%></th>	
					<td>
						${fmGoods.userId}							
					</td>							
					<th><%=TfmGoods.ALIAS_PARENT_ID%></th>	
					<td>
						${fmGoods.parentId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_ACCESS_NUM%></th>	
					<td>
						${fmGoods.accessNum}							
					</td>							
					<th><%=TfmGoods.ALIAS_SOURCE%></th>	
					<td>
						${fmGoods.source}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_OUTER_ID%></th>	
					<td>
						${fmGoods.outerId}							
					</td>							
					<th><%=TfmGoods.ALIAS_OUTER_NUMBER%></th>	
					<td>
						${fmGoods.outerNumber}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmGoods.ALIAS_GRADE%></th>	
					<td colspan="3">
						${fmGoods.gradeName}
					</td>							
				</tr>

		</table>
	</div>
</div>