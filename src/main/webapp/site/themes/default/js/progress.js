(function( $ ){
  $.fn.animateProgress = function(progress, callback) {    
    return this.each(function() {
      $(this).animate({
        width: progress+'%'
      }, {
        duration: 1000, 
        
        // swing or linear
        easing: 'swing',

        // this gets called every step of the animation, and updates the label
        step: function( progress ){
          var labelEl = $('.ui-label', this),
              valueEl = $('.value', labelEl);
          
          if (Math.ceil(progress) < 0 && $('.ui-label', this).is(":visible")) {
            labelEl.hide();
          }else{
            if (labelEl.is(":hidden")) {
              labelEl.fadeIn();
            };
          }
          
          if (Math.ceil(progress) == 100) {
            // labelEl.text('Done');
            setTimeout(function() {
              labelEl.fadeOut();
            }, 1000);
          }else{
            valueEl.text(Math.ceil(progress) + '%');
          }
        },
        complete: function(scope, i, elem) {
          if (callback) {
            callback.call(this, i, elem );
          };
        }
      });
    });
  };
})( jQuery );

$(function() {
  // Hide the label at start
  // $('#progress_bar .ui-progress .ui-label').hide();
  var now=$('#now_pro').val();
  // alert(now);
  //募集结束，隐藏剩余时间，显示募集结束。
  if(now==100)
  {
    $('#shengyu').hide();
    $('#muji').show();
  }
  // Set initial value
  //开始样式，给个0%即可
  $('#progress_bar .ui-progress').css('width', '0%');

  // Simulate some progress
  $('#progress_bar .ui-progress').animateProgress(0, function() {
    $(this).animateProgress(now);
  });
  
});