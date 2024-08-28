$(window).resize(function() {
    var windowWidth = $(window).width();
    if( windowWidth<=850){
        $("#menuSlideIcon").css("color","#000");
        $("#side").addClass("toggle");
    }else{
        $("#menuSlideIcon").css("color","#fff");
        $("#side").removeClass("toggle");
    }
});



$(function(){

    var currentUrl = window.location.pathname;
    $(".menu").removeClass("active");
    $.each($(".menu"), function(){
        if( $(this).find("a").attr("href")==currentUrl)
            $(this).addClass("active");
    });

    $("#menuSlideIcon").click(function(){
        if( $("#side").hasClass("toggle")){
            $(this).css("color","#fff");
            $("#side").removeClass("toggle");
        }else{
            $(this).css("color","#000");
            $("#side").addClass("toggle");
        }
    });

});