<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
 .select_box {
    float: right;
 }

 #chartArea {
    background-color: #fff;
 }
  h1 {
    font-size: 40px;
    color: #333;
  }

  .select_box {
    text-align: right;
    margin-top: 20px;
  }

  select {
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  a {
    text-decoration: none;
    margin-left: 10px;
  }
  iframe {
    width: 100%;
    height: 100%;
    border: none;
    background-color: #fff;
  }

  body {
    background-color: #fff;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }
  #sidebar{
    background-color: #007bff;
    color: #fff;
    padding: 20px;
    width: 200px;
    display: flex;
    flex-direction: column;
}
#content {
               flex: 1;
               padding: 20px;
        } 
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#chartArea").attr("src", "/chart/chart11");
		//  상품 select 박스 선택 시 iframe을 변경
		$("select[name='productStat']").change(function() {
			var selectedOption = $(this).val();
			if (selectedOption === "/chart/chart3") {
				$("#chartArea").attr("src", selectedOption);
			}
			if (selectedOption === "/chart/chart4") {
				$("#chartArea").attr("src", selectedOption);
			}
			if (selectedOption === "/chart/chart11") {
				$("#chartArea").attr("src", selectedOption);
			}
			if (selectedOption === "/chart/chart6") {
				$("#chartArea").attr("src", selectedOption);
			}
		});
		
		
		$(document).ready(function() {
            var $iframe = $('#myiframe');
            
            $iframe.on('load', function() {
                var iframeContent = $iframe.contents().find('body');
                
                $iframe.height(iframeContent.height());
                $iframe.width(iframeContent.width());
            });
        });
		
	});
</script>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
    <p class="font_subtitle">통계 대시보드</p>
</div>
<div class="container">
		<div class="select_box">
			<!--  <button id="chartOpenBtn">버튼</button> -->
				<select name="productStat">
					<option>판매량별</option>
					<option value="/chart/chart3">월별 전체 상품 판매량 순위</option>
					<option value="/chart/chart4">연별 전체 상품 판매량 순위</option>
					<option value="/chart/chart11">일자별 전체 상품 판매량 순위</option>
					<option value="/chart/chart6">전체 상품 판매량 순위</option>
				</select>                               
		<!--       <a href="/chart/chart11" target="chartArea">일자별 전체 상품 판매량 순위</a> -->
		</div>
		<div>
			<iframe id="chartArea" name="chartArea"></iframe>
		</div>
	</div>
</body>
</html>