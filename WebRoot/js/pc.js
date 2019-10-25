    function confirmdialog(){
       if(window.confirm("您确定要删除此条信息？")){
       return true;
       }
       else{
     //  alert("取消删除！");
       return false;
       }      
    }
    window.onload=function(){//定时器每秒调用一次fnDate()
    	setInterval(function(){
    	fnDate();
    	},1000);
    }
    //js 获取当前时间
    function fnDate(){
    	var oDiv=document.getElementById("div1");
    	var date=new Date();
    	var year=date.getFullYear();//当前年份
    	var month=date.getMonth();//当前月份
    	var data=date.getDate();//天
    	var hours=date.getHours();//小时
    	var minute=date.getMinutes();//分
    	var second=date.getSeconds();//秒
    	var time=year+"年"+fnW((month+1))+"月"+fnW(data)+"日"+fnW(hours)+":"+fnW(minute)+":"+fnW(second);
    	oDiv.innerHTML=time;
    }
    //补位 当某个字段不是两位数时补0
    function fnW(str){
    	var num;
    	str>10?num=str:num=str;
    	return num;
    }
    function CloseWebPage(){
     if (navigator.userAgent.indexOf("MSIE") > 0) {
      if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
       window.opener = null;
       window.close();
      } else {
       window.open('', '_top');
       window.top.close();
      }
     }
     else if (navigator.userAgent.indexOf("Firefox") > 0) {
      window.location.href = 'about:blank ';
     } else {
      window.opener = null;
      window.open('', '_self', '');
      window.close();
     }
    }

    function validate()
    {
    var student_id=document.forms[0].student_id.value;
    var leave_person=document.forms[0].leave_person.value;
    var leave_reason=document.forms[0].leave_reason.value;
    var deal_person=document.forms[0].deal_person.value;
    var deal_result=document.forms[0].deal_result.value;
    if(student_id<=0){
    	alert("请输入学号！");
    	return false;
    }else if(leave_person.length<=0){
    	alert("请输入姓名！");
    	return false;
    }else if(leave_reason<=0){
    	alert("请输入请假原因！");
    	return false;
    }else if(deal_person.length<=0){
    	alert("请输入处理人！");
    	return false;
    }else if(deal_result.length<=0){
    	alert("请输入处理结果！");
    	return false;
    }else{
    	return true;
    }
    }   
    function validate1()
    {
    var student_id=document.forms[0].student_id.value;
    var student_name=document.forms[0].student_name.value;
    var clazz=document.forms[0].clazz.value;
    var password=document.forms[0].password.value;
    if(student_id<=0){
    	alert("请输入学号！");
    	return false;
    }else if(student_name.length<=0){
    	alert("请输入姓名！");
    	return false;
    }else if(clazz<=0){
    	alert("请输入班级！");
    	return false;
    }else if(password.length<=0){
    	alert("请输入密码！");
    	return false;
    }else{
    	return true;
    }
    }  
    function validate2()
    {
    var course_name=document.forms[0].course_name.value;
    var name=document.forms[0].name.value;
    var clazz=document.forms[0].clazz.value;
    if(course_name<=0){
    	alert("请输入课程名！");
    	return false;
    }else if(name.length<=0){
    	alert("请输入老师姓名！");
    	return false;
    }else if(clazz<=0){
    	alert("请输入班级！");
    	return false;
    }else{
    	return true;
    }
    }  
    function validate3()
    {
    var name=document.forms[0].name.value;
    var password=document.forms[0].password.value;
    if(name<=0){
    	alert("请输入老师姓名！");
    	return false;
    }else if(password.length<=0){
    	alert("请输入密码！");
    	return false;
    }else{
    	return true;
    }
    }    
    
    function validate4()
    {
    var password=document.forms[0].password.value;
    if(password.length<=0){
    	alert("请输入密码！");
    	return false;
    }else{
    	return true;
    }
    }   
    
    function validate5()
    {
    var student_id=document.forms[0].student_id.value;
    var leave_person=document.forms[0].leave_person.value;
    var leave_reason=document.forms[0].leave_reason.value;
    if(student_id.length<=0){
    	alert("请输入学号！");
    	return false;
    }else if(leave_person.length<=0){
    	alert("请输入请假人！");
    	return false;
    }else if(leave_reason.length<=0){
    	alert("请输入请假原因！");
    	return false;
    }else{
    	return true;
    }
    }   
    
// $(function(){
        //     $(".menu > ul").eq(0).show();
        //     $(".menu h3").click(function(){
        //         $(this).next().stop().slideToggle();
        //         $(this).siblings().next("ul").stop().slideUp();
        //     });
        //     $(".menu > ul > li > a").click(function(){
        //     	var index=$(this).index();
        //         $(this).addClass("selected").parent().siblings().find("a").removeClass("selected");
        //         $(this).addClass("active").parent().siblings().find("a").removeClass("active");

        //     })
        // });
		$(document).ready(function(){

			function myfunction(li,li_a,menu_tab){
				li.click(function(){
				var index=$(this).index();
				menu_tab.eq(index).addClass("active").siblings().removeClass("active");
				li_a.removeClass("selected");
				li_a.eq(index).addClass("selected").siblings().removeClass("selected");
				
			});
			}

			myfunction($(".container .menu .ulmenu1 li"),$(".container .ulmenu1 li a"),$(".container .content .menu1 .tab"));
			myfunction($(".container .menu .ulmenu2 li"),$(".container .ulmenu2 li a"),$(".container .content .menu2 .tab"));
			myfunction($(".container .menu .ulmenu3 li"),$(".container .ulmenu3 li a"),$(".container .content .menu3 .tab"));


			// var li1=$(".container .menu ul li");
			// var lia=$(".container .menu ul li a");
			// var tab1=$(".container .content .menu1 .tab ");

			// li1.click(function(){
			// 	var index=$(this).index();

			// 	tab1.eq(index).addClass("active").siblings().removeClass("active");
			// 	lia.removeClass("selected");
			// 	lia.eq(index).addClass("selected").siblings().removeClass("selected");
			// });

			$(function(){            //ul/li的折叠效果
				$(".menu > ul").eq(0).show();
				 $(".menu h3").click(function(){
				 		//找menu对应的tab
				 		$(".menu_tab > div").removeClass("active");

				 		var val=($(this).next().attr('class'));
				 		var menu_value=(val.substring(val.length-1));
				 		$(".container .content .menu"+menu_value+" .tab:first-child").addClass("active");
				 		$(".container .menu .ulmenu"+menu_value+" li>a").removeClass("selected");
				 		$(".container .menu .ulmenu"+menu_value+" li a").eq(0).addClass("selected");//默认设置为被选中状态
				 		

				 		// $("."+"val").child().child().("selected")
				 		
				 			//这是ul收缩效果
				            $(this).next().stop().slideToggle();
				            $(this).siblings().next("ul").stop().slideUp();
							
				           // if($(".container .ulmenu"+menu_value+" li ").find("a").attr("class")==="selected"){
				           // 		$(".container .content .menu"+menu_value+" .tab:first-child")
				           // }
			            });

			});
			
			$(function(){   // 导航 >
				 $(".container .menu > h3").click(function(){

				 	$(".container .content .A1").empty().text($(this).text());
				 	
				 });
			});
		});



