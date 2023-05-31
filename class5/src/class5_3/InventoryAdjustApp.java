package class5_3;

import java.io.*;
import java.util.*;

public class InventoryAdjustApp {
	private static ArrayList<Product> list = new ArrayList<Product>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// read inventory data from file
		readInventoryData("inventory.txt");

		// main loop
		while (true) {
			System.out.print("1.search \n2.add \n3.delete \n4.save \n5.exit \nEnter a number: ");
			int number = scanner.nextInt();
			switch (number) {
			case 1:
				searchItem();
				break;
			case 2:
				adjustQuantity(true);
				break;
			case 3:
				adjustQuantity(false);
				break;
			case 4:
				saveInventoryData("inventory.txt");
				break;
			case 5:
				System.out.println("Exiting program...");
				System.exit(0);
			}
		}
	}

	private static void readInventoryData(String fileName) {
		try {
            File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0].trim());
				String name = tokens[1];
				int quantity = Integer.parseInt(tokens[2].trim());
				list.add(new Product(id, name, quantity));
			}

			fileScanner.close();
			System.out.println("Data read from file to list: \n");
			for (Product p : list) {
				System.out.println(p.getId() + "," + p.getName() + "," + p.getQuantity());
			}
			System.out.print("\n");
		} catch (FileNotFoundException e) {
			System.out.println("Inventory file not found.");
		}
	}

	private static void searchItem() {
		System.out.print("Enter ID: ");
		int id = scanner.nextInt();
		boolean found = false;
		for (Product p : list) {
			if (p.getId() == (id)) {
				found = true;
				System.out.println("Quantity of" + p.getName() + ": " + p.getQuantity() + "\n");
//                break;
			}
		}
		if (!found) {
			System.out.println("Item not found in inventory.\n");
		}
		System.out.println("---------------------------------------");
	}

	private static void adjustQuantity(boolean add) {
		System.out.print("Enter ID to adjust quantity: ");
		int id = scanner.nextInt();
		boolean idFound = false;
		for (Product p : list) {
			if (p.getId() == (id)) {
				idFound = true;
				System.out.println("Current quantity of " + p.getName() + ": " + p.getQuantity()+ "\n");
				System.out.print("Enter quantity to " + (add ? "add" : "delete") + ": ");
				int quantity = scanner.nextInt();
				scanner.nextLine();
				if (add) {
					p.setQuantity(p.getQuantity() + quantity);
				} else {
					p.setQuantity(p.getQuantity() - quantity);
				}
				System.out.println("New quantity of " + p.getName() + ": " + p.getQuantity()+ "\n");
				break;
			}
		}
		
		if (!idFound) {
			System.out.println("ID not found in inventory.\n");
		}
		System.out.println("---------------------------------------");
	}

	private static void saveInventoryData(String fileName) {
		try {
			//PrintWriter writer;
			//writer = new PrintWriter(new FileWriter(fileName));
			FileWriter writer = new FileWriter(fileName);
			for (Product p : list) {
				int id = p.getId();
				int quantity = p.getQuantity();
				writer.write(id + ", " +p.getName()+ ", " + quantity + "\n");
			}
			writer.close();
			System.out.println("Inventory data saved to file: " + fileName+ "\n");
		} catch (IOException e) {
			System.out.println("Error saving inventory data to file.\n");
		}
	}
}
