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
	<form action="/KN_CSDL/changepass" method="POST">
		<h2>Nhập mật khẩu mới</h2>
		
		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" placeholder="Nhập mật khẩu mới" name="new_passw"
						class="form-control">
				</div>
			</label>
		</section>
		
		<button type="submit">Nhập mã</button>
</body>
</html>