<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>CS320 Extra Credit</title>

<!---------------------------------- CSS -------------------------------------->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link href="../Content/footer-distributed-with-address-and-phones.css"
	rel="stylesheet" />
<link href="../Content/bootstrap.css" rel="stylesheet" />
<link href="../Content/animate.css" rel="stylesheet" />
<link href="../Content/add_styling.css" rel="stylesheet" />
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">

<!---------------------------------- END CSS -------------------------------------->


<script src="http://maps.google.com/maps/api/js?sensor=false"></script>

</head>
<body class="container-fluid">

	<!-------------------------------------- TOP NAVBAR ------------------------------->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><i class="fa fa-sitemap"></i>&nbsp;&nbsp;&nbsp;
				Extra Credit Assignment</a>
		</div>
	</div>
	</nav>
	<!-------------------------------------- END TOP NAVBAR ------------------------------->
	<br />
	<br />
	<br />
	<br />
	<div class="row">
		<!-------------------------------- SEARCH FORM ------------------------------>
		<div class="col-md-4 col-md-offset-4">
			<div class="well text-center">
				<h1>Extra Credit</h1>
				<hr />
				<p class="lead">Start your Search</p>
				<form action="Search" method="post">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Latitude</div>
							<input id="lat" class="form-control" type="text" name="lat"
								placeholder="Latitude" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Longitude</div>
							<input id="lon" class="form-control" type="text" name="lon"
								placeholder="Longitude" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Radius</div>
							<input class="form-control" type="text" name="radius"
								placeholder="Radius" />
						</div>
					</div>
					<input type="submit" class="btn btn-info btn-block btn-md"
						value="Search" />
				</form>
				<br />
				<button class="btn btn-info btn-block btn-md" id="get-location">
					<i class="fa fa-caret-square-o-right"></i>&nbsp;&nbsp;&nbsp;Get
					your location
				</button>
			</div>
			<!-------------------------------- END  SEARCH FORM ------------------------------>
		</div>
	</div>
	<!-------------------------------- RESULTS  ------------------------------>
	<div class="row" id="results">

		<!-------------------------------- RESULTS LIST ------------------------------>
		<div class="col-md-4 col-md-offset-4">
			<ul id="results-list" class="list-group">
				<li id="result-item" class="list-group-item">Google Maps APIs
					Customers</li>
				<li id="result-item" class="list-group-item">Google Maps APIs
					Customers</li>
				<li id="result-item" class="list-group-item">Google Maps APIs
					Customers</li>
				<li id="result-item" class="list-group-item">Google Maps APIs
					Customers</li>
				<li id="result-item" class="list-group-item">Google Maps APIs
					Customers</li>
				<li id="result-item" class="list-group-item">Google Maps APIs
					Customers</li>
			</ul>
		</div>
		<!-------------------------------- END RESULTS LIST ------------------------------>

		<!-------------------------------- GOOGLE MAP ------------------------------>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div id="google-maps">
					<img src="	https://www.google.com/maps/embed/v1/search
		  ?key=AIzaSyDv_0sVA5OuZzQuulNUuP6gkYKMWt88vwk
		  &center=-33.8569,151.2152
		  &zoom=10" />
				</div>
			</div>
		</div>
		<!-------------------------------- END GOOGLE MAP ------------------------------>

	</div>
	<!-------------------------------- RESULTS  ------------------------------>

	<!---------------------------------  JAVASCRIPT ------------------------------------>
	<script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script src="../Scripts/bootstrap.min.js"></script>
	<script src="../Scripts/cs320.js"></script>
	<!--------------------------------- END JAVASCRIPT ------------------------------ -->

</body>
</html>