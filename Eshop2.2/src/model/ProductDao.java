package model;

import java.util.ArrayList;
import java.util.List;

import beans.Product;

public class ProductDao {

	private List<Product> products = new ArrayList<Product>();

	public ProductDao() {
	}

	public void saveProduct(Product product) {
		products.add(product);
	}

	public void deleteProduct(Product product) {
		products.remove(product);
	}

	public List<Product> getProducts() {
		return products;
	}
}
