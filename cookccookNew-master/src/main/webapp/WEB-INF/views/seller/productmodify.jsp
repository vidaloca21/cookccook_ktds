<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	div.errors {
		background-color: #FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	#orderMemberInfo > .orderMemberInfoArea {
		display: flex;
		flex-direction: row;
	}
	#orderMemberInfo > .orderMemberInfoArea > li {
		display: flex;
		align-items: center;
		margin-top: 10px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li:first-child {
		width: 200px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li:last-child {
		width: 450px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > input {
		display: inline;
		width: 450px;
		height: 40px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > textarea {
		display: inline;
		width: 450px;
		height: 40px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > select {
		display: inline;
		width: 450px;
		height: 40px;
	}
	#inputBtn {
		cursor: pointer;
		width: 150px;
		height: 40px;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script src="/js/AjaxUtil.js"></script>
<script type="text/javascript">
	$().ready(function() {
		//옵션 추가/삭제
		$("#addOption").click(function() {
			var optionList = $(".optionList")
			var item = $("<li class='inline'></li>")

			item.append(`<input type='text' name='choiceList.choName' placeholder="옵션명"/>`)
			item.append(`<input type='number' name='choiceList.choPrice' placeholder="가격"/>원`)
			item.append(`<input type='button' class="removeOption btnNormal" value="삭제" />`)

			optionList.append(item)
		})
		$(document).on('click', '.removeOption', function() {
			$(this).parent().remove();
		})
	});
	function modifyBtn() {
		var url = "/seller/productmodify"
		var ajaxutil = new AjaxUtil();
		if($("input[name=prdTitleImgfile]").val() != null && $("input[name=prdTitleImgfile]").val() != "" && $("input[name=prdContentImgfile]").val() != null && $("input[name=prdContentImgfile]").val() != ""){
			ajaxutil.upload("#post-form", url, function(response) {
				var result = response.result
				if(result){
					alert("상품이 정상적으로 등록되었습니다.")
					location.href = "/seller/productlist"
				}
			})
		}else {
			alert("이미지 파일을 등록해주세요.")
		}
	}
</script>
</head>
<body>
	<form:form modelAttribute="productVO"
		  id="modify_form"
		  method="post" 
		  action="/seller/productmodify"
		  enctype="multipart/form-data">
		<div>
			<form:errors path="prdName" element="div" cssClass="errors" />
			<form:errors path="prdPrice" element="div" cssClass="errors" />
			<form:errors path="prdOrigin" element="div" cssClass="errors" />
			<form:errors path="prdContent" element="div" cssClass="errors" />
			<form:errors path="stock" element="div" cssClass="errors" />
		</div>
		<div id="orderMemberInfo">
			<h2>상품 수정</h2>
			<ul class="orderMemberInfoArea">
				<li><label for="prd_id">상품번호</label></li>
				<li><input id="prd_id" type="text" name="productId" value='${productVO.productId}' readonly/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="prd_name">상품명</label></li>
				<li><input id="prd_name" type="text" name="prdName" value='${productVO.prdName}'/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="prd_price">가격</label></li>
				<li><input id="prd_price" type="number" name="prdPrice" value='${productVO.prdPrice}'/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="buyerTel"><label for="prdTitleImg">상품 이미지</label></label></li>
				<li><input id="prdTitleImg" type="file" name="prdTitleImgfile" value='${productVO.prdTitleImgOrigin}'/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="prdContentImg">상세 이미지</label></li>
				<li><input id="prdContentImg" type="file" name="prdContentImgfile" value='${productVO.prdContentImgOrigin}'/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="prd_origin">원산지</label></li>
				<li><input id="prd_origin" type="text" name="prdOrigin" value='${productVO.prdOrigin}'/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="ingredient_id">재료</label></li>
				<li><select id="ingredient_id" type="text" name="ingredientId" value='${productVO.ingredientId}'>
					<c:forEach items="${ingredientList }" var="ingredient">
						<option value='${ingredient.ingredientId}'>${ingredient.ingredientName}</option>
					</c:forEach>
				</select></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="prd_content">상품설명</label></li>
				<li><textarea id="prd_content" name="prdContent" >${productVO.prdContent}</textarea></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="sale_state">상품상태</label></li>
				<li><select id="sale_state" name="saleState" value='${productVO.saleState}'>
					<option value="1">판매중</option>
					<option value="2">일시품절</option>
					<option value="3">판매종료</option>
				</select></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="stock">재고</label></li>
				<li><input id="stock" type="number" name="stock" value='${productVO.stock}'/></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label>옵션</label><button type="button" id="addOption" class="inline btnNormal">추가</button></li>
				<li>
					<ul class='optionList'>
						<c:forEach items="${choiceList }" var="choiceVO">
						<li class='inline'>
							<input type='text' name='choiceList.choName' value='${choiceVO.choName}'/>
							<input type='number' name='choiceList.choPrice' value='${choiceVO.choPrice}'/>원
							<input type='button' class="removeOption btnNormal" value="삭제" />
						</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li></li>
				<li><input type="button" class="btn_fill_s" id="inputBtn" value="저장" onclick="modifyBtn()"/></li>
			</ul>
		</div>
	</form:form>
</body>
</html>
