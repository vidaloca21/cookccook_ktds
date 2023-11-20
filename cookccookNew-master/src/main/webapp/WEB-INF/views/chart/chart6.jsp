<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>전체 상품 판매량 순위</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/chart.css">
     
    <style type="text/css">
    .graph {
        width: 1700px;
        text-align: center;
    }
    </style>
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawCharts);

        function drawCharts() {
            // 데이터는 그대로 유지
            var data = google.visualization.arrayToDataTable([
                ['상품명', '판매량'],
                <c:forEach items="${chartList}" var="chart">
                    ['${chart.prdName}', ${chart.totalQuantity}],
                </c:forEach>
            ]);

            // 파이 차트 옵션
            var pieOptions = {
                title: '전체 상품 판매량 순위 (파이차트)',
                is3D: true,
                pieSliceText: 'value',
                slices: { 0: { color: 'blue' }, 1: { color: 'orange' }, 2: { color: 'red' }
                }
            };

            // 막대 차트 옵션
            var barOptions = {
                title: '전체 상품 판매량 순위 (막대차트)',
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
    <form:form modelAttribute="chartSearchVO" method="get" enctype="multipart/form-data" action="/chart/chart6">
        <div>
            <label for="start_regist_date">조회기간</label>
                <input id="start_date" type="date" name="startDate" value='${chartSearchVO.startDate}'/>
                ~
                <input id="end_date" type="date" name="endDate" value='${chartSearchVO.endDate}'/>
                <input type="submit" value="검색" />
        </div>
    </form:form>
    <div style="display: flex;">
	    <div id="piechart" ></div>
	    <div id="barchart" ></div>
    </div>
	<h1>전체 상품 판매량 순위</h1>
	
	<table class="graph">
		<tr>
			<th>랭킹</th>
			<th>판매일자</th>
			<th>총 판매량</th>
			<th>총 매출</th>
			<th>상품 ID</th>
			<th>상품명</th>
			<th>판매자 아이디</th>
			<th>판매자 이름</th>
		</tr>
		<c:forEach items="${chartList}" var="chart">
			<tr>
				<td>${chart.rank}</td>
				<td>${chart.year}</td>
				<td>${chart.totalQuantity}</td>
				<td>${chart.totalPrice}</td>
				<td>${chart.productId}</td>
				<td>${chart.prdName}</td>
				<td>${chart.memberId}</td>
				<td>${chart.memName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>