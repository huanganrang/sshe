<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmMarquee" %>
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
					<th><%=TfmMarquee.ALIAS_ADDTIME%></th>	
					<td>
						${fmMarquee.addtime}							
					</td>							
					<th><%=TfmMarquee.ALIAS_UPDATETIME%></th>	
					<td>
						${fmMarquee.updatetime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMarquee.ALIAS_ISDELETED%></th>	
					<td>
						${fmMarquee.isdeleted}							
					</td>							
					<th><%=TfmMarquee.ALIAS_IMAGE_NAME%></th>	
					<td>
						${fmMarquee.imageName}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMarquee.ALIAS_URL%></th>	
					<td>
						${fmMarquee.url}							
					</td>							
					<th><%=TfmMarquee.ALIAS_SEQ%></th>	
					<td>
						${fmMarquee.seq}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfmMarquee.ALIAS_LOGIN_ID%></th>	
					<td>
						${fmMarquee.loginId}							
					</td>							
					<th><%=TfmMarquee.ALIAS_GOODS_ID%></th>	
					<td>
						${fmMarquee.goodsId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>