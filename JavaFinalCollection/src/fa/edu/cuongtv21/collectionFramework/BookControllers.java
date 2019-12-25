package fa.edu.cuongtv21.collectionFramework;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BookControllers {

	private Map<Integer, Books> listBooks = new HashMap<Integer, Books>();
	Scanner scanner = new Scanner(System.in);
	private int id;

	// add Book
	public void addBook() {
		System.out.print("Input name Book: ");
		String nameBook = scanner.nextLine();
		System.out.print("Input price Book: ");
		float priceBook = Float.parseFloat(scanner.nextLine());
		listBooks.putIfAbsent(++id, new Books(nameBook, priceBook));
	}

	// edit Book
	public void editBook() {
		while (true) {
			System.out.print("Input id Book need to edit: ");
			int idBook = Integer.parseInt(scanner.nextLine());
			System.out.print("Input new Name Book: ");
			String newNameBook = scanner.nextLine();
			System.out.print("Input new Price Book: ");
			float newPriceBook = Float.parseFloat(scanner.nextLine());
			for (Map.Entry<Integer, Books> entry : listBooks.entrySet()) {
				if (entry.getKey().equals(idBook)) {
					entry.getValue().setNameBook(newNameBook);
					entry.getValue().setPriceBook(newPriceBook);
					return;
				} else {
					System.out.println("Input id Book again!");
				}
			}
		}
	}

	public void detaiBooks() {
		for (Map.Entry<Integer, Books> entry : listBooks.entrySet()) {
			System.out.println(
					entry.getKey() + ": " + entry.getValue().getNameBook() + ": " + entry.getValue().getPriceBook());
		}
	}

	public void deleteBook() {
		System.out.print("Input id Book need to delete: ");
		int idBook = Integer.parseInt(scanner.nextLine());
		if (listBooks.containsKey(idBook)) {
			listBooks.remove(idBook);
		}
	}

	public void sortBook() {
		Map<Integer, Books> treeMap = new TreeMap<Integer, Books>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return listBooks.get(o1).getNameBook().compareTo(listBooks.get(o2).getNameBook());
			}
		});

		treeMap.putAll(listBooks);
		for (Map.Entry<Integer, Books> tree : treeMap.entrySet()) {
			System.out.println(
					tree.getKey() + ": " + tree.getValue().getNameBook() + ": " + tree.getValue().getPriceBook());
		}
	}

}
