package model;

import java.util.ArrayList;
import java.util.List;

import beans.Order;

public class OrderDao {

	private List<Order> orders = new ArrayList<Order>();

	public OrderDao() {
	}

	public void saveOrder(Order order) {
		orders.add(order);
	}

	public List<Order> getOrders() {
		return orders;
	}
}
