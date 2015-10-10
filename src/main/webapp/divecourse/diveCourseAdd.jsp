<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCourse"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	var editor;
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#introduce', {
				width : '580px',
				height : '300px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${pageContext.request.contextPath}/fileController/upload',
				fileManagerJson : '${pageContext.request.contextPath}/fileController/fileManage',
				allowFileManager : true
			});
		}, 1);
		
	 	parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveCourseController/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				editor.sync();
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		function ProcessFile() {
			var file = document.getElementById('iconFile').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function ( event ) {
					var txt = event.target.result;
					$('.img-preview').attr('src',txt);
				};
			}
		    reader.readAsDataURL(file);
		}
		$(document).delegate('#iconFile','change',function () {
			ProcessFile();
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: auto;">
		<form id="form" method="post" enctype="multipart/form-data">
			<table class="table table-hover table-condensed">
				<tr>
					<th width="8%"><%=TdiveCourse.ALIAS_TITLE%></th>
					<td width="42%"><input class="span2" name="title" type="text"
						class="span2" /></td>
					<th width="8%"><%=TdiveCourse.ALIAS_PRICE%></th>
					<td width="42%"><input class="span2" name="price" type="text"
						class="span2" /></td>
				</tr>
				<tr>
					<th><%=TdiveCourse.ALIAS_COURSE_TYPE%></th>
					<td>
						<jb:select dataType="CT" name="courseType"></jb:select>
					</td>
					<th><%=TdiveCourse.ALIAS_STATUS%></th>
					<td>
						<jb:select dataType="ST" name="status"></jb:select>
					</td>
				</tr>
				<tr>
					<th><%=TdiveCourse.ALIAS_ICON%></th>
					<td colspan="3">
						<img class="img-preview" src="" width="50" height="50"/> 
						<input type="file" id="iconFile" name="iconFile">
					</td>
				</tr>
				<tr>
					<th><%=TdiveCourse.ALIAS_FILE_PATH%></th>
					<td colspan="3">
						<input type="file" name="filePathFile">
					</td>
				</tr>
				<tr>
					<th><%=TdiveCourse.ALIAS_CONTENT%></th>
					<td colspan="3">
						<textarea style="width: 500px;" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<th><%=TdiveCourse.ALIAS_INTRODUCE%></th>
					<td colspan="3">
						<textarea  name="introduce" id="introduce" style="height:180px;visibility:hidden;"></textarea>
					</td>	
				</tr>
			</table>
		</form>
	</div>
</div>