<%@page import="java.util.Currency"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="khoa.entity.Category"%>

<title>Hiển thị danh sách category</title>

<body>

	<form method="post" action="/category/list">
		Rows per page: 
		<input type="number" name="rowEachPage" value="${rowEachPage}" min="1" /> 
		<input type="submit" value="Apply" />
	</form>

	<table border="1">
		<c:forEach items="${categories}" var="cate" varStatus="STT">
			<tr class="odd gradeX">
				<td>${STT.index+1 }</td>
				<c:url value="/image?fname=${cate.getImages()}" var="imgUrl"></c:url>
				<td><img height="150" width="200" src="${imgUrl}" /></td>
				<td>${cate.getCategoryName()}</td>
				<td>user id: ${cate.getUid() }</td>
				<td><a href="<c:url value='/category/edit/${cate.getId()}'/>"
					class="center">Sửa</a> | <a
					href="<c:url value='/category/delete/${cate.getId()}'/>"
					class="center">Xóa</a> | <a
					href="<c:url value='/video/add/${cate.getId()}'/>"
					class="center">Thêm video</a> | <a
					href="<c:url value='/video/list?id=${cate.getId()}'/>"
					class="center">Hiển thị danh sách video</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>${pages}/${totalPages}</div>
	<!-- Điều hướng trang -->
	<div>
		<c:choose>
			<c:when test="${pages > 1}">
				<a
					href="/category/list?pages=${pages - 1}&rowEachPage=${rowEachPage}">Previous</a>
			</c:when>
			<c:otherwise>
				<span style="color: gray;">Previous</span>
			</c:otherwise>
		</c:choose>

		|

		<c:choose>
			<c:when test="${pages < totalPages}">
				<a
					href="/category/list?pages=${pages + 1}&rowEachPage=${rowEachPage}">Next</a>
			</c:when>
			<c:otherwise>
				<span style="color: gray;">Next</span>
			</c:otherwise>
		</c:choose>
	</div>
</body>