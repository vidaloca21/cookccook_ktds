<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>Subscribe</title>

<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">

<style type="text/css">
.container {
    align-items: center;
}

#subMainImg {
  position: relative;
  width: 100%;
  height: auto;
  text-align: center;
  overflow: hidden; /* 배경 이미지가 넘치지 않도록 숨김 처리 */
}

.img_group > div> img {
  width: 900px;
  height: 600px;
  z-index: 1;
  overflow: hidden; /* 배경 이미지가 넘치지 않도록 숨김 처리 */
}

#subMainArticle {
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  flex-direction: column; 
  align-items: flex-start; 
  margin: 20px;
  z-index: 2;
  color: #fff; 
  background-color: #7775;
}

.img_group {
    width: 900px; 
    overflow: hidden;
    white-space: nowrap;
	display: flex;
}



.btn_group {
  position: absolute;
  bottom: 0;
  right: 0;
  display: flex;
  flex-direction: column; 
  align-items: flex-end; 
  padding: 20px;
  z-index: 2;
}

button {
margin: 5px 0;}

.carousel_btn {
	border-radious: 150px;
	border-type:none;
	cursor: pointer;
}

</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
   
    $().ready(function () {
        $("#subsImg1").show()

        $(".carousel_btn").click(function () {
            $(".img_group div").hide()

            let imageId = $(this).attr("id").replace("btn", "subsImg")
            $("#" + imageId).show()
        })
    })
    
</script>

	<jsp:include page="../layout/header2.jsp"></jsp:include>
	        <div id="subMainImg">
	            <div id="subMainArticle">
	                <div class="font_title" style="padding-bottom: 8px">신선식품 문앞 정기배송</div>
	                <div class="sub_title">내 손으로 직접 차린 건강한 한 끼 식사 하고 싶은데,</div>
	                <div class="sub_title">장 보러갈 시간이 없으시다구요?</div>
	            </div>
	           
				<div class="img_group">
					<div id="subsImg1">
					    <img src="/img/subs_img1.jpg"/>
					    <div id="subMainArticle">
						    <div class="font_title" style="padding-bottom: 8px">신선식품 문앞 정기배송</div>
			                <div class="sub_title">내 손으로 직접 차린 건강한 한 끼 식사 하고 싶은데,</div>
			                <div class="sub_title">장 보러갈 시간이 없으시다구요?</div>
		                </div>
				    </div>
				    
				    <div id="subsImg2">
					    <img src="/img/subs_img2.jpg"/>
					    <div id="subMainArticle">
							<div class="sub_title">바빠도 건강은 소홀히 하지 마세요.</div>
			                <div class="sub_title">신선한 식재료가 문 앞에 도착, 한 끼 식사의 특별함을 느껴보세요.</div>
		                </div>
				    </div>
				    
				    <div id="subsImg3">
					    <img src="/img/subs_img3.jpg"/>
					    <div id="subMainArticle">
							<div class="sub_title"></div>
			                <div class="sub_title">신선한 식재료가 문 앞에 도착, 한 끼 식사의 특별함을 느껴보세요.</div>
		                </div>
				    </div>
				    
				   
				</div>
				<div>
				    <button class="carousel_btn" id="btn1"></button>
				    <button class="carousel_btn" id="btn2"></button>
				    <button class="carousel_btn" id="btn3"></button>
				</div>
	          
	            <div class=btn_group>
		            <a href="/subscribe/info"><button class="btn_fill">구독 하러가기</button></a>
		          	<a href="/subscribe/mysubs"><button class="btn_empty">구독 내역 보기</button></a>
	        	</div>
	        	
	        </div>
 	<jsp:include page="../layout/footer.jsp"></jsp:include>
