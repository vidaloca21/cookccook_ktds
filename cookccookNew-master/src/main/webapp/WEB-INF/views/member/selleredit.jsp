<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   
<title>내 정보 수정 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	.inputFormArea {
		display: flex;
		flex-direction: column;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script>
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		window.open("/member/address","주소 검색 팝업","width=570,height=420, scrollbars=yes, resizable=yes")
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
	function confirmUpdate() {
		if (confirm("판매자 회원 신청을 완료합니다")) {
			return true;
		} else {
			return false;
		}
	}
</script>
<jsp:include page="../layout/${header_type}.jsp"></jsp:include>

<div id="content" style="margin-left: 220px;">

	<h2>판매자 회원 신청</h2>
	<form:form modelAttribute="memberVO" action="/seller/selleredit" enctype="multipart/form-data" method="post" onsubmit="return confirmUpdate();">
	    <div>
	        <form:errors path="memNickname" element="div" cssClass="errors" />
	        <form:errors path="memName" element="div" cssClass="errors" />
	        <form:errors path="memPhoneNumber" element="div" cssClass="errors" />
	        <form:errors path="memAddress" element="div" cssClass="errors" />
	    </div>
	    <div class="inputFormArea">
	        <label for="memNickname">판매자용 닉네임</label>
	        <input type="text" name="memNickname" id="memNickname" value="${memberVO.memNickname }" />
	        
			<label for="memPhoneNumber">전화번호</label>
	        <input type="number" name="memPhoneNumber" id="memPhoneNumber" value="${memberVO.memPhoneNumber}" />

			<label for="businessNumber">사업자 번호</label>
	        <input type="text" name="businessNumber" id="businessNumber" value="${memberVO.businessNumber}" />
	        
			<label for="memAddressNo">주소</label>
			<input type="text" class="form-control" id="zipNo" name="memAddressNo" placeholder="우편번호" >
			<input type="text" class="form-control" id="roadFullAddr" name="memAddress"  placeholder="기본 주소를 입력해주세요" >
			<input type="text" class="form-control"  id="addrDetail" name="memAddressDetail" placeholder = "나머지 상세주소" />
	        <button class="btn btn-default" type="button" onClick="goPopup();"><i class="fa fa-search"></i>주소검색</button>
	 
	 		<label for="file">프로필 사진 (선택사항)</label>
 			<input type="file" name="file" id="file" />
	        
	        <div class="btn-group">
	            <div class="right-align">
	                <input id="btn-regist" type="submit" value="판매자 회원 신청" />
	            </div>
	        </div>
	    </div>
	    </form:form>
    </div>


<jsp:include page="../layout/footer.jsp"></jsp:include>
