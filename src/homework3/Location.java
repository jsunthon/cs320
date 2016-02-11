package homework3;

public class Location {
	private double longitude;
	private double latitude;
	private String name;
	private String address;
	private String city;
	private String zip;
	private String[] locationData;
	private String[] parsedAddress;
	private String[] stateZipContainer;

	public Location(String[] locationData) {
		this.locationData = locationData;
		initData();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String[] getLocationData() {
		return locationData;
	}

	public void setLocationData(String[] locationData) {
		this.locationData = locationData;
	}

	public void initData() {

		this.longitude = Float.valueOf(locationData[0]);
		this.latitude = Float.valueOf(locationData[1]);
		this.name = locationData[2];
		this.address = locationData[3].replace("\"", ""); // gets rid of the
															// quotation marks
															// in address
		this.parsedAddress = address.split("[;,]");

		if (parsedAddress.length == 3 || parsedAddress.length == 4) {
			this.city = parsedAddress[1];
			stateZipContainer = parsedAddress[2].split("[A-Z]{2}\\s");
			if (stateZipContainer != null) {
				try {
					this.zip = stateZipContainer[1];
				} catch (ArrayIndexOutOfBoundsException e) {
					this.zip = null;
				}
			}
		} else if (parsedAddress.length == 2) {
			this.city = parsedAddress[0];
			this.zip = null;
		}
	}
}
