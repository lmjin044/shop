$(function() {

    function calculateSubtotal() {
        $('#cartTable tbody tr').each(function() {
            var $row = $(this);
            var price = parseFloat($row.find('.price').text().replace('원', '').replace(/,/g, ''), 10);
            var quantity = parseInt($row.find('input[type="number"]').val());
            var subtotal = price * quantity;
            $row.find('.subtotal').text(subtotal + '원');
        });
    }

    // 총 결제 금액 계산
    function calculateTotal() {
        var total = 0;
        $('#cartTable tbody tr').each(function() {
            var $row = $(this);
            if ($row.find('.itemCheckbox').is(':checked')) {
                var subtotal = parseFloat($row.find('.subtotal').text().replace('원', '').replace(/,/g, ''), 10);
                total += subtotal;
            }
        });
        $('#totalPrice').text(total + '원');
    }

    // 수량 변경 시 합계 계산
    $('#cartTable').on('input', 'input[type="number"]', function() {
        calculateSubtotal();
        calculateTotal();
        var cartItemId = $(this).parent().parent().find("input[name=cartChk]").val();
        var quantity = $(this).val();

        updateItemQuantity(cartItemId, quantity);
    });

    // 체크박스 선택 시 총 결제 금액 업데이트
    $('#cartTable').on('change', '.itemCheckbox', function() {
        calculateTotal();
    });

    calculateSubtotal();
    calculateTotal();

    // 삭제 버튼 클릭 시 행 제거
    $('#cartTable').on('click', '.removeButton', function() {
        $(this).closest('tr').remove();
        calculateTotal();
        //서버에 삭제 요청을 위한 함수를 호출
        var cartItemId = $(this).parent().parent().find("input[name=cartChk]").val();
        deleteCartItem(cartItemId);
    });

    //결제하기 버튼 클릭 시
    $("#checkoutButton").click(function(){
        var token = $("meta[name=_csrf]").attr("content");
        var header = $("meta[name=_csrf_header]").attr("content");

        var url = "/cart/order";

        //체크한 상품만 주문하도록 만들기
        var ciList = new Array();
        $("input[name=cartChk]:checked").each(function(){
            var cartItemId = $(this).val();
            ciList.push({cartItemId : cartItemId });
        });

        var param = JSON.stringify({cartOrderDtoList : ciList});

        $.ajax({
            url : url,
            type : "POST",
            data : param,
            dataType : "json",
            contentType : "application/json",
            cache : false,
            beforeSend : function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success : function(result, status){
                alert("주문이 완료 되었습니다.");
                location.href="/orderList";
            },
            error : function(jqXHR, status, error){
                if(jqXHR.status == "200"){
                    alert("로그인 후 이용해주세요");
                }else{
                    alert(jqXHR.responseText);
                }

            }
        });

    });




});

//장바구니에 담긴 상품을 삭제 요청하기
function deleteCartItem(cartItemId){
    var token = $("meta[name=_csrf]").attr("content");
    var header = $("meta[name=_csrf_header]").attr("content");

    var url = "/cart/delete"+cartItemId;

  $.ajax({
    url : url,
    type : "DELETE",
    dataType : "json",
    cache : false,
    beforeSend : function(xhr){
        xhr.setRequestHeader(header, token);
    },
    success : function(result, status){
        console.log("삭제 성공")
    },
    error : function(jqXHR, status, error){
        if(jqXHR.status=="200"){
            alert("로그인 후 이용해주세요");
        }else{
            alert(jqXHR.responseJSON.message);
        }
    }
  });
}

//수량변경을 서버에 요청하기
function updateItemQuantity(cartItemId, quantity){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    //변경사항 된 주소를 파라미터로 요청하기
    var url = "/cart/update/" + cartItemId + "?quantity=" + quantity;
    $.ajax({
        url : url,
        type : "PATCH",
        dataType : "json",
        cache : false,
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success : function(result, status){
            console.log("수량변경 성공");
        },
        error : function(jqXHR, status, error){
            if(jqXHR.status == '200'){
                alert("로그인 후 이용해 주세요");
            } else {
                alert(jqXHR.responseJSON.message);
            }
        }
    });
}
