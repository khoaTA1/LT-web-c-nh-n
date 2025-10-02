<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add video</title>
</head>
<body>
	<form role="form" action="/video/add" method="post"
		enctype="multipart/form-data">
		<h2>Thêm video cho category: ${category.categoryName }</h2>
		<c:if test="${msg != null}">
			<h3 class="alert alert-danger">${msg}</h3>
		</c:if>
		<section>
			<input name="cateid" value="${category.id }" hidden="">
			<div class="card-body">
				<div class="mb-3">
					<label for="videoname" class="form-label">Video Name:</label> <input
						type="text" class="form-control" id="videoname"
						name="videoname" aria-describedby="nameid"
						placeholder="Video Name">
				</div>
			</div>
		</section>

		<div class="form-group">
			<label>Ảnh đại diện</label> <input type="file" name="video" />
		</div>
		<button type="submit">Tạo video</button>
	</form>
</body>
</html>