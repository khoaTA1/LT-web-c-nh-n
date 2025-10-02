<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add category</title>
</head>
<body>
	<form role="form" action="/category/add" method="post"
		enctype="multipart/form-data">
		<h2>Thêm category</h2>
		<c:if test="${msg != null}">
			<h3 class="alert alert-danger">${msg}</h3>
		</c:if>
		<section>
			<div class="card-body">
				<div class="mb-3">
					<label for="categoryname" class="form-label">Category Name:</label>
					<input type="text" class="form-control"
						value="${category.categoryName}" id="name" name="catename"
						aria-describedby="nameid" placeholder="Category Name">
				</div>
			</div>
		</section>

		<div class="form-group">
			<label>Ảnh đại diện</label> <input type="file" name="icon" />
		</div>
		<button type="submit">Tạo category</button>
	</form>
</body>
</html>