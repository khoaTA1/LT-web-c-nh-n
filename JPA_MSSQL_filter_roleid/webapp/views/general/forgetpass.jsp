<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Khôi phục mật khẩu</title>
</head>
<body>
	<form action="/JPA/forgetpass" method="GET">
		<h2>Nhập email để nhận mã khôi phục mật khẩu</h2>
		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" placeholder="Email" name="email"
						class="form-control">
				</div>
			</label>
		</section>
		
		<button type="submit">Gửi mã</button>
</body>
</html>