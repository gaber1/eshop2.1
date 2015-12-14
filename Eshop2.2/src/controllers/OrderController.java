package controllers;

import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Product;
import model.OrderDao;
import model.ProductDao;

public class OrderController {

	private OrderDao orderDao;
	private ProductDao productDao;
	private int nextOrderId = 1;

	public OrderController(OrderDao orderDao, ProductDao productDao) {
		this.orderDao = orderDao;
		this.productDao = productDao;
	}

	public boolean createOrder(List<Integer> productIds) {
		List<Product> orderProducts = new ArrayList<Product>();

		List<Product> products = productDao.getProducts();
		for (int productId : productIds) {
			for (Product product : products) {
				if (product.getId() == productId) {
					orderProducts.add(product);
					break;
				}
			}
		}
		if (orderProducts.isEmpty()) {
			return false;
		} else {
			Order order = new Order();
			order.setId(nextOrderId);
			order.setProducts(orderProducts);
			nextOrderId++;

			orderDao.saveOrder(order);
			return true;
		}
	}

	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
}
