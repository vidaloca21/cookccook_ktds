<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>실시간 데이터 차트</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/chart.css">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <style>
        #chart-container {
            height: 900px;
            width: 100%;
        }
        
    </style>

    <script>
        // 그래프 초기화
        google.charts.load('current', {packages: ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('datetime', 'Time');
            data.addColumn('number', 'Value');

            var options = {
                title: '실시간 접속자 데이터 차트',
                curveType: 'function',
                legend: {position: 'bottom'}
            };

            var chart = new google.visualization.LineChart(document.getElementById('chart-container'));

            var maxDataPoints = 100; // 표시할 최대 데이터 개수
            var startTime = new Date();
            var dataPoints = [];

            // 데이터 추가 및 그래프 업데이트
            function addData() {
                var x = new Date();
                var y = Math.floor(Math.random() * (500 - 200 + 1)) + 200;
                dataPoints.push([x, y]);

                if (dataPoints.length > maxDataPoints) {
                    dataPoints.shift(); 
                }

                data = new google.visualization.DataTable();
                data.addColumn('datetime', 'Time');
                data.addColumn('number', '접속자');
                data.addRows(dataPoints);

                chart.draw(data, options);
                setTimeout(addData, 1000); // 10초마다 데이터 추가
            }

            addData();
        }
    </script>
</head>
<body>
    <h2>실시간 접속자 </h2>
    <div id="chart-container"></div>
    <table class="graph">
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${chartList}" var="chart">
			<tr>
				<td>${chart.rank}</td>
				<td>${chart.month}</td>
				<td>${chart.totalPrice}</td>
				<td>${chart.totalQuantity}</td>
				<td>${chart.productId}</td>
				<td>${chart.prdName}</td>
				<td>${chart.memberId}</td>
				<td>${chart.memName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>