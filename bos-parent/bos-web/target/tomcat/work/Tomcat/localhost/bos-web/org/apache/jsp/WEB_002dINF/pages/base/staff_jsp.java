/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2016-12-29 12:48:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.base;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class staff_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<!-- 导入jquery核心类库 -->\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("<!-- 导入easyui类库 -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/portal.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/default.css\">\t\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.portal.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.cookie.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"\r\n");
      out.write("\ttype=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction doAdd(){\r\n");
      out.write("\t\t//alert(\"增加...\");\r\n");
      out.write("\t\t$('#addStaffWindow').window(\"open\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doView(){\r\n");
      out.write("\t\t$(\"#searchStaffWindow\").window(\"open\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doDelete(){\r\n");
      out.write("\t\tvar arr = $(\"#grid\").datagrid(\"getSelections\");\r\n");
      out.write("\t\tif(arr.length==0||arr==null){\r\n");
      out.write("\t\t\t$.messager.alert(\"警告\",\"至少选中一行\",\"warning\");\r\n");
      out.write("   \t     return ;\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tvar idsArr = new Array();\r\n");
      out.write("\t\t\tfor(var i = 0; i<arr.length;i++){\r\n");
      out.write("\t\t\t\tidsArr.push(arr[i].id);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar ids = idsArr.join(\",\");\r\n");
      out.write("\t\t\t $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/staffAction_batchDel\",{\"ids\":ids},function(data){\r\n");
      out.write("\t\t\t\t if(data){\r\n");
      out.write("\t\t\t    \t $.messager.alert(\"恭喜\",\"批量修改成功\",\"info\"); \r\n");
      out.write("\t\t\t     }else{\r\n");
      out.write("\t\t\t    \t $.messager.alert(\"错误\",\"批量作废失败\",\"error\");\r\n");
      out.write("\t\t\t     }\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$(\"#grid\").datagrid(\"reload\");\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doRestore(){\r\n");
      out.write("\t\talert(\"将取派员还原...\");\r\n");
      out.write("\t}\r\n");
      out.write("\t//工具栏\r\n");
      out.write("\tvar toolbar = [ {\r\n");
      out.write("\t\tid : 'button-view',\t\r\n");
      out.write("\t\ttext : '查询',\r\n");
      out.write("\t\ticonCls : 'icon-search',\r\n");
      out.write("\t\thandler : doView\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'button-add',\r\n");
      out.write("\t\ttext : '增加',\r\n");
      out.write("\t\ticonCls : 'icon-add',\r\n");
      out.write("\t\thandler : doAdd\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'button-delete',\r\n");
      out.write("\t\ttext : '作废',\r\n");
      out.write("\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\thandler : doDelete\r\n");
      out.write("\t},{\r\n");
      out.write("\t\tid : 'button-save',\r\n");
      out.write("\t\ttext : '还原',\r\n");
      out.write("\t\ticonCls : 'icon-save',\r\n");
      out.write("\t\thandler : doRestore\r\n");
      out.write("\t}];\r\n");
      out.write("\t// 定义列\r\n");
      out.write("\tvar columns = [ [ {\r\n");
      out.write("\t\tfield : 'id',\r\n");
      out.write("\t\tcheckbox : true,\r\n");
      out.write("\t},{\r\n");
      out.write("\t\tfield : 'name',\r\n");
      out.write("\t\ttitle : '姓名',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tsortable:true\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'telephone',\r\n");
      out.write("\t\ttitle : '手机号',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tstyler: function(value,row,index){\r\n");
      out.write("\t\t\t// value 当前key对应值  match 匹配目标对象\r\n");
      out.write("\t\t\t if(value.match(8)){\r\n");
      out.write("\t\t\t\t return 'background-color:#ffee00;color:red;';\r\n");
      out.write("\t\t\t }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'haspda',\r\n");
      out.write("\t\ttitle : '是否有PDA',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tformatter : function(data,row, index){\r\n");
      out.write("\t\t\tif(data==\"1\"){\r\n");
      out.write("\t\t\t\treturn \"有\";\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\treturn \"无\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'deltag',\r\n");
      out.write("\t\ttitle : '是否作废',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tformatter : function(data,row, index){\r\n");
      out.write("\t\t\tif(data==\"0\"){\r\n");
      out.write("\t\t\t\treturn \"正常使用\"\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\treturn \"已作废\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'standard',\r\n");
      out.write("\t\ttitle : '取派标准',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center'\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'station',\r\n");
      out.write("\t\ttitle : '所谓单位',\r\n");
      out.write("\t\twidth : 200,\r\n");
      out.write("\t\talign : 'center'\r\n");
      out.write("\t} ] ];\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t// 先将body隐藏，再显示，不会出现页面刷新效果\r\n");
      out.write("\t\t$(\"body\").css({visibility:\"visible\"});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 取派员信息表格\r\n");
      out.write("\t\t$(\"#grid\").datagrid( {\r\n");
      out.write("\t\t\ticonCls : \"icon-forward\",\r\n");
      out.write("\t\t\tfit : true,\r\n");
      out.write("\t\t\tborder : false,\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\tstriped : true,\r\n");
      out.write("\t\t\tpageList: [5,10,15],\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\ttoolbar : toolbar,\r\n");
      out.write("\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/staffAction_pageQuery\",\r\n");
      out.write("\t\t\tidField : \"id\",\r\n");
      out.write("\t\t\tcolumns : columns,\r\n");
      out.write("\t\t\tonDblClickRow : doDblClickRow\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 添加取派员窗口\r\n");
      out.write("\t\t$('#addStaffWindow').window({\r\n");
      out.write("\t        title: '添加取派员',\r\n");
      out.write("\t        width: 400,\r\n");
      out.write("\t        modal: true,\r\n");
      out.write("\t        shadow: true,\r\n");
      out.write("\t        closed: true,\r\n");
      out.write("\t        height: 400,\r\n");
      out.write("\t        resizable:false,\r\n");
      out.write("\t        onBeforeClose:function(){\r\n");
      out.write("\t        \t// 清空表单数据  尤其 隐藏域Id    reset 重置  \r\n");
      out.write("\t        \t$(\"#sform\")[0].reset();//  能否清除隐藏域 不能!\r\n");
      out.write("\t        \t$(\"#id\").val(\"\")//  清除隐藏id  为添加取派员业务提供支持\r\n");
      out.write("\t        }\r\n");
      out.write("\t    });\r\n");
      out.write("\t\t$('#searchStaffWindow').window({\r\n");
      out.write("\t        title: '条件查询',\r\n");
      out.write("\t        width: 300,\r\n");
      out.write("\t        modal: true,\r\n");
      out.write("\t        shadow: true,\r\n");
      out.write("\t        closed: true,\r\n");
      out.write("\t        height: 350,\r\n");
      out.write("\t        resizable:false\r\n");
      out.write("\t    });\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#save\").click(function(){\r\n");
      out.write("\t\t\tvar flag = $(\"#sform\").form(\"validate\");\r\n");
      out.write("\t\t\tif(flag){\r\n");
      out.write("\t\t\t\t$(\"#sform\").submit();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"#addStaffWindow\").window(\"close\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//条件查询\r\n");
      out.write("\t\t$(\"#search\").click(function(){\r\n");
      out.write("\t\t\t  //  让datagrid 发送url地址的请求...\r\n");
      out.write("\t\t\t $(\"#grid\").datagrid(\"load\",{name:$(\"#sname\").val(),telephone:$(\"#stelephone\").val(),station:$(\"#sstation\").val()});\r\n");
      out.write("\t\t\t   // 窗体关闭\r\n");
      out.write("\t\t\t   $('#searchStaffWindow').window(\"close\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doDblClickRow(rowIndex,rowData){\r\n");
      out.write("\t\t$(\"#addStaffWindow\").window(\"open\");\r\n");
      out.write("\t\t$(\"#tel\").validatebox(\"remove\");  \r\n");
      out.write("\t\t$(\"#sform\").form(\"load\",rowData);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$.extend($.fn.validatebox.defaults.rules, { \r\n");
      out.write("\r\n");
      out.write("\t\ttelephone: { \r\n");
      out.write("\r\n");
      out.write("\t\tvalidator: function(value, param){ \r\n");
      out.write("\t\t\tvar reg =/^1[3|4|5|7|8]\\d{9}$/;\r\n");
      out.write("\t\t       return reg.test(value); \r\n");
      out.write("\r\n");
      out.write("\t\t}, \r\n");
      out.write("\r\n");
      out.write("\t\tmessage: '请输入正确的手机号码' \r\n");
      out.write("\r\n");
      out.write("\t\t} , uniquePhone:{\r\n");
      out.write("\t        validator: function (value) {  \r\n");
      out.write("\t            var flag;\r\n");
      out.write("\t            $.ajax({  \r\n");
      out.write("                    url : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/staffAction_validTelephone\",  \r\n");
      out.write("                    type : 'POST',                    \r\n");
      out.write("                    timeout : 60000,  \r\n");
      out.write("                    data:{\"telephone\" : value},  \r\n");
      out.write("                    async: false,    \r\n");
      out.write("                    success : function(data, textStatus, jqXHR) {     \r\n");
      out.write("                       flag=data;\r\n");
      out.write("                    }  \r\n");
      out.write("                });  \r\n");
      out.write("\t            if(!flag){  \r\n");
      out.write("\t            \t// 样式效果\r\n");
      out.write("                    $(\"#tel\").removeClass('validatebox-invalid');  \r\n");
      out.write("                }  \r\n");
      out.write("                return flag;  \r\n");
      out.write("\t        },  \r\n");
      out.write("\t        message: '手机号已经存在'  \r\n");
      out.write("\t    }  \r\n");
      out.write("});\r\n");
      out.write("//清楚手机格式\r\n");
      out.write("\t\t$.extend($.fn.validatebox.methods, {\r\n");
      out.write("\t\t    remove: function (jq, newposition) {\r\n");
      out.write("\t              return $(jq).removeClass(\"validatebox-text validatebox-invalid\");\r\n");
      out.write("\t\t    },\r\n");
      out.write("\r\n");
      out.write("\t\t    reduce: function (jq, newposition) {\r\n");
      out.write("\t\t        return jq.each(function () {\r\n");
      out.write("\t\t            var opt = $(this).data().validatebox.options;\r\n");
      out.write("\t\t            $(this).addClass(\"validatebox-text\").validatebox(opt);\r\n");
      out.write("\t\t        });\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\t\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" style=\"visibility:hidden;\">\r\n");
      out.write("\t<div region=\"center\" border=\"false\">\r\n");
      out.write("    \t<table id=\"grid\"></table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"easyui-window\" title=\"对取派员进行添加或者修改\" id=\"addStaffWindow\" collapsible=\"false\" minimizable=\"false\" maximizable=\"false\" style=\"top:20px;left:200px\">\r\n");
      out.write("\t\t<div region=\"north\" style=\"height:31px;overflow:hidden;\" split=\"false\" border=\"false\" >\r\n");
      out.write("\t\t\t<div class=\"datagrid-toolbar\">\r\n");
      out.write("\t\t\t\t<a id=\"save\" icon=\"icon-save\" href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" >保存</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div region=\"center\" style=\"overflow:auto;padding:5px;\" border=\"false\">\r\n");
      out.write("\t\t\t<form id=\"sform\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/staffAction_save\" method=\"post\">\r\n");
      out.write("\t\t\t\t<table class=\"table-edit\" width=\"80%\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t<tr class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">取派员信息</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<!-- TODO 这里完善收派员添加 table -->\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>姓名</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"id\" id=\"id\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"name\" class=\"easyui-validatebox\" required=\"true\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>手机</td><!-- validType=\"['telephone','uniquePhone'] -->\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"text\" id=\"tel\" name=\"telephone\" class=\"easyui-validatebox\" data-options=\"required:true,validType:['telephone','uniquePhone']\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>单位</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"text\" name=\"station\" class=\"easyui-validatebox\" required=\"true\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"haspda\" value=\"1\" />\r\n");
      out.write("\t\t\t\t\t\t是否有PDA</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>取派标准</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"standard\" class=\"easyui-combobox\" required=\"true\"\r\n");
      out.write("\t\t\t\t\t\t\tdata-options=\"editable:false,required:true,valueField:'name',textField:'name',url:'standardAction_ajaxList'\"/>  \r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"easyui-window\" title=\"条件查询\" id=\"searchStaffWindow\" collapsible=\"false\" minimizable=\"false\" maximizable=\"false\" style=\"top:20px;left:200px\">\r\n");
      out.write("\t\t<div region=\"north\" style=\"height:31px;overflow:hidden;\" split=\"false\" border=\"false\" >\r\n");
      out.write("\t\t\t<div class=\"datagrid-toolbar\">\r\n");
      out.write("\t\t\t\t<a id=\"search\" icon=\"icon-save\" href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" >查询</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div region=\"center\" style=\"overflow:auto;padding:5px;\" border=\"false\">\r\n");
      out.write("\t\t\t<form id=\"searchStaffForm\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/staffAction_pageQuery\" method=\"post\">\r\n");
      out.write("\t\t\t\t<table class=\"table-edit\" width=\"80%\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t<tr class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">查询取派员信息</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>姓名</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"name\" id=\"sname\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>手机</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"text\" id=\"stelephone\" name=\"telephone\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>单位</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"text\" name=\"station\" id=\"sstation\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\t");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
