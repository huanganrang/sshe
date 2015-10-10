<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveStore"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	var editor;
	var first = true;
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#description', {
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
			url : '${pageContext.request.contextPath}/diveStoreController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				} else {
					editor.sync();
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
				}
				
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
		$('#delta').combobox({
			onSelect: function(record){
				first = false;
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
				first = false;
				var opts =[{ 'text':'请选择','id':''}];
				$("#province").combobox("loadData", opts);
				$("#province").combobox("setValue", "");
				var countryCode = $('#country').combobox("getValue");
				if(countryCode != "") {
					drawRegion($("#province"), countryCode);
				}
				
			}
		});
		
		drawRegion($('#delta'), 0, "${delta}", 1);
		
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
	
	// 获取数据填充洲国省
	function drawRegion(obj, regionParentId, region, level) {
		$.post('${pageContext.request.contextPath}/diveRegionController/getByParentRegionId', {
			regionParentId : regionParentId
		}, function(result) {
			if (result.success) {
				var opts =[{ 'text':'请选择','id':''}];
				for(var i=0; i<result.obj.length; i++) {
					opts.push({"text":result.obj[i].regionNameZh,"id":result.obj[i].regionId});
     			}
				$(obj).combobox("loadData", opts);
				if(first) {
					$(obj).combobox("setText", region);
					if(region != '请选择') {
						for(var i=0; i<$(obj).combobox("getData").length; i++) {
							if($(obj).combobox("getData")[i].text == region) {
								if(level == 1) {
									drawRegion($('#country'), $(obj).combobox("getData")[i].id, "${country}" || '请选择', 2);
								} else if(level == 2) {
									drawRegion($('#province'),  $(obj).combobox("getData")[i].id, "${province}"||'请选择', 3);
								}
								break;
							}
						}
					}
					
				}
			}
		}, 'JSON');
	}
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: auto;">
		<form id="form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${diveStore.id}" />
			<input type="hidden" name="area" id="area" value="${diveStore.area}"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th width="8%"><%=TdiveStore.ALIAS_NAME%></th>
					<td width="42%"><input class="span2" name="name" type="text" class="span2" value="${diveStore.name}"/>
					</td>
					<th width="8%"><%=TdiveStore.ALIAS_STATUS%></th>
					<td width="42%">
						<jb:select dataType="ST" name="status" value="${diveStore.status}"></jb:select>	
					</td>
				</tr>
				<tr>
					<th><%=TdiveStore.ALIAS_ICON%></th>
					<td colspan="3">
						<img class="img-preview" src="${diveStore.icon}" width="50" height="50"/> 
						<input type="file" id="iconFile" name="iconFile">	
					</td>
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
					
				</tr>
				<tr>
					<th>省</th>
					<td>
						<select class="easyui-combobox" data-options="valueField:'id',textField:'text',width:140,height:29,editable:false" id="province">
							<option value="">请选择</option>
						</select>
					</td>
					<th><%=TdiveStore.ALIAS_SERVER_SCOPE%></th>
					<td><input class="span2" name="serverScope" type="text"
						class="span2" value="${diveStore.serverScope}"/></td>
				</tr>
				<tr>
					<th><%=TdiveStore.ALIAS_SUMARY%></th>
					<td colspan="3">
						<textarea style="width: 500px;" name="sumary">${diveStore.sumary}</textarea>
					</td>
				</tr>
				<tr>
					<th><%=TdiveStore.ALIAS_DESCRIPTION%></th>
					<td colspan="3">
						<textarea  name="description" id="description" style="height:180px;visibility:hidden;">${diveStore.description}</textarea>
					</td>	
				</tr>
			
			</table>
		</form>
	</div>
</div>