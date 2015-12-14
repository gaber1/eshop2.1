package beans;

import java.util.List;

public class Order {

	private int id;
	private List<Product> products;

	public Order() {
	}

	public Order(int id, List<Product> products) {
		this.id = id;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
