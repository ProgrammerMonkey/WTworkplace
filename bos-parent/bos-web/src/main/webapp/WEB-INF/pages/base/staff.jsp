<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		$("#searchStaffWindow").window("open");
	}
	
	function doDelete(){
		var arr = $("#grid").datagrid("getSelections");
		if(arr.length==0||arr==null){
			$.messager.alert("警告","至少选中一行","warning");
   	     return ;
		}else{
			var idsArr = new Array();
			for(var i = 0; i<arr.length;i++){
				idsArr.push(arr[i].id);
			}
			var ids = idsArr.join(",");
			 $.post("${pageContext.request.contextPath}/staffAction_batchDel",{"ids":ids},function(data){
				 if(data){
			    	 $.messager.alert("恭喜","批量修改成功","info"); 
			     }else{
			    	 $.messager.alert("错误","批量作废失败","error");
			     }

				$("#grid").datagrid("reload");
			 });
		}
		
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center',
		sortable:true
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center',
		styler: function(value,row,index){
			// value 当前key对应值  match 匹配目标对象
			 if(value.match(8)){
				 return 'background-color:#ffee00;color:red;';
			 }
		}
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$("#grid").datagrid( {
			iconCls : "icon-forward",
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [5,10,15],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staffAction_pageQuery",
			idField : "id",
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false,
	        onBeforeClose:function(){
	        	// 清空表单数据  尤其 隐藏域Id    reset 重置  
	        	$("#sform")[0].reset();//  能否清除隐藏域 不能!
	        	$("#id").val("")//  清除隐藏id  为添加取派员业务提供支持
	        }
	    });
		$('#searchStaffWindow').window({
	        title: '条件查询',
	        width: 300,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 350,
	        resizable:false
	    });
		
		$("#save").click(function(){
			var flag = $("#sform").form("validate");
			if(flag){
				$("#sform").submit();
			}
			$("#addStaffWindow").window("close");
		});
		
		//条件查询
		$("#search").click(function(){
			  //  让datagrid 发送url地址的请求...
			 $("#grid").datagrid("load",{name:$("#sname").val(),telephone:$("#stelephone").val(),station:$("#sstation").val()});
			   // 窗体关闭
			   $('#searchStaffWindow').window("close");
		});
	});
	
	function doDblClickRow(rowIndex,rowData){
		$("#addStaffWindow").window("open");
		$("#tel").validatebox("remove");  
		$("#sform").form("load",rowData);
	}
	
	$.extend($.fn.validatebox.defaults.rules, { 

		telephone: { 

		validator: function(value, param){ 
			var reg =/^1[3|4|5|7|8]\d{9}$/;
		       return reg.test(value); 

		}, 

		message: '请输入正确的手机号码' 

		} , uniquePhone:{
	        validator: function (value) {  
	            var flag;
	            $.ajax({  
                    url : "${pageContext.request.contextPath}/staffAction_validTelephone",  
                    type : 'POST',                    
                    timeout : 60000,  
                    data:{"telephone" : value},  
                    async: false,    
                    success : function(data, textStatus, jqXHR) {     
                       flag=data;
                    }  
                });  
	            if(!flag){  
	            	// 样式效果
                    $("#tel").removeClass('validatebox-invalid');  
                }  
                return flag;  
	        },  
	        message: '手机号已经存在'  
	    }  
});
//清楚手机格式
		$.extend($.fn.validatebox.methods, {
		    remove: function (jq, newposition) {
	              return $(jq).removeClass("validatebox-text validatebox-invalid");
		    },

		    reduce: function (jq, newposition) {
		        return jq.each(function () {
		            var opt = $(this).data().validatebox.options;
		            $(this).addClass("validatebox-text").validatebox(opt);
		        });
		    }
		});





</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对取派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="sform" action="${pageContext.request.contextPath }/staffAction_save" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">取派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td>
						<input type="hidden" name="id" id="id">
						<input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td><!-- validType="['telephone','uniquePhone'] -->
						<td><input type="text" id="tel" name="telephone" class="easyui-validatebox" data-options="required:true,validType:['telephone','uniquePhone']" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-combobox" required="true"
							data-options="editable:false,required:true,valueField:'name',textField:'name',url:'standardAction_ajaxList'"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	<div class="easyui-window" title="条件查询" id="searchStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="search" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >查询</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="searchStaffForm" action="${pageContext.request.contextPath }/staffAction_pageQuery" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询取派员信息</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td>
						<input type="text" name="name" id="sname"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" id="stelephone" name="telephone"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" id="sstation"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	