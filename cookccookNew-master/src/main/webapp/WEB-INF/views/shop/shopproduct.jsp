<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
</head>
<body>
상품ID: ${productVO.productId}<br/>
판매자ID(FK): ${productVO.memberId}<br/>
재료ID(FK): ${productVO.ingredientId}<br/>
상품명: ${productVO.prdName}<br/>
가격: ${productVO.prdPrice}<br/>
원산지: ${productVO.prdOrigin}<br/>
상세설명: ${productVO.prdContent}<br/>
판매상태: ${productVO.saleState}<br/>
재고: ${productVO.stock}<br/>
등록일: ${productVO.prdRegistDate}<br/>

</body>
</html>