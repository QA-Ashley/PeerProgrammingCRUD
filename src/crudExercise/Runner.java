package crudExercise;


import java.util.InputMismatchException;




public class Runner {
	public static void main(String[] args) {
//		DB db = new DB();
		// db.viewDB("customers");
		// db.create("customers", 0, "Josh Barrett", "123 Fake Drive",
		// "Joshyboi@hotmail.co.uk", "password");
		// db.deleteCustomer("customers", 22);
//		db.update("customers", 3, "123 Update Street");
//		List<Order> orders = new ArrayList<Order>();
//		
//		orders.add(new Order(2,1));
//		orders.add(new Order(4,3));
//		orders.add(new Order(10,2));
//		
//		int[] productID = new int[orders.size()];
//		int[] quantity = new int[orders.size()];
//		
//		for(int i = 0; i < orders.size(); i++) {
//			productID[i] = orders.get(i).getProductID();
//			quantity[i] = orders.get(i).getQuantity();
//		}
//		
//		for(int a:quantity) {
//			System.out.println(a);
//		}

		boolean proceed = false;

		for (int i = 0; i < 3; i++) {
			proceed = false;
			do {
				try {
					System.out.println(i);
					proceed = true;
				} catch (InputMismatchException e1) {
					System.out.println("Input must be a number");
				}
			} while (!proceed);
		}

	}
}
