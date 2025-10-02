<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đăng ký</title>
</head>
<body>
	<c:url value="register" var="register"></c:url>

	<form:form role="form" action="/general/${register}" method="post"
		enctype="multipart/form-data" modelAttribute="user">
		<h2>Tạo tài khoản mới</h2>
		<c:if test="${msg != null}">
			<h3 class="alert alert-danger">${msg}</h3>
		</c:if>
		<form:errors path="*" element="ul"/>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i>
					</span> <form:input type="text" placeholder="Username" path="userName"
						class="form-control"/>
				</div>
			</label>
		</section>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i>
					</span> <form:input type="text" placeholder="Họ tên" path="fullName"
						class="form-control"/>
				</div>
			</label>
		</section>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i>
					</span> <form:input type="text" placeholder="Email" path="email"
						class="form-control"/>
				</div>
			</label>
		</section>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i>
					</span> <form:input type="text" placeholder="Số điện thoại" path="phone"
						class="form-control"/>
				</div>
			</label>
		</section>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i>
					</span> <form:input type="password" placeholder="Mật khẩu" path="passWord"
						class="form-control"/>
				</div>
			</label>
		</section>
		<div class="form-group">
			<label>Ảnh đại diện</label> <input type="file" name="userAvatar" />
		</div>
		<button type="submit">Đăng kí</button>
	</form:form>
	|
	<a href="${pageContext.request.contextPath}/views/general/login.jsp">Đăng
		nhập</a>
</body>
</html>