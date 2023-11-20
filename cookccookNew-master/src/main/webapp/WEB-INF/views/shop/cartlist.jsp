<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>장바구니 조회</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
$().ready(function() {
	let defaultCartlistDOM = $("<ul class='cartList'></ul>");
	defaultCartlistDOM.append($("<li>선택</li>"));
	defaultCartlistDOM.append($("<li></li>"));
	defaultCartlistDOM.append($("<li>상품명</li>"));
	defaultCartlistDOM.append($("<li>가격</li>"));
	defaultCartlistDOM.append($("<li>옵션</li>"));
	defaultCartlistDOM.append($("<li>추가금</li>"));
	defaultCartlistDOM.append($("<li>수량</li>"));
	defaultCartlistDOM.append($("<li>총액</li>"));
	defaultCartlistDOM.append($("<li></li>"));
	$("#cart").append(defaultCartlistDOM)
    $.ajax({
        url: "/shop/cart",
        method: "get",
        success: function(response) {
        	let memeberId = response.memberId;
        	for(var i in response.cartListVO) {
        		let cartId = response.cartListVO[i].cartId;
        		let checkboxDOM = $("<li></li>");
        		let checkInput = $("<input type='checkbox' name='orderCheck'/>");
        		checkInput.val(cartId)
        		checkboxDOM = checkboxDOM.append(checkInput);
        		
        		let prdImage = response.cartListVO[i].productVO.prdTitleImgOrigin;
        		let productId = response.cartListVO[i].productVO.productId;
        		let prdPrice = response.cartListVO[i].productVO.prdPrice;
        		let choiceId = response.cartListVO[i].choiceVO.choiceId;
        		let choPrice = response.cartListVO[i].choiceVO.choPrice;
        		let quantity = response.cartListVO[i].quantity;
        		let totalPrice = (prdPrice + choPrice) *quantity;
        		
        		checkInput.data("cartId", cartId)
        		checkInput.data("productId", productId)
        		checkInput.data("choiceId", choiceId)
        		checkInput.data("quantity", quantity)
        		checkInput.data("purPrdPrice", totalPrice)
        		checkInput.data("deliverFee", 3000)
        		
        		let prdImg = $("<li><img src='/shop/product/titleimg/" +prdImage+"'></li>");
        		let prdNameDOM = $("<li></li>");
        		prdNameDOM = prdNameDOM.text(response.cartListVO[i].productVO.prdName);
        		let prdPriceDOM = $("<li></li>");
        		prdPriceDOM = prdPriceDOM.text(prdPrice);
        		let choNameDOM = $("<li></li>");
        		choNameDOM = choNameDOM.text(response.cartListVO[i].choiceVO.choName);
        		let choPriceDOM = $("<li></li>");
        		choPriceDOM = choPriceDOM.text(choPrice);
        		let quantityDOM = $("<li></li>");
        		let quantityInput = $("<input type='number' />");
        		quantityInput.val(quantity)
        		quantityDOM.append(quantityInput);
        		let totalPriceDOM = $("<li></li>")
        		totalPriceDOM = totalPriceDOM.text(totalPrice)
        		      		
        		let editBtn = $("<button class='editCartButton btn_empty'>수량 변경</button>")
        		editBtn.click(function(){
                    let parentElement = $(this).closest(".cartList"); 
                    let cartId = parentElement.attr("id");
                    let quantity = quantityInput.val();
                    $.ajax({
                        url: "/shop/cart/edit/" + cartId, 
                        method: "POST",
                        data: { memberId: memeberId, cartId: cartId, quantity:quantity },
                        success: function(response) {
                            if(response.result) {
                            	alert("수량이 변경되었습니다")
                            	location.reload();
                            }
                        }
                    });
        		})
        		
        		let deleteBtn = $("<button class='removeCartButton btn_empty'>장바구니 삭제</button>")
        		deleteBtn.click(function(){
                    let parentElement = $(this).closest(".cartList"); 
                    let cartId = parentElement.attr("id");
                    $.ajax({
                        url: "/shop/cart/delete/" + cartId, 
                        method: "POST",
                        data: { memberId: memeberId, cartId: cartId },
                        success: function(response) {
                            if(response.result) {
                               parentElement.remove();
                            }
                        }
                    });
        		})
        		let btnDOM = $("<li></li>")
        		let btndiv = $("<div class='btnCol'></div>")
				btndiv.append(editBtn)
        		btndiv.append(deleteBtn)
        		btnDOM.append(btndiv)
        		let cartItem = $("<ul class='cartList'></ul>")
        		cartItem.append(checkboxDOM)
        		cartItem.append(prdImg)
        		cartItem.append(prdNameDOM)
        		cartItem.append(prdPriceDOM)
        		cartItem.append(choNameDOM)
        		cartItem.append(choPriceDOM)
        		cartItem.append(quantityDOM)
        		cartItem.append(totalPriceDOM)
        		cartItem.append(btnDOM)
        		cartItem.attr("id", response.cartListVO[i].cartId)
        		
        		$("#cart").append(cartItem)
        	}
        	$("#makeOrderBtn").click(function(){
        		if ($("input:checkbox[name='orderCheck']:checked").length > 0) {
	        		var count = 0;
	        		let orderList = [];
		   			$("input:checkbox[name='orderCheck']").each(function () {
		    			if($(this).is(":checked")==true){
	    				orderList.push($(this).val());
		    		    }
		    		});
	   				let orderData =  $("<input type='hidden' name='orderData' readonly/>'")
	   				orderData.val(orderList)
					$("form").append(orderData)
		   			$("form").submit();
        		}
        		else {
        			alert("상품을 먼저 선택해주세요")
        		}
       		})
        }
       
    })
})
</script>
<style type="text/css">
#cart {
	display: flex;
	flex-direction: column;
}
#cart .cartList {
	display: flex;
	flex-direction: row;
	padding-left: 0;
	align-items: center;
	width: 1040px;
}
#cart .cartList > li {
	display: flex;
	margin-right: 20px;
}
.cartList > li:first-child {
	width: 40px;
}
.cartList > li:first-child > input {
	width: 20px;
	height: 20px;
}
.cartList > li:nth-child(2) {
	width: 100px;
}
.cartList > li:nth-child(2) > img{
	width: 90px;
	height: 90px;
}
.cartList > li:nth-child(3) {
	width: 240px;
}
.cartList > li:nth-child(4) {
	width: 100px;
}
.cartList > li:nth-child(5) {
	width: 80px;
}
.cartList > li:nth-child(6) {
	width: 50px;
}
.cartList > li:nth-child(7) {
	width: 50px;
}
.cartList > li:nth-child(7) > input {
	width: 50px;
}
.cartList > li:nth-child(8) {
	width: 100px;
}
.cartList > li:nth-child(9) {
	width: 100px;
}
.cartList > li div.btnCol {
	display: flex;
	flex-direction: column;
	justify-content: right;
	margin-right: 0;
}
.cartList div.btnCol > button {
	width: 120px !important;
	height: 40px !important;
	margin-bottom: 5px;
	cursor: pointer;
}
#cartBtn {
	display: flex;
	justify-content: right;
	width: 1040px;
}
#makeOrderBtn {
	cursor: pointer;
}
</style>
<jsp:include page="../layout/header2.jsp"></jsp:include>
<h1>장바구니 조회</h1>
	<div id="cart"></div>
	<div id="cartBtn">
		<form:form modelAttribute="orderData" name="orderData" action="/shop/order" enctype="multipart/form-data" method="post"></form:form>
		<button id="makeOrderBtn" class="btn_fill">구매하기</button>
	</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>