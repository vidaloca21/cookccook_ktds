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
        
    
        .content{
		    text-align: center;
		    position:relative;   
		    width:100%;
		    height:1100px;
		    top: 100px;
		    margin-bottom:50px;
		}
		
		.regist{
		    left: 50%;  
		    top:50%;
		    transform:translate(-50%,-50%);
		    position:relative;
		    width: 400px;
		    height: 100%;
		} 
       
		#registid{
		    font-size: 20px;
		    font-weight:bold;
		}
        .form-label{
		    width:300px;
		    text-align:left;
		    margin-right: 100px;
		}
        /* 체크박스 */
		.checkbox{
		    font-size: 10px;
		    text-align: left;
		}
		
		#btn-regist{
			background-color: black;
		    color: white;
		    width: 240px;
		    height: 54px;
		    margin-top: 50px;
		    border-radius:5px;
		    border:1px solid darkgrey;
		}
		
		#btn-regist:hover{
			background-color: #0056b3;
		}
		
        .form-label {
		    display: block;
		    font-weight: bold;
		    margin-bottom: 5px;
		}
        
        
		.input-group {
		    position: relative;
		    margin-bottom: 15px;
		}
		
		/* 이메일 입력란 스타일 */
		#email {
		    width: 100%;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
		    outline: none; /* 포커스 시 기본 아웃라인 제거 */
		}
		
		#email:focus {
		    border-color: #0056b3; /* 포커스 시 테두리 색상 변경 */
		}
		
		/* 비밀번호, 비밀번호 확인, 이름, 전화번호 입력란 스타일 */
		#password, #confirmPassword, #name, #phonenumber {
		    width: 100%;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
		    outline: none; /* 포커스 시 기본 아웃라인 제거 */
		}
		
		#password:focus, #confirmPassword:focus, #name:focus, #phonenumber:focus {
		    border-color: #0056b3; /* 포커스 시 테두리 색상 변경 */
		}
		
		
    </style>
    <script src="/js/lib/jquery-3.7.1.js"></script>
    <jsp:include page="../layout/header2.jsp"></jsp:include>
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
    </script>


<div class = "content" >
	<div class = "regist">
	<div>
		<h1 id="registtitle" class="form-label">회원가입</h1><br>
	</div>
	<form:form modelAttribute="memberVO" method="post" onsubmit="return validateForm();">
	    <div>
	        <form:errors path="memName" element="div" cssClass="errors" />
	        <form:errors path="memEmail" element="div" cssClass="errors" />
	        <form:errors path="memPassword" element="div" cssClass="errors" />
	        <form:errors path="confirmPassword" element="div" cssClass="errors" />
	        <form:errors path="memPhoneNumber" element="div" cssClass ="errors"/>
	    </div>
	
	    <div class="grid-regist"> 
	        <label for="email" class="form-label">이메일*</label>
	        <div class="input-group">
	        	<input type="email" name="memEmail" id="email" value="${memberVO.memEmail}" />
			</div>
			
			<label for="password" class="form-label">비밀번호*</label>
			<div class="input-group">
	        	<input type="password" name="memPassword" id="password" value="${memberVO.memPassword}" />
			</div>
				
	        <label for="confirmPassword" class="form-label">비밀번호 확인*</label>
	        <div class="input-group">
	        	<input type="password" name="confirmPassword" id="confirmPassword" value="${memberVO.confirmPassword}" />
			</div>
				
	        <label for="name" class="form-label">이름*</label>
	        <div class="input-group">
	        	<input type="text" name="memName" id="name" value="${memberVO.memName}" />
	        </div>
	        
	        <label for="phonenumber" class="form-label">전화번호*</label>
	        <div class="input-group">
	        	<input type="number" name="memPhoneNumber" id="phonenumber" value="${memberVO.memPhoneNumber}" />
			</div>
			
			
			<div class="checkbox">
                    <div>
                        <input type="checkbox" value='checkoff' onclick="agreeCheck(this);">
                        <label for="all_check_agree">모두 동의합니다.</label>
                    </div>
                    <br>
                    <div>
                        <input type="checkbox" class='check_box'> 
                        <label for="all_check_agree">(필수)이용약관과 개인정보 수집 및 이용에 동의합니다.</label>
                    </div>
                    <br>    
                    <div>
                        <input type="checkbox" align="left" class='check_box'> (필수) 만 14세 이상입니다.만19세 미만의 미성년자가 결제 시 법정대리인이 거래를 취소할수있습니다.
                    </div> 
                    <br>
                    <div>
                        <input type="checkbox" align="left" class='check_box'> (필수) 이메일 및 SMS 마케팅 정보 수신에 동의합니다. 회원은 언제든지 회원 정보에서 수신거부로 변경할수있습니다.
                    </div>
                    <br>
              </div>
			
	
	
	
	        <div class="btn-group">
	            <div class="right-align">
	                <input id="btn-regist" disabled="disabled" 
	                       type="submit" value="회원가입" />
	            </div>
	        </div>
	    </div>
	</form:form>
	</div>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>