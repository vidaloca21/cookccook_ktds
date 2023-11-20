<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>관리자 페이지</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
        var latestSort = "";
		$(".submitBtn").click(function() {
			let sortColumn = $(this).attr("id");
            let input = $("<input type='hidden' name='sortColumn' value='"+sortColumn+"' />")
            $("#sortForm").append(input)
            $("#sortForm").submit()
            latestSort = sortColumn;
            console.log(latestSort)
		})
	})
</script>
<style type="text/css">
	.container {
		display: flex;
	}
	table.table {
		border-collapse: collapse;
		border: 1px solid #DDD;
	}

	table.table > thead > tr {
		background-color: #c5c5d2;
	}

	table.table > thead th {
		padding: 10px;
		color: #333;
	}

	table.table th, table.table td {
		border-right: 1px solid #F0F0F0;
		text-align: center;
        width: auto;
	}

	table.table th:last-child,
	table.table td:last-child {
		border-right: none;
	}

	table.table > tbody > tr:nth-child(odd) {
		background-color: #d9d9e3;
	}
	table.table > tbody td {
		padding-right: 10px;
		/*color: white;*/
	}
	.memberAlllist-title{
		font-size: 30px;
	}
	.memId {
		cursor: pointer;
	}
    #tempStop{
        color: blue;
    }
    #salesStop{
        color: red;
    }
    .submitBtn{
        cursor: pointer;
    }
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
    <p class="font_subtitle">구독 회원 조회</p>
</div>
<div class="container">
	<table class="table">
		<thead>
			<tr>
                <form:form id="sortForm" method="post" action="/admin/adminproductalllist">
                    <th id="M.MEM_NICKNAME" class="submitBtn">회원ID</th>
                    <th id="P.PRD_NAME" class="submitBtn">구독 구분</th>
                    <th id="P.PRD_PRICE" class="submitBtn">구독 주기</th>
                    <th id="P.PRD_ORIGIN" class="submitBtn">가격</th>
                    <th id="P.PRD_ORIGIN" class="submitBtn">결제 수단</th>
                    <th id="P.SALE_STATE" class="submitBtn">구독 시작일</th>
                    <th id="P.STOCK" class="submitBtn">구독 종료일</th>
                </form:form>
            </tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty subMemberlist}">
                    <c:forEach var="subMember" items="${subMemberlist}">
                        <tr>
							<td>${subMember.memberId}</td>
							<td>${subMember.subsCategory}</td>
							<td>${subMember.subsCycle}</td>
							<td>${subMember.subscribeVO.subPrice}</td>
							<td>${subMember.payMethod}</td>
							<td>${subMember.payDate}</td>
							<td>${subMember.cancelDate}</td>
                        </tr>
                    </c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="10">등록된 회원이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
</body>
</html>