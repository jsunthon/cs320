<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quotation View</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body class="container-fluid">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<a class="page-header btn btn-info" href="AddQuotationController">Add
				a Quotation</a> <br />
			<p class="page-header">Welcome to the Quotation View JSP.</p>
			<br />
			<form action="QuotationController" method="post">
				<input type="text" name="query" /><input type="submit"
					name="Search" value="Search" />
			</form>
			<br />

			<!-- Print out results only if results is non-empty. -->
			<c:if test="${not empty quotations}">
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<th>Quotation</th>
						<th>Author</th>
						<th></th>
					</tr>
					<c:forEach items="${quotations }" var="quotation">
						<tr>
							<td>${quotation.quotation }</td>
							<td>${quotation.author }</td>
							<td><a class="btn btn-danger"
								href="DeleteQuotationController?id=${quotation.id }">Delete</a>&nbsp;
								<a class="btn btn-primary"
								href="EditQuotationController?id=${quotation.id }&quotation=${quotation.quotation}&author=${quotation.author}">Edit</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>

</body>
</html>