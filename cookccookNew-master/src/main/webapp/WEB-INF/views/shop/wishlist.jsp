<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜 목록 조회</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
$().ready(function() {
    $.ajax({
        url: "/shop/wish",
        method: "get",
        success: function(response) {
        	let memeberId = response.memberId;
        	for(var i in response.wishListVO) {
        		let wishListDOM = $("<li></li>");
        		wishListDOM = wishListDOM.text(response.wishListVO[i].wishlistId)
        		let memberIdDOM = $("<li></li>");
        		memberIdDOM = memberIdDOM.text(response.wishListVO[i].memberId)
        		let productIdDOM = $("<li></li>");
        		productIdDOM = productIdDOM.text(response.wishListVO[i].productId)
        		      		
        		let deleteBtn = $("<button class='removeWishButton'>찜 삭제</button>")
        		deleteBtn.click(function(){
                    let parentElement = $(this).closest(".wishlist"); 
                    let wishlistId = parentElement.attr("id");
                    $.ajax({
                        url: "/shop/wish/delete/" +wishlistId, 
                        method: "POST",
                        data: { memberId: memeberId, wishlistId: wishlistId },
                        success: function(response) {
                            if(response.result) {
                               parentElement.remove();
                            }
                        }
                    });
        		})
        		
        		let wishlist = $("<ul class='wishlist'></ul> ")
        		wishlist.append(wishListDOM)
        		wishlist.append(memberIdDOM)
        		wishlist.append(productIdDOM)
        		wishlist.append(deleteBtn)
        		wishlist.attr("id", response.wishListVO[i].wishlistId)
        		
        		$("#wish").append(wishlist)
        	}
        }
       
    })
})
	
</script>
</head>
<body>
	<h1>전체 찜 조회</h1>
	<div id="wish"></div>
</body>
</html>