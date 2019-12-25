package fa.edu.cuongtv21.collectionFramework;

import java.util.Scanner;

public class Main {

	public static void showMenu() {
		System.out.println("*** BOOK MANAGEMENT SYSTEM ***");
		System.out.println("1. Add Books");
		System.out.println("2. Edit Books");
		System.out.println("3. Detail Books");
		System.out.println("4. Delete Books");
		System.out.println("5. Sort Books");
		System.out.println("6. Exit");
	}
	
	public static void main(String[] args) {
		BookControllers bookControllers = new BookControllers();
		Scanner sc = new Scanner(System.in);
		while(true) {
			showMenu();
			System.out.println("Your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1:
				bookControllers.addBook();
				break;
			case 2: 
				bookControllers.editBook();
				break;
			case 3:
				bookControllers.detaiBooks();
				break;
			case 4:
				bookControllers.deleteBook();
				break;
			case 5:
				bookControllers.sortBook();
				break;
			case 6:
				System.out.println("Exit!");
				sc.close();
				return;
			default:
				System.out.println("Choose Again!!!");
				break;
			}
		}
	}
}
