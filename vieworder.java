package oop_OSS;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public interface vieworder {
	void vieworder(String Cid) throws IOException;
}
class view implements vieworder {

	// viewOrder(String CCN, double amount) {
	// super(CCN, amount);
	//
	// }

	public void vieworder(String Cid) throws IOException {
		int choice = JOptionPane.showOptionDialog(null, "Do you want to view your order?", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		// interpret the user's choice
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Customer orders details: see in the Console!!!");
			File Read = new File("order.txt");
			if (!Read.exists()) {
				System.out.println("The file order.txt does not exist.");
				System.exit(0);
			}
			Scanner reading = new Scanner(Read);
			// read the contents of the first file,
			String itemId = reading.nextLine();
			String itemName = reading.nextLine();
			String itemQua = reading.nextLine();
			String itemTotalA = reading.nextLine();
			String IdC = reading.nextLine();
			String auCode = reading.nextLine();
			String status = reading.nextLine();
			String CCN = reading.nextLine();
			reading.close();
			String[] yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
			String[] yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
			System.out.println("==========YOUR ORDER DETAIL========");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("You have order: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("You have to pay the toal amount: $" + itemTotalA);
			System.out.println("Using the creadit card number: "+CCN);
			System.out.println("Your order status: " + status + "\n and the  authorization number is: " + auCode);
			System.out.println("===================================");
			JOptionPane.showMessageDialog(null, " You has viewed order !!!");
		}

	}

	}
class supplierView implements vieworder{

	@Override
	public void vieworder(String Cid) throws IOException {
		int choice = JOptionPane.showOptionDialog(null, "You want to see the delivery orders!!", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		// interpret the user's choice
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Supplier see orders details: see in the Console!!!");
			File Read = new File("order.txt");
			if (!Read.exists()) {
				System.out.println("The file order.txt does not exist.");
				System.exit(0);
			}
			Scanner reading = new Scanner(Read);
			// read the contents of the first file, 
			String itemId = reading.nextLine();
			String itemName = reading.nextLine();
			String itemQua = reading.nextLine();
			String itemTotalA = reading.nextLine();
			String IdC = reading.nextLine();
			String auCode = reading.nextLine();
			String status = reading.nextLine();
			String CCN = reading.nextLine();
			reading.close();
			String[] yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
			String[] yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
			System.out.println("The customer with the ID: "+ IdC);
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("has ordered: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("The customer order status: " + status + "\n and the authorization number is: " + auCode);
			System.out.println("=========================");
//			JOptionPane.showMessageDialog(null, " You has viewed order !!!");

		}
		
	}
	public void changeOrder() throws IOException {
		boolean inventoryAvail=true;
		File Read = new File("order.txt");
		if (!Read.exists()) {
			System.out.println("The file order.txt does not exist.");
			System.exit(0);
		}
		File invent=new File("Inventory.txt");
		Scanner inventRead= new Scanner(invent);
		int[] inventoryQuantities=new int[5];
		inventoryQuantities[0]=Integer.parseInt(inventRead.nextLine());
		inventoryQuantities[1]=Integer.parseInt(inventRead.nextLine());
		inventoryQuantities[2]=Integer.parseInt(inventRead.nextLine());
		inventoryQuantities[3]=Integer.parseInt(inventRead.nextLine());
		inventoryQuantities[4]=Integer.parseInt(inventRead.nextLine());
		Scanner reading = new Scanner(Read);
		// read the contents of the first file, 
		String itemId = reading.nextLine();
		String itemName = reading.nextLine();
		String itemQua = reading.nextLine();
		String itemTotalA = reading.nextLine();
		String IdC = reading.nextLine();
		String auCode = reading.nextLine();
		String status = reading.nextLine();
		String CCN = reading.nextLine();
		reading.close();
		if(!status.equalsIgnoreCase("ordered"))
		{
			JOptionPane.showMessageDialog(null, "Order already claimed ");
			
		}else 
		{
			
			String[] ids = itemId.substring(1, (itemId.lastIndexOf("]"))).toString().split(",");
			String[] itemQuantities = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
			for(int i=0;i<itemQuantities.length;i++) {
				itemQuantities[i]=itemQuantities[i].strip();
				ids[i]=ids[i].strip();
			}
			String[] yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
			for(int i=0;i<ids.length;i++) {
				if(Integer.parseInt(itemQuantities[i])>inventoryQuantities[Integer.parseInt(ids[i])-1]) {
					inventoryAvail=false;
					JOptionPane.showMessageDialog(null, "Need a inventory order for "+yourItemNameArray[i]);
				}else {
					inventoryQuantities[Integer.parseInt(ids[i])-1]-=Integer.parseInt(itemQuantities[i]);
				}
			}
			if(inventoryAvail) {
				status="ready";
				PrintWriter orderFile = new PrintWriter("order.txt");
				
				// store a delivery order in file
				orderFile.println(itemId);
				orderFile.println(itemName);
				orderFile.println(itemQua);
				orderFile.println(itemTotalA);
				orderFile.println(IdC);
				orderFile.println(auCode);
				orderFile.println(status);
				orderFile.println(CCN);
				orderFile.close();
				
				PrintWriter inven=new PrintWriter("Inventory.txt");
				for(int i=0;i<inventoryQuantities.length;i++) {
					inven.println(inventoryQuantities[i]);
				}
				inven.close();
				JOptionPane.showMessageDialog(null, "The items for the order have been reserved");
			}
			
		}
	}


	public void confirmShipment() throws IOException{
		File Read = new File("order.txt");
		Scanner reading = new Scanner(Read);
		// read the contents of the first file, 
		String itemId = reading.nextLine();
		String itemName = reading.nextLine();
		String itemQua = reading.nextLine();
		String itemTotalA = reading.nextLine();
		String IdC = reading.nextLine();
		String auCode = reading.nextLine();
		String status = reading.nextLine();
		String CCN = reading.nextLine();
		reading.close();
		if(status.equalsIgnoreCase("ready")) {
			status="shipped";
			vieworder(IdC);
			String[] supplier = {"Order 1","Exit"};
			int Request2 = JOptionPane.showOptionDialog(null, "Chose delivery orders!!!",
					"Hello Supplier!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
					supplier, supplier[0]);
			if(Request2==0) {
				PrintWriter orderFile = new PrintWriter("order.txt");
				
				// store a delivery order in file
				orderFile.println(itemId);
				orderFile.println(itemName);
				orderFile.println(itemQua);
				orderFile.println(itemTotalA);
				orderFile.println(IdC);
				orderFile.println(auCode);
				orderFile.println(status);
				orderFile.println(CCN);
				orderFile.close();
				JOptionPane.showMessageDialog(null, "Order has been shipped");
			}else if(Request2==1) {
				System.exit(0);
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "No orders ready to start shipping");

		}
		
	}
}
