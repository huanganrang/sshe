<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveOrder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th width="10%">下单人账号</th>	
					<td width="40%">
						${diveOrder.userName}							
					</td>
					<th width="10%">下单人昵称</th>	
					<td width="40%">
						${diveOrder.nickname}							
					</td>							
				</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_ADDRESS%></th>	
					<td colspan="3">
						${diveOrder.address}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_EXPRESS_NAME%></th>	
					<td>
						${diveOrder.expressName}							
					</td>							
					<th><%=TdiveOrder.ALIAS_EXPRESS_NO%></th>	
					<td>
						${diveOrder.expressNo}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_SEND_STATUS%></th>	
					<td>
						${diveOrder.sendStatusZh}							
					</td>	
					<th><%=TdiveOrder.ALIAS_PAY_WAY%></th>	
					<td>
						${diveOrder.payWay}							
					</td>	
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_PAY_STATUS%></th>	
					<td>
						${diveOrder.payStatusZh}							
					</td>							
					<th><%=TdiveOrder.ALIAS_ORDER_STATUS%></th>	
					<td>
						${diveOrder.orderStatusZh}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TdiveOrder.ALIAS_ADDTIME%></th>	
					<td>
						<fmt:formatDate value="${diveOrder.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>						
					</td>
					<th><%=TdiveOrder.ALIAS_PAYTIME%></th>	
					<td>
						<fmt:formatDate value="${diveOrder.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>						
					</td>							
				</tr>	
				<tr>	
					<th><%=TdiveOrder.ALIAS_REMARK%></th>	
					<td colspan="3">
						${diveOrder.remark}							
					</td>						
				</tr>	
				<tr>	
					<td colspan="4">
						<table style="width: 100%; margin-top: 10px;">
							<tr>
								<th width="20%">商品图片</th>	
								<th width="20%">名称</th>	
								<th width="10%">类型</th>	
								<th width="10%">单价（元）</th>	
								<th width="10%">数量</th>	
								<th width="10%">总价</th>	
								<th width="10%">颜色</th>	
								<th width="10%">尺寸</th>	
							</tr>
							<c:forEach items="${diveOrder.detail_list}" var="detail" varStatus="vs">
								<tr>
									<td>
										<img alt="" src="${detail.businessIcon}" width="50" height="50">
									</td>
									<td>
										${detail.businessName}
									</td>
									<td>
										${detail.businessTypeZh}
									</td>
									<td>
										${detail.price}
									</td>
									<td>
										${detail.number}
									</td>
									<td>
										${detail.price*detail.number}
									</td>
									<td>
										${detail.colorZh}
									</td>
									<td>
										${detail.sizeZh}
									</td>
								</tr>
								
							</c:forEach>
						</table>						
					</td>							
				</tr>	
		</table>
	</div>
</div>