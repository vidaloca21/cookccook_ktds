<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
    <title>판매자 통계 페이지</title>
<style type="text/css">
.select_box {
    float: right;
}

 #chartArea {
    background-color: #fff;
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
    height:100%;
    border: none;
    background-color: #fff;
    margin: 0;
    padding: 0;
  }

  body {
    background-color: #fff;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }

  h2 {
    text-align: left;
  }
  
.footer {
  height: 1px;
 }
 
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
    $().ready(function() {
    	$("#chartArea").attr("src", "/sellerChart/sellerChart11");

        //  상품 select 박스 선택 시 iframe을 변경
        $("select[name='productStat']").change(function() {
            var selectedOption = $(this).val();
            if (selectedOption === "/sellerChart/sellerChart1") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart2") {
                $("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart3") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart4") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart5") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart6") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart11") {
            	$("#chartArea").attr("src", selectedOption);
            }
        });
        
    //  판매자 select 박스 선택 시 iframe을 변경
        $("select[name='sellerStat']").change(function() {
            var selectedOption = $(this).val();
            if (selectedOption === "/sellerChart/sellerChart7") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart8") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart9") {
            	$("#chartArea").attr("src", selectedOption);
            }
            if (selectedOption === "/sellerChart/sellerChart10") {
            	$("#chartArea").attr("src", selectedOption);
            }
        });
    
        
    });
    		
</script>
<jsp:include page="../layout/header_seller.jsp"></jsp:include>
          <h2>통계</h2>
        <div class="select_box">
             <select name="productStat">
                 <option>상품별</option>
                 <option value="/sellerChart/sellerChart1">월별 전체 상품 매출 순위</option>
                 <option value="/sellerChart/sellerChart2">연별 전체 상품 매출 순위</option>
                 <option value="/sellerChart/sellerChart3">월별 전체 상품 판매량 순위</option>
                 <option value="/sellerChart/sellerChart4">연별 전체 상품 판매량 순위</option>
                 <option value="/sellerChart/sellerChart5">전체 상품 매출 순위</option>
                 <option value="/sellerChart/sellerChart6">전체 상품 판매량 순위</option>
                 <option value="/sellerChart/sellerChart11">전체 상품 매출액 top3</option>
             </select>                               
             <select name="sellerStat">
                 <option>단일 상품 조회</option>
                 <option value="/sellerChart/sellerChart7">월별 단일 상품 매출</option>
                 <option value="/sellerChart/sellerChart8">연별 단일 상품 매출</option>
                 <option value="/sellerChart/sellerChart9">단일 상품 매출</option>
                 <option value="/sellerChart/sellerChart10">단일 상품 판매량</option>
             </select>
        </div>
	            <iframe id="chartArea" name="chartArea" ></iframe>
<jsp:include page="../layout/chart_footer.jsp"></jsp:include>
