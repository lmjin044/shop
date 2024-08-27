$(function(){
    //상단 로고 이미지 클릭시 메인 페이지로 이동
    $(".logo").click(function(){
        location.href="/";
    });

    //800px보다 작은 화면일때 아이콘 클릭 시
    $(".m-menu-icon").on("click", function(){
        $(".m-menu-slide").slideToggle();

    });
    $(".m-menu-title").on("click", function(){
        $(".m-menu-sub").slideUp();
        $(this).next(".m-menu-sub").slideDown();
    });

    //검색 아이콘 클릭시.
    $(".searchBox i").click(function(){
        if( !$("#keyword").hasClass("active")){

            $("#keyword").addClass("active");
            $(".navbar-menu").css("width","50%");
            $("#sub-nav").css("width", "calc(100% - 50% - 200px)");
            $("#keyword").focus();
        }
    });
    //만약에 : 검색하려고 했다가 취소할 경우
    $("#keyword").blur(function(){
        $(this).val("");
        $(this).removeClass("active");
        $(".navbar-menu").css("width","65%");
        $("#sub-nav").css("width", "calc(100% - 65% - 200px)");

    });

});