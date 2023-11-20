<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout_as.css">
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
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
    $().ready(function() {
    	$("#chartArea").attr("src", "/chart/chart11");
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
            if (selectedOption === "/chart/chart11") {
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
<div class="select_box">
<!--  <button id="chartOpenBtn">버튼</button> -->
  <select name="productStat">
      <option>상품별</option>
      <option value="/chart/chart1">월별 전체 상품 매출 순위</option>
      <option value="/chart/chart2">연별 전체 상품 매출 순위</option>
      <option value="/chart/chart3">월별 전체 상품 판매량 순위</option>
      <option value="/chart/chart4">연별 전체 상품 판매량 순위</option>
      <option value="/chart/chart11">일자별 전체 상품 판매량 순위</option>
      <option value="/chart/chart5">전체 상품 매출 순위</option>
      <option value="/chart/chart6">전체 상품 판매량 순위</option>
      <option value="/chart/chart10">전체 상품 매출액 top3</option>
  </select>                               
  <select name="sellerStat">
      <option>판매자별</option>
      <option value="/chart/chart7">월별 전체 판매자 매출 순위</option>
      <option value="/chart/chart8">연별 전체 판매자 매출 순위</option>
      <option value="/chart/chart9">전체 판매자 매출 순위</option>
  </select>   
<!--       <a href="/chart/chart11" target="chartArea">일자별 전체 상품 판매량 순위</a> -->
</div>
<div class="area">
    <iframe id="chartArea" name="chartArea" ></iframe>
</div>



