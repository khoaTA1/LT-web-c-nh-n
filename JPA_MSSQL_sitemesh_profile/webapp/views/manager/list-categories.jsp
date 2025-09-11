<%@page import="java.util.Currency"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="Entity.Category"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager: list categories</title>
</head>
<body>

	<%
	List<Category> ent = (List<Category>) request.getAttribute("catelist");
	int pages = (int) request.getAttribute("page");
	int rowEachPage = (int) request.getAttribute("rowEachPage");
	int totalPages = (int) request.getAttribute("totalPages");
	%>

	<form method="get" action="/JPA/categorycontroller">
		Rows per page: <input type="number" name="rowEachPage"
			value="<%=rowEachPage%>" min="1" /> <input type="submit"
			value="Apply" />
	</form>

	<table border="1">
		<c:forEach items="${catelist}" var="cate" varStatus="STT">
			<tr class="odd gradeX">
				<td>${STT.index+1 }</td>
				<c:url value="/image?fname=${cate.getImages()}" var="imgUrl"></c:url>
				<td><img height="150" width="200" src="${imgUrl}" /></td>
				<td>${cate.getCategoryName()}</td>
				<td>user id: ${cate.getUid() }</td>
				<td><a href="<c:url value='/categoryedit?id=${cate.getId()}'/>"
					class="center">Sửa</a> | <a
					href="<c:url value='/categorydelete?id=${cate.getId()}'/>"
					class="center">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%=pages%>/<%=totalPages%>
	</div>
	<!-- Điều hướng trang -->
	<div>
		<%
		if (pages > 1) {
		%>
		<a
			href="/JPA/categorycontroller?page=<%=pages - 1%>&rowEachPage=<%=rowEachPage%>">Previous</a>
		<%
		} else {
		%>
		<span style="color: gray; text-decoration: none;">Previous</span>
		<%
		}
		%>
		|
		<%
		if (pages < totalPages) {
		%>
		<a
			href="/JPA/categorycontroller?page=<%=pages + 1%>&rowEachPage=<%=rowEachPage%>">Next</a>
		<%
		} else {
		%>
		<span style="color: gray; text-decoration: none;">Next</span>
		<%
		}
		%>
	</div>

</body>
</html>