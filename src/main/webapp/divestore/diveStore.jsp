<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveStore"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<!DOCTYPE html>
<html>
<head>
<title>DiveStore管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveStoreController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveStoreController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveStoreController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/diveStoreController/dataGrid',
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
				field : 'name',
				title : '<%=TdiveStore.ALIAS_NAME%>',
				width : 50		
				}, {
				field : 'area',
				title : '<%=TdiveStore.ALIAS_AREA%>',
				width : 50		
				}, {
				field : 'statusZh',
				title : '<%=TdiveStore.ALIAS_STATUS%>',
				width : 50		
				}, {
				field : 'addtime',
				title : '<%=TdiveStore.ALIAS_ADDTIME%>',
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
		
		drawRegion($('#delta'), 0);
		
		$('#delta').combobox({
			onSelect: function(record){
				var opts =[{ 'text':'请选择','id':''}];
				$("#country").combobox("loadData", opts);
				$("#country").combobox("setValue", "");
				$("#province").combobox("loadData", opts);
				$("#province").combobox("setValue", "");
				var delta = $('#delta').combobox("getValue");
				if(delta != "") {
					drawRegion($('#country'), delta);
				}
				
			}
		});
		
		$('#country').combobox({
			onSelect: function(record){
				var opts =[{ 'text':'请选择','id':''}];
				$("#province").combobox("loadData", opts);
				$("#province").combobox("setValue", "");
				var countryCode = $('#country').combobox("getValue");
				if(countryCode != "") {
					drawRegion($("#province"), countryCode);
				}
			}
		});
	});
	
	// 获取数据填充洲国省
	function drawRegion(obj, regionParentId) {
		$.post('${pageContext.request.contextPath}/diveRegionController/getByParentRegionId', {
			regionParentId : regionParentId
		}, function(result) {
			if (result.success) {
				var opts =[{ 'text':'请选择','id':''}];
				for(var i=0; i<result.obj.length; i++) {
					opts.push({"text":result.obj[i].regionNameZh,"id":result.obj[i].regionId});
     			}
				$(obj).combobox("loadData", opts);
			}
		}, 'JSON');
	}

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
				$.post('${pageContext.request.contextPath}/diveStoreController/delete', {
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
			href : '${pageContext.request.contextPath}/diveStoreController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/diveStoreController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/diveStoreController/addPage',
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
	        url:'${pageContext.request.contextPath}/diveStoreController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
	        	
       	 }
        }); 
	}
	function searchFun() {
		var delta = $("#delta").combobox("getText");
		var country = $("#country").combobox("getText");
		var province = $("#province").combobox("getText");
		if(country != "请选择") {
			delta += "_" + country;
		}
		if(province != "请选择") {
			delta += "_" + province;
		}
		$("#area").val(delta);
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
				<input type="hidden" name="area" id="area"/>
				<table class="table table-hover table-condensed"
					style="display: none;">
					<tr>
						<th><%=TdiveStore.ALIAS_NAME%></th>
						<td><input type="text" name="name" maxlength="128"
							class="span2" /></td>
						<th><%=TdiveStore.ALIAS_STATUS%></th>
						<td>
							<jb:select dataType="ST" name="status"></jb:select>	
						</td>
						<th><%=TdiveStore.ALIAS_ADDTIME%></th>
						<td><input type="text" class="span2"
							onclick="WdatePicker({dateFmt:'<%=TdiveStore.FORMAT_ADDTIME%>'})"
							id="addtimeBegin" name="addtimeBegin" /> <input type="text"
							class="span2"
							onclick="WdatePicker({dateFmt:'<%=TdiveStore.FORMAT_ADDTIME%>'})"
							id="addtimeEnd" name="addtimeEnd" /></td>
					</tr>
					<tr>
						<th>洲</th>
						<td>
							<select class="easyui-combobox" data-options="valueField:'id',textField:'text',width:140,height:29,editable:false" id="delta">
								<option value="">请选择</option>
							</select>
						</td>
						<th>国</th>
						<td>
							<select class="easyui-combobox" data-options="valueField:'id',textField:'text',width:140,height:29,editable:false" id="country">
								<option value="">请选择</option>
							</select>
						</td>
						<th>省</th>
						<td>
							<select class="easyui-combobox" data-options="valueField:'id',textField:'text',width:140,height:29,editable:false" id="province">
								<option value="">请选择</option>
							</select>
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
			test="${fn:contains(sessionInfo.resourceList, '/diveStoreController/addPage')}">
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
			test="${fn:contains(sessionInfo.resourceList, '/diveStoreController/download')}">
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