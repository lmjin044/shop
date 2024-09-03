$(function() {
    $('.cancel-button').on('click', function() {
        if (confirm('정말로 주문을 취소하시겠습니까?')) {

            var token = $("meta[name=_csrf]").attr("content");
            var header = $("meta[name=_csrf_header]").attr("content");

            var orderId = $(this).data("orderid");
            var url = "/orderCancel?orderId="+orderId;

            $.ajax({
                url : url,
                type : "POST",
                contentType : "application/json",
                //data : param,
                dataType : "json",
                cache : false,
                beforeSend : function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success : function(result , status){
                    alert("주문이 취소 되었습니다.");
                    location.href="/orderList";
                },
                error : function(jqXHR , status, error){
                    if(jqXHR.status == '200')
                        alert("로그인후 이용하세요");
                    else
                        alert(jqXHR.responseText);
                }
            });

        }
    });
});