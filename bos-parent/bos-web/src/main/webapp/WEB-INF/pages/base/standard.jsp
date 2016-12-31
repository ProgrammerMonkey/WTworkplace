<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>取派标准</title>
		<!-- 导入jquery核心类库 -->
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
			$(function(){
				// 先将body隐藏，再显示，不会出现页面刷新效果
				$("body").css({visibility:"visible"});
				
				// 收派标准信息表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : false,
					rownumbers : true,
					striped : true,
					pageList: [1,2,3],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/standardAction_pageQuery",
					idField : 'id',
					columns : columns
				});
				// 添加取派员窗口
				$('#addStandardWindow').window({
			        title: '取派员操作',
			        width: 600,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false,
			        onBeforeClose:function(){
			        	//   清除form 表单数据 尤其  隐藏id 一定要清除  reset   jquery --->Dom
			        	  $("#addStandardForm")[0].reset();//  text  
			        	 $("#tel").removeClass('validatebox-invalid');  
			             $("#id").val("");  //  一定将隐藏id 值清除 // hidden
			        }
			    });
				
				$("#save").click(function(){
					var flag = $("#addStandardForm").form("validate");
					if(flag){
						$("#addStandardForm").submit();
					}
				});
			});	
			
			//工具栏
			var toolbar = [ {
				id : 'button-add',
				text : '增加',
				iconCls : 'icon-add',
				handler : function(){
					$('#addStandardWindow').window("open");
				}
			}, {
				id : 'button-edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function(){
					alert('修改');
				}
			},{
				id : 'button-delete',
				text : '作废',
				iconCls : 'icon-cancel',
				handler : function(){
				var arr	=$("#grid").datagrid("getSelections");
				if(arr==null||arr.length==0){
					$.messager.alert("警告","请至少选择一行","warning");
					return;
				}else{
					var idsArr = new Array();
					for(var i=0;i<arr.length;i++){
						idsArr.push(arr[i].id);
						}
					var ids = idsArr.join(",");
				//$.post("${pageContext.request.contextPath}/standardAction_batchDel",{"ids":ids},function(data){
				$.post("${pageContext.request.contextPath}/standardAction_batchDel",{"ids":ids},function(data){	
					//alert(data);
					if(data){
						$.messager.alert("恭喜","删除成功","info");
					}else{
						$.messager.alert("失败","删除失败","error");
					}
						$("#grid").datagrid("reload");  
				});
				}
				}
			},{
				id : 'button-restore',
				text : '还原',
				iconCls : 'icon-save',
				handler : function(){
					alert('还原');
				}
			}];
			
			// 定义列
			var columns = [ [ {
				field : 'id',
				checkbox : true
			},{
				field : 'name',
				title : '标准名称',
				width : 120,
				align : 'center'
			}, {
				field : 'minweight',
				title : '最小重量',
				width : 120,
				align : 'center'
			}, {
				field : 'maxweight',
				title : '最大重量',
				width : 120,
				align : 'center'
			}, {
				field : 'minlength',
				title : '最小长度',
				width : 120,
				align : 'center'
			}, {
				field : 'maxlength',
				title : '最大长度',
				width : 120,
				align : 'center'
			}, {
				field : 'operator',
				title : '操作人',
				width : 120,
				align : 'center'
			}, {
				field : 'operationtime',
				title : '操作时间',
				width : 120,
				align : 'center'
			}, {
				field : 'operatorcompany',
				title : '操作单位',
				width : 120,
				align : 'center'
			} ] ];
			
		
			
		</script>
	</head>

	<body class="easyui-layout" style="visibility:hidden;">
		<div region="center" border="false">
			<table id="grid"></table>
		</div>
		<!-- 添加收派员窗体  -->
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStandardWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="javascript:;" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStandardForm" method="post" action="${pageContext.request.contextPath }/standardAction_save">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派标准信息</td>
					</tr>
					<tr>
						<td>收派标准名称</td>
						<td>
						<input type="hidden" name="id" id="id"/>
						<input type="text" name="name" class="easyui-combobox" 
						data-options="editable:false,required:true,valueField:'name',textField:'name',url:'json/standard_name.json'"/></td>
					</tr>
					<tr>
						<td>最小重量</td>
						<td><input id="xx" type="text" name="minweight" class="easyui-combobox" 
						   data-options="editable:false,required:true,valueField:'minweight',textField:'minweight',url:'json/standard_minweight.json'"/>
						   </td>
					</tr>
					<tr>
						<td>最大重量</td>
						<td><input type="text" name="maxweight" class="easyui-combobox" 
						data-options="editable:false,required:true,valueField:'maxweight',textField:'maxweight',url:'json/standard_maxweight.json'"/>
						</td>
					</tr>
					<tr>
						<td>最小长度</td>
						<td><input type="text" name="minlength" class="easyui-numberbox"
						data-options="required:true"/>
						</td>
					</tr>
					<tr>
						<td>最大长度</td>
						<td><input type="text" name="maxlength" class="easyui-validatebox" 
						data-options="required:true"/>
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	</body>

</html>