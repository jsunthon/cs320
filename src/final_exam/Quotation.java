package final_exam;

public class Quotation {
	private int id;
	private String quotation;
	private String author;

	public Quotation(int id, String quotation, String author) {
		super();
		this.id = id;
		this.quotation = quotation;
		this.author = author;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getQuotation() {
		return quotation;
	}

	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
