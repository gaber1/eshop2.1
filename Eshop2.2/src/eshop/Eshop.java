package eshop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Product;
import controllers.OrderController;
import controllers.ProductController;
import model.OrderDao;
import model.ProductDao;

public class Eshop {

	private ProductController productController;
	private OrderController orderController;

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public Eshop() {
		ProductDao productDao = new ProductDao();
		OrderDao orderDao = new OrderDao();

		productController = new ProductController(productDao);
		orderController = new OrderController(orderDao, productDao);
	}

	public static void main(String[] args) throws Exception {
		Eshop eshop = new Eshop();
		eshop.mainMenu();
	}

	private void mainMenu() throws Exception {
		while (true) {
			System.out.println("================");
			System.out.println("Welcome to Eshop");
			System.out.println("1. Add a new product");
			System.out.println("2. Remove existing product");
			System.out.println("3. List all products");
			System.out.println("4. Order a product");
			System.out.println("5. List all orders");
			System.out.println("6. Exit");
			System.out.print("Enter your choice (1-6): ");
			int choice = 0;

			try {
				choice = Integer.parseInt(input.readLine());
			} catch (NumberFormatException e) {
				System.out.println("You have to enter a number between (1-6)!");
			}

			switch (choice) {
			case 1:
				addNewProduct();
				break;
			case 2:
				removeExistingProduct();
				break;
			case 3:
				listAllProducts();
				break;
			case 4:
				orderProduct();
				break;
			case 5:
				listAllOrders();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Your choice is not in range (1-6), try again.");
				break;
			}
		}
	}

	private void addNewProduct() throws Exception {
		System.out.println("================");
		System.out.print("Enter product name: ");
		String name = input.readLine();
		System.out.print("Enter product description: ");
		String description = input.readLine();

		double price = 0;

		while (true) {
			System.out.print("Enter product price: ");
		    try {
		        price = Double.parseDouble(input.readLine());
		        break; 
		    } catch (NumberFormatException ignore) {
		        System.out.println("You have NOT entered a number!");
		    }
		}

		boolean success = productController.createProduct(name, description, price);
		if (success) {
			System.out.println("New product added.");
		} else {
			System.out.println("Error: new product not added.");
		}
	}

	private void removeExistingProduct() throws Exception {
		System.out.println("================");
		System.out.print("Enter product id: ");
		int id = Integer.parseInt(input.readLine());

		boolean success = productController.removeProduct(id);
		if (success) {
			System.out.println("Existing product removed.");
		} else {
			System.out.println("Error: existing product NOT removed.");
		}
	}

	private void listAllProducts() {
		System.out.println("================");
		System.out.println("Products:");

		List<Product> products = productController.getProducts();
		for (Product product : products) {
			printProduct(product);
		}
	}

	private void orderProduct() throws Exception {
		System.out.println("================");
		List<Integer> productIds = new ArrayList<Integer>();
		while (true) {
			System.out.print("Enter product id (or 'f' for finished): ");
			String stringId = input.readLine();
			if (stringId.equals("f")) {
				break;
			} else {
				int id = Integer.parseInt(stringId);
				productIds.add(id);
			}
		}

		boolean success = orderController.createOrder(productIds);
		if (success) {
			System.out.println("Products ordered.");
		} else {
			System.out.println("Error: products not ordered.");
		}
	}

	private void listAllOrders() {
		System.out.println("================");
		System.out.println("Orders:");

		List<Order> orders = orderController.getOrders();
		for (Order order : orders) {
			printOrder(order);
		}
	}

	private void printProduct(Product product) {
		System.out.println("Product --> Id: " + product.getId() + ", Name: " + product.getName() + ", Description: "
				+ product.getDescription() + ", Price: " + product.getPrice());
	}

	private void printOrder(Order order) {
		System.out.println("Order --> Id: " + order.getId());

		List<Product> products = order.getProducts();
		for (Product product : products) {
			printProduct(product);
		}
	}
}
