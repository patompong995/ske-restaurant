package defult;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;

public class RestaurantManager {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<String> menuItems = new ArrayList<>();
	static ArrayList<Double> prices = new ArrayList<>();
	public static void readFile() {
		String filename = "data/menuFile.txt";
		ClassLoader loader = RestaurantManager.class.getClassLoader();

		// This is the key part: find and open the file as InputStream
		InputStream in = loader.getResourceAsStream(filename);
		if (in == null) {
			System.out.println("Could not access file " + filename);
			return;
		}

		Scanner reader = new Scanner(in);
		// do something with the data - example: print it
		while (reader.hasNextLine()) {
			try {
				String line = reader.nextLine().trim();
				if (line.charAt(0) != '#') {
					String[] cut = line.split(";");
					menuItems.add(cut[0]);
					prices.add(Double.parseDouble(cut[1]));
				}
			} catch (Exception e) {

			}
		}
//		for(String s : menuItems){
//			System.out.println(s);
//		}
//		for(double s : prices){
//			System.out.println(s);
//		}
		// Done reading file. Close it to free resources.
		reader.close();
	}
	public static String[] getMenuItems(){
		String[] array = new String[ menuItems.size() ];	
		return menuItems.toArray( array );
	}
	public static double[] getPrices(){
		double[] array = new double[ prices.size() ];
		for(int i=0;i<prices.size();i++){
			array[i]=prices.get(i);
		}
		return array;
	}
	
//	public static void main(String[] args) {
//		readFile();
//		for (String s : menuItems) {
//		System.out.println(s);
//	}
//	for(double s : prices){
//		System.out.println(s);
//	}
//	}

	public static void recordOrder(int orderNumber, int[] order, double total){
		
	}
	
}
