<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveCourse"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.all_percent {
        width: 730px;
        height: 20px;
        border: #5BB75B 1px solid;
        padding: 2px;
        position: relative;
    }
	.percent {
        width: 0px;
        height: 20px;
        background: #5BB75B;
    }

    .percent_text {
        color: #000;
        width: 100%;
        height: 20px;
        line-height: 23px;
        font-weight: normal;
        text-align: center;
        position: absolute;
        left: 0;
        top: 0;
    }
</style>
<script type="text/javascript">
var info = document.getElementById("info");
var btn = document.getElementById("upload");
var btnStartUpload = document.getElementById("btnstart");
var btnstar = document.getElementById("trstartup");
var per = document.getElementById("percent");
var fileinfo = {};
var vcop = null;
var uoploader=null;  // 上传
var fileId = "${diveCourse.fileId}";
var fileStatus = false;

	$(function() {
		parent.$.messager.progress('close');	
		vcop = new Q.vcopClient({
	        appKey:"2a1b15cfc6154a2088f82f9eab17a52d",  // 填写申请的app key 
	        appSecret:"2fcf5d737755ef904167bf570a90b2f0" // 填写app secret
	        //managerUrl:"http://openapi.iqiyi.com/",
	       // uploadUrl:"http://upload.iqiyi.com/",
			//needMeta:false
	    });
		resetBtn();
		getEntAuth();
		
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveCourseController/edit',
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
	});
	
	function resetBtn(){
        vcop.resetBtn({
            uploadBtn:{dom:btn,
                btnW:"100px",
                btnH:"100px",
                btnT:"100px",
                btnL:"100px",
                btnZ:"999",
                hasBind:false
            }
        });
    }
	
	// 5-27 企业级授权
    function getEntAuth(flag){
        vcop.getAuthEnterprise(function (data) {
            if(data){
                vcop.authtoken = data.data.access_token;
                if(fileId) getStatus();
                else info.innerHTML = '尚未上传视频';
                /* if(/msie/.test(navigator.userAgent.toLowerCase())){
                    initUpload();
                }
                
                if(uoploader){
                    uoploader.initOneFile({btnW:"100px",btnH:"100px",btnT:"100px",btnL:"12px"});
                } */
                if(flag) initUpload();
            }
        });
    }
	
    function initUpload() {
        if (!vcop.authtoken) {
            getEntAuth(true);
        } else {
            uoploader = vcop.initUpload({
				slicesize:1024*128,
                access_token:vcop.authtoken, 
                //device_id:"test", 
                //uid:"test",
                allowType:["xv","avi","dat","mpg","mpeg","vob","mkv","mov","wmv","asf","rm","rmvb","ram","flv","mp4","3gp","dv","qt","divx","cpk","fli","flc","m4v","pfv"]  // 重置类型
                }, {
                onSuccess:function (data) {
                    if(data && data.data){
                       // info.innerHTML = data.data.file_id;
                        fileinfo = data.data;
                        btnstar.style.display = "block";
                        btnStartUpload.style.display="block";
                        $("#fileId").val(data.data.file_id);
                    }
                },
                onError:function (data) {
                    if (data && data.msg) {
                        info.innerHTML = data.msg;
                    } else {
                        info.innerHTML = "网络错误";
                    }
                }}
            );
        }

    }
    
    var breakdown=false;
    function sartUpload() {
    	if(fileStatus) {
    		vcop.delVideoById({delete_type:1, file_ids:fileId},
   	   	        function (data) {
   		    		if(data.code == 'A00000') {
   		    			upload();
   		    		}
   	   	        }
   	        );
    	} else {
    		upload();
    	}
    	
    }
    
    function upload() {
    	vcop.setMetadata({
            file_id:fileinfo.file_id,
            file_name:"${diveCourse.title}",
            description:"{description:'${diveCourse.content}',type:'${diveCourse.courseType}',typeZh:'${diveCourse.courseTypeZh}'}", 
            uploader:uoploader         
        }, function (data) {
            if(data.code == 'A00000') {
            	uoploader.startUpload(fileinfo, {
                    onFinish:function (data) {
                        if (data && data.manualFinish === true) {
                            uoploader.finishUpload({
                                onSuccess:function () {
                                	getStatus();
                                },
                                onError:function () {
                                    info.innerHTML = "上传失败";
                                }
                            });
                        } else {
                        	getStatus();
                        }
                        setTimeout(function () {
                            uoploader.delLocal(fileinfo.file_name,fileinfo.file_id);     
                            resetPer(true);
                        }, 2000);
                    },
                    onError:function (data) {
                        if(data.msg){
                            //info.innerHTML = data.msg;
                            // 续传
                            if(data.msg=='network break down'){
                                breakdown=true;
                                uoploader.pauseUpload();
                            }
                        } else{
                            info.innerHTML = "上传失败";
                        }
                        
                    },
                    onProgress:function (data) {    // 5/7 增加速度，剩余时间
                        per.style.width = data.percent + "%";
                        info.innerHTML="上传中....速度："+data.speed.toFixed(2)+"kb/s , 剩余时间："+parseInt(data.remainTime) + "s";
                    }
                });
                btnStartUpload.style.display = "none";
                btn.style.display = "none";
            } else {
            	info.innerHTML = data.msg;
            }
        });
    }
    
    function pauseUpload() {
        uoploader.pauseUpload(function(data){
            breakdown=data;
        });
    }
    
    function resumeUpload() {
        uoploader.resumeUpload({
            onFinish:function (data) {
                if (data && data.manualFinish === true) {
                    uoploader.finishUpload({
                        onSuccess:function () {
                            //info.innerHTML = "上传成功";
                        	getStatus();
                        },
                        onError:function () {
                            info.innerHTML = "上传失败";
                        }
                    });
                } else {
                	//info.innerHTML = "上传成功";
                	getStatus();
                }
                setTimeout(function () {
                	uoploader.delLocal(fileinfo.file_name,fileinfo.file_id);
                    resetPer(true);
                }, 600);
            },
            onError:function (data) {
                info.innerHTML = "上传失败";
            },
            onProgress:function (data) {
                per.style.width = data.percent + "%";
                info.innerHTML="上传中....速度："+data.speed.toFixed(2)+"kb/s , 剩余时间："+parseInt(data.remainTime) + "s";
            }
        },breakdown);   // 续传传参
    }
    
    function cancelUpload() {
        uoploader.cancelUpload({
            onSuccess:function (data) {                
                info.innerHTML = "已取消";
                setTimeout(function () {
                    resetPer();
                }, 600);
            },
            onError:function (data) {
                if(data && data.code)
                    info.innerHTML = "取消失败";
                else
                    info.innerHTML = "网络错误";
            }
        });
    }
    
    function delFile() {
        vcop.delVideoById({delete_type:1, file_ids:fileId},
	        function (data) {
	            if(data)  info.innerHTML = JSON.stringify(data);
	        }
       	);
    }
    
    function getStatus(){  
        vcop.getFileStatus({
            file_id:fileId
        },function (data) {
        	info.innerHTML = JSON.stringify(data);
            if(data.code == 'A00000') {
            	info.innerHTML = '已经发布';
            	fileStatus = true;
            } else if(data.code == 'A00001') {
            	info.innerHTML = '发布中';
            	fileStatus = true;
            } else if(data.code == 'A00002') {
            	info.innerHTML = '审核未通过';
            } else if(data.code == 'A00003') {
            	info.innerHTML = '视频不存在';
            } else if(data.code == 'A00004') {
            	info.innerHTML = '上传中';
            	fileStatus = true;
            } else if(data.code == 'A00007') {
            	if(data.data && data.data.is_repeat == 1) {
            		info.innerHTML = "上传失败，重复上传";
            	} else {
            		info.innerHTML = "视频上传失败";
            	}
            } else {
            	info.innerHTML = "视频上传失败";
            }
        });
    }
    
    function resetPer(flag) {
        per.style.width = "0%";
        btnstar.style.display = "none";
        info.innerHTML='';
        uoploader=null;
        btn.style.display='';
        if(flag) $("#form").submit();
    }
    
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<form id="form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${diveCourse.id}" />
			<input type="hidden" name="fileId" id="fileId"/>
		</form>
		<table class="table table-hover table-condensed">
			<tr>
				<td>
					<div id="upload"><a href="javascript:void(0);" onclick="initUpload();">上传</a></div>
				</td>
			</tr>
			<tr height="80" style="display: none;" id="trstartup">
				<td>
					<div class="all_percent">
					    <a href="javascript:void(0);" onclick="sartUpload();" style="position:absolute;top:30px;" id="btnstart">开始上传</a>
					    <a href="javascript:void(0);" onclick="pauseUpload();" style="position:absolute;top:30px;left:70px;">暂停</a>
					    <a href="javascript:void(0);" onclick="resumeUpload();" style="position:absolute;top:30px;left:140px;">恢复</a>
					    <a href="javascript:void(0);" onclick="cancelUpload();" style="position:absolute;top:30px;left:210px;">取消</a>
					   <!--  <a href="javascript:void(0);" onclick="setMeta();" style="position:absolute;top:30px;left:280px;">设置信息</a> -->
					    
					    <div class="percent" id="percent"></div>
					    <div class="percent_text" ><span></span></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<span id="info"></span>
				</td>
			</tr>
			
		</table>
	</div>
</div>