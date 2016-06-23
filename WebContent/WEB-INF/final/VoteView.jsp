<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vote View</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body>

		<a class="page-header btn btn-info" href="Admin">Back to
				Admin Controller</a>
	<!-- Print out results only if results is non-empty. -->
	<c:if test="${not empty candidate}">
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<td>${candidate.name }</td>
				<td><a class="btn btn-primary" href="Vote?action=yes&id=${candidate.id }">Yes</a></td>
				<td><a class="btn btn-danger" href="Vote?action=no&id=${candidate.id }">No</a></td>		
			</tr>
		</table>
	</c:if>	
	<input type="hidden" name="id" value="${candidate.id }" />
</body>
</html>