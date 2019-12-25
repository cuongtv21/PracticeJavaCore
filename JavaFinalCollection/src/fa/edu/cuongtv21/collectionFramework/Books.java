package fa.edu.cuongtv21.collectionFramework;

public class Books {

	private String nameBook;
	private float priceBook;

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Books(String nameBook, float priceBook) {
		super();
		this.nameBook = nameBook;
		this.priceBook = priceBook;
	}

	public String getNameBook() {
		return nameBook;
	}

	public void setNameBook(String nameBook) {
		this.nameBook = nameBook;
	}

	public float getPriceBook() {
		return priceBook;
	}

	public void setPriceBook(float priceBook) {
		this.priceBook = priceBook;
	}

	@Override
	public String toString() {
		return "Books [nameBook=" + nameBook + ", priceBook=" + priceBook + "]";
	}

}
