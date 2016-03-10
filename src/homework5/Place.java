package homework5;

public class Place {
	
	private String name;
	private String street_address;
	private String city;
	private String state;
	private String zip;
	private double latitude;
	private double longitude;

		
	public Place(String name, String street_address, String city, String state, String zip, double latitude,
			double longitude) {
		super();
		this.name = name;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}

