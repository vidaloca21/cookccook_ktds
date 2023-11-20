<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<title>내 구독 정보</title>

<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style css="text/css">
.container {
	margin: 10px;
	padding-left:10px;
	padding-right:10px;
	background-color: #fff;
}

.font_title {
	margin-top: 20px;
	margin-left: 20px;
}
.font_subtitle {
	font-weight:bold;
}

.sub_grid_container {
	display: grid;
	grid-template-columns: 50% 50%;
}
.recipe-item{
	
}

.myInfo {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin: 20px;
	padding-bottom: 10px;
	background-color: #f3f3f3;
	justify-content: space-around;
	
}

.myInfo2{
	display: flex;
	font-size: 15px;
	justify-content: space-between;
}

.mySubInfo_detail {
	justify-items: center;
	width: 280px;
	height: 150px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

.recipe_recom {
	display: flex;
	justify-content: center;
    align-items: center;
	flex-direction: row;
	margin: 20px;
	background-color: #f3f3f3;
	padding-bottom: 10px;
}

.ing_thismonth{
	display: flex;
	flex-direction: column;
    align-items: center;
}

.img_container {
	overflow: hidden;
	width: 100%;
	height: 147px;
}


.img_container>a>img {
	width: 261px;
	height: 147px;
	opacity: 1;
	transition: opacity 0.2s;
	object-fit: cover;
	object-position: center;
	z-index: 1;
}



a>img:hover {
	opacity: 0.5;
	
}

.recom_menu {
	position: absolute;
	top:415px;
	z-index: 2;
	width: 251px;
	height: 30px;
	padding-left: 5px;
	padding-right: 5px;

	display: flex;
	justify-content: space-around;	
	font-size: 17px;
	color: #fff;
	background-color: #5555;
}


#prevButton, #nextButton {
	background-color: #5555;
	border: none;
	cursor: pointer;
	margin: 10px;
	height: 30px;
	width: 30px;
	font-size: 18px;
	color: #fff;
	border-radius: 50%;
}
#empty, #empty2{
	height: 30px;
	width: 30px;
	margin: 10px;
}
#prevButton:hover, #nextButton:hover {
  	background-color: #3333; /* 호버 시 배경색 변경 */
}

.del_info_container {
	margin: 15px;
	display: grid;
	grid-template-rows: 50px 1fr;
	padding-bottom: 20px;
}

.container1stRow {
	display: grid;
	grid-template-columns: 50% 50%;
	text-align: center;
	cursor: pointer;
	align-items: center;
}

#thisDelivery {
	font-weight: bold;
	color: #FF6347;
	background-color: #f3f3f3;
	
}

.subs_info {
text-align: center;
font-size: 17px;
}


.item_container {
	padding: 10px;
	text-align: center;
}

.item_name {
	display: inline-block;
    padding: 5px 10px 5px 10px;
    background-color: #aaa;
    border-radius: 20px;
}

table {
  border-collapse: collapse;
}

table {
  border: none;
}

tr {
  margin: 7px;
  
}


.empty_td {
  width: 550px;
}
 

.previous_delivery {
	padding-top: 15px;
	padding: 5px;
}

.seeInfoSlider {
    display: grid;
    grid-template-columns: 40% 40% 20%;
    background-color: #fff;
    align-items: center;
    padding: 10px 15px 15px 40px;
    background-color: #f3f3f3;

}

.deliveredItems {
    background-color: #f3f3f3;
    padding-bottom: 30px;
    display: flex;
    flex-direction: row-reverse;
	}
    
.this_subs_delivery, .next_subs_delivery {

	background-color: #EDEDEC;
}


.btn_group {
	display: flex;
	flex-direction: column;
	justify-content: center; 
	align-items: center; 
}

.btn_empty {
	margin: 5px;
	color: #FB8F67;
	width: 120px;
	height: 40px;
}
.cancelarea {
	display:flex;
	flex-direction: row-reverse;
    align-items: flex-end;
	height: 50px;
	margin-top: 50px;
	margin-right: 20px;
}
.btn_fill {
	width: 80px;
	height: 20px;
	font-size: 15px;
	font-weight: normal;
}

