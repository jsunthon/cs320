package final_exam;

public class Candidate {
	private int id;
	private String name;
	private int numOfYes;
	private int numOfNo;
		
	public Candidate(int id, String name, int numOfYes, int numOfNo) {
		super();
		this.id = id;
		this.name = name;
		this.numOfYes = numOfYes;
		this.numOfNo = numOfNo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumOfYes() {
		return numOfYes;
	}
	public void setNumOfYes(int numOfYes) {
		this.numOfYes = numOfYes;
	}
	public int getNumOfNo() {
		return numOfNo;
	}
	public void setNumOfNo(int numOfNo) {
		this.numOfNo = numOfNo;
	}
	
	
}
