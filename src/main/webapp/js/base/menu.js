$(document).ready(function() {
	$('.inactive').click(function(){
		var $oe_overlay	= $('#oe_overlay');
		if($(this).siblings('ul').css('display') == 'none'){
			//显示背景半透明
			$oe_overlay.stop(true,true).fadeTo(200, 0.6);
			window.setTimeout(function(){
				$oe_overlay.show();
			},200); 
			//显示下拉
			$(this).parent('li').siblings('li').removeClass('inactives');
			$(this).addClass('inactives');
			$(this).siblings('ul').slideDown(100).children('li');
			var item = $(this).parents('li').siblings('li').children('ul');
			if($(this).parents('li').siblings('li').children('ul').css('display') == 'block'){
				$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
				$(this).parents('li').siblings('li').children('ul').slideUp(100);
			}
		}else{
			//控制自身变成+号
			$(this).removeClass('inactives');
			//控制自身菜单下子菜单隐藏
			$(this).siblings('ul').slideUp(100);
			//控制自身子菜单变成+号
			$(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
			//控制自身菜单下子菜单隐藏
			$(this).siblings('ul').children('li').children('ul').slideUp(100);
			//控制同级菜单只保持一个是展开的（-号显示）
			$(this).siblings('ul').children('li').children('a').removeClass('inactives');
			//隐藏背景半透明
			$oe_overlay.stop(true,true).fadeTo(200, 0);
			window.setTimeout(function(){
				$oe_overlay.hide();
			},200); 
		}
	})
});
$(function() {
	var $oe_menu		= $('#oe_menu');
	var $oe_menu_items	= $oe_menu.children('li');
	var $oe_overlay		= $('#oe_overlay');

	$oe_menu_items.bind('mouseenter',function(){
		var $this = $(this);
		$this.addClass('slided selected');
		$this.children('div').css('z-index','9999').stop(true,true).slideDown(200,function(){
			$oe_menu_items.not('.slided').children('div').hide();
			$this.removeClass('slided');
		});
	}).bind('mouseleave',function(){
		var $this = $(this);
		$this.removeClass('selected').children('div').css('z-index','1');
		$this.children('ul').hide();
		$this.children('a').removeClass('inactives');
	});

	$oe_menu.bind('mouseenter',function(){
		var $this = $(this);
		$oe_overlay.stop(true,true).fadeTo(200, 0.6);
		$this.addClass('hovered');
	}).bind('mouseleave',function(){
		var $this = $(this);
		$this.removeClass('hovered');
		$oe_overlay.stop(true,true).fadeTo(200, 0);
		window.setTimeout(function(){
			$oe_overlay.hide();
		},200); 
		$oe_menu_items.children('div').hide();
	})
});
