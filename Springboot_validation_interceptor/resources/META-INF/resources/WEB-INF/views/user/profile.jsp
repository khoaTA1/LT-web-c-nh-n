<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User: profile</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.account == null}">
		</c:when>
		<c:otherwise>
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a
						href="${pageContext.request.contextPath}/general/edituser"
						class="center">${sessionScope.account.fullName}</a> | <a
						href="${pageContext.request.contextPath}/general/logout">Đăng Xuất</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/category/add">Thêm
					category</a> <br> <a
					href="${pageContext.request.contextPath}/category/list">Danh
					sách các category</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>