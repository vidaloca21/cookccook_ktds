<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>전체 상품 탑 3 외 기타</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/sellerChart.css">
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['상품명', '매출 금액'],
                <c:forEach items="${chartList}" var="chart">
                    ['${chart.prdName}', ${chart.totalPrice}],
                </c:forEach>
            ]);
         // 파이 차트 옵션
            var pieOptions = {
                title: '판매량 TOP 3 상품 (파이차트)',
                is3D: true,
                pieSliceText: 'value',
                slices: { 0: { color: 'blue' }, 1: { color: 'orange' }, 2: { color: 'red' }
                }
            };

            // 막대 차트 옵션
            var barOptions = {
                title: '판매량 TOP 3 상품  (막대차트)',
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
			<form:form modelAttribute="chartSearchVO" method="get" enctype="multipart/form-data" action="/sellerChart/sellerChart1">
				<input type="hidden" name="memberId" value="<%=request.getParameter("memberId") %>" />
			    <div>
			    	<label for="start_date">조회기간</label>
						<input id="start_date" type="date" name="startDate" value='${chartSearchVO.startDate}'/>
						~
						<input id="end_date" type="date" name="endDate" value='${chartSearchVO.endDate}'/>
						<input type="submit" value="검색" />
			    </div>
		    </form:form>
	    </div>
		    <div style="display: flex;">
		    	<div id="piechart" ></div>
		    	<div id="barchart" ></div>
		    </div>
	<h3>전체 상품 탑 3 외 기타</h3>
	
		<table class="graph">
			<tr>
				<th>총 매출</th>
				<th>상품명</th>
			</tr>
			<c:forEach items="${chartList}" var="chart">
				<tr>
					<td>${chart.totalPrice}</td>
					<td>${chart.prdName}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>