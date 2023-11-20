<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>상품 취소</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".cancelCancelBtn").click(function() {
		    location.href="/shop/orderlist"
		})
 		$(".cancelSubmitBtn").click(function() {
 			if(confirm("정말로 취소하시겠습니까?")) {
				let formData = {
 				    	imp_key: "8440145738425552",
 				    	imp_secret: "tDKItaRuSs1DoANIfMtRGPKlx6f9CvoooN1SrRFpcTkgHp1rGoFcfcOzxIXrltUZ8GbM7uytjKYvkcx3"
 				    }
				$.post("https://api.iamport.kr/users/getToken", formData, function(response) {
					let purchaseId = $("#purchaseId").text();
	 				let access_token = response.response.access_token
	 				let imp_uid = $("#impUid").text();
					$.ajax({
				          url: "https://api.iamport.kr/payments/cancel",
				          method: "post",
				          headers: {
				            "Authorization": access_token // 포트원 서버로부터 발급받은 엑세스 토큰
				          },
				          data: {
				            reason: $('select[name=canExcuse]').val(), // 가맹점 클라이언트로부터 받은 환불사유
				            imp_uid: imp_uid // imp_uid를 환불 `unique key`로 입력
				          },
				          success: function(response) {}
					}).done(function() {
						$("form").submit()
					})
				})
 			}
 			else {
 				location.href="/shop/orderlist/"+ purchaseId;
 			}
		})
	})
</script>
<style type="text/css">
	div.errors {
		background-color: #FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	#cancel-btn-group {
		display: flex;
		flex-direction: row;
		justify-content: center;
		text-align: center;
		margin-top: 50px;
	}
	#cancel-btn-group > button {
		margin-left: 10px;
		margin-right: 10px;
	}
	#makeCancel {
		text-align: center;
		width: 600px;
	}
</style>
  	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<div id="makeCancel">
		<div id="impUid" style="display: none;">${purchaseProductVO.paymentsVO.impUid}</div>
		<form:form modelAttribute="cancelVO" method="post" 
				   action="/shop/orderlist/cancelrequest">
			<div>
				<form:errors path="canExcuse" element="div" cssClass="errors" />
				<form:errors path="canDetail" element="div" cssClass="errors" />
			</div>
			<h2>정말로 취소하시겠습니까?</h2>
				<input type="hidden" name="purchaseId" value="${purchaseId}" />
				<div>
					<h3>취소 사유 선택</h3>
					<select id="canExcuse" name="canExcuse">
						<option value="">사유 선택</option>
						<option value="단순 변심">단순 변심</option>
						<option value="배송 지연">배송 지연</option>
						<option value="상품 불만족">상품 불만족</option>
						<option value="기타">기타</option>
					</select>
				</div>
				<div>
					<h3>취소 사유 입력</h3>
					<textarea id="canDetail" name="canDetail" placeholder="취소 사유를 입력해주세요"></textarea>
				</div>
		</form:form>
		<div class="btn-group" id="cancel-btn-group">
			<button class="cancelCancelBtn btn_fill">취소</button>
			<button class="cancelSubmitBtn btn_empty" value="저장">확인</button>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>