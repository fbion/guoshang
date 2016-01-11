// JavaScript Document
$(function(){
	// 设置变量
    var n = 0;
    var c = 0;
  //图片地址信息
  var img_add=$("#img_add").val();
  // alert(img_add);
  //以分号划分数组
  img_add=img_add.split(';');
  // alert(img_add);
  //计算图片数量
  count=img_add.length-1;
  // alert(n);
	// 自动轮播部分---------------------------------------
    // 函数体
    // function run(){
    //    n++;
    //    // 临界点判断
    //    if(n==count){
    //    	 n=0;
    //    }
    //    // 当前图片显示，其他的隐藏
    //    $("#scroll1_box img").eq(n).fadeIn(800).siblings("img").stop().fadeOut(800);
    //    // 当前li变红,其他的变灰
    //    $("#scroll1_box ul li").eq(n).css({background:"#b61b1f"}).siblings("li").css({background:"#3e3e3e"});
    // }
    // 定时器
    // timer = setInterval(run,1000);

    // 鼠标移入部分-------------------------------------
    // $("#scroll1_box").hover(function(){
    // 	// 停止定时器
    //     // clearInterval(timer);
    //     // 左右箭头显示
    //     $(".scroll1_left,.scroll1_right").css({display:"block"});
    // },function(){
    // 	// 开启定时器
    //     // timer = setInterval(run,1000);
    //     // 左右箭头消失
    //     $(".scroll1_left,.scroll1_right").css({display:"none"});
    // });

    // li的控制------------------------------------------
     $(".scroll1_ul li").mouseover(function(){
     	// 获得当前序号
     	  n = $(this).index();
     	  // document.title=n;
        // alert(n);
     	  // 修改背景
         $(".page1").css({background:'url('+img_add[n]+')',backgroundPosition:'center center',backgroundRepeat:'no-repeat',backgroundSize:'cover'});
         $('.page1').css({opacity:'0.4'});
          $('.page1').animate({opacity:'1'},1000);
          // 针对ie写法
          $('.page1').animate({filter:'alpha(opacity=1)'},1000);
	       //当前li变色,其他的变另一色
	       $(".scroll1_ul li").eq(n).css({background:"#92E439"}).siblings("li").css({background:"#8B9DB1"});
     });

     // 左点击--------------------------------------------
    
     $(".scroll1_left").click(function(){


     	  n--;
        // alert(n);
     	  // 最小界点判断
     	  if(n==-1){
     	  	 n=count-1;
     	  }
     	  //修改背景
        $(".page1").css({background:'url('+img_add[n]+')',backgroundPosition:'center center',backgroundRepeat:'no-repeat',backgroundSize:'cover'});
        $('.page1').css({opacity:'0.4'});
        $('.page1').animate({opacity:'1'},1000);
         // 针对ie写法
          $('.page1').animate({filter:'alpha(opacity=1)'},1000);
         //当前li变色,其他的变另一色
         $(".scroll1_ul li").eq(n).css({background:"#92E439"}).siblings("li").css({background:"#8B9DB1"});
     })

     // 右点击--------------------------------------------
     $(".scroll1_right").click(function(){
     	  n++;
     	  // 最小界点判断
     	  if(n==count){
     	  	 n=0;
     	  }
     	 //修改图片
       var pic_n=n+1;
        //修改背景
        $(".page1").css({background:'url('+img_add[n]+')',backgroundPosition:'center center',backgroundRepeat:'no-repeat',backgroundSize:'cover'});
        $('.page1').css({opacity:'0.4'});
        $('.page1').animate({opacity:'1'},1000);
         // 针对ie写法
          $('.page1').animate({filter:'alpha(opacity=1)'},1000);
         //当前li变色,其他的变另一色
         $(".scroll1_ul li").eq(n).css({background:"#92E439"}).siblings("li").css({background:"#8B9DB1"});
     })
})