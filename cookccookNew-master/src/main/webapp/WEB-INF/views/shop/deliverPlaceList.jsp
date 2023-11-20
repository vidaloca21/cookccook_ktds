<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>배송지 조회</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.get("/deliverPlace/list", function(response) {
		if(response.deliverPlaceList.length < 1) {
			$(".deliverPlaceList").append("<p>새 배송지를 등록하세요!</p>")
		}
		else{
			for (var i in response.deliverPlaceList) {
				let deliverPlaceListResponse = response.deliverPlaceList[i]
				let deliverPlaceIdDOM = $("<li></li>")
				deliverPlaceIdDOM = deliverPlaceIdDOM.text(deliverPlaceListResponse.deliverPlaceId)
				let delAliasDOM = $("<li></li>")
				delAliasDOM = delAliasDOM.text(deliverPlaceListResponse.delAlias)
				let delAddressDOM = $("<li></li>")
				delAddressDOM = delAddressDOM.text(deliverPlaceListResponse.delAddress)
				let delDetailAddressDOM = $("<li></li>")
				delDetailAddressDOM = delDetailAddressDOM.text(deliverPlaceListResponse.delDetailAddress)
				let zipCodeDOM = $("<li></li>")
				zipCodeDOM = zipCodeDOM.text(deliverPlaceListResponse.zipCode)
				let recipientDOM = $("<li></li>")
				recipientDOM = recipientDOM.text(deliverPlaceListResponse.recipient)
				let recipientPhoneNumberDOM = $("<li></li>")
				recipientPhoneNumberDOM = recipientPhoneNumberDOM.text(deliverPlaceListResponse.recipientPhoneNumber)
				
				let dpmdfyBtn = $("<li><button class='dpmdfyBtn'>수정</button</li>")
				let dpdeleteBtn = $("<button class='dpdeleteBtn'></button>")
				dpdeleteBtn.text("X")
				
				dpdeleteBtn.data("deliverPlaceId", deliverPlaceListResponse.deliverPlaceId)
				let dpdletDOM = $("<li></li>")
				dpdletDOM.append(dpdeleteBtn)

				let deliverPlaceListDOM = $("<ul></ul>")
				let deliverRadioInput = $("<input type='radio' name='deliverPlace'/>")
				deliverRadioInput.val(deliverPlaceListResponse.deliverPlaceId)
				if (i == 0) {
					deliverRadioInput.attr("checked", "checked")
				}
				deliverRadioInput.data("buyerAddr", deliverPlaceListResponse.delAddress)
				deliverRadioInput.data("buyerPostcode", deliverPlaceListResponse.zipCode)
				deliverPlaceListDOM.append(deliverRadioInput)
				deliverPlaceListDOM.append(delAliasDOM)
				deliverPlaceListDOM.append(delAddressDOM)
				deliverPlaceListDOM.append(delDetailAddressDOM)
				deliverPlaceListDOM.append(zipCodeDOM)
				deliverPlaceListDOM.append(recipientDOM)
				deliverPlaceListDOM.append(recipientPhoneNumberDOM)
				// deliverPlaceListDOM.append(dpmdfyBtn)
				deliverPlaceListDOM.append(dpdletDOM)
				$(".deliverPlaceList").prepend(deliverPlaceListDOM)
			}

			// 기존 배송지 삭제
			$(".dpdeleteBtn").click(function() {
				Swal.fire({
					title: '삭제하시겠습니까?',
					text: "확인을 누르시면 배송지 목록에서 삭제됩니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					}).then((result) => {
					if (result.isConfirmed) {
						data = {deliverPlaceId: $(this).data("deliverPlaceId")}
						$.post("/deliverPlace/deleteOne", data, function(response) {
							if (response.result) {
								Swal.fire({
									icon: 'success',
									title: '성공적으로 삭제되었습니다',
								});
								setTimeout(function() {
									location.reload()
								}, 1800);
							}
							else {
								Swal.fire({
									icon: 'error',
									title: '오류가 발생하였습니다',
								});
								setTimeout(function() {}, 1800);
							}
						})
					}
				})
				
			})
		}
	})

	/* 배송지 등록 모달 */
	// 모달팝업
	var modal = $("#deliverPlaceModal");
	var btn = $("#createNewDeliverPlaceBtn");
	var span = document.getElementsByClassName("close")[0];

	$("#createNewDeliverPlaceBtn").click(function() {
		$("#deliverPlaceModal").css("visibility", "visible");
	})
	span.onclick = function() {
		$("#deliverPlaceModal").css("visibility", "hidden");
	}

	$("#submitBtnForDeliverPlace").click(function() {
		let delAlias = $("input[name='delAlias']").val()
		let delAddress = $("input[name='delAddress']").val()
		let delDetailAddress = $("input[name='delDetailAddress']").val()
		let zipCode = $("input[name='zipCode']").val()
		let recipient = $("input[name='recipient']").val()
		let recipientPhoneNumber = $("input[name='recipientPhoneNumber']").val()
		$.ajax({
			url: "/deliverPlace/new",
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
				if (response.result) {
					var modal = document.getElementById("deliverPlaceModal");
					modal.style.display = "none";
					Swal.fire({
						icon: 'success',
						title: '성공적으로 등록되었습니다',
					});
					setTimeout(function() {
						location.reload()
					}, 1500);
				}
				else {
					Swal.fire({
						icon: 'error',
						title: '오류가 발생하였습니다',
					});
				}
			}
		})
	})
})
function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		window.open("/deliverPlace/newadress","주소 검색 팝업","width=500,height=600, scrollbars=yes, resizable=yes")
	}
	
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	var addressNumber = document.querySelector("#zipNo");
	addressNumber.value = zipNo;
	var addressFull = document.querySelector("#roadFullAddr");
	addressFull.value = roadFullAddr;
	var addressDetail = document.querySelector("#addrDetail");
	addressDetail.value = addrDetail
}

