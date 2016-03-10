package midterm;

import java.util.ArrayList;
import java.util.List;

public class Slot {	
	String name;
	String time;
	String day;

	List<Slot> slots = new ArrayList<Slot>();
	
	
	
	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Slot(String name, String time) {
		super();
		this.name = name;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDay() {
		return day;
	}
	public void setDays(String day) {
		this.day = day;
	}
}
