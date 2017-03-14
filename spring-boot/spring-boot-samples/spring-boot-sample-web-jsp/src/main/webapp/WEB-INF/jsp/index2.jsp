<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
           + request.getServerName() + ":" + request.getServerPort()
           + path + "/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>SVN</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
    href="plugs/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="plugs/icon.css">
<script type="text/JavaScript" src="plugs/jquery.js"></script>
<script type="text/javascript" src="plugs/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function() {
       $('#test').treegrid({
           title : 'SVN列表',
           nowrap : false,
           rownumbers : true,
           collapsible : false,
           url : 'SvnTreeServlet?pid=',
           idField : 'id',
           treeField : 'url',
           frozenColumns : [ [ {
              title : '路径',
              field : 'url',
              width : 350,
              formatter : function(value) {
            	  var str =value.split("/");
                  return '<span style="color:red">' + decodeURI(str[str.length-1]) + '</span>';
              }
           } ] ],
           columns : [ [ {
              field : 'name',
              title : '名称',
              width : 120
           }, {
              field : 'size',
              title : '文件大小(KB)',
              width : 80,
              rowspan : 2
           }, {
              field : 'revision',
              title : '版本号',
              width : 80,
              rowspan : 2
           }, {
              field : 'author',
              title : '作者',
              width : 100,
              rowspan : 2
           }, {
              field : 'date',
              title : '修改日期',
              width : 130,
              rowspan : 2
           }, {
              field : 'commitMessage',
              title : '注释',
              width : 150,
              rowspan : 2
           }, {
              field : 'kind',
              title : '操作',
              width : 120,
              align : 'center',
              rowspan : 2,
              formatter : function(value) {
                  return value=='file' ? '<a onclick="download()" style="cursor: pointer;color:red">下载</a><a onclick="viewHistory()" style="margin-left:5px; cursor: pointer;color:red">历史版本</a>' : '';
              }
           }] ],
           onBeforeExpand : function(row, param) {
              $(this).treegrid('options').url = 'SvnTreeServlet?pid='+encodeURI(row.url);
           }
       });
    });
   
    function download(){
       setTimeout(function(){
           var node = $('#test').treegrid('getSelected');
           if(node !=null)
              window.open("download?url="+encodeURI(decodeURI(node.url))+"&size="+node.size+"&name="+encodeURI(decodeURI(node.name))+"&revision="+node.revision);
       },200);
      
    }
   
    function viewHistory(){
       setTimeout(function(){
           var node = $('#test').treegrid('getSelected');
           if(node != null) {
              window.open("history.jsp?uri="+encodeURI(decodeURI(node.url)),"_blank","height=400,width=700,status=yes,toolbar=no,menubar=no,location=no");
           }
       }, 200);
    }
</script>
 
</head>
<body>
 
<table id="test"></table>
 
</body>
</html>