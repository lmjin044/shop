$(function(){
    $("#chk_all").click(function(){
        //'모두 동의'를 체크할 때
        if($(this).prop('checked')){//'체크가 되었다면?'
            $("#chk1").prop('checked',true);
            $("#chk2").prop('checked',true);
            $("#chk3").prop('checked',true);
            // = chk1~3까지를 모두 체크 상태로 만들어라
        }else{//'체크가 안 되었다면?'
            $("#chk1").prop('checked',false);
            $("#chk2").prop('checked',false);
            $("#chk3").prop('checked',false);
            // = chk1~3을 모두 체크 해제 해라.
        }
    });

    $(".chk").click(function(){
        //3개 모두 체크가 되었을 때 '모두 동의'도 같이 체크가 되도록 한다.
        if($(this).prop('checked')){
            var isAll = true;
            $.each($(".chk"), function(){
                if(!$(this).prop('checked') )
                    isAll=false
            });
            if(isAll) $("#chk_all").prop('checked', true);
        }else{
        //하나라도 체크해제 하면 '모두 동의' 체크 상태를 해제하기
           $("#chk_all").prop('checked',false);
        }
    });


    let agree_pass=false;
    $.each($(".fieldError"),function(){
        if($(this).text !='') agree_pass=true;
    });

    if(agree_pass) joinShow();

    $(".btn_agree").click(function(){
        //'모두 동의' 상태가 되면 회원가입 절차로 이행되게끔(단 '필수'항목 기준으로)
        if($("#chk1").prop('checked') && $("#chk2").prop('checked')){
            joinShow();
        }else{
            alert("필수 항목을 동의해야 합니다.")
        }
    });

    $(".btn-zipCode").click(function(){
        //카카오 우편번호를 이용한 주소검색 서비스 연동하기
        addrSearch();
    });
});

function joinShow(){
     $(".stage_arrow").fadeIn(1500);
     $(".stage_b").fadeIn(1500);
     $("#terms").fadeOut(1000);
     $("#joinForm").fadeIn(2000);
}