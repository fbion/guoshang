// JavaScript Document
$(function(){
	// 设置变量
    var n = 0;
    var c = 0;
	// 自动轮播部分---------------------------------------
    // 函数体
    // function run(){
    //    n++;
    //    // 临界点判断
    //    if(n==6){
    //    	 n=0;
    //    }
    //    // 当前图片显示，其他的隐藏
    //    $("#guide_box img").eq(n).fadeIn(800).siblings("img").stop().fadeOut(800);
    //    // 当前li变红,其他的变灰
    //    $("#guide_box ul li").eq(n).css({background:"#b61b1f"}).siblings("li").css({background:"#3e3e3e"});
    // }
    // 定时器
    // timer = setInterval(run,1000);

    // 鼠标移入部分-------------------------------------
    // $("#box").hover(function(){
    // 	// 停止定时器
    //     clearInterval(timer);
    //     // 左右箭头显示
    //     $(".left,.right").css({display:"block"});
    // },function(){
    // 	// 开启定时器
    //     timer = setInterval(run,1000);
    //     // 左右箭头消失
    //     $(".left,.right").css({display:"none"});
    // });

    // li的控制------------------------------------------
     $(".guide_b_li_s").mouseover(function(){
     	// 获得当前序号
     	  n = $(this).parent('li').index();
     	  // document.title=n;

     	  // 当前图片显示，其他的隐藏
         $(".guide_box_img img").eq(n).fadeIn(400).siblings("img").stop().fadeOut(400);
	      // 当前li，其他li变色
	     $(".guide_box_ul").children('li').eq(n).find('.guide_b_li_s').css({background:"url(images/guideieli1.jpg)"}).parent('li').siblings("li").find('.guide_b_li_s').css({background:"url(images/guideieli2.jpg)"});
     });

     // 左点击--------------------------------------------
     $(".guide_left").click(function(){
     	// // 判断是否可点击
      //   if(c==0){
      //   	c=1;  
      //   	// 两秒钟以后变回0      	
      //   	setTimeout(function(){
      //   		c=0;
      //   	},2000);
      //   }else{
      //   	// 停止
      //   	return;
      //   }

     	  n--;
     	  // 最小界点判断
     	  if(n==-1){
     	  	 n=3;
     	  }
     	 // 当前图片显示，其他的隐藏
         $(".guide_box_img img").eq(n).fadeIn(400).siblings("img").stop().fadeOut(400);
	     // 当前li变红,其他的变灰
	     $(".guide_box_ul").children('li').eq(n).find('.guide_b_li_s').css({background:"url(images/guideieli1.jpg)"}).parent('li').siblings("li").find('.guide_b_li_s').css({background:"url(images/guideieli2.jpg)"});
     })

     // 右点击--------------------------------------------
     $(".guide_right").click(function(){
     	  n++;
     	  // 最小界点判断
     	  if(n==4){
     	  	 n=0;
     	  }
     	 // 当前图片显示，其他的隐藏
         $(".guide_box_img img").eq(n).fadeIn(400).siblings("img").stop().fadeOut(400);
       // 当前li变红,其他的变灰
       $(".guide_box_ul").children('li').eq(n).find('.guide_b_li_s').css({background:"url(images/guideieli1.jpg)"}).parent('li').siblings("li").find('.guide_b_li_s').css({background:"url(images/guideieli2.jpg)"});
     })
})