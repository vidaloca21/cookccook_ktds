<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>전체 상품 조회</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<script src="https://unpkg.com/feather-icons"></script>
<script src="https://kit.fontawesome.com/63879a02ce.js" crossorigin="anonymous"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$.get("/shop/wish", function(response) {
			let wishlistProduct = response.wishlistProduct
			$(".feather-heart").each(function() {
				if (wishlistProduct.includes($(this).attr("id")) ) {
					$(this).css("fill", "#FF1211")
					$(this).css("stroke", "#FF1211")
				}
			})
		})
		$(".feather-heart").click(function() {
			if ("${memberId}" == "") {
				Swal.fire({
						title: '로그인을 먼저 진행해주세요',
						text: "확인을 누르시면 로그인 페이지로 이동합니다",
						icon: 'warning',
						showCancelButton: true,
						confirmButtonColor: '#FB8F67',
						cancelButtonColor: '#D4D4D4',
						confirmButtonText: '확인',
						cancelButtonText: '취소',
						//reverseButtons: true, // 버튼 순서 거꾸로
						}).then((result) => {
						if (result.isConfirmed) {
							location.href = "/member/memberlogin"	
						}
				})
			}else {
				const productId = $(this).attr("id");
				$.post("/shop/wish/add", productId, function(response) {
					if (response.result) {
						if (response.action == "add") {
							Swal.fire({
								icon: 'success',
								title: '찜 목록에 추가되었습니다',
							});
							setTimeout(function() {
								location.reload()
							}, 1800);
						}
						else if (response.action == "delete") {
							Swal.fire({
								icon: 'success',
								title: '찜 목록에서 삭제되었습니다',
							});
							setTimeout(function() {
								location.reload()
							}, 1800);
						}
						else {
							Swal.fire({
								icon: 'error',
								title: '문제가 발생하였습니다',
								text: '관리자에게 문의하세요',
							});
							setTimeout(function() {
								location.reload()
							}, 1800);
						}
					}
					else {
						Swal.fire({
								icon: 'error',
								title: '문제가 발생하였습니다',
								text: '관리자에게 문의하세요',
							});
							setTimeout(function() {
								location.reload()
							}, 1800);
					}
				})
			}
		})
	})
</script>
<style type='text/css'>

	#productList {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
	}
	#productList > .productCard {
		width: 200px;
		height: 300px;
		margin: 20px;
		padding: 0;
	}
	.productCard > ul {
		display: flex;
		flex-direction: column;
		padding-left: 0;
	}
	.productCard > ul > li {
		display: flex;
		margin-bottom: 4px;
	}
	.productCard > ul > li:first-child {
		height: 40px;
	}
	.productCard > ul > li:nth-child(2) {
		height: 20px;
		justify-content: flex-end;
	}
	.productCard > ul > li:last-child {
		height: 20px;
		justify-content: flex-end;
	}
	.productCard > a > img {
		width: 200px;
		border-radius: 20px;
		transition: 0.1s;
	}

	.productCard > a > img:hover {
		width: 205px;
		height: 210px;
	}
	li.productPrice {
		text-align: right;
		font-size: 15pt;
		font-weight: bold;
		color: #FF6347;
	}
	.feather-heart {
		font-size: 12px;
		cursor: pointer;
		position: relative;
		bottom: 41px;
		left: 4px;
	}
	form {
    display: flex;
    padding-left: 63px;
    margin-top: 10px;
	padding-left: 100px;
	}
	input[type=search] {
		width: 600px;
		padding: 10px;
		border: 3px solid #FB8F67;
		border-top-left-radius: 10px;
		border-bottom-left-radius: 10px;
		border-right: 0px;
		outline: none;
		height: 60px;
		font-size: 17px;
		
	}
	#search-btn {
		width: 75px;
		background-image: url("/img/search_orange_icon.png");
		background-size: 75px;
		background-repeat: no-repeat;
		background-color: white;
		display: inline-block;
		padding: 10px;
		border: 3px solid #FB8F67;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 10px;
		border-left: 0px;
		outline: none;
	}
</style>

<jsp:include page="../layout/header2.jsp"></jsp:include>
	<form action="/shop/product/search">
        <input id="inputKeyword" type="search" name="prdName" placeholder="검색어를 입력하세요">
		<button id="search-btn"></button>
    </form>
    
	<div id="productList">
		<c:forEach items="${productList}" var="product">
			<div class="productCard">
				<a href="/shop/product/${product.productId}">
					<img src="/shop/product/titleimg/${product.prdTitleImgOrigin}" alt="" />
				</a>
				<ul class="productInfoList">
					<li class="productName">${product.prdName}</li>
					<li class="productSellers">${product.memberVO.memNickname}</li>
					<li class="productPrice"><fmt:formatNumber value="${product.prdPrice}" pattern="###,###,###" />원</li>
				</ul>
				<i id="${product.productId}" class="feather-heart" data-feather="heart"></i>
			</div>
		</c:forEach>
	</div>	
<script>
	feather.replace();
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
