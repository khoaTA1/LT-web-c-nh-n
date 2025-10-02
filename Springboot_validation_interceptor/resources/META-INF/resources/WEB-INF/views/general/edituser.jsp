<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="jakarta.tags.core"%>
<%@ page import="khoa.entity.User"%>

<title>Profile user</title>

<body>
	<%
	User user = (User) session.getAttribute("account");
	%>

	<a>Username: ${account.userName }</a>
	<br>
	<a>Full name: ${account.fullName }</a>
	<br>
	<a>Email: ${account.email}</a>
	<br>
	<a>phone: ${account.phone }</a>
	<br>
	<a>avatar: ${account.avatar }</a>
	<c:url value="/image?fname=${account.avatar}" var="avaUrl"></c:url>
	<img height="150" width="200" src="${avaUrl}" />
	<br>
	<a>========================================================</a>
	<br>
	<a>==============Cập nhật thông tin tài khoản==============</a>
	<br>
	
	<c:url value="general/edituser" var="update"></c:url>
	
	<form role="form" action="${update}" method="post"
		enctype="multipart/form-data">
		<input name="id" value="${account.id }" hidden="">
		<div class="form-group">
			<label>Full name:</label> <input type="text" class="form-control"
				value="${account.fullName}" name="fname" />
		</div>
		<div class="form-group">
			<label>Phone:</label> <input type="text" class="form-control"
				value="${account.phone}" name="phone" />
		</div>
		<div class="form-group">
			<c:url value="/image?fname=${account.avatar }" var="avaUrl"></c:url>
			<img class="img-responsive" width="100px" src="${avaUrl}" alt="">
			<label>Ảnh đại diện</label> <input type="file" name="avatar"
				value="${account.avatar}" />
		</div>
		<button type="submit" class="btn btn-default">Cập nhật</button>
	</form>
</body>