<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<title>내 정보 수정 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
        div.grid {
            display: grid;
            grid-template-columns: 200px 1fr;
            grid-template-rows: 28px 28px 28px 28px 1fr;
            row-gap: 20px;
        }

        div.grid > div.btn-group {
            display: grid;
            grid-column: 1 / 3;
        }

        div.grid div.right-align {
            text-align: right;
        }

        label {
            padding-left: 10px;
        }

        button, input {
            padding: 10px;
            border: 1px solid #CCC;
        }

        div.errors {
            background-color: #FF00004A;
            opacity: 0.8;
            padding: 10px;
            color: #333;
        }

        div.errors:last-child {
            margin-bottom: 10px;
        }

        .available {
            background-color: #0F03;
        }

        .unusable {
            background-color: #F003;
        }
        input[type=file]{
        padding:0px;
        }
        
        /* 페이지 타이틀 스타일 */
	    .page-title {
	        font-size: 32px;
	        font-weight: bold;
	        text-align: center;
	        color: #333; 
	        text-transform: uppercase;
	        margin-top: 10px; /* 상단 여백 조정 */
	    }
	
	    /* 이메일 정보 스타일 */
	    /* .email-info {
	        font-size: 18px; 
	        color: #666;
	        margin-top: 5px;
	    } */
        
        /* .menu-item a {
            display: block;
            color: white;
            text-decoration: none;
            margin-bottom: 10px;
        } */
        #content {
            margin-left: 220px; /* 사이드바 너비 + 여백 크기 */
            padding: 20px; /* 페이지 내용 주변 여백을 추가합니다. */
        }
        
        #sidebar {
            background-color: #FF8C69;
            color: #fff;
            padding: 20px;
            width: 200px;
            display: flex;
            flex-direction: column;
        }
        
        .page-email{
        	font-size: 20px;
	        font-weight: bold;
	        text-align: left;
	        color: #333; 
	        margin-top: 5px;
        }
        
        .menu-item {
            margin: 10px 0;
            text-align: center;
        }

        .menu-item a {
            text-decoration: none;
            color: #fff;
        }
        
        .deleteMember-link{
        	margin-top: 100px;
        	font-size: 18px;
        	text-align: right;
        }
        .deleteMember-link:hover{
        	color:#FF8C69;
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
		        if (confirm("회원 정보를 수정하셨습니다. 최신 정보를 반영하기 위해 재로그인을 진행하시겠습니까?")) {
		            // 사용자가 확인을 누르면 폼을 제출하고, 아니면 제출을 취소
		            return true;
		        } else {
		            return false;
		        }
		    }
		    
    </script>
<jsp:include page="../layout/${header_type}.jsp"></jsp:include>

<div id="content" style="margin-left: 220px;">
	<h1 class="page-title"> 내 정보 수정 페이지</h1>
	<h2 class="page-email"> 내 이메일 : ${memberVO.memEmail } </h2>


	<form:form modelAttribute="memberVO" enctype="multipart/form-data" method="post" onsubmit="return confirmUpdate();">
	    <!-- 주석입니다. -->
	    <div>
	        <form:errors path="memNickname" element="div" cssClass="errors" />
	        <form:errors path="memName" element="div" cssClass="errors" />
	        <form:errors path="memBirthday" element="div" cssClass="errors" />
	        <form:errors path="memPhoneNumber" element="div" cssClass="errors" />
	        <form:errors path="memSex" element="div" cssClass="errors" />
	        <form:errors path="memAddress" element="div" cssClass="errors" />
	    </div>
	
	    <div class="grid">
	        
	        <label for="memNickname">현재 내 닉네임 : ${memberVO.memNickname }</label>
	        <input type="text" name="memNickname" id="memNickname" value="${memberVO.memNickname }" />
	        
	        <label for="memName">현재 내 이름 : ${memberVO.memName }</label>
	        <input type="text" name="memName" id="memName" value="${memberVO.memName }" />
	        
	        
	        
			<label for="currentPassword">현재 비밀번호:</label>
			<input type="password" name="currentPassword" id="currentPassword">
	
			<label for="newPassword">새 비밀번호:</label>
			<input type="password" name="newPassword" id="newPassword">
	
			<label for="confirmPassword">새 비밀번호 확인:</label>
			<input type="password" name="confirmPassword" id="confirmPassword">
			
			
			
			<label for="memBirthday">현재 내 생일 :<br> ${memberVO.memBirthday }</label>
	        <input type="date" name="memBirthday" id="memBirthday" value="${memberVO.memBirthday }" />
	        
			<label for="memPhoneNumber">현재 내 전화번호 : ${memberVO.memPhoneNumber}</label>
	        <input type="number" name="memPhoneNumber" id="memPhoneNumber" value="${memberVO.memPhoneNumber}" />
	        
	        <%-- <label for="memSex">현재 내 성별 : ${memberVO.memSex }</label> --%>
	        <%-- <input type="text" name="memSex" id="memSex" value="${memberVO.memSex}" />
	         --%>
	        <label for="memSex">현재 내 성별 : ${memberVO.memSex }</label>
			<select name="memSex" id="memSex">
			    <option value="남자" ${memberVO.memSex == '남자' ? 'selected' : ''}>남자</option>
			    <option value="여자" ${memberVO.memSex == '여자' ? 'selected' : ''}>여자</option>
			    <option value="" ${memberVO.memSex == null ? 'selected' : ''}>선택 안 함</option>
			</select> 
	         
	        
	        
	        <label for="memAddress">현재 내 주소 : ${memberVO.memAddress } + ${memberVO.memAddressDetail}</label></br>
	        
	        
			<input type="text" class="form-control" id="zipNo" name="memAddressNo" placeholder="우편번호" >
	        <button class="btn btn-default" type="button" onClick="goPopup();"><i class="fa fa-search"></i>우편번호 검색</button>
	
			<input type="text" class="form-control" id="roadFullAddr" name="memAddress"  placeholder="기본 주소를 입력해주세요" >
	        <button class="btn btn-default" type="button" onClick="goPopup();"><i class="fa fa-search"></i>주소검색</button>
			
	
			<label for="memAddressDetail">현재 내 상세 주소 : ${memberVO.memAddressDetail }</label>
			<input type="text" class="form-control"  id="addrDetail" name="memAddressDetail" placeholder = "나머지 상세주소" />
	
	 
	 		<label for="file">프로필 사진</label>
	 		<div>
	 			<input type="file" name="file" id="file" />
	 			현재 프로필 사진 : ${memberVO.originFileName}
	        </div>
	        
	        
	        <label for="preferFoodId">선호 식품</label><br/>
	        <%-- <input type="text" name="preferFoodId" id="preferFoodId" value="${prferFoodVO.preferFoodId}" /> --%>
	        
	        <label for="allergyId">알레르기 식품</label><br/>
	        <%-- <input type="text" name="allergyId" id="allergyId" value="${allergyVO.allergyId}" /> --%>
	        
	        
	        <div class="btn-group">
	            <div class="right-align">
	                <input id="btn-regist"
	                       type="submit" value="등록" />
	            </div>
	        </div>
	    </div>
	    </form:form>
	    
	    <div class = "deleteMember-link">
	    	<a href="/member/memberDelete">회원 탈퇴</a>
	    </div>
    </div>


<jsp:include page="../layout/footer.jsp"></jsp:include>
