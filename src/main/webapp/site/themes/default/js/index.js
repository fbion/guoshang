$(function() {

//行业动态滚动
$('#slides_news').slides({
		container : 'slides_container_news',
    	paginationClass: 'slides_news',
    	preload : true,
    	play : 36000000,
    	pause : 36000000,
    	hoverPause : true,
    	effect : 'slide',
    	slideSpeed : 1500
});


//底层轮播
	var  seamless = document.getElementById("seamless");
			var  timer=0;
			var  count0=$('#seamless').children('li').length;
			// alert(count0);
			//根据li的数量判断轮播样式，都要满12格
			if(count0==2)
			{
				//一组li添加5次
				for(var j=0;j<5;j++)
				{
					for(var i=0;i<2;i++)
					{
						var c=$('#seamless li:eq('+i+')').find('img').attr('src');
						// alert(c);
						$('#seamless').append('<li><img src="'+c+'" alt="" /></li>')
					}
				}
			}
			if(count0==4)
			{
				//一组li添加4次
				for(var j=0;j<4;j++)
				{
					for(var i=0;i<2;i++)
					{
						var c=$('#seamless li').eq(i).find('img').attr('src');
						// alert(c);
						$('#seamless').append('<li><img src="'+c+'" alt="" /></li>')
					}
				}
			}
			if(count0==6)
			{
				//一组li添加2次
				for(var j=0;j<2;j++)
				{
					for(var i=0;i<3;i++)
					{
						var c=$('#seamless li').eq(i).find('img').attr('src');
						// alert(c);
						$('#seamless').append('<li><img src="'+c+'" alt="" /></li>')
					}
				}
			}
			if(count0==8)
			{
				//一组li添加1次

				for(var i=0;i<4;i++)
				{
					var c=$('#seamless li').eq(i).find('img').attr('src');
					// alert(c);
					$('#seamless').append('<li><img src="'+c+'" alt="" /></li>')
				}
			}
			if(count0==10)
			{
				//一组li，前两个添加1次

				for(var i=0;i<2;i++)
				{
					var c=$('#seamless li').eq(i).find('img').attr('src');
					// alert(c);
					$('#seamless').append('<li><img src="'+c+'" alt="" /></li>')
				}

			}
			//计算添加li后的数量
			count1=$('#seamless').children('li').length;
			// alert(count1);
			var  ul_width=count1*183;
			var  ul_width2=ul_width/2;
			$("#seamless").css({width:ul_width});
	        function run(){
	            var old_l =parseInt(seamless.style.left);
	            var new_l = old_l - 1;
	            seamless.style.left = new_l + "px";
        		if(count0==8)
        		{
        			if(new_l<-732)
        			{
        				seamless.style.left="0px";
        			}
        		}
        		else if(count0==10)
        		{
        			if(new_l<-915)
        			{
        				seamless.style.left="0px";
        			}
        		}
        		else
        		{
        			if(new_l<-ul_width2)
        			{
        				seamless.style.left="0px";
        			}
        		}
          	}
		    timer = setInterval(run,15);
		   	$('#seamless').hover(function() {
		   		clearInterval(timer);
		   	}, function() {
		   		timer=setInterval(run,15);
		   	});
//公告滚动

$("#notice").jCarouselLite({auto:1000,speed:700,visible:1,vertical:true,stop:$("#notice"),btnGoOver:true});
var box=document.getElementById("notice"),can=true;
box.innerHTML+=box.innerHTML;
box.onmouseover=function(){can=false};
box.onmouseout=function(){can=true};
new function (){
		var stop=box.scrollTop%53==0&&!can;
		if(!stop)box.scrollTop==parseInt(box.scrollHeight/2)?box.scrollTop=0:box.scrollTop++;
			setTimeout(arguments.callee,box.scrollTop%53?10:3000);
		};






//投资项目圆形进度条
var canvas = document.getElementById('canvas'),
			circlesCreated = false;

		function onScroll() {
			if (!circlesCreated && elementInViewport(canvas)) {
				circlesCreated = true;
				createCircles();
			}
		}

		function elementInViewport(el) {
		var rect = el.getBoundingClientRect();

		return (
		  rect.top  >= 0 &&
		  rect.left >= 0 &&
		  rect.top  <= (window.innerHeight || document.documentElement.clientHeight)
		);
	  }

		function createCircles() {
			var colors = [
					['#FDFDFD', '#FE6C6F']
				],
				circles = [];
				var child1 = document.getElementById('circles-1');
				if(child1!=null){
					var percentage1 = child1.innerHTML ;
					circle1 = Circles.create({
						id:         child1.id,
						value:      percentage1,
						radius:     35,
						width:      6,
						colors:     colors[0]
					});
						circles.push(circle1);
				}
				var child2 = document.getElementById('circles-2');
				if(child2!=null){
					var percentage2 = child2.innerHTML ;
					circle2 = Circles.create({
						id:         child2.id,
						value:      percentage2,
						radius:     35,
						width:      6,
						colors:     colors[0]
					});
						circles.push(circle2);
				}
				
				var child3 = document.getElementById('circles-3');
				if(child3!=null){
					var percentage3 = child3.innerHTML ;
					circle3 = Circles.create({
						id:         child3.id,
						value:      percentage3,
						radius:     35,
						width:      6,
						colors:     colors[0]
					});
						circles.push(circle3);
				}
				
			
		}

	  window.onscroll = onScroll;

})