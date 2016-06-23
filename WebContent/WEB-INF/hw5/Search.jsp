<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC Coffee Shop Locater</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body class="container-fluid">
	<div class="row">
		<div class="col-md-4 col-md-offset-1">
			<h1 class="page-header">POST Request Search</h1>
			<form action="Search" method="post">
				<div>
					<select name="type">
						<option value="zip" ${param.type == "zip" ? 'selected="selected"' : ''}>Zip</option>
						<option value="city" ${param.type == "city" ? 'selected="selected"' : ''}>City</option>
						<option value="location" ${param.type == "location" ? 'selected="selected"' : ''}>Location</option>
					</select>
				</div>
				<br />
				<div>
					SELECT <b>'ZIP'</b> option, then search: <input type="text" name="zip" value="${zip}" />
				</div>
				<br />
				<div>
					SELECT <b>'CITY'</b> option, then search: <input type="text" name="city"
						value="${city}" />
				</div>
				<br />
				<div>
					SELECT <b>'LOCATION'</b> option, then search:<br /> Latitude: <input type="text"
						name="lat" value="${latitude}" /> <br /> Longitude: <input
						type="text" name="lon" value="${longitude}" /><br /> Within
					Radius: <input type="text" name="radius" value="${radius}" />
				</div>
				<br /> <input class="btn btn-primary" type="submit" value="Submit">
			</form>


		</div>
		<div class="col-md-5 col-md-offset-1">
			<h1 class="page-header">GET Request Search</h1>
			<form action="Search">
				<div>
					<select name="type">
						<option value="zip" ${param.type == "zip" ? 'selected="selected"' : ''}>Zip</option>
						<option value="city" ${param.type == "city" ? 'selected="selected"' : ''}>City</option>
						<option value="location" ${param.type == "location" ? 'selected="selected"' : ''}>Location</option>
					</select>
				</div>
				<br />
				<div>
					SELECT <b>'ZIP'</b> option, then search: <input type="text" name="zip" value="${zip}" />
				</div>
				<br />
				<div>
					SELECT <b>'CITY'</b> option, then search: <input type="text" name="city"
						value="${city}" />
				</div>
				<br />
				<div>
					SELECT <b>'LOCATION'</b> option, then search:<br /> Latitude: <input type="text"
						name="lat" value="${latitude}" /> <br /> Longitude: <input
						type="text" name="lon" value="${longitude}" /><br /> Within
					Radius: <input type="text" name="radius" value="${radius}" />
				</div>
				<br /> <input class="btn btn-primary" type="submit" value="Submit">
			</form>
		</div>
	</div>

	<c:if test="${not empty places }">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<h3 class="page-header">Search Results: ${fn:length(places) } shops found.</h3>
				<ul id="results-list">
					<c:forEach items="${places}" var="place">
						<li class="result-item">${place.name},${place.street_address},
							${place.city}, ${place.state}, ${place.zip}, ${place.latitude},
							${place.longitude}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</c:if>

</body>
</html>