</script>
<style type="text/css">
	.deliverPlaceInfo > #infoArea {
		display: flex;
		width: 100%;
		align-items: center;
	}
	.deliverPlaceInfo > #infoArea > li {
		display: flex;
	}
	.deliverPlaceInfo > #infoArea > li > label {
		width: 240px;
	}
	.deliverPlaceInfo > #infoArea > li > input{
		width: 280px;
		height: 40px;
	}
	.deliverPlaceInfo > #infoArea > li > button{
		margin-left: 80px;
		cursor: pointer;
		height: 40px !important;
	}
	.deliverPlaceList {
		display: flex;
		flex-direction: column;
	}
	.deliverPlaceList > ul {
		display: flex;
		padding: 0;
	}
	.deliverPlaceList > ul > li {
		display: flex;
	}
	.deliverPlaceList > ul > li:first-child {
		width: 10%;
	}
	.deliverPlaceList > ul > li:nth-child(2) {
		padding-left: 10px;
		width: 10%;
	}
	.deliverPlaceList > ul > li:nth-child(3)  {
		width: 36%;
	}
	.deliverPlaceList > ul > li:nth-child(4)  {
		width: 10%;
	}
	.deliverPlaceList > ul > li:nth-child(5)  {
		width: 8%;
	}
	.deliverPlaceList > ul > li:nth-child(6)  {
		width: 8%;
	}
	.deliverPlaceList > ul > li:nth-child(7)  {
		width: 16%;
	}
	.deliverPlaceList > ul > li:last-child {
		width: 5%;
	}
	.dpdeleteBtn {
		cursor: pointer;
		background-color: transparent;
		border: none;
		font-weight: bolder;
		color: #bb1515;
	}
	#createNewDeliverPlaceBtn {
		cursor: pointer;
		width: 160px;
		height: 40px;
	}
	/* The Modal (background) */
	#deliverPlaceModal {
		visibility: hidden;
		width: 600px;
		left: 0;
		top: 0;
		right: 0;
		bottom: 0;
		visibility: hidden; /* Hidden by default */
		position: fixed; /* Stay in place */
		z-index: 920521; /* Sit on top */
		padding-top: 100px; /* Location of the box */
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
	  width: 500px;
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
	#deliverPlaceInputs > label {
		color: #2B1512;
		font-weight: bold;
	}
	#deliverPlaceInputs > input {
		height: 40px;
		margin-bottom: 4px;
		border: 1px solid #CACACA;
		border-radius: 4px;
	}
	#addr_search {
		width: 100%;
		cursor: pointer;
	}
	#submitBtnForDeliverPlace {
		cursor: pointer;
		align-self: flex-end;
	}
	#memoArea {
		display: flex;
		justify-content: space-between;
	}
	input[name=deliverMemo] {
		width: 300px;
		height: 40px;
	}
	
</style>
<div class="createNew">
	<h2>배송지 정보</h2>
</div>
<div class="deliverPlaceList"></div>
<ul id="infoArea">
	<li>
		<label for="deliverMemo">배송메모</label>
		<input type="text" name="deliverMemo" />
	</li>
	<li><button id="createNewDeliverPlaceBtn" class="btn_empty">새 배송지 등록하기</button></li>
</ul>
<div id="deliverPlaceModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<span class="close">&times;</span>
			<div id="deliverPlaceInputs">
				<h3>새로운 배송지 등록</h3>
				<label for="delAlias">배송지별칭</label>
				<input type="text" name="delAlias"/>

				<button id="addr_search" class="btn_empty" type="button" onClick="goPopup();">주소 검색</button>
				
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
				
				<button id="submitBtnForDeliverPlace" class="btn_fill">저장</button>
			</div>
	</div>
</div>