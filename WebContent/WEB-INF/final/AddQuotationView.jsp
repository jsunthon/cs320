<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Quotation</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body class="container-fluid">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<a class="page-header btn btn-primary" href="QuotationController">Click
				here to view all the quotations;</a> <br />
			<p>Welcome to the Add Quotation View JSP.</p>
			<br />
			<form action="AddQuotationController" method="post">
				<input type="text" name="quotation" /> <input type="text"
					name="author" /> <input type="submit" name="submit" value="submit" />
			</form>
		</div>
	</div>
</body>
</html>