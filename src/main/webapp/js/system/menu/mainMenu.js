/**
 * 用户登录成功，菜单加载
 */
$(function(){
	/*
	$("#wnav").accordion( {
		animate : false
	});
	addNav(menus);
	InitLeftMenu();
	*/
});

/**
 * 菜单列表
 * @param data
 */
function addNav(data) {
	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.menus, function(j, o) {
			menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="'
					+ o.url + '" ><span class="icon ' + o.icon
					+ '" >&nbsp;</span><span class="nav">' + o.menuname
					+ '</span></a></div></li> ';
		});
		menulist += '</ul>';

		$('#wnav').accordion('add', {
			title : sm.menuname,
			content : menulist,
			iconCls : 'icon ' + sm.icon
		});

	});

	var pp = $('#wnav').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#wnav').accordion('select', t);
}