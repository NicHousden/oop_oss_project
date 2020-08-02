ppackage oop_OSS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public interface catalogList {
	void catalogList(String Cid);

	void cart(String Cid);

	void cartDetail(String Cid);
}

interface choiceInlist {
	ArrayList<Integer> ItemId();

	ArrayList<Integer> ItemQuan();

	ArrayList<String> ItemName();

	double totalPrice();

	String Cid();
}

class customerChoice implements catalogList, choiceInlist {

	String Cid;
	double total = 0; 

	@Override
	public void catalogList(String Cidin) {
		showdatalog();
		Cid = Cidin;
	}

	public static ArrayList<Integer> itemId = new ArrayList<Integer>();
	public static ArrayList<Integer> itemQ = new ArrayList<Integer>();
	public static ArrayList<String> itemName = new ArrayList<String>();
	public static ArrayList<Double> pricePeritem = new ArrayList<Double>();

	private void showdatalog() {
		int[][] cart = new int[4][1];
		String[] options = { "Buy Iphone X at price $865.32", "Buy Laptop Lenovo with price $999",
				"Buy Scooter at price $100", " Buy a Guita price $299.9", "Buy XBox at price $325.2", "Exit" };
		int x = JOptionPane.showOptionDialog(null, "Choice Items you want!!", "Wellcome to the Catalog",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (x == 0) {
			try {
				String amount = JOptionPane.showInputDialog("Enter the quantity you want to buy Iphone");
				int IdItem = 1;
				String ItemName = "Iphone";
				double priceitem = 865.32;
				int fistItem = Integer.parseInt(amount);
				total+=priceitem*fistItem;
				itemQ.add(fistItem);
				itemId.add(IdItem);
				itemName.add(ItemName);
				pricePeritem.add(priceitem);
				shopMoreOrOut();
			} catch (Exception e) {
				System.out.println("You must input interger only");
				System.exit(0);
			}

		} else if (x == 1) {
			try {
				String amount = JOptionPane.showInputDialog("Enter the quantity you want to buy Lenovo");
				int IdItem = 2;
				String ItemName = "Lenovo";
				double priceitem = 999;
				pricePeritem.add(priceitem);
				int secondItem = Integer.parseInt(amount);
				total+=priceitem*secondItem;
				itemId.add(IdItem);
				itemQ.add(secondItem);
				itemName.add(ItemName);
				shopMoreOrOut();
			} catch (Exception e) {
				System.out.println("You must input interger only");
				System.exit(0);
			}

		} else if (x == 2) {
			try {
				String amount = JOptionPane.showInputDialog("Enter the quantity you want to buy Scooter");
				int IdItem = 3;
				String ItemName = "Scooter";
				double priceitem = 100;
				pricePeritem.add(priceitem);
				int thridItem = Integer.parseInt(amount);
				total+=priceitem*thridItem;
				itemId.add(IdItem);
				itemQ.add(thridItem);
				itemName.add(ItemName);
				shopMoreOrOut();
			} catch (Exception e) {
				System.out.println("You must input interger only");
				System.exit(0);
			}

		} else if (x == 3) {
			try {
				String amount = JOptionPane.showInputDialog("Enter the quantity you want to buy Guita");
				int IdItem = 4;
				String ItemName = "Guita";
				double priceitem = 299.9;
				pricePeritem.add(priceitem);
				int fourthItem = Integer.parseInt(amount);
				total+=priceitem*fourthItem;
				itemId.add(IdItem);
				itemQ.add(fourthItem);
				itemName.add(ItemName);
				shopMoreOrOut();
			} catch (Exception e) {
				System.out.println("You must input interger only");
				System.exit(0);
			}
		} else if (x == 4) {
			try {
				String amount = JOptionPane.showInputDialog("Enter the quantity you want to buy XBox");
				int IdItem = 5;
				String ItemName = "XBox";
				double priceitem = 325.2;
				pricePeritem.add(priceitem);
				int fivthItem = Integer.parseInt(amount);
				total+=priceitem*fivthItem;
				itemId.add(IdItem);
				itemQ.add(fivthItem);
				itemName.add(ItemName);
				shopMoreOrOut();
			} catch (Exception e) {
				System.out.println("You must input interger only");
				System.exit(0);
			}
		} else {
			System.out.println("The customer does not select item and exits");
			System.exit(0);
		}

	}

	private void shopMoreOrOut() {
		// do you want to shop more or do you want to check out
		String[] shopOrOutOP = { "Do you want to shop more", "Go to Your cart" };
		int shopOrOut = JOptionPane.showOptionDialog(null, "You have make your order", "Main page",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, shopOrOutOP, shopOrOutOP[0]);
		if (shopOrOut == 0) {
			showdatalog();
		} else if (shopOrOut == 1) {
			// See the items has added to the cart
			cart(Cid);

		} else {
			System.exit(0);
		}

	}

	private void viewOrder() {

		JOptionPane.showMessageDialog(null, "This is your order detail ans Status", null, 3);

	}

	@Override
	public ArrayList<Integer> ItemId() {
		// TODO Auto-generated method stub
		return itemId;
	}

	@Override
	public ArrayList<Integer> ItemQuan() {
		// TODO Auto-generated method stub
		return itemQ;
	}

	@Override
	public String Cid() {
		// TODO Auto-generated method stub
		return Cid;
	}

	@Override
	public ArrayList<String> ItemName() {
		// TODO Auto-generated method stub
		return itemName;
	}

	@Override
	public double totalPrice() {
		return total;
	}

	@Override
	public void cart(String Cid) {
		JOptionPane.showMessageDialog(null, "Your has browsed and add item: " + itemName + " to your cart");
	}

	@Override
	public void cartDetail(String Cid) {
		JOptionPane.showMessageDialog(null, "Customer orders details: see in the Console!!!");
		System.out.println("===========================");
		System.out.println("Customer orders details:");
		System.out.println("The customer with id: " + Cid);
		for (int i = 0; i < pricePeritem.size(); i++) {
			System.out.println(itemName.get(i) + " with quantities is: " + itemQ.get(i));
		}
		System.out.println("The total price is: $" + total);
		System.out.println("============================");

	}

}
