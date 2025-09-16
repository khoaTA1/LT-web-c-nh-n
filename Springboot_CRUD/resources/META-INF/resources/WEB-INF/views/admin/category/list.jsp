<%@page import="java.util.Currency"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="khoa.entity.Category"%>

<title>Admin: danh sách category</title>

<body>
	<%
	int pages = (int) session.getAttribute("page");
	int rowEachPage = (int) session.getAttribute("rowEachPage");
	int totalPages = (int) session.getAttribute("totalPages");
	%>

	<form method="post" action="admin/category/list">
		Rows per page: <input type="number" name="rowEachPage"
			value="<%=rowEachPage%>" min="1" /> <input type="submit"
			value="Apply" />
	</form>

	<table border="1">
		<c:forEach items="${categories}" var="cate" varStatus="STT">
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
			href="admin/category/list?page=<%=pages - 1%>&rowEachPage=<%=rowEachPage%>">Previous</a>
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