<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	#deliverPlaceModal {
		display: none;
	}
	/* The Modal (background) */
	.modal {
	  display: none; /* Hidden by default */
	  position: fixed; /* Stay in place */
	  z-index: 920521; /* Sit on top */
	  padding-top: 100px; /* Location of the box */
	  left: 0;
	  top: 0;
	  width: 100%; /* Full width */
	  height: 100%; /* Full height */
	  overflow: auto; /* Enable scroll if needed */
	  background-color: rgb(0,0,0); /* Fallback color */
	  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
	}
	
	/* Modal Content */
	.modal-content {
	  background-color: #fefefe;
	  margin: auto;
	  padding: 20px;
	  border: 1px solid #888;
	  width: 80%;
	}
	
	/* The Close Button */
	.close {
	  color: #aaaaaa;
	  float: right;
	  font-size: 28px;
	  font-weight: bold;
	}
	
	.close:hover,
	.close:focus {
	  color: #000;
	  text-decoration: none;
	  cursor: pointer;
	}
	#deliverPlaceInputs {
		display: flex;
		flex-direction: column;
	}
	input {
		height: 20px;
		margin-bottom: 4px;
	}
</style>
</head>
<body>
<!-- Modal -->
<div id="deliverPlaceModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<span class="close">&times;</span>
			<div id="deliverPlaceInputs">
				<label for="delAlias">배송지별칭</label>
				<input type="text" name="delAlias"/>
				
				<label for="delAddress">배송지 주소</label>
				<input type="text" name="delAddress" />
				
				<label for="delDetailAddress">상세주소</label>
				<input type="text" name="delDetailAddress" />
				
				<label for="zipCode">우편번호</label>
				<input type="text" name="zipCode" />
				
				<label for="recipient">수령인</label>
				<input type="text" name="recipient" />
				
				<label for="recipientPhoneNumber">수령인 전화번호</label>
				<input type="text" name="recipientPhoneNumber" />
				
				<button id="submitBtnForDeliverPlace">저장</button>
			</div>
	</div>
</div>
</body>
</html>