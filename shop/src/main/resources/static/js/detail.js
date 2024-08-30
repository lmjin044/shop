$(function() {
    // 큰 이미지와 작은 이미지 요소를 선택
    const $bigImg = $('.bigImg img');
    const $smallImgs = $('.smallImgsWrap ul li img');

    // 작은 이미지 클릭 시 큰 이미지 변경
    $smallImgs.on('click', function() {
        $bigImg.attr('src', $(this).attr('src')); // 큰 이미지의 src를 클릭한 작은 이미지의 src로 변경
    });

    // 큰 이미지에 마우스 오버 시 확대 효과
    const $bigImgWrap = $('.bigImgWrap');

    $bigImgWrap.on('mouseover', function() {
        $bigImgWrap.addClass('zoom');
    });

    $bigImgWrap.on('mouseout', function() {
        $bigImgWrap.removeClass('zoom');
    });



    // 단가를 데이터 속성에서 가져오기
        const unitPrice = parseFloat($('.itemMoney').data('price'));

        function updateTotalPrice() {
            const quantity = parseInt($('#quantity').val(), 10);
            const totalPrice = unitPrice * quantity;
            $('#totalPrice').text(`${totalPrice.toLocaleString()}원`);
        }

        // 수량이 변경될 때마다 총 결제 금액 업데이트
        $('#quantity').on('input', updateTotalPrice);

        // 페이지 로드 시 총 결제 금액 초기화
        updateTotalPrice();


});