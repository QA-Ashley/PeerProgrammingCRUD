package extension;

public class Runner {
	public static void main(String[] args) {
		DB db = new DB();
		
//		if(db.createUser("jdog2001", "Pa$$word", "Jason", "Mcgree")) {
//			System.out.println("Customer created");
//		} else {
//			System.out.println("Error creating customer");
//		}
//		if(db.createProduct("Fifa 19", 9.44, 8)) {
//			System.out.println("Product inserted");
//		} else {
//			System.out.println("Error creating product");
//		}
//		if(db.createOrder(1, 1, 2)) {
//			System.out.println("Order created");
//		} else {
//			System.out.println("Error creating order");
//		}
//		db.deleteCustomer(2);
//		db.deleteProduct(2);
//		db.deleteOrder(4);
		db.viewProducts("Products");
	}
}
