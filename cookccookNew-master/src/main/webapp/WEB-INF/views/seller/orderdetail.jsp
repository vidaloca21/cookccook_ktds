<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	div.errors {
		background-color: #FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	#orderMemberInfo > .orderMemberInfoArea {
		display: flex;
		flex-direction: row;
	}
	#orderMemberInfo > .orderMemberInfoArea > li {
		display: flex;
		height: 40px;
		align-items: center;
		margin-top: 10px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li:first-child {
		width: 200px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li:last-child {
		width: 400px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > input {
		display: inline;
		width: 400px;
		height: 40px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > textarea {
		display: inline;
		width: 400px;
		height: 40px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > select {
		display: inline;
		width: 400px;
		height: 40px;
	}
	#inputBtn {
		cursor: pointer;
		width: 150px;
		height: 40px;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	function modifyBtn() {
		var url = "/seller/orderdetail"
		var body = { 
			"purchaseProductId" : "${purchaseProductVO.purchaseProductId}",
			"purState" : $("select[name=purState]").val()
		}
		$.post(url, body, function(response){
			var result = response.result
			if(result){
				alert("주문이 정상적으로 수정되었습니다.")
				window.close();
			}
		})
	}
</script>
</head>
<body>
	<div id="orderMemberInfo">
		<h2>주문 상세 조회</h2>
		<p style="display: none;"><input id="purchaseProductId" type="text" name="purchaseProductId" value='${purchaseProductVO.purchaseProductId}'/></p>
		<ul class="orderMemberInfoArea">
			<li><label for="prdName">상품명</label></li>
			<li><input id="prdName" type="text" name="prdName" value='${purchaseProductVO.choiceVO.productVO.prdName}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="purState">주문상태</label></li>
			<li><select id="purState" name="purState" value='${purchaseProductVO.purState}'>
				<option value="1">결제완료</option>
				<option value="2">상품준비중</option>
				<option value="3">배송중</option>
				<option value="4">배송완료</option>
			</select></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="choName">옵션명</label></li>
			<li><input id="choName" type="text" name="choName" value='${purchaseProductVO.choiceVO.choName}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="quantity">주문수량</label></li>
			<li><input id="quantity" type="number" name="quantity" value='${purchaseProductVO.quantity}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="purchaseId">주문번호</label></li>
			<li><input id="purchaseId" type="text" name="purchaseId" value='${purchaseProductVO.purchaseVO.purchaseId}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="purchaseDate">결제완료일</label></li>
			<li><input id="purchaseDate" type="text" name="purchaseDate" value='${purchaseProductVO.purchaseVO.purchaseDate}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="payMethod">결제수단</label></li>
			<li><input id="payMethod" type="text" name="payMethod" value='${purchaseProductVO.purchaseVO.payMethod}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="purPrdPrice">최종결제금액</label></li>
			<li><input id="purPrdPrice" type="text" name="purPrdPrice" value='${purchaseProductVO.purPrdPrice}' readonly/>원</li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="memName">구매자성함</label></li>
			<li><input id="memName" type="text" name="memName" value='${purchaseProductVO.purchaseVO.memberVO.memName}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="memPhoneNumber">구매자연락처</label></li>
			<li><input id="memPhoneNumber" type="tel" name="memPhoneNumber" value='${purchaseProductVO.purchaseVO.memberVO.memPhoneNumber}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="recipient">수취인성함</label></li>
			<li><input id="recipient" type="text" name="recipient" value='${purchaseProductVO.purchaseVO.deliverPlaceVO.recipient}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="recipientPhoneNumber">수취인연락처</label></li>
			<li><input id="recipientPhoneNumber" type="tel" name="recipientPhoneNumber" value='${purchaseProductVO.purchaseVO.deliverPlaceVO.recipientPhoneNumber}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="delAddress">배송지주소</label></li>
			<li><input id="delAddress" type="text" name="delAddress" value='${purchaseProductVO.purchaseVO.deliverPlaceVO.delAddress}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="deliverMemo">배송요청사항</label></li>
			<li><input id="deliverMemo" type="text" name="deliverMemo" value='${purchaseProductVO.purchaseVO.deliverMemo}' readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li></li>
			<li><input type="button" class="btn_fill_s" id="inputBtn" value="저장" onclick="modifyBtn()"/></li>
		</ul>
	</div>
</body>
</html>
