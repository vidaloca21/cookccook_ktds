<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>관리자 페이지</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".isValidBN").click(function() {
			let serviceKey = "MiMOFpiVdkf1vqRFozVT%2BJWXZU1FGLxSKEYMT9nqXSBAImqckvDlG1wiusOVMGAwfLUh3%2B5YsIu82jJR3TPRxw%3D%3D";
			let validCheckBtn = $(this)
			let businessNumber = $(this).val()
			var data = {
				"b_no": [businessNumber] // 사업자번호 "xxxxxxx" 로 조회 시,
			}; 
			let row = $(this).parent().parent()
			$.ajax({
			url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey="+serviceKey,  // serviceKey 값을 xxxxxx에 입력
			type: "POST",
			data: JSON.stringify(data), // json 을 string으로 변환하여 전송
			dataType: "JSON",
			contentType: "application/json",
			accept: "application/json",
			success: function(response) {
				console.log(response)
				if(response.data[0].b_stt == '계속사업자') {
					Swal.fire({
						title: '사업자 번호 진위 확인',         // Alert 제목
						text: response.data[0].b_stt+"로 조회되었습니다",  // Alert 내용
						icon: 'success',                         // Alert 타입
					})
					$(row).css("background-color", "#B2FFBF")
					validCheckBtn.parent().text('계속사업자')
					validCheckBtn.remove()
				}
				else {
					Swal.fire({
						title: '사업자 번호 진위 확인',         // Alert 제목
						text: response.data[0].tax_type,  // Alert 내용
						icon: 'warning',                         // Alert 타입
					})
					$(row).css("background-color", "#FFCCE7")
					validCheckBtn.parent().text('등록되지 않은 사업자')
					validCheckBtn.remove()
				}
			},
			error: function(response) {
				Swal.fire({
					title: '문제가 발생하였습니다',         // Alert 제목
					text: response.responseText,  // Alert 내용
					icon: 'error',                         // Alert 타입
				})
			}
			})
		})
		$("#submitBtn").click(function() {
			let memberVOList = [];
			$("input[name=memberId]:checked").each(function() {
				let memberVO = {memberId: $(this).val()};
				memberVOList.push(memberVO);
			})
			Swal.fire({
					title: '판매자 가입을 승인합니다',
					text: "확인을 누르시면 판매자 가입 승인이 완료됩니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					
					}).then((result) => {
					if (result.isConfirmed) {
						$.ajax({
							url: "/admin/sellerconfirm",
							type: "post",
							data: JSON.stringify(memberVOList),
							contentType: "application/json",
							success: function(response){
								Swal.fire({
									title: '변경이 완료되었습니다.',
									icon: 'success',
								}).then(() => location.reload())
								
							}
						})
					}
			})
		})
		
		$("#denyBtn").click(function() {
			let memberVOList = [];
			$("input[name=memberId]:checked").each(function() {
				let memberVO = {memberId: $(this).val()};
				memberVOList.push(memberVO);
			})
			Swal.fire({
					title: '판매자 가입을 거절합니다',
					text: "확인을 누르시면 판매자 가입 거부가 완료됩니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					}).then((result) => {
					if (result.isConfirmed) {
						$.ajax({
							url: "/admin/sellerdeny",
							type: "post",
							data: JSON.stringify(memberVOList),
							contentType: "application/json",
							success: function(response){
								Swal.fire({
									title: '변경이 완료되었습니다.',
									icon: 'success',
								}).then(() => location.reload())
								
							}
						})
					}
			})
		})
		$("#checkbox_all").click(function() {
			if($("#checkbox_all").is(":checked")) {
				$("input[name=memberId]").prop("checked", true);
			}
			else {
				$("input[name=memberId]").prop("checked", false);
			}
		});
	})
</script>
<style type="text/css">
	.container {
		display: flex;
		flex-direction: column;
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
	}
	.memberAlllist-title{
		font-size: 30px;
	}
	.memId {
		cursor: pointer;
	}
	button {
		border: 1px solid #DDD;
		border-radius: 3px;
		width: 80px;
		cursor: pointer;
	}
	#button_area {
		display: flex;
		flex-direction: row;
		margin-bottom: 10px;
	}
	#button_area > button {
		margin-right: 10px;
	}
	#submitBtn {
		background-color: #c1c1fc;
	}
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
    <p class="font_subtitle">판매자 승인</p>
</div>
<div class="container">
	<div id="button_area">
		<button id="submitBtn">승인</button>
		<button id="denyBtn">거절</button>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th><input type='checkbox' name='checkbox_all' value='selectall' id="checkbox_all"/></th>
				<th>이메일</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>전화번호</th>
				<th>주소</th>
                <th>사업자 번호</th>
                <th>진위 확인</th>
                <th>차단 상태</th>
                <th>IP 주소</th>
                <th>최근 접속일</th>
				<th>회원 가입일</th>
				<th>회원 탈퇴일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty presellerList}">
                    <c:forEach items="${presellerList}" var="member" >
						<tr>
							<td><input type="checkbox" name="memberId" value="${member.memberId}"/></td>
							<td>
								<span class="memId" data-id="${member.memEmail}"><c:out value="${member.memEmail}" /></span></a>
							</td>
							<td>${member.memName}</td>
							<td>${member.memNickname}</td>
							<td>${member.memPhoneNumber}</td>
							<td>${member.memAddress}</td>
                            <td>${member.businessNumber}</td>
                            <td><button class="isValidBN" value="${member.businessNumber}">진위 확인</button></td>
							<td>${member.blockYn}</td>
                            <td>${member.ipAddress}</td>
                            <td>${member.latestLoginSuccessDate}</td>
							<td>${member.memJoinDate}</td>
							<td>${member.memUnregistDate}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="14">대기 중인 회원이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
</body>
</html>