<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<script type="text/javascript" src="plugs/layer/1.9.3/layer.js"></script>
<script type="text/javascript">
	$(function() {
		$('#test').treegrid(
				{
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
						width : 900,
						formatter : function(value) {
							var str = value.split("/");
							return '<span style="color:red">'
									+ decodeURI(str[str.length - 1])
									+ '</span>';
						}
					} ] ],
					columns : [ [ {
						field : 'date',
						title : '修改日期',
						width : 150,
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
		setTimeout(function() {
			var node = $('#test').treegrid('getSelected');
			if (node != null)
				window.open("download?path=" + encodeURI(decodeURI(node.url))
						+ "&size=" + node.size + "&name="
						+ encodeURI(decodeURI(node.name)) + "&revision="
						+ node.revision);
		}, 200);

	}

	function history() {
					var node = $('#test').treegrid('getSelected');
					if (node != null) {
						//window.open("SvnHistory?path=" + encodeURI(decodeURI(node.url)), "_blank", "height=400,width=700,status=yes,toolbar=no,menubar=no,location=no");
						var index = parent.layer
						.open({
							type : 2,
							btn : [ '确定' ],
							yes : function(index, layero) {
								parent.layer.closeAll();
							},
							cancel : function(index) {
								
							},
							success : function(layero, index) {
								
							},
							title : '历史记录',
							area : [ '800px', '600px' ],
							content : 'jumpToCheck?path='+encodeURI(decodeURI(node.url))
						});
					} 
	}
</script>

</head>
<body>

	<div id="mm" class="easyui-menu" style="width: 80px;">
		<div onclick="check()">查看详细信息</div>
		<div onclick="history()">查看历史</div>
		<div onclick="download()">下载</div>
	</div>
	<table id="test" style="padding: 0"></table>

</body>
</html>