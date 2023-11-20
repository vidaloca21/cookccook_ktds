<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 API</title>
	<script src="/js/lib/jquery-3.7.1.js"></script>
	<script type="text/javascript">
		$().ready(function() {
            $.ajax({
                url: "/apitest",
                method: "get",
                success: function(response) {
                   		let rows = response.COOKRCP01.row;
                    	for (var j in rows) {
                    		let title = $("<h3 class='recipeTitle'></h3>");
                    		title = title.append(rows[j]["RCP_NM"]);
                    		
                    		let ingredient = $("<h4 class='ingredients'></h4>");
                    		ingredient = ingredient.append(rows[j]["RCP_PARTS_DTLS"]);
                    		
                    		let recipeArea = $("<ul></ul>");
	                    	for (var i=1; i<=20; i++) {
	                    		let target = ""
	                    		let recipeLi = $("<li></li>");
	                    		if (i < 10) {
		                    		target = "MANUAL0" + i;
	                    		}else {
	                    			target = "MANUAL" + i;
	                    		}
	                    		if(rows[j][target] != "" ){
	                    			recipeLi = recipeLi.text(rows[j][target]);
	                    			recipeArea.append(recipeLi)
	                    		}
	                    	}
	                    	$(".recipeContent").append(title);
	                    	$(".recipeContent").append(ingredient);
	                    	$(".recipeContent").append(recipeArea);
                    	}
                    	
                    }
            })
        })
	</script>
</head>
<body>
	<div class="recipeContent"></div>
</body>
</html>