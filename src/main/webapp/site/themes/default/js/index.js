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
				var child2 = document.getElementById('circles-2');
				var child3 = document.getElementById('circles-3');
				var percentage1 = 31.4 ;
				var percentage2 = 72.7 ;
				var percentage3 = 12.3 ;

					circle1 = Circles.create({
						id:         child1.id,
						value:      percentage1,
						radius:     35,
						width:      6,
						colors:     colors[0]
					});

					circle2 = Circles.create({
						id:         child2.id,
						value:      percentage2,
						radius:     35,
						width:      6,
						colors:     colors[0]
					});
					
					circle3 = Circles.create({
						id:         child3.id,
						value:      percentage3,
						radius:     35,
						width:      6,
						colors:     colors[0]
					});

				circles.push(circle1);
				circles.push(circle2);
				circles.push(circle3);
			
		}

	  window.onscroll = onScroll;

})