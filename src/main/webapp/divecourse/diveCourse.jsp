<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCourse"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>DiveCourse管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveCourseController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveCourseController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveCourseController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/diveCourseController/upload')}">
	<script type="text/javascript">
		$.canUpload = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	var vcop = null;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/diveCourseController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'addtime',
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
				field : 'title',
				title : '<%=TdiveCourse.ALIAS_TITLE%>',
				width : 100		
				}, 
// 				{
// 				field : 'price',
<%-- 				title : '<%=TdiveCourse.ALIAS_PRICE%>', --%>
// 				width : 50		
// 				}, 
				{
				field : 'courseTypeZh',
				title : '<%=TdiveCourse.ALIAS_COURSE_TYPE%>',
				width : 50	
				}, {
				field : 'statusZh',
				title : '<%=TdiveCourse.ALIAS_STATUS%>',
				width : 50		
				}, {
				field : 'fileId',
				title : '<%=TdiveCourse.ALIAS_FILE_ID%>',
				width : 50		
				}, {
				field : 'addtime',
				title : '<%=TdiveCourse.ALIAS_ADDTIME%>',
				width : 50,
				sortable : true
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
						str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row.id, row.fileId, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');
					}
					str += '&nbsp;';
					if ($.canUpload) {
						str += $.formatString('<a onclick="uploadFun(\'{0}\');" style="color:red;cursor: pointer;">上传iQIYI</a>', row.id);
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
		
		vcop = new Q.vcopClient({
	        appKey:"2a1b15cfc6154a2088f82f9eab17a52d",  // 填写申请的app key 
	        appSecret:"2fcf5d737755ef904167bf570a90b2f0" // 填写app secret
	    });
		getEntAuth();
	});
	
	// 5-27 企业级授权
    function getEntAuth(){
        vcop.getAuthEnterprise(function (data) {
            if(data){
                vcop.authtoken = data.data.access_token;
            }
        });
    }

	function deleteFun(id, fileId) {
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
				$.post('${pageContext.request.contextPath}/diveCourseController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						if(fileId) {
							vcop.delVideoById({delete_type:1, file_ids:fileId},
						        function (data) {
									console.log(data);
						        }
					       	);
						}
						
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
			href : '${pageContext.request.contextPath}/diveCourseController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/diveCourseController/view?id=' + id
		});
	}
	
	function uploadFun(id) {
		parent.$.modalDialog({
			title : '上传爱奇艺',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/diveCourseController/upload?id=' + id
		});
		parent.$.modalDialog.openner_dataGrid = dataGrid;
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/diveCourseController/addPage',
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
	        url:'${pageContext.request.contextPath}/diveCourseController/download',
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
			style="height: 80px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="display: none;">
					<tr>
						<th><%=TdiveCourse.ALIAS_TITLE%></th>
						<td><input type="text" name="title" maxlength="128"
							class="span2" /></td>
						<th><%=TdiveCourse.ALIAS_COURSE_TYPE%></th>
						<td>
							<jb:select dataType="VT" name="courseType"></jb:select>
						</td>
						<th><%=TdiveCourse.ALIAS_STATUS%></th>
						<td>
							<jb:select dataType="ST" name="status"></jb:select>
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
			test="${fn:contains(sessionInfo.resourceList, '/diveCourseController/addPage')}">
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
			test="${fn:contains(sessionInfo.resourceList, '/diveCourseController/download')}">
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