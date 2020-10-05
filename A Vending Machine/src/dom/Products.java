package dom;

import dom.Soda;

//This class makes a pre-determined list of Soda's
public class Products {
	private Soda productList[] = { new Soda("Orange Soda", 2.50), new Soda("Black Soda", 4.50),
			new Soda("Red Soda", 2.25), new Soda("Purple Soda", 3.75), new Soda("Brown Soda", 2.00),
			new Soda("White Soda", 1.75), new Soda("Yellow Soda", 4.00), new Soda("Blue Soda", 6.25),
			new Soda("Green Soda", 5.50), new Soda("Pink Soda", 7.75) };

	public Products() {
	}

	public Soda[] getProductList() {
		return productList;
	}

	public String getName(Soda soda) {
		return soda.getName();
	}

}