</style>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/lib/ckeditor.js"></script>
<script src="/js/Editor.js"></script>


<script>
	function getToken() {
		$
				.ajax(
						{
							"url" : "https://api.iamport.kr/users/getToken",
							"type" : "POST",
							"contentType" : "application/json",
							"data" : JSON
									.stringify({
										// REST API키
										imp_key : "8440145738425552",
										// REST API Secret
										imp_secret : "tDKItaRuSs1DoANIfMtRGPKlx6f9CvoooN1SrRFpcTkgHp1rGoFcfcOzxIXrltUZ8GbM7uytjKYvkcx3"
									}),
							"dataType" : "json"
						}).done(function(result) { // 토큰 발급 성공
					console.log("result2:" + result)
					if (confirm("구독을 취소하시겠습니까?")) {
						refund(result.response.access_token)
					}
					return false;
				}).fail(function(error) { // 토큰 발급 실패
					console.log(error)
				});
	}

	function refund(token) {
		$.ajax({
			"url" : "https://api.iamport.kr/payments/cancel",
			"type" : "POST",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST",
				Authorization : "Bearer " + token
			},
			"data" : JSON.stringify({
				reason : "테스트 결제 환불", // 가맹점 클라이언트로부터 받은 환불사유
				imp_uid : "${paymentsVO.impUid}", // imp_uid를 환불 `unique key`로 입력
			//amount: 100, // 가맹점 클라이언트로부터 받은 환불금액, 미입력시 전액이 환불
			//checksum: cancelableAmount // [권장] 환불 가능 금액 입력, 테스트모드는 사용불가(?)
			}),
			"dataType" : "json"
		}).done(function(result) { // 환불 성공
			console.log("result3:" + result)
			location.href = "/subscribe/mysubs";
		}).fail(function(error) { // 환불 실패
			console.log(error)
		});
	}
	function cancelPay() {
		$
				.ajax(
						{
							// 예: http://www.myservice.com/payments/cancel
							"url" : "/subscribe/mysubs/cancelrequest/"
									+ "${getMySubsInfo.subPayId}",
							"type" : "POST",
							"contentType" : "application/json",
							"data" : JSON
									.stringify({
										"merchant_uid" : "${paymentsVO.merchantUid}", // 예: ORD20180131-0000011
										"cancel_request_amount" : "${getMySubsInfo.subscribeVO.subPrice}", // 환불금액
										"reason" : "테스트 결제 환불", // 환불사유
										// [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
										"refund_holder" : "홍길동",
										// [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
										"refund_bank" : "88",
										// [가상계좌 환불시 필수입력] 환불 수령계좌 번호
										"refund_account" : "56211105948400"
									}),
							"dataType" : "json"
						}).done(function(result) { // 환불 성공시 로직 
					console.log("result1:" + result)
					getToken()

				}).fail(function(error) { // 환불 실패시 로직
					console.log(error)
				});
	}

	$().ready(function() {
		// 초기 상태에서는 "이번 배송 정보"를 표시하고 "다음 배송 정보"를 숨깁니다.
		$("#nextDelivery").removeClass("active")
		$("#thisDelivery").addClass("active")
		$(".next_subs_delivery").hide()
		$(".this_subs_delivery").show()

		// "다음 배송 정보" 클릭 시
		$("#nextDelivery").click(function() {
			if (!$(this).hasClass("active")) {
				// "다음 배송 정보"가 활성화되지 않았으면 토글합니다.
				$(this).addClass("active")
				$("#thisDelivery").removeClass("active")
				$(".next_subs_delivery").show()
				$(".this_subs_delivery").hide()
			}
		})

		// "이번 배송 정보" 클릭 시
		$("#thisDelivery").click(function() {
			if (!$(this).hasClass("active")) {
				// "이번 배송 정보"가 활성화되지 않았으면 토글합니다.
				$(this).addClass("active")
				$("#nextDelivery").removeClass("active")
				$(".this_subs_delivery").show()
				$(".next_subs_delivery").hide()
			}
		})

		// 메뉴 처리
		$("#thisDelivery").click(function() {
			$(this).css("font-weight", "bold")
			$(this).css("color", "#FF6347")
			$("#nextDelivery").css("font-weight", "normal")
			$("#nextDelivery").css("color", "#2B1512")
		})

		$("#nextDelivery").click(function() {
			$(this).css("font-weight", "bold");
			$(this).css("color", "#FF6347");
			$("#thisDelivery").css("font-weight", "normal")
			$("#thisDelivery").css("color", "#2B1512")
		})

		// 레시피 추천 항목
		var currentIndex = 0;

		showItem(currentIndex)
		
		if (currentIndex == 0) {
			$('#empty').show()
			$('#prevButton').hide()
			$('#nextButton').show()
			$('#empty2').hide()
		}

		$('#nextButton').click(function() {
			console.log(currentIndex)
			if (currentIndex < 4 ) {
				currentIndex++;
				showItem(currentIndex)
			}
			
			if (currentIndex == 0) {
				$('#empty').show()
				$('#prevButton').hide()
				$('#nextButton').show()
				$('#empty2').hide()
			}
				
			if (currentIndex !== 0) {
				$('#empty').hide()
				$('#prevButton').show()
				$('#nextButton').show()
				$('#empty2').hide()
			}
			if (currentIndex == 4) {
				$('#nextButton').hide()
				$('#prevButton').show()
				$('#empty2').show()

			}
		})

		$('#prevButton').click(function() {
			if (currentIndex > 0) {
				currentIndex--;
				showItem(currentIndex)
			}
			
			if (currentIndex == 0) {
				$('#empty').show()
				$('#prevButton').hide()
				$('#nextButton').show()
				$('#empty2').hide()
			}
				
			if (currentIndex !== 0) {
				$('#empty').hide()
				$('#prevButton').show()
				$('#nextButton').show()
				$('#empty2').hide()

			}
		})

		function showItem(index) {
			$('.recipe-item').hide()
			$('.recipe-item').eq(index).show()
		}
		
		// 구독내역 토글슬라이더
		 $(".deliveredItems").hide();

		  $(".seeInfoSlider").click(function() {
			 var index = $(".seeInfoSlider").index(this);
			 $(".deliveredItems").eq(index).slideToggle();
			 $(".bt_line").hide()
		  })
	})
</script>


<jsp:include page="../layout/${header_type}.jsp"></jsp:include>
<c:choose>
	<c:when test="${not empty getMySubsInfo}">
		<div class="font_title">내 구독 정보</div>
		<div class="sub_grid_container">
			<div class="myInfo">
				<div>
					<div class="font_subtitle" style="font-weight: bold">이번 달 구독 정보</div>
				</div>
				<div class="mySubInfo_detail">
					<div class="myInfo2">
						<div class="font_content">구독중인 상품 : </div>
						<div class="font_content">${getMySubsInfo.subsCategory}</div>
					</div>
					<div class="myInfo2">
						<div class="font_content">배송 주기 : </div>
						<div class="font_content">${getMySubsInfo.subsCycle}</div>
					</div>
					<div class="myInfo2">
						<div class="font_content">구독료: </div>
						<div class="font_content">	${getMySubsInfo.subscribeVO.subPrice}원</div>
					</div>
					<div class="myInfo2">
						<div class="font_content">다음 결제일: </div>
						<div class="font_content">${getMyNextSubName.nextDeliveryDate}</div>
					</div>
				</div>
			</div>


			<div class="recipe_recom">
				<button id="prevButton">&lt</button>
				<div id="empty"></div>
				<c:forEach items="${getMySubRecom}" var="articleVO">
					<c:forEach items="${articleVO.artIngVOList}" var="artIngList">
						<div class="recipe-item">
							<div class="ing_thismonth">
								<div class="font_subtitle" style="font-weight: bold">레시피 추천</div>
								<div>이달의 재료 &lt ${artIngList.ingredientVO.ingredientName} &gt</div>
							</div>
							<div class="img_container">
								<a href="/recipe/${articleVO.articleId}"> <img
									src="${articleVO.recipeVO.attImgSmall}"/></a>
								<div class="recom_menu">${articleVO.title}</div>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
				<button id="nextButton">&gt</button>
				<div id="empty2"></div>
			</div>
		</div>
		
		<div style=" height: 1px; border-top: 1px solid #FF6347; border-bottom: 1px solid #FF6347"></div>

		<div class="del_info_container">
			<div class="container1stRow">
				<div class="font_subtitle" id="thisDelivery">이번 배송 정보</div>
				<div class="font_subtitle" id="nextDelivery">다음 배송 정보</div>
			</div>
			
			<div class="next_subs_delivery">
				<div class="subs_info">
				<c:choose>
					<c:when test="${not empty getMyNextSubItem}">
						<div>${getMySubsInfo.subsCategory}</div>
						<div>${getMyNextSubName.nextDeliveryDate} 배송 예정 상품 </div>
						<div class="item_container">
							<c:forEach items="${getMyNextSubItem}" var="nextSubscribeList">
							<c:forEach items="${nextSubscribeList.subIngList}" var="nextSubIngList">
							<c:forEach items="${nextSubIngList.ingredientList}" var="nextIngredientList">
								<div class="item_name">
								${nextIngredientList.ingredientName}&nbsp${nextSubIngList.subIngOpt}
								</div>
							</c:forEach>
							</c:forEach>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div style="text-align: center"> 다음 회차 상품을 선별 중이에요! </div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="this_subs_delivery">
				<div class="subs_info">
					<div>${getMySubsInfo.subsCategory}</div>
					<div>${getLatestDeliveryDate.deliveredDate} 배송된 상품</div>
				</div>
				<div class="item_container">
					<c:forEach items="${getLatestSubItems.ingredientList}" var="thisIngredientList">
					<c:forEach items="${thisIngredientList.subIngList}" var="thisSubIngList">
						<div class="item_name">
						${thisIngredientList.ingredientName}&nbsp${thisSubIngList.subIngOpt}
						</div>
					</c:forEach>
					</c:forEach>
				</div>
			</div>
		</div>

	</c:when>
	<c:otherwise>
		<div class="myinfo">
			<div class="font_subtitle" style="margin: 10px;">내 구독 정보</div>
			<div style="margin: 10px">현재 구독중인 정보가 없습니다!</div>
			<div class="navigate_btn" style="margin: 10px">
				<a href="/subscribe/info"> 👉 구독 하러가기</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>

	<div style=" height: 1px; border-top: 1px solid #FF6347; border-bottom: 1px solid #FF6347"></div>
	

	<div>
		<div class="font_subtitle" style="margin-top:20px; margin-left:20px">구독 내역</div>
		<c:choose>
			<c:when test="${not empty getPrevHistory}">
				<c:forEach items="${getPrevHistory}" var="SubscribeList">
					<c:forEach items="${SubscribeList.subPayList}" var="subPayList">
						<div class="previous_delivery">
							<div class="seeInfoSlider">
								<div>
									<div>배송일: ${subPayList.deliveredDate}</div>
									<div>${subPayList.memberVO.deliverPlaceVO.delAddress}
									${subPayList.memberVO.deliverPlaceVO.delDetailAddress}</div>
								</div>
								<div style="text-align: right">
									<div>결제일: ${subPayList.payDate}</div>
									<div>${SubscribeList.subChoice} - ${SubscribeList.subPrice} 원</div>
									<div>${subPayList.payMethod} 결제 완료 </div>
								</div>
								<div class="btn_group">
									<a href="/subscribe"><button class="btn_empty">리뷰 작성</button></a> 
									<a href="/subscribe"><button class="btn_empty">배송 추적</button></a>
								</div>
							</div>
							<div class="deliveredItems">
								<table>
 									<c:forEach items="${SubscribeList.subIngList}" var="subIngList">
 										<c:forEach items="${subIngList.ingredientList}" var="ingredientList">
 											<div>
												<div class="item_name">${ingredientList.ingredientName}&nbsp${subIngList.subIngOpt}</div>
											</div>
 										</c:forEach> 
 									</c:forEach>
								</table>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div style="text-align: center">구독 내역이 없습니다.</div>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty getMySubsInfo}">
				<div class="cancelarea">
					<button onclick="cancelPay()" class="btn_fill">
						구독 취소
						<!-- <a href="/subscribe/mysubs/${memberId}" onclick="cancelPay()">취소 요청</a> -->
					</button>
				</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>
