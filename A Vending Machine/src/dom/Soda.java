package dom;

//Base Soda class with defined attributes
public class Soda {
	private String name;
	private double price;
	private int pennyPrice; // variable needed to calculate change

	public Soda(String name, double price) {
		this.setName(name);
		this.setPrice(price);
		setPennyPrice((int) (price * 100));
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPennyPrice() {
		return pennyPrice;
	}

	public void setPennyPrice(int pennyPrice) {
		this.pennyPrice = pennyPrice;
	}

	public String toString() {
		return name;
	}
}
