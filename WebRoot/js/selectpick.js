/**
 * @插件名 selectpick
 * @作用 用Jquery开发的一款美化下拉框插件
 * @作者 郜仕伟
 * @日期 2014-11-25
 * @修改者 iulog.com
 * @修改日期 2016-01-28
 * @Version 1.0
 */
;
(function($, window, document, undefined) {

   $.fn.selectpick = function(options) {
      // selectpick的配置
      var selectpick_config = {
		 container:"body",//模拟select生成的DIV存放的父容器
         height: 30,
         width: 150,
        // optionColor: "#3BAFDA",
        // selectedColor:"#3BAFDA",
         disabled: false, // 是否禁用,默认false
        // selectText: "", // 设置哪个文本被选中
         onSelect: "" // 点击后选中事件
      }
      var settings = $.extend({}, selectpick_config, options);
	  
      // 每个下拉框组件的操作
      return this.each(function(elem_id) {
         var obj = this;
         var _offset = $(this).offset();
         var top = _offset.top + $(document).scrollTop();
         var elem_width = $(obj).width();
         var left = _offset.left + $(document).scrollLeft();
         var elem_id = $(obj).attr("id"); // 元素的ID
         // 生成的div的样式
         var _selectBody = "<div class='selectpick_div_box' onselectstart='return false;'><div class='selectpick_div selectpick_div_" + elem_id + "'  id='selectpick_" + elem_id + "'><span id='selectpick_span_" + elem_id + "'></span><span class='selectpick_icon' id='selectpick_icon_" + elem_id + "'></span></div><div class='selectpick_options selectpick_options_" + elem_id + "'></div></div>";
         $(_selectBody).appendTo(settings.container);
         $(obj).addClass("select_hide");

         // 设置selectpick显示的位置
         $(".selectpick_div_" + elem_id).css({
            "height": settings.height,
            "width": settings.width
         });
		 
		 //设置默认显示在div上的值为当前select的选中值
		 $(".selectpick_div_" + elem_id + " span").first().text($(obj).find("option:selected").text());

         // 是否禁用下拉框
         if(settings.disabled){
        	 $(".selectpick_div_" + elem_id).addClass("selectpick_no_select");
        	 return;
         }
         // 点击div显示列表
         $(".selectpick_div_" + elem_id + ",#selectpick_span_" + elem_id + ",#selectpick_options_" + elem_id + "").bind("click", function(event) {
            var selected_text = $(".selectpick_div_" + elem_id + " span").first().text(); // 当前div中的值
            event.stopPropagation(); //  阻止事件冒泡

            if ($(".selectpick_ul_" + elem_id + " li").length > 0) {
               // 隐藏和显示div
               $(".selectpick_options_" + elem_id).empty().hide();
               return;
            } else {
			   $(".selectpick_options").hide();
               $(".selectpick_options_" + elem_id).show();
               $(".selectpick_options ul li").remove();
               // 添加列表项
               var ul = "<ul class='selectpick_ul_" + elem_id + "'>";
               $(obj).children("option").each(function() {
                  if ($(this).text() == selected_text) {
                     ul += "<li class='selectpick_options_selected'><label style='display:none;'>" + $(this).val() + "</label><label>" + $(this).text() + "</label></li>";
                  } else {
                     ul += "<li style='height:" + (settings.height - 3) + "px; line-height:" + (settings.height - 3) + "px;'><label style='display:none;'>" + $(this).val() + "</label><label>" + $(this).text() + "</label></li>";
                  }
               });
               ul += "</ul>";
               $(".selectpick_options_" + elem_id).css({
                  "width": settings.width + 5,
                  "left": 0,
                  "top": settings.height
               }).append(ul).show();
			   
               // 每个li点击事件
               $(".selectpick_ul_" + elem_id + " li").bind("click", function() {
                  $(".selectpick_div_" + elem_id + " span").first().text($(this).children("label").first().next().text());
                  $(".selectpick_options_" + elem_id).empty().hide();
				  $(obj).val($(this).children("label").first().text());//设置下拉框的值
                  // 回调函数
                  if (settings.onSelect != undefined && settings.onSelect != "" && typeof settings.onSelect == "function") {
                     settings.onSelect($(this).children("label").first().text(), $(this).children("label").first().next().text());
                  }
               });
            }

         });
         // 点击div外面关闭列表
         $(document).bind("click", function(event) {
            var e = event || window.event;
            var elem = e.srcElement || e.target;
            if (elem.id == "selectpick_" + elem_id || elem.id == "selectpick_icon_" + elem_id || elem.id == "selectpick_span_" + elem_id) {
               return;
            } else {
               $(".selectpick_options_" + elem_id).empty().hide();
            }
         });

      });
   }
})(jQuery, window, document);