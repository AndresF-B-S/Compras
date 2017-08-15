(function($){
  // constants
  var SHOW_CLASS = 'show',
      HIDE_CLASS = 'hide',
      ACTIVE_CLASS = 'active';
  
  $('.forgot').on('click', 'a', function(e){
    e.preventDefault();
    var $tab = $( this ),
         href = $tab.attr( 'href' );
  
     $('.active').removeClass(ACTIVE_CLASS);
     $tab.addClass(ACTIVE_CLASS);
  
     $('.show')
        .removeClass(SHOW_CLASS)
        .addClass(HIDE_CLASS)
        .hide();
    
      $(href).fadeToggle(1500)
        .removeClass(HIDE_CLASS)
        .addClass(SHOW_CLASS)
        .hide()
        .fadeIn( 550 );
       
  });
  $('.footer').on('click', 'a', function(e){
    e.preventDefault();
    var $tab = $( this ),
         href = $tab.attr( 'href' );
  
     $('.active').removeClass(ACTIVE_CLASS);
     $tab.addClass(ACTIVE_CLASS);
  
     $('.show')
        .removeClass(SHOW_CLASS)
        .addClass(HIDE_CLASS)
        .hide();
    
      $(href).fadeToggle(1500)
        .removeClass(HIDE_CLASS)
        .addClass(SHOW_CLASS)
        .hide()
        .fadeIn( 550 );
       
  });
})(jQuery);