<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看</title>
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
<script type="text/JavaScript" src="plugs/browser.js"></script>
<script type="text/javascript" src="plugs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugs/layer/1.9.3/layer.js"></script>
<script type="text/javascript">
	$(function() {
		$('#test').treegrid(
				{
					title : 'SVN列表',
					nowrap : false,
					rownumbers : true,
					collapsible : false,
					url : 'SvnHistory?path=${ path}',
					idField : 'id',
					treeField : 'url',
					frozenColumns : [ [ {
						title : '版本',
						field : 'revision',
						width : 50
					} ] ],
					columns : [ [ {
						field : 'date',
						title : '日期',
						width : 150,
						rowspan : 2
					}, {
						field : 'commitMessage',
						title : '提交日志',
						width : 450,
						rowspan : 2
					}, {
						field : 'author',
						title : '作者',
						width : 100,
						rowspan : 2
					} ] ],
					onBeforeExpand : function(row, param) {
						$(this).treegrid('options').url = 'SvnTreeServlet?pid='
								+ encodeURI(row.url);
					},
					onContextMenu : function(e, row) { //这个就是树形菜单加载鼠标右键菜单的代码
						e.preventDefault();
						//屏蔽浏览器的菜单
						$(this).treegrid('unselectAll');
						//清除所有选中项
						$(this).treegrid('select', row.id);
						//选中状态 
						$('#mm').menu('show', {
							left : e.pageX,
							top : e.pageY
						});

					}
				});
	});
	function download() {
			var node = $('#test').treegrid('getSelected');
			if (node != null)
				window.open("download?path=" + encodeURI("${ path}") + "&revision="+ node.revision);

	}

</script>
</head>
<body>
	<table id="test" style="padding: 0"></table>
	<div id="mm" class="easyui-menu" style="width: 80px;">
		<div onclick="download()">下载</div>
	</div>
</body>
</html>