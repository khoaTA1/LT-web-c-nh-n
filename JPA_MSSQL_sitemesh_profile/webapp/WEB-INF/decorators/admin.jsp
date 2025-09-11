<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ Admin</title>
</head>
<body>
	<div>
		<%@ include file="/common/header_admin.jsp"%>
	</div>
	<div>
		<sitemesh:write property="body" />
	</div>
</body>
</html>