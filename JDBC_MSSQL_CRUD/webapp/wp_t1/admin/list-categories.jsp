<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin: list categories</title>
</head>
<body>
	<c:forEach items="${catelist}" var="cate" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			<c:url value="/image?fname=${cate.getIcon()}" var="imgUrl"></c:url>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.getCatename()}</td>
			<td><a
				href="<c:url value='/categoryedit?id=${cate.getCateid()}'/>"
				class="center">Sửa</a> | <a
				href="<c:url value='/categorydelete?id=${cate.getCateid()}'/>"
				class="center">Xóa</a></td>
		</tr>
	</c:forEach>
</body>
</html>