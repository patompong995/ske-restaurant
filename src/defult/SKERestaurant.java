package defult;

import java.util.Scanner;
import java.io.IOException;

public class SKERestaurant {
	static final Scanner scan = new Scanner(System.in);
	public static String[] menu;
	public static double[] prices;
	public static int[] order;
	static RestaurantManager mn;
	public static int totalQty = 0;
	public static double totalPrice = 0.0;

	public static void main(String[] args) {
		topPage();
		printDemand();
		printMenu();
		getCommand();
	}

	public static void topPage() {
		System.out.println("+-------------------------------------------------------+");
		System.out.println("|--------------****************************-------------|");
		System.out.println("|----+++++++++  Welcome to SKE Restaurant.  ++++++++----|");
		System.out.println("|--------------****************************-------------|");
		System.out.println("+-------------------------------------------------------+");
	}

	public static void printDemand() {
		System.out.print("Key :\t G ==> Get Order.\n");
		System.out.println("\t R --> Check Order.");
		System.out.println("\t O --> Checkout.");
		System.out.println("\t X --> Exist.");
	}

	public static void printMenu() {
		mn.readFile();
		menu = mn.getMenuItems();
		prices = mn.getPrices();
		order = new int[menu.length];

		System.out.println("========================= Menu =========================");
		for (int i = 0; i < menu.length; i++) {
			System.out.printf("\t%2d. ", i + 1);
			for (int j = i; j <= i; j++) {
				System.out.printf("%-30s %7.2f Baht.\n", menu[j], prices[j]);
			}
		}
		System.out.println("                 Key -1 if Finish Order.               ");
	}

	public static void getCommand() {
		System.out.print("Key your command : ");
		String command = scan.next();
		command = command.toLowerCase();
		switch (command) {
		case "g":
			getoreditOrder();
			break;
		case "r":
			reviewOrder();
			break;
		case "o":
			checkOut();
			break;
		case "x":
			System.out.println("Bye Bye.");
			System.exit(0);
		default:
			System.out.println("Can you key agian ?");
			getCommand();
			break;
		}

	}

	public static void getoreditOrder() {
		while (true) {
			System.out.print("Select your menu : ");
			int target = scan.nextInt();
			if (target == -1) {
				getCommand();
				break;
			}
			System.out.print("Enter your Quantity : ");
			int qtt = scan.nextInt();
			order[target - 1] += qtt;
		}
	}

	public static void reviewOrder() {
		System.out.println("+---------  Menu  ---------+--  Qty  --+---  Price  ---+");
		for (int i = 0; i < order.length; i++) {
			if (order[i] != 0) {
				System.out.printf("|%-26s|%9d  | %12.2f  | \n", menu[i], order[i], prices[i] * order[i]);
				totalQty += order[i];
				totalPrice += order[i] * prices[i];
			}
		}
		System.out.println("+--------------------------+-----------+---------------+");
		System.out.printf("|%-26s|%9d  | %12.2f  | \n", "total", totalQty, totalPrice);
		System.out.println("+--------------------------+-----------+---------------+");
		getCommand();
	}

	public static void printBill(double cash) {
		System.out.println("+---------  Menu  ---------+--  Qty  --+---  Price  ---+");
		for (int i = 0; i < order.length; i++) {
			if (order[i] != 0) {
				System.out.printf("|%-26s|%9d  | %12.2f  | \n", menu[i], order[i], prices[i] * order[i]);
			}
		}
		System.out.println("+--------------------------+-----------+---------------+");
		System.out.printf("|%-26s|%9d  | %12.2f  | \n", "Total", totalQty, totalPrice);
		System.out.printf("|%-26s|%-9s  | %12.2f  | \n", "Cash", " ", cash);
		System.out.println("+--------------------------+-----------+---------------+");
		System.out.printf("|%-26s|%-9s  | %12.2f  | \n", "Change", " ", cash - totalPrice);
		System.out.println("+--------------------------+-----------+---------------+");
		System.out.println("THANK YOU SO MUCH");
	}

	public static void checkOut() {
		System.out.print("Give Me Your Money!!! : ");
		double cash = scan.nextDouble();
		do {
			if (cash < totalPrice) {
				System.out.print("Give Me More!!! : ");
				cash += scan.nextDouble();
			}
		} while (cash < totalPrice);
		printBill(cash);
	}

	private SKERestaurant(RestaurantManager mn) {
		this.mn = mn;
	}

}