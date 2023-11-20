<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
    <style type="text/css">
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
        .registArea {
            display: grid;
            grid-template-columns: 1fr 1fr;
            column-gap: 20px;
        }
        .regist-left {
            grid-column: 1/2;
            display: flex;
            flex-direction: column;
        }
        .regist-right {
            grid-column: 2/3;
            display: flex;
            flex-direction: column;
        }

        /* 체크박스 */
		.checkbox{
            display: flex;
            flex-direction: column;
		    font-size: 12px;
		}
        #btn-regist{
			background-color: black;
		    color: white;
		    width: 360px;
		    height: 54px;
		    border-radius:5px;
		    border:1px solid darkgrey;
		}
		#btn-regist:hover{
			background-color: #0056b3;
		}
		
        .form-label {
		    display: block;
		    font-weight: bold;
            margin-top: 10px;
		    margin-bottom: 5px;
		}
		.input-group {
		    position: relative;
		    margin-bottom: 15px;
		}
		
		/* 이메일 입력란 스타일 */
		input[type=text], input[type=number], input[type=email], input[type=password] {
            height: 36px;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            margin-bottom: 5px;
		    outline: none; /* 포커스 시 기본 아웃라인 제거 */
		}

		#email:focus, #password:focus, #confirmPassword:focus, #name:focus, #phonenumber:focus {
		    border-color: #0056b3; /* 포커스 시 테두리 색상 변경 */
		}

        .btn-search {
            height: 36px;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            margin-bottom: 5px;
		    outline: none;
        }
    </style>
    <script src="/js/lib/jquery-3.7.1.js"></script>
    <script type='text/javascript'>
        $().ready(function() {
            $("#email").keyup(function() {
                console.log("키 입력 이벤트 감지됨.");
                //GET Ajax를 호출
                $.get("/member/regist/available", {
                    email: $("#email").val()
                }, function(response) {
                    var email = response.email;
                    var available = response.available;
                    console.log("response에 들어옴 이벤트 감지됨.");

                    if (available) {
                        $("#email").addClass("available");
                        $("#email").removeClass("unusable");
                        $("#btn-regist").removeAttr("disabled");
                        console.log("available 들어옴 이벤트 감지됨.");
                    }
                    else {
                        $("#email").addClass("unusable");
                        $("#email").removeClass("available");
                        $("#btn-regist").attr("disabled", "disabled");
                        console.log("else 들어옴 이벤트 감지됨.");
                    }
                });
            });
        });
        
        function agreeCheck(self) {
            if(self.value == 'checkoff') {
                let check_box = document.querySelectorAll('.check_box');
                for(let ele in check_box) {
                    check_box[ele].checked = true;
                }

                self.value = 'checkon';

            } 
            else {
                let check_box = document.querySelectorAll('.check_box');
                for(let ele in check_box) {
                    check_box[ele].checked = false;
                }
                self.value = 'checkoff'
            }
        }
        
         function validateForm() {
            // 이메일, 비밀번호, 비밀번호 확인, 이름, 전화번호 입력란 값 가져오기
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var name = document.getElementById("name").value;
            var phoneNumber = document.getElementById("phonenumber").value;

         	// 비밀번호와 비밀번호 확인 비교
            if (password !== confirmPassword) {
                alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                return false;
            }
            
            // 체크박스 상태 확인
            var checkboxes = document.querySelectorAll('.check_box');
            var allChecked = true;

            for (var i = 0; i < checkboxes.length; i++) {
                if (!checkboxes[i].checked) {
                    allChecked = false;
                    break;
                }
            }

            // 모든 조건 확인
            if (email === "" || password === "" || confirmPassword === "" || name === "" || phoneNumber === "") {
                alert("모든 필수 항목을 입력하세요.");
                return false;
            }

            if (!allChecked) {
                alert("모든 체크박스를 선택하세요.");
                return false;
            }

            // 모든 조건이 만족되면 true를 반환하여 폼 제출을 허용
            return true;
        }

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

    <jsp:include page="../layout/header2.jsp"></jsp:include>
	<h1 id="registtitle" class="form-label">판매자 회원 신청</h1><br>
	<form:form modelAttribute="memberVO" action="/seller/sellerregist" method="post" onsubmit="return validateForm();">
	    <div>
	        <form:errors path="memName" element="div" cssClass="errors" />
	        <form:errors path="memEmail" element="div" cssClass="errors" />
	        <form:errors path="memPassword" element="div" cssClass="errors" />
	        <form:errors path="confirmPassword" element="div" cssClass="errors" />
	        <form:errors path="memPhoneNumber" element="div" cssClass ="errors"/>
	    </div>
	
	    <div class="registArea"> 
            <div class="regist-left">
	        <label for="email" class="form-label">이메일</label>
	        <input type="email" name="memEmail" id="email" value="${memberVO.memEmail}" />
			
			<label for="password" class="form-label">비밀번호</label>
	        <input type="password" name="memPassword" id="password" value="${memberVO.memPassword}" />
				
	        <label for="confirmPassword" class="form-label">비밀번호 확인</label>
	        <input type="password" name="confirmPassword" id="confirmPassword" value="${memberVO.confirmPassword}" />
				
	        <label for="memName" class="form-label">이름</label>
	        <input type="text" name="memName" id="memName" value="${memberVO.memName}" />
	        
	        <label for="phonenumber" class="form-label">전화번호</label>
	        <input type="number" name="memPhoneNumber" id="phonenumber" value="${memberVO.memPhoneNumber}" />
			
			<label for="businessNumber" class="form-label">사업자 번호</label>
	        <input type="text" name="businessNumber" id="businessNumber" value="${memberVO.businessNumber}" />
	        
            </div>
            <div class="regist-right">
            <label for="memNickname" class="form-label">판매자용 닉네임</label>
            <input type="text" name="memNickname" id="memNickname" value="${memberVO.memNickname }" />
            
            <label for="memAddressNo" class="form-label">주소</label>
            <input type="text" class="form-control" id="zipNo" name="memAddressNo" placeholder="우편번호" >
            <input type="text" class="form-control" id="roadFullAddr" name="memAddress"  placeholder="기본 주소를 입력해주세요" >
            <input type="text" class="form-control"  id="addrDetail" name="memAddressDetail" placeholder = "나머지 상세주소" />
            <button class="btn-search" type="button" onClick="goPopup();"><i class="fa fa-search"></i>주소검색</button>
            <label for="file" class="form-label">프로필 사진 (선택사항)</label>
            <input type="file" name="file" id="file" />

			<div class="checkbox">
                <div>
                    <input type="checkbox" value='checkoff' onclick="agreeCheck(this);">
                    <label for="all_check_agree">모두 동의합니다.</label>
                </div>
                <div>
                    <input type="checkbox" class='check_box'> 
                    <label for="all_check_agree">(필수)이용약관과 개인정보 수집 및 이용에 동의합니다.</label>
                </div>
                <div>
                    <input type="checkbox" class='check_box'>
                    <label for="">(필수) 만 14세 이상입니다.만19세 미만의 미성년자가 결제 시 법정대리인이 거래를 취소할수있습니다.</label>
                </div>
                <div>
                    <input type="checkbox" class='check_box'>
                    <label for="">(필수) 이메일 및 SMS 마케팅 정보 수신에 동의합니다.</label>
                </div>
                <input id="btn-regist" disabled="disabled" type="submit" value="회원가입" />
            </div>
        </div>
    </form:form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>