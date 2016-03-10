<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--Search by Zip Code --%>
	<form action="Search" method="post">
		<div>
			<select name="type">
				<option value="zip">Zip</option>
				<option value="city">City</option>
				<option value="location">Location</option>
			</select> <input type="submit" value="Submit">
		</div>
		<br/>
		<div>
			IF SEARCHING BY ZIP: <input type="text" name="zip"
				value="${zip}" />
		</div>
		<br />
		<div>
			IF SEARCHING BY CITY: <input type="text"
				name="city" value="${city}" />
		</div>
		<br />
		<div>
			IF SEARCHING BY LOCATION:<br /> Latitude: <input type="text" name="lat"
				value="${latitude}" /> <br /> Longitude: <input type="text"
				name="lon" value="${longitude}" /><br /> Within Radius: <input
				type="text" name="radius" value="${radius}" />
		</div>
	</form>
	<br />
	<ul id="results-list">
		<c:forEach items="${places}" var="place">
			<li class="result-item">${place.name},${place.street_address},
				${place.city}, ${place.state}, ${place.zip}, ${place.latitude},
				${place.longitude}</li>
		</c:forEach>
	</ul>

</body>
</html>