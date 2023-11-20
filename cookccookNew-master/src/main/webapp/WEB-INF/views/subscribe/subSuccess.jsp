<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<title>결제왼료창</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	.box {
		border: solid 2px #FB8F67;
		position: relative;
		top: 100px;
		text-align: center;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type='text/javascript'>
	$().ready(function() {
		let str = "${subscribeVO.subChoice}"
		let nextPayDate = new Date();
		if(str.includes('2주')){
			nextPayDate = new Date(nextPayDate.setDate(nextPayDate.getDate() + 14));
		}else{
			nextPayDate = new Date(nextPayDate.setDate(nextPayDate.getDate() + 28)); //nextPayDate.setMonth(nextPayDate.getMonth() + 1);
		}
		let year = nextPayDate.getFullYear(); // 년도
		let month = nextPayDate.getMonth() + 1;  // 월
		let date = nextPayDate.getDate();  // 날짜
		$(".nextPayDate").text("다음 결제 예정일자: " + year + "년 " + month + "월 " + date + "일");
	})
</script>
<jsp:include page="../layout/header2.jsp"></jsp:include>
<div class="container">
	<div class="box">
		<h3>결제가 성공적으로 완료되었습니다.</h3>
		<div>
			이번 배송 예정 상품은<br/> 
			<c:forEach items="${ingredientList}" var="ingredientVO">
				${ingredientVO.ingredientName},
			</c:forEach>
			<br/>입니다.
		</div>
		<div class="nextPayDate"></div>
		<a href="/subscribe/mysubs"><button class="btn_fill_s">내 구독 정보 확인</button></a>
		<a href="/subscribe"><button class="btn_empty_s">메인으로</button></a>
	</div>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>