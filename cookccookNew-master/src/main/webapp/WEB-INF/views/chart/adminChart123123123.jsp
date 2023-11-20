<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 통계 페이지</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
    $().ready(function() {
        $("#chartOpenBtn").click(function() {
            console.log($("#chartArea"));
            $("#chartArea").attr("src", "/chart/chart9");
        });

        //  상품 select 박스 선택 시 iframe을 변경
        $("select[name='productStat']").change(function() {
            var selectedOption = $(this).val();
            if (selectedOption === "/chart/chart1") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart2") {
                $("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart3") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart4") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart5") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart6") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart10") {
            	$("#chartArea").attr("src", selectedOption);
            }
        });
        
    //  판매자 select 박스 선택 시 iframe을 변경
        $("select[name='sellerStat']").change(function() {
            var selectedOption = $(this).val();
            if (selectedOption === "/chart/chart7") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart8") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/chart/chart9") {
            	$("#chartArea").attr("src", selectedOption);
            }
        });     
        
    });
    		
</script>
</head>
<body>
    <h3>통계</h3>
    <div>
        <iframe id="chartArea" name="chartArea"  width="1700" height="600"></iframe>
    </div>
    <div>
        <button id="chartOpenBtn">버튼</button>
        <select name="productStat">
            <option>상품별</option>
            <option value="/chart/chart1">월별 전체 상품 매출 순위</option>
            <option value="/chart/chart2">연별 전체 상품 매출 순위</option>
            <option value="/chart/chart3">월별 전체 상품 판매량 순위</option>
            <option value="/chart/chart4">연별 전체 상품 판매량 순위</option>
            <option value="/chart/chart5">전체 상품 매출 순위</option>
            <option value="/chart/chart6">전체 상품 판매량 순위</option>
            <option value="/chart/chart7">전체 상품 매출액 top3</option>
        </select>
        <select name="sellerStat">
            <option>판매자별</option>
            <option value="/chart/chart8">월별 전체 판매자 매출 순위</option>
            <option value="/chart/chart9">연별 전체 판매자 매출 순위</option>
            <option value="/chart/chart10">전체 판매자 매출 순위</option>
        </select>
        <a href="/chart/chart11" target="chartArea">일자별 전체 매출 및 판매량</a>
    </div>
</body>
</html>




