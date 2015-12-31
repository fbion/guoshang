//数量加减效果
	$(function(){
		//数量加减效果
		$('#jian').click(function(){
			var a=$('#numChange').val();
			// alert(a);
			a=parseInt(a);
			if(a>100)
			{
				a=a-100;
			}
			else
			{
				a=a;
			}
			$('#numChange').val(a);
		})
		$('#jia').click(function(){
			var a=$('#numChange').val();
			// alert(a);
			a=parseInt(a);
			// alert(kucun);
			
			a=a+100;
			
			
			$('#numChange').val(a);
		})
	})

/*鼠标经过某一项投资记录，背景变化*/
$(function(){
	$('.c3_td2').mouseover(function(){
		$(this).css({background:'#DCDCDC'});
		$(this).siblings('.c3_td2').css({background:'#DCDCDC'});
	})
	$('.c3_td2').mouseout(function(){
		$(this).css({background:'white'});
		$(this).siblings('.c3_td2').css({background:'white'});
	})
})

// 产品详情tab切换
$(function(){
	$('.intro_tab1').mouseover(function(){
		//显示红色底边，隐藏其他
		// alert(1);
		$(this).find('.intro_tab_line1,.intro_tab_line2').show();
		
		$('.intro_tab2').find('.intro_tab_line1,.intro_tab_line2').hide();
	
		$('.intro_tab3').find('.intro_tab_line1,.intro_tab_line2').hide();
		
		//显示下方内容，隐藏其他
		$('.i_t_d_c1').show();
		$('.i_t_d_c2').hide();
		$('.i_t_d_c3').hide();

	})

	$('.intro_tab2').mouseover(function(){
		//显示红色底边，隐藏其他
		$(this).find('.intro_tab_line1,.intro_tab_line2').show();
		
		$('.intro_tab1').find('.intro_tab_line1,.intro_tab_line2').hide();
	
		$('.intro_tab3').find('.intro_tab_line1,.intro_tab_line2').hide();
		
		//显示下方内容，隐藏其他
		$('.i_t_d_c1').hide();
		$('.i_t_d_c2').show();
		$('.i_t_d_c3').hide();

	})

	$('.intro_tab3').mouseover(function(){
		//显示红色底边，隐藏其他
		$(this).find('.intro_tab_line1,.intro_tab_line2').show();
		
		$('.intro_tab2').find('.intro_tab_line1,.intro_tab_line2').hide();
	
		$('.intro_tab1').find('.intro_tab_line1,.intro_tab_line2').hide();
		
		//显示下方内容，隐藏其他
		$('.i_t_d_c1').hide();
		$('.i_t_d_c2').hide();
		$('.i_t_d_c3').show();

	})
})

// 进度条显示
$(function(){
	var v=$('#jinduvalue').val();
	// alert(v);
	// 最初期，目前假定数据库值为1，不显示红色
	if(v==1)
	{
		$('.r_up_pro_line li:eq(1)').css({background:'#D8D8D8'});
		$('.r_up_pro_line li:eq(3)').css({background:'#D8D8D8'});
		$('.r_up_pro_line li:eq(5)').css({background:'#D8D8D8'});
	}
	//第二期，显示一格红色
	if(v==2)
	{
		$('.r_up_pro_line li:eq(3)').css({background:'#D8D8D8'});
		$('.r_up_pro_line li:eq(5)').css({background:'#D8D8D8'});
	}
	//第三期，显示二格红色
	if(v==3)
	{
		$('.r_up_pro_line li:eq(5)').css({background:'#D8D8D8'});
	}
	//完成期，全显示红色，默认全是红色
})

// 轮播区域
$(function(){
	// 设置变量
    var n = 0;
    var c = 0;
	// 自动轮播部分---------------------------------------
    // 函数体
    function run(){
       n++;
       // 临界点判断
       if(n==5){
       	 n=0;
       }
       // 当前图片显示，其他的隐藏
       $(".intro_tab_down_lunbo  img").eq(n).fadeIn(800).siblings("img").stop().fadeOut(800);
       // 当前li边框变色
       $(".intro_tab_down_lunbo  ul li").eq(n).css({border:'3px solid #666666'}).siblings("li").css({border:'3px solid white'});
    }
    // 定时器
    // timer = setInterval(run,3000);

    // 鼠标移入部分-------------------------------------
    $(".intro_tab_down_lunbo ").hover(function(){
    	// 停止定时器
        // clearInterval(timer);
       
    },function(){
    	// 开启定时器
        // timer = setInterval(run,3000);
        
    });

    // li的控制------------------------------------------
     $(".intro_tab_down_lunbo  ul li").mouseover(function(){
     	// 获得当前序号
     	  n = $(this).index();
     	  // document.title=n;
     	  // 当前图片显示，其他的隐藏
         $(".intro_tab_down_lunbo  img").eq(n).fadeIn(800).siblings("img").stop().fadeOut(800);
	      // 当前li边框变色
	     $(".intro_tab_down_lunbo  ul li").eq(n).css({border:'3px solid #666666'}).siblings("li").css({border:'3px solid white'});
     });

     // 左点击--------------------------------------------
     $(".intro_tab_down_lunbo .left").click(function(){
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
     	  	 n=4;
     	  }
     	 // 当前图片显示，其他的隐藏
         $(".intro_tab_down_lunbo  img").eq(n).fadeIn(800).siblings("img").stop().fadeOut(800);
	     // 当前边框变色
	     $(".intro_tab_down_lunbo  ul li").eq(n).css({border:'3px solid #666666'}).siblings("li").css({border:"3px solid white"});
     })

     // 右点击--------------------------------------------
     $(".intro_tab_down_lunbo .right").click(function(){
     	  n++;
     	  // 最小界点判断
     	  if(n==5){
     	  	 n=0;
     	  }
     	 // 当前图片显示，其他的隐藏
         $(".intro_tab_down_lunbo  img").eq(n).fadeIn(800).siblings("img").stop().fadeOut(800);
	     // 当前边框变色
	     $(".intro_tab_down_lunbo  ul li").eq(n).css({border:'3px solid #666666'}).siblings("li").css({border:'3px solid white'});
     })
})
