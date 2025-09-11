<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Admin: edit category</title>

<c:url value="/categoryedit" var="edit"></c:url>
<form role="form" action="${edit}" method="post"
	enctype="multipart/form-data">
	<input name="id" value="${category.id }" hidden="">
	<div class="form-group">
		<label>Tên phân loại:</label> <input type="text" class="form-control"
			value="${category.categoryName }" name="name" />
	</div>
	<div class="form-group">
		<c:url value="/image?fname=${category.images }" var="imgUrl"></c:url>
		<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
		<label>Ảnh đại diện</label> <input type="file" name="icon"
			value="${category.images }" />
	</div>
	<button type="submit" class="btn btn-default">Edit</button>
	<button type="reset" class="btn btn-primary">Reset</button>
</form>