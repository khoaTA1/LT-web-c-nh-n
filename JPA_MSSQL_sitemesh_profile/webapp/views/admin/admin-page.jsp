<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<title>Tranh chủ admin</title>
<c:choose>
	<c:when test="${sessionScope.account == null}">
	</c:when>
	<c:otherwise>
		<div class="col-sm-6">
			<ul class="list-inline right-topbar pull-right">
				<li><a>${sessionScope.account.userName}</a> | <a
					href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
				<li><i class="search fa fa-search search-button"></i></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
<c:if test="${sessionScope.account != null}">
	<a href="/JPA/views/admin/add-category.jsp">Tạo category</a>
	<br>
	<a href="/JPA/categorycontroller">Hiển thị danh sách các category
		hiện có</a>
	<br>
</c:if>
