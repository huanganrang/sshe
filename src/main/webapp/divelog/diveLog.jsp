<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveLog"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>DiveLog管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveLogController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveLogController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveLogController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/diveLogController/dataGrid',
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
				field : 'logTypeZh',
				title : '<%=TdiveLog.ALIAS_LOG_TYPE%>',
				width : 50		
				}, {
				field : 'accountId',
				title : '<%=TdiveLog.ALIAS_ACCOUNT_ID%>',
				width : 50		
				}, {
					field : 'diveDate',
					title : '<%=TdiveLog.ALIAS_DIVE_DATE%>',
					width : 50,
					formatter: function(value,row,index){
						if(value && value.length > 16) {
							return value.substring(0, 16);
						}
					}
				}, {
					field : 'diveAddress',
					title : '<%=TdiveLog.ALIAS_DIVE_ADDRESS%>',
					width : 50		
				}, {
				field : 'diveTypeZh',
				title : '<%=TdiveLog.ALIAS_DIVE_TYPE%>',
				width : 50		
				}, {
				field : 'weatherZh',
				title : '<%=TdiveLog.ALIAS_WEATHER%>',
				width : 50		
				}, {
					field : 'weatherTemperature',
					title : '<%=TdiveLog.ALIAS_WEATHER_TEMPERATURE%>',
					width : 50		
				}, {
				field : 'waterTemperature',
				title : '<%=TdiveLog.ALIAS_WATER_TEMPERATURE%>',
				width : 50		
				}, {
				field : 'seeing',
				title : '<%=TdiveLog.ALIAS_SEEING%>',
				width : 50		
				}, {
					field : 'windPower',
					title : '<%=TdiveLog.ALIAS_WIND_POWER%>',
					width : 50		
				}, {
					field : 'inTime',
					title : '<%=TdiveLog.ALIAS_IN_TIME%>',
					width : 50,
					formatter: function(value,row,index){
						if(value && value.length > 16) {
							return value.substring(11, 16);
						}
					}
				}, {
					field : 'outTime',
					title : '<%=TdiveLog.ALIAS_OUT_TIME%>',
					width : 50,
					formatter: function(value,row,index){
						if(value && value.length > 16) {
							return value.substring(11, 16);
						}
					}
				}, {
				field : 'diveHeight',
				title : '<%=TdiveLog.ALIAS_DIVE_HEIGHT%>',
				width : 50		
				}, {
					field : 'highGas',
					title : '<%=TdiveLog.ALIAS_HIGH_GAS%>',
					width : 50,
					formatter: function(value,row,index){
						return value == 1 ? "是" : "否";
					}
				}, {
				field : 'gasStart',
				title : '<%=TdiveLog.ALIAS_GAS_START%>',
				width : 50		
				}, {
				field : 'gasEnd',
				title : '<%=TdiveLog.ALIAS_GAS_END%>',
				width : 50		
				}, {
				field : 'addtime',
				title : '<%=TdiveLog.ALIAS_ADDTIME%>',
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
				$.post('${pageContext.request.contextPath}/diveLogController/delete', {
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
			href : '${pageContext.request.contextPath}/diveLogController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/diveLogController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/diveLogController/addPage',
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
	        url:'${pageContext.request.contextPath}/diveLogController/download',
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
		<div data-options="region:'north',title:'查询条件',border:false"
			style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="display: none;">
					<tr>
						<th><%=TdiveLog.ALIAS_LOG_TYPE%></th>
						<td>
							<jb:select dataType="LT" name="logType"></jb:select>
						</td>
						<th><%=TdiveLog.ALIAS_DIVE_TYPE%></th>
						<td>
							<jb:select dataType="DT" name="diveType"></jb:select>
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
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/diveLogController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a
			href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"
			onclick="cleanFun();">清空条件</a>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/diveLogController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'server_go',plain:true"
				onclick="downloadTable();">导出</a>
			<form id="downloadTable" target="downloadIframe" method="post"
				style="display: none;"></form>
			<iframe id="downloadIframe" name="downloadIframe"
				style="display: none;"></iframe>
		</c:if>
	</div>
</body>
</html>