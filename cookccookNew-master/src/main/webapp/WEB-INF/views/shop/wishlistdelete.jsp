<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>찜 삭제</title>
    <script src="/js/lib/jquery-3.7.1.js"></script>
    <script type="text/javascript">
        /*$().ready(function() {
            $("#removeWishButton").click(function() {
           	 	var wishlistData = {
                    memberId: "A011", 
                    productId: "AL011",
                    wishlistId: "AP011"
                };
                $.ajax({
                    url: "/api/wish/delete/A011",
                    method: "POST",
                    data: wishlistData, 
                    success: function(response) {
                    	if(response.result) {
                    		window.location.replace("/wish/A011");
                    	}
                    }
                });
            });
        });*/
    </script>
</head>
<body>
    <h1>찜 삭제</h1>
    <%-- <button id="removeWishButton">찜 삭제</button> --%>
    <div id="wish"></div>
</body>
</html>