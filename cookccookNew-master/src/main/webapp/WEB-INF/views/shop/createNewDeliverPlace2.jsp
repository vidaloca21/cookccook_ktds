<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지 등록</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
		
	$().ready(function() {
		$("#submitBtnForDeliverPlace").click(function() {
			let delAlias = $("input[name='delAlias']").val()
			let delAddress = $("input[name='delAddress']").val()
			let delDetailAddress = $("input[name='delDetailAddress']").val()
			let zipCode = $("input[name='zipCode']").val()
			let recipient = $("input[name='recipient']").val()
			let recipientPhoneNumber = $("input[name='recipientPhoneNumber']").val()
			$.ajax({
				url: "/deliverPlace/create",
				method: "post",
				data: {
					delAlias: delAlias,
					delAddress: delAddress,
					delDetailAddress: delDetailAddress,
					zipCode: zipCode,
					recipient: recipient,
					recipientPhoneNumber: recipientPhoneNumber
				},
				success: function(response) {
					if(response.result) {
						alert("성공!")
						var modal = document.getElementById("deliverPlaceModal");
						modal.style.display = "none";
						location.reload()
					}else {
						alert("실패!")
					}
				}
			})
		})
	})
</script>
<style type="text/css">
#deliverPlaceInputs {
	display: flex;
	flex-direction: column;
	width: 500px;
	heigh: auto;
}
</style>
</head>
<body>
<form:form modelAttribute="deliverPlaceVO" enctype="multipart/form-data" method="post">
			<div id="deliverPlaceInputs">
				<label for="delAlias">배송지별칭</label>
				<input type="text" name="delAlias"/>
				
				<button class="btn btn-default" type="button" onClick="goPopup();">주소 검색</button>
				<label for="delAddress">배송지 주소</label>
				<input type="text" name="delAddress" id="roadFullAddr" />
				
				<label for="delDetailAddress">상세주소</label>
				<input type="text" name="delDetailAddress" id="addrDetail" />
				
				<label for="zipCode">우편번호</label>
				<input type="text" name="zipCode" id="zipNo"/>
				
				<label for="recipient">수령인</label>
				<input type="text" name="recipient" />
				
				<label for="recipientPhoneNumber">수령인 전화번호</label>
				<input type="text" name="recipientPhoneNumber" />
				
				<button id="submitBtnForDeliverPlace">저장</button>
			</div>
</form:form>
</body>
</html>