<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/KN_CSDL/forgetpass" method="POST">
		<h2>Mã OTP đã được gửi đến email của bạn; nếu không tìm thấy, hãy thử kiểm tra trong mục thư rác trước khi yêu cầu lại mã</h2>
		<c:if test="${alert !=null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" placeholder="Nhập mã OTP nhận được" name="otp"
						class="form-control">
				</div>
			</label>
		</section>
		
		<button type="submit">Nhập mã</button> | <a href="${pageContext.request.contextPath}/wp_t1/forgetpass.jsp">Gửi lại mã?</a>
</body>
</html>