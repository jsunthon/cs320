package homework2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Homework2User {
	private String firstName;
	private String lastName;
	private String email;
	private Date date;
	private String dateCreated;
	
	public Homework2User(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		date = new Date();
		Locale currentLocale = new Locale("en-US");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy", currentLocale);
		dateCreated = formatter.format(date);
		
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getdateCreated() {
		return dateCreated;
	}
	
}
