<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>찜 추가</title>
    <script src="/js/lib/jquery-3.7.1.js"></script>
    <script type="text/javascript">
        /*$().ready(function() {
            $("#addWishButton").click(function() {
                var wishlistData = {
                    memberId: "A011", 
                    productId: "AL011"
                };
                console.log(wishlistData)
                $.ajax({
                    url: "/api/wish/add/A011",
                    method: "POST",
                    data: wishlistData, 
                    success: function(response) {
                    	if(response.result) {
                    		window.location.replace("/wish/A011");
                    	}
                    },
                });
            });
        });*/
    </script>
</head>
<body>
    <h1>찜 추가</h1>
    <div id="wish"></div>
</body>
</html>