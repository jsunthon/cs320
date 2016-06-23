<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Quotation View</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body class="container-fluid">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<a class="page-header btn btn-primary" href="QuotationController">Go
				back to Quotation Search Page</a>
			<p>Welcome to the Edit Quotation Page.</p>
			<br />
			<form action="EditQuotationController" method="post">
				<input type="hidden" name="id" value="${param.id }" /> Quotation: <input
					type="text" name="quotation" value="${param.quotation }" />
				Author: <input type="text" name="author" value="${param.author }" /><br />
				<input type="submit" name="edit" value="edit" />
			</form>
		</div>
	</div>
</body>
</html>