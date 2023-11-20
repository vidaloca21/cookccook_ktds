<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>일자별 전체 매출 및 판매량</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/chart.css">
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', '일자');
            data.addColumn('number', '매출');
            data.addColumn('number', '판매량');

            var chartData = [
                <c:forEach items="${chartList}" var="chart" varStatus="loop">
                    ['${chart.purchaseDate}', ${chart.totalPrice}, ${chart.totalQuantity}],
                </c:forEach>
            ];

            data.addRows(chartData);

            var options = {
                title: '일자별 전체 매출 및 판매량',
                curveType: 'function',
                legend: { position: 'bottom' },
                series: {
                    0: { targetAxisIndex: 0 },
                    1: { targetAxisIndex: 1 }
                },
                vAxes: {
                    0: { title: '매출', titleTextStyle: { color: 'blue' } }, // '매출'을 파란색으로
                    1: { title: '판매량', titleTextStyle: { color: 'red' } }   // '판매량'을 빨간색으로
                }
            };

            var chart = new google.visualization.ComboChart(document.getElementById('linechart_material'));

            chart.draw(data, options);
        }
    </script>
</head>
<body>
    
    <form:form modelAttribute="chartSearchVO" method="get" enctype="multipart/form-data" action="/chart/chart11">
	    <div>
	    	<label for="start_regist_date">조회기간</label>
				<input id="start_date" type="date" name="startDate" value='${chartSearchVO.startDate}'/>
				~
				<input id="end_date" type="date" name="endDate" value='${chartSearchVO.endDate}'/>
				<input type="submit" value="검색" />
	    </div>
    </form:form>
    
    <div id="linechart_material" style="width: 1600px;"></div>
    
    <h1>일자별 전체 매출 및 판매량</h1>
    
    <table class="graph">
        <tr>
            <th>매출</th>
            <th>판매량</th>
            <th>일자</th>
        </tr>
        <c:forEach items="${chartList}" var="chart">
            <tr>
                <td>${chart.totalPrice}</td>
                <td>${chart.totalQuantity}</td>
                <td>${chart.purchaseDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
