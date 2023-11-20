<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<Style type="text/css">

@font-face {
    font-family: 'SUIT-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
/* 디폴트 컴포넌트 */

.btn_fill {
  display: flex;
  position: relative;
  width: 150px;
  height: 50px;
  font-size: 18px; 
  font-weight: bold;
  background-color: #FB8F67;
  color: #fff;
  border: none;
  border-radius: 10px;
  text-align: center;
  align-items: center;
  justify-content: center;
  padding: 5px;
}

.btn_empty {
  display: flex;
  position: relative;
  width: 150px;
  height: 50px;
  font-size: 18px;
  font-weight: bold;
  background-color: #fff;
  border: 1px solid #FB8F67;
  color: #FB8F67;
  border-radius: 10px;
  text-align: center;
  align-items: center;
  justify-content: center;
   padding: 5px;
}

.btn_navigate {
	padding: 5px;
}

.btn_fill_s{
	padding: 0 21px;
	color: #fff;
	font-size: 14px;
	font-family: "맑은 고딕", Malgun Gothic, sans-serif;
	line-height: 30px;
	border: 1px solid transparent;
	background-color: #FB8F67;
}

.btn_empty_s{
	padding: 0 21px;
	color: #FB8F67;
	font-size: 14px;
	font-family: "맑은 고딕", Malgun Gothic, sans-serif;
	line-height: 30px;
	border: 1px solid #FB8F67;
	background-color: #fff;
}

.btnNormal {
	padding: 0 6px;
	color: #1c1c1c;
	font-size: 12px;
	font-weight: normal;
	line-height: 20px;
	line-height: 22px\0;
	border: 1px solid #cacaca;
	background-color: #f8f8f8;
}

* {
  color: #2B1512;
  text-decoration: none;
	font-family: 'SUIT-Regular';
	vertical-align: middle;
}

a {
	text-decoration: none;
}

.font_title {
  font-size: 28px;
  font-weight: bold;
  padding:5px;
  
}
.font_subtitle {
  font-size: 20px;
  padding:5px;

}

.font_content {
  font-size: 16px;

}

.font_mini {
  font-size: 15px;
  color: #555;

}

.font_emphasis {
  font-size: 18px;
  font-weight: bold;
  color: #FF6347;

}
/* HEADER */
.header {
  display: grid;
  grid-template-columns: 25% 50% 25%;
  height: 130px;
  top: 0;
  position: sticky;
  background-color: #fff;
  z-index: 9;
  min-width: 1484px;
  align-content: space-between;
  margin: 0;
  
}


.icon_bar {
  grid-column: 1/2;
  text-align: center;
  padding: 10px;
  }
  
  #icon {
  height: 100px;
  }
 
.navi_bar {
  text-align: center;
  grid-column: 2/3;
  align-self: end;
  height: 35px;
}

.menu {
  display: inline-block;
  height: 33px;
  width: 150px;
  font-size: 25px;
  font-weight: bold;
  padding-left: 15px;
  padding-right: 15px;
}

.menu_highlight {
  display: inline-block;
  height: 33px;
  width: 150px;
  font-size: 25px;
  font-weight: bold;
  color: #FF6347;
  border-bottom: solid 5px #FF6347;
  padding-bottom: 10px;
  align-self: center;
    

}
.mem_bar {
	position: fixed;
	top: 10px;
	right: 30px;
	display: inline-block;
}

.mem .none_mem {
	position: fixed;
	top: 10px;
	right: 30px;
	display: inline-block;
}

#login, #signup, #logout, #myinfo, #managementPg {
	display: inline-block;
}

/* MAIN */

.main {
  display: grid;
  grid-template-columns: 20% 60% 20%;
  min-height: 100%;
  min-width: 1484px;
  padding: 20px;
  background-color: #fbf7f1;

}

/* .container 는 각자가 작업한 내용 */

.container {
  align-items: center;
  justify-content: center;
  min-height: 100%;
}

/* FOOTER */ 

.footer {
  margin-top: 10px;
  display: grid;
  grid-template: 1/4;
  border-top: 1px solid #FFF1E0;
  font-size: 12px; 
  bottom: 0;
  width: 100%;
  justify-content:center;
}

.footer > ul {
	display: flex;
	flex-direction: column;
	text-align: center;
}

.footer > ul > li {
	display: flex;
	flex-direction: column;
	
}

/*raido button css*/
[type="radio"] {
	background-color: #fff;
	appearance: none;
	border: max(2px, 0.1em) solid gray;
	border-radius: 50%;
	width: 1.25em;
	height: 1.25em;
	transition: border 0.5s ease-in-out;
}

[type="radio"]:checked {
	border: 0.4em solid #FF6347;
}

[type="radio"]:hover {
	box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
	cursor: pointer;
}

[type="radio"]:hover + span {
	cursor: pointer;
}

/*input text css*/
[type="text"] {
	max-width: 100%;
	height: 22px;
	padding: 0 5px;
	line-height: 22px;
	box-sizing: border-box;
	border: 1px solid #a7a7a7;
	border-right-color: #cfcfcf;
	border-bottom-color: #cfcfcf;
	outline: 0 none;
}

</Style>
<div class="footer">
    <ul>
        <li id="introduction1">
         상호명: (주)Recipe Introduce Platform | 대표: 2조 | 개인정보보호책임자: 2조 <br>
         사업자등록번호: 123-45-678910 | 통신판매업신고번호: 제2023-경기수원-00000호<br>
         사업장소재지: 경기 수원시 팔달구 팔달문로3번길 37 맛있는 빌딩 9층 915호<br>
     </li>
     <li id="introduction2">
         주식회사 kt DS University는 전자상거래 등에서의 소비자보호에 관한 법률에 따른 통신판매중개자로서 중개하는 통신판매에 관하여서는 통신판매의 당사자가 아니며 상품의 주문, 배송 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.
     </li>
    </ul>
</div>