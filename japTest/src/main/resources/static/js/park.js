$(function(){
    $("#menuIcon").click(function(){
        if($("#header").hasClass("slide")){
            $("#header").removeClass("slide");
            $("#main").removeClass("full");
        }else{
            $("#header").addClass("slide");
            $("#main").addClass("full");
        }

    });
});