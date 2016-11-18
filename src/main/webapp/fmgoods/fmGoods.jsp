<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TfmGoods" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FmGoods管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/addToAd')}">
	<script type="text/javascript">
		$.canViewAddToAd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/addToSanAd')}">
	<script type="text/javascript">
		$.canViewAddToSanAd = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fmGoodsController/dataGrid',
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
				title : 'ID',
				width : 125,
				hidden : false
				}, {
				field : 'updatetime',
				title : '<%=TfmGoods.ALIAS_UPDATETIME%>',
				width : 50,
				hidden : true
				}, {
				field : 'nameName',
				title : '<%=TfmGoods.ALIAS_NAME%>',
				width : 50		
				}, {
				field : 'typeName',
				title : '商品分类',
				width : 50
				}, {
				field : 'price',
				title : '<%=TfmGoods.ALIAS_PRICE%>',
				width : 20
				}, {
				field : 'unitName',
				title : '<%=TfmGoods.ALIAS_UNIT%>',
				width : 20
				}, {
				field : 'extFields',
				title : '规格',
				width : 100
				},{
				field : 'minBatch',
				title : '<%=TfmGoods.ALIAS_MIN_BATCH%>',
				width : 22
				}, {
				field : 'statusName',
				title : '状态',
				width : 20
				}, {
				field : 'bornArea',
				title : '<%=TfmGoods.ALIAS_BORN_AREA%>',
				width : 50		
				}, {
				field : 'onlineStatusName',
				title : '上下架',
				width : 22
				}, {
				field : 'addtime',
				title : '<%=TfmGoods.ALIAS_ADDTIME%>',
				width : 70
				}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<input type="button" class="button gray" onclick="viewFun(\'{0}\');" href="javascript:void(0)" value="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/link.png');
					}
					str += '&nbsp;';
					if ($.canViewAddToAd) {
						str += $.formatString('<input type="button" class="button gray" onclick="addToAd(\'{0}\');" href="javascript:void(0)" value="活动广告" />', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/link.png');
					}
					str += '&nbsp;';
					if ($.canViewAddToSanAd) {
						str += $.formatString('<input type="button" class="button gray" onclick="addToAd2(\'{0}\');" href="javascript:void(0)" value="三品一标" />', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/link.png');
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
				$.post('${pageContext.request.contextPath}/fmGoodsController/delete', {
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
			href : '${pageContext.request.contextPath}/fmGoodsController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/fmGoodsController/view?id=' + id
		});
	}

	function addToAd(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要推送至活动广告？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/fmGoodsController/addToAd', {
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
	function addToAd2(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要推送至三品一标？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/fmGoodsController/addToSanAd', {
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
	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fmGoodsController/addPage',
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
	        url:'${pageContext.request.contextPath}/fmGoodsController/download',
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
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 120px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>
							<th><%=TfmGoods.ALIAS_NAME%></th>
							<td>
								<jb:select dataType="GN" name="name"></jb:select>
							</td>
							<th>状态</th>
							<td>
								<jb:select dataType="GS" name="status"></jb:select>
							</td>
							<th><%=TfmGoods.ALIAS_BORN_AREA%></th>
							<td>
								<input type="text" name="bornArea" maxlength="36" class="span2"/>
							</td>

						</tr>	
						<tr>
							<th><%=TfmGoods.ALIAS_USER_ID%></th>
							<td>
								<input type="text" name="userId" maxlength="36" class="span2"/>
							</td>
							<th>上架状态</th>
							<td>
								<jb:select dataType="OS" name="onlineStatus"></jb:select>
							</td>
							<th><%=TfmGoods.ALIAS_ADDTIME%></th>
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmGoods.FORMAT_ADDTIME%>'})" id="addtimeBegin" name="addtimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TfmGoods.FORMAT_ADDTIME%>'})" id="addtimeEnd" name="addtimeEnd"/>
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
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'common_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fmGoodsController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>