<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmPurchase" %>
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

					<th><%=TfmPurchase.ALIAS_NAME%></th>	
					<td>
						${fmPurchase.nameName}
					</td>
					<th>状态</th>
					<td>
						${fmPurchase.statusName}
					</td>
				</tr>
				<tr>
					<th>规格</th>
					<td colspan="3">
						${fmPurchase.extFields}
					</td>
				</tr>
				<tr>	
					<th>期望价格</th>
					<td>
						${fmPurchase.startPrice}-${fmPurchase.endPrice}/${fmPurchase.unitName}
					</td>
					<th>采购数量</th>
					<td>
						${fmPurchase.minNum}-${fmPurchase.maxNum}/${fmPurchase.unitName}
					</td>							
				</tr>		

				<tr>	
					<th><%=TfmPurchase.ALIAS_BORN_AREA%></th>	
					<td>
						${fmPurchase.bornArea}							
					</td>							
					<th><%=TfmPurchase.ALIAS_TRANSACTION_AREA%></th>	
					<td>
						${fmPurchase.transactionArea}							
					</td>							
				</tr>		
				<tr>	
					<th>收货时间</th>
					<td colspan="3">
						${fmPurchase.startTime}-${fmPurchase.endTime}
					</td>							

				</tr>		
				<tr>	

					<th><%=TfmPurchase.ALIAS_REQUIRE%></th>	
					<td colspan="3">
						${fmPurchase.require}							
					</td>							
				</tr>
				<tr>
					<td colspan="4">
						<c:forEach items="${fmPurchase.imageList}" var="image">
							<img class="img-preview" src="${image}" width="200" height="150"/>
						</c:forEach>
					</td>
				</tr>

				<%--<tr>
					<th><%=TfmPurchase.ALIAS_VOICE_URL%></th>	
					<td>
						${fmPurchase.voiceUrl}							
					</td>							
					<th><%=TfmPurchase.ALIAS_VOICE_DURATION%></th>	
					<td>
						${fmPurchase.voiceDuration}							
					</td>							
				</tr>--%>
				<tr>	
					<th>昵称</th>
					<td>
						${fmPurchase.fmUser.nickName}
					</td>
					<th>手机号</th>
					<td>
						${fmPurchase.fmUser.phone}
					</td>

				</tr>
				<tr>
					<th>发布时间</th>
					<td>
						${fmPurchase.addtime}
					</td>
					<th><%=TfmPurchase.ALIAS_ONLINE_STATUS%></th>
					<td>
						${fmPurchase.onlineStatusName}
					</td>
				</tr>
		</table>
	</div>
</div>