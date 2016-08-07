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
					<th><%=TfmPurchase.ALIAS_ADDTIME%></th>	
					<td>
						${fmPurchase.addtime}							
					</td>							
					<th><%=TfmPurchase.ALIAS_UPDATETIME%></th>	
					<td>
						${fmPurchase.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_ISDELETED%></th>	
					<td>
						${fmPurchase.isdeleted}							
					</td>							
					<th><%=TfmPurchase.ALIAS_NAME%></th>	
					<td>
						${fmPurchase.name}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_START_PRICE%></th>	
					<td>
						${fmPurchase.startPrice}							
					</td>							
					<th><%=TfmPurchase.ALIAS_END_PRICE%></th>	
					<td>
						${fmPurchase.endPrice}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_UNIT%></th>	
					<td>
						${fmPurchase.unit}							
					</td>							
					<th><%=TfmPurchase.ALIAS_MIN_NUM%></th>	
					<td>
						${fmPurchase.minNum}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_MAX_NUM%></th>	
					<td>
						${fmPurchase.maxNum}							
					</td>							
					<th><%=TfmPurchase.ALIAS_STATUS%></th>	
					<td>
						${fmPurchase.status}							
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
					<th><%=TfmPurchase.ALIAS_START_TIME%></th>	
					<td>
						${fmPurchase.startTime}							
					</td>							
					<th><%=TfmPurchase.ALIAS_END_TIME%></th>	
					<td>
						${fmPurchase.endTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_IMAGES%></th>	
					<td>
						${fmPurchase.images}							
					</td>							
					<th><%=TfmPurchase.ALIAS_REQUIRE%></th>	
					<td>
						${fmPurchase.require}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_DIAMETER%></th>	
					<td>
						${fmPurchase.diameter}							
					</td>							
					<th><%=TfmPurchase.ALIAS_DIAMETER_UNIT%></th>	
					<td>
						${fmPurchase.diameterUnit}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_COLOR%></th>	
					<td>
						${fmPurchase.color}							
					</td>							
					<th><%=TfmPurchase.ALIAS_IS_PACK%></th>	
					<td>
						${fmPurchase.isPack}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_PACK%></th>	
					<td>
						${fmPurchase.pack}							
					</td>							
					<th><%=TfmPurchase.ALIAS_FORMAT_DESC%></th>	
					<td>
						${fmPurchase.formatDesc}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_VOICE_URL%></th>	
					<td>
						${fmPurchase.voiceUrl}							
					</td>							
					<th><%=TfmPurchase.ALIAS_VOICE_DURATION%></th>	
					<td>
						${fmPurchase.voiceDuration}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmPurchase.ALIAS_USER_ID%></th>	
					<td>
						${fmPurchase.userId}							
					</td>							
					<th><%=TfmPurchase.ALIAS_ONLINE_STATUS%></th>	
					<td>
						${fmPurchase.onlineStatus}							
					</td>							
				</tr>		
		</table>
	</div>
</div>