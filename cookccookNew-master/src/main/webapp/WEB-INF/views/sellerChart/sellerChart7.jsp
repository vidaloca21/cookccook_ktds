<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>월별 단일상품 매출 순위</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/sellerChart.css">
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawCharts);

        function drawCharts() {
            // 데이터는 그대로 유지
            var data = google.visualization.arrayToDataTable([
                ['상품명', '판매량'],
                <c:forEach items="${chartList}" var="chart">
                    ['${chart.month}', ${chart.totalPrice}],
                </c:forEach>
            ]);

            // 파이 차트 옵션
            var pieOptions = {
                title: '월별 단일상품 매출 순위 (파이차트)',
                is3D: true,
                pieSliceText: 'value',
                slices: { 0: { color: 'blue' }, 1: { color: 'orange' }, 2: { color: 'red' }
                }
            };

            // 막대 차트 옵션
            var barOptions = {
                title: '월별 단일상품 매출 순위 (막대차트)',
                legend: { position: 'none' }
            };

            // 파이 차트
            var pieChart = new google.visualization.PieChart(document.getElementById('piechart'));
            pieChart.draw(data, pieOptions);

            // 막대 차트
            var barChart = new google.visualization.ColumnChart(document.getElementById('barchart'));
            barChart.draw(data, barOptions);
        }
    </script>
</head>
<body>
    <div class="container">
		<div class="saleState">
		    <form:form modelAttribute="chartSearchVO" method="post" enctype="multipart/form-data" action="/sellerChart/sellerChart7">
		        <div>
		            <label for="start_regist_date">조회기간</label>
		                <input id="start_date" type="date" name="startDate" value='${chartSearchVO.startDate}'/>
		                ~
		                <input id="end_date" type="date" name="endDate" value='${chartSearchVO.endDate}'/>
		        <p>상품선택</p>
		        	<c:forEach items="${productList}" var="product">
		        		<input type="radio" name="productId" value="${product.productId }"/>
		        		<label for="productId">"${product.prdName }"</label>
		        	</c:forEach>
		            <input type="submit" value="검색" />
		     		</div>
		    </form:form>
    	</div>
    <div style="display: flex;">
	    <div id="piechart" ></div>
	    <div id="barchart" ></div>
    </div>
	
	<h3>월별 단일상품 매출 순위</h3>
	
		<table class="graph">
			<tr>
				<th>랭킹</th>
				<th>Month</th>
				<th>판매자 아이디</th>
				<th>판매자 이름</th>
				<th>상품명</th>
				<th>총 매출</th>
				<th>총 판매량</th>
			</tr>
			<c:forEach items="${chartList}" var="chart">
				<tr>
					<td>${chart.rank}</td>
					<td>${chart.month}</td>
					<td>${chart.memberId}</td>
					<td>${chart.memName}</td>
					<td>${chart.prdName}</td>
					<td>${chart.totalPrice}</td>
					<td>${chart.totalQuantity}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>