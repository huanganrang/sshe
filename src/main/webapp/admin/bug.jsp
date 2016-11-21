<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>BUG管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/bugController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'createdatetime',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			}, {
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'systemType',
				title : '系统类型',
				width : 80,
				sortable : true
			}, {
				field : 'phoneModel',
				title : '手机型号',
				width : 100,
				sortable : true
			}, {
				field : 'typeId',
				title : 'BUG类型ID',
				width : 150,
				hidden : true
			}, {
				field : 'typeName',
				title : 'BUG类型名称',
				width : 150
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<input type="button" class="button gray" onclick="editFun(\'{0}\');" value="修改"/>', row.id);
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<input type="button" class="button gray" onclick="deleteFun(\'{0}\');" value="删除"/>', row.id);
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<input type="button" class="button gray" onclick="viewFun(\'{0}\');" value="查看"/>', row.id);
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前BUG？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/bugController/delete', {
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
			title : '编辑BUG',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/bugController/editPage?id=' + id,
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
			title : '查看BUG',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/bugController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加BUG',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/bugController/addPage',
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
						<th>系统类型</th>
						<td><input name="systemType" placeholder="可以模糊查询" class="span2" /></td>
						<th>手机型号</th>
						<td><input name="phoneModel" placeholder="可以模糊查询" class="span2" /></td>
						<th>BUG类型</th>
						<td><select name="typeId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
								<option value=""></option>
								<c:forEach items="${bugTypeList}" var="bugType">
									<option value="${bugType.id}">${bugType.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>创建时间</th>
						<td colspan="5"><input class="span2" name="createdatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />至<input class="span2" name="createdatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'common_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/addPage')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/delete')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/bugController/editPage')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
	</div>
</body>
</html>