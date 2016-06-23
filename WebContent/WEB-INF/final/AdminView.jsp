<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin View</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body class="container-fluid">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<a class="page-header btn btn-info" href="Vote">Back to
				Vote Controller</a> <br />
			<p class="page-header">Welcome to the Admin View JSP.</p>
			<br />
			<form action="Admin" method="post">
				<input type="text" name="name" />&nbsp;<input type="submit"
					name="Search" value="Add Candidate" />
			</form>
			<br />

			<!-- Print out results only if results is non-empty. -->
			<c:if test="${not empty candidates}">
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<th>Candidate's Name</th>
						<th>Yes</th>
						<th>No</th>
						<th>Action</th>
						<th></th>
					</tr>
					<c:forEach items="${candidates }" var="candidate">
						<tr>
							<td>${candidate.name }</td>
							<td>${candidate.numOfYes }</td>
							<td>${candidate.numOfNo }</td>
							<td><a class="btn btn-danger"
								href="Admin?action=delete&id=${candidate.id }">Delete</a>&nbsp;
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>

</body>