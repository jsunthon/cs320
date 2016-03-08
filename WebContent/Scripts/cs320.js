$(document).ready(function() {
	
	$("#get-location").on("click", function() {
		getLocation();
	});
	
	function getLocation() {
    	if (navigator.geolocation) {
        	navigator.geolocation.getCurrentPosition(showPosition);
    	}
    	else { 
        	$("#status-msg").html("Geolocation is not supported by this browser.");
    	}
	}
	
	function showPosition(position) {
	    var lat = position.coords.latitude;	    
	    var lon = position.coords.longitude;
	    
	    $("#lon").val(lon);
	    $("#lat").val(lat);
	    
	    var mapholder = $("#google-maps");

	}
		
});

