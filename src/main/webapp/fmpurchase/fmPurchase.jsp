<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmPurchase" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FmPurchase管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmPurchaseController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmPurchaseController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmPurchaseController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fmPurchaseController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
				}, {
				field : 'addtime',
				title : '<%=TfmPurchase.ALIAS_ADDTIME%>',
				width : 50		
				}, {
				field : 'updatetime',
				title : '<%=TfmPurchase.ALIAS_UPDATETIME%>',
				width : 50		
				}, {
				field : 'isdeleted',
				title : '<%=TfmPurchase.ALIAS_ISDELETED%>',
				width : 50		
				}, {
				field : 'name',
				title : '<%=TfmPurchase.ALIAS_NAME%>',
				width : 50		
				}, {
				field : 'startPrice',
				title : '<%=TfmPurchase.ALIAS_START_PRICE%>',
				width : 50		
				}, {
				field : 'endPrice',
				title : '<%=TfmPurchase.ALIAS_END_PRICE%>',
				width : 50		
				}, {
				field : 'unit',
				title : '<%=TfmPurchase.ALIAS_UNIT%>',
				width : 50		
				}, {
				field : 'minNum',
				title : '<%=TfmPurchase.ALIAS_MIN_NUM%>',
				width : 50		
				}, {
				field : 'maxNum',
				title : '<%=TfmPurchase.ALIAS_MAX_NUM%>',
				width : 50		
				}, {
				field : 'status',
				title : '<%=TfmPurchase.ALIAS_STATUS%>',
				width : 50		
				}, {
				field : 'bornArea',
				title : '<%=TfmPurchase.ALIAS_BORN_AREA%>',
				width : 50		
				}, {
				field : 'transactionArea',
				title : '<%=TfmPurchase.ALIAS_TRANSACTION_AREA%>',
				width : 50		
				}, {
				field : 'startTime',
				title : '<%=TfmPurchase.ALIAS_START_TIME%>',
				width : 50		
				}, {
				field : 'endTime',
				title : '<%=TfmPurchase.ALIAS_END_TIME%>',
				width : 50		
				}, {
				field : 'images',
				title : '<%=TfmPurchase.ALIAS_IMAGES%>',
				width : 50		
				}, {
				field : 'require',
				title : '<%=TfmPurchase.ALIAS_REQUIRE%>',
				width : 50		
				}, {
				field : 'diameter',
				title : '<%=TfmPurchase.ALIAS_DIAMETER%>',
				width : 50		
				}, {
				field : 'diameterUnit',
				title : '<%=TfmPurchase.ALIAS_DIAMETER_UNIT%>',
				width : 50		
				}, {
				field : 'color',
				title : '<%=TfmPurchase.ALIAS_COLOR%>',
				width : 50		
				}, {
				field : 'isPack',
				title : '<%=TfmPurchase.ALIAS_IS_PACK%>',
				width : 50		
				}, {
				field : 'pack',
				title : '<%=TfmPurchase.ALIAS_PACK%>',
				width : 50		
				}, {
				field : 'formatDesc',
				title : '<%=TfmPurchase.ALIAS_FORMAT_DESC%>',
				width : 50		
				}, {
				field : 'voiceUrl',
				title : '<%=TfmPurchase.ALIAS_VOICE_URL%>',
				width : 50		
				}, {
				field : 'voiceDuration',
				title : '<%=TfmPurchase.ALIAS_VOICE_DURATION%>',
				width : 50		
				}, {
				field : 'userId',
				title : '<%=TfmPurchase.ALIAS_USER_ID%>',
				width : 50		
				}, {
				field : 'onlineStatus',
				title : '<%=TfmPurchase.ALIAS_ONLINE_STATUS%>',
				width : 50		
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_edit.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前数据？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/fmPurchaseController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fmPurchaseController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function viewFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '查看数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fmPurchaseController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fmPurchaseController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function downloadTable(){
		var options = dataGrid.datagrid("options");
		var $colums = [];		
		$.merge($colums, options.columns); 
		$.merge($colums, options.frozenColumns);
		var columsStr = JSON.stringify($colums);
	    $('#downloadTable').form('submit', {
	        url:'${pageContext.request.contextPath}/fmPurchaseController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
	        	
       	 }
        }); 
	}
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>	
							<th><%=TfmPurchase.ALIAS_ADDTIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_ADDTIME%>'})" id="addtimeBegin" name="addtimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_ADDTIME%>'})" id="addtimeEnd" name="addtimeEnd"/>
							</td>
							<th><%=TfmPurchase.ALIAS_UPDATETIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_UPDATETIME%>'})" id="updatetimeBegin" name="updatetimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_UPDATETIME%>'})" id="updatetimeEnd" name="updatetimeEnd"/>
							</td>
							<th><%=TfmPurchase.ALIAS_ISDELETED%></th>	
							<td>
											<input type="text" name="isdeleted" maxlength="0" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_NAME%></th>	
							<td>
											<jb:select dataType="GN" name="name"></jb:select>	
							</td>
						</tr>	
						<tr>	
							<th><%=TfmPurchase.ALIAS_START_PRICE%></th>	
							<td>
											<input type="text" name="startPrice" maxlength="12" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_END_PRICE%></th>	
							<td>
											<input type="text" name="endPrice" maxlength="12" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_UNIT%></th>	
							<td>
											<jb:select dataType="GU" name="unit"></jb:select>	
							</td>
							<th><%=TfmPurchase.ALIAS_MIN_NUM%></th>	
							<td>
											<input type="text" name="minNum" maxlength="12" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfmPurchase.ALIAS_MAX_NUM%></th>	
							<td>
											<input type="text" name="maxNum" maxlength="12" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_STATUS%></th>	
							<td>
											<jb:select dataType="GS" name="status"></jb:select>	
							</td>
							<th><%=TfmPurchase.ALIAS_BORN_AREA%></th>	
							<td>
											<input type="text" name="bornArea" maxlength="36" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_TRANSACTION_AREA%></th>	
							<td>
											<input type="text" name="transactionArea" maxlength="100" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfmPurchase.ALIAS_START_TIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_START_TIME%>'})" id="startTimeBegin" name="startTimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_START_TIME%>'})" id="startTimeEnd" name="startTimeEnd"/>
							</td>
							<th><%=TfmPurchase.ALIAS_END_TIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_END_TIME%>'})" id="endTimeBegin" name="endTimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmPurchase.FORMAT_END_TIME%>'})" id="endTimeEnd" name="endTimeEnd"/>
							</td>
							<th><%=TfmPurchase.ALIAS_IMAGES%></th>	
							<td>
											<input type="text" name="images" maxlength="500" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_REQUIRE%></th>	
							<td>
											<input type="text" name="require" maxlength="500" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfmPurchase.ALIAS_DIAMETER%></th>	
							<td>
											<input type="text" name="diameter" maxlength="100" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_DIAMETER_UNIT%></th>	
							<td>
											<jb:select dataType="ZU" name="diameterUnit"></jb:select>	
							</td>
							<th><%=TfmPurchase.ALIAS_COLOR%></th>	
							<td>
											<jb:select dataType="CR" name="color"></jb:select>	
							</td>
							<th><%=TfmPurchase.ALIAS_IS_PACK%></th>	
							<td>
											<jb:select dataType="IS" name="isPack"></jb:select>	
							</td>
						</tr>	
						<tr>	
							<th><%=TfmPurchase.ALIAS_PACK%></th>	
							<td>
											<jb:select dataType="PK" name="pack"></jb:select>	
							</td>
							<th><%=TfmPurchase.ALIAS_FORMAT_DESC%></th>	
							<td>
											<input type="text" name="formatDesc" maxlength="500" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_VOICE_URL%></th>	
							<td>
											<input type="text" name="voiceUrl" maxlength="100" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_VOICE_DURATION%></th>	
							<td>
											<input type="text" name="voiceDuration" maxlength="10" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfmPurchase.ALIAS_USER_ID%></th>	
							<td>
											<input type="text" name="userId" maxlength="36" class="span2"/>
							</td>
							<th><%=TfmPurchase.ALIAS_ONLINE_STATUS%></th>	
							<td>
											<jb:select dataType="OS" name="onlineStatus"></jb:select>	
							</td>
						</tr>	
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fmPurchaseController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fmPurchaseController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>