package controllers;

import java.util.List;

import beans.Product;
import model.ProductDao;

public class ProductController {

	private ProductDao productDao;
	private int nextProductId = 1;

	public ProductController(ProductDao productDao) {
		this.productDao = productDao;
	}

	public boolean createProduct(String name, String description, double price) {
		Product product = new Product();
		product.setId(nextProductId);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		nextProductId++;

		productDao.saveProduct(product);
		return true;
	}

	public boolean removeProduct(int id) {
		List<Product> products = productDao.getProducts();

		for (Product product : products) {
			if (product.getId() == id) {
				productDao.deleteProduct(product);
				return true;
			}
		}
		return false;
	}

	public List<Product> getProducts() {
		return productDao.getProducts();
	}
}
