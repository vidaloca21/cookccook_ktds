<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <title>멤버 마이 페이지 입니다</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
     <style type="text/css">
        /* 초기화 */
        .main {
            padding: 0;
        }
        /* 초기화 end */

        /* side-bar 조정 */
        .main {
            grid-template-columns: 16% 60% 20%;
            overflow: hidden;
        }
        /* side-bar 조정 end */

        /* 내용 */
        .mypage-content {
            padding-left: 20px;
        }
        .mypage-info {
            font-weight: bold;
            line-height: 1.5;
            align-items: center;
        }
        .mypage-image {
            width: 100px;
            margin: 50px auto 0;
        }
        .mypage-image > img {
            width: 100px;
            height: 100px;
            margin: 0 auto;
            object-fit: cover;
            cursor: pointer;
            border-radius: 100%;
        }
        .mypage-texts {
            text-align: center;
        }
        .mypage-email,
        .mypage-nickname {
            font-size: 15px;
        }
        .mypage-nickname {
            margin-top: 10px;
        }

        /* 테이블 css */
        .mypage-details {
            margin-top: 10px;
            border-top: 1px solid #dcdcdc;
            margin: 30px auto 0;
        }
        .mypage-details__title {
            font-size: 15px;
            font-weight: bold;
            width: 50px;
            padding: 10px 0;
        }
        .mypage-details__content {
            width: auto;
            font-size: 13px;
        }

    </style>
	<script src="/js/lib/jquery-3.7.1.js"></script>
	<jsp:include page="../layout/${header_type}.jsp"></jsp:include>

    <div class="mypage-content">
        <div class="mypage-content__wrapper">
            <div class="mypage-info">
                <div class="mypage-image">
                    <img src="/member/profile-image">
                </div>
                <div class="mypage-texts">
                    <div class="mypage-nickname">${member.memNickname }</div>
                    <div class="mypage-email">${member.memEmail }</div>
                </div>
            </div>

            <table class="mypage-details">
                <tr">
                    <td class="mypage-details__title">등급</td>
                    <td class="mypage-details__content">${member.role }</td>
                </tr>
                <tr>
                    <td class="mypage-details__title">번호</td>
                    <td class="mypage-details__content">${member.memPhoneNumber }</td>
                </tr>
                <tr>
                    <td class="mypage-details__title">이름</td>
                    <td class="mypage-details__content">${member.memName }</td>
                </tr>
                <tr>
                    <td class="mypage-details__title">주소</td>
                    <td class="mypage-details__content">${member.memAddress }</td>
                </tr>
                <tr>
                    <td class="mypage-details__title">성별</td>
                    <td class="mypage-details__content">${member.memSex }</td>
                </tr>
                <tr>
                    <td class="mypage-details__title">생일</td>
                    <td class="mypage-details__content">${member.memBirthday }</td>
                </tr>
                <tr>
                    <td class="mypage-details__title">날짜</td>
                    <td class="mypage-details__content">${member.memJoinDate }</td>
                </tr>
            </table>
        </div>	
    </div>
<jsp:include page="../layout/footer.jsp"></jsp:include>


