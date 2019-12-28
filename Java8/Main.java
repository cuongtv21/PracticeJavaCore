package fa.edu.javacore.java8;

import static java.util.function.Predicate.isEqual;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {

    private static Author a = new Author("Robert C. Martin");
    private static Author b = new Author("Benjamin J. Evans");
    private static Author c = new Author("Michael Caughey");
    private static List<Book> myBooks = Arrays.asList(
            new Book(a, "Clean Code", 466, 18, 5, 0),
            new Book(a, "The Clean Coder", 256, 15, 5, 0),
            new Book(b, "The Well-Grounded Java Developer", 462, 31, 2, 21),
            new Book(c, "Bitcoin Step by Step", 125, 3, 1, 8)
    );

    public static void main(String[] args) {
        creatingAndUsingAStream();
        functionalInterface();
        methodReferences();
        intermediateVsTerminal();
        consumingOperations();
        statefulIntermediateOperations();
        shortCircuitingOperations();
        collectorExamples();
        joiningCollector();
    }

    private static void creatingAndUsingAStream() {
        System.out.println("------ Creating and using a Stream ------");

        Stream<Book> books = Main.myBooks.stream();
        Stream<Book> goodBooks = books.filter(b -> b.getStarRating() > 3);
        goodBooks.forEach(b -> System.out.println(b.toString()));
    }

    private static void functionalInterface() {
        System.out.println("\n------ Functional Interface ------");

        Consumer<Book> reduceRankForBadAuthors = (Book b) -> {
            if (b.getStarRating() < 2) b.getAuthor().addRank(-1);
        };
        myBooks.stream().forEach(reduceRankForBadAuthors);
        myBooks.stream().forEach(b -> b.setEstimatedReadingTime(90 * b.getPages()));

        System.out.println(myBooks);
    }

    private static void methodReferences() {
        System.out.println("\n------ Method References ------");

        myBooks.stream().forEach(b -> b.fixSpellingErrors());
        myBooks.stream().forEach(Book::fixSpellingErrors); // instance method

        myBooks.stream().forEach(b -> System.out.println(b.toString()));
        myBooks.stream().forEach(System.out::println); // expression

        Stream<ISBN> isbns1 = myBooks.stream().map(b -> new ISBN(b));
        Stream<ISBN> isbns2 = myBooks.stream().map(ISBN::new); // constructor

        isbns1.forEach(Main::prettyPrint); // static method in own class
        System.out.println();
        isbns2.forEach(Main::prettyPrint);
        System.out.println();
    }

    private static void prettyPrint(Object o) {
        System.out.print(o + " ");
    }

    private static void intermediateVsTerminal() {
        System.out.println("\n------ Intermediate vs. Terminal ------");

        double totalPrice = myBooks.stream().mapToDouble(Book::getPrice).reduce(0.0, (p1, p2) -> p1 + p2);
        System.out.println("Total price: " + totalPrice);
    }

    private static void consumingOperations() {
        System.out.println("\n------ Terminal = Consuming  Operations ------");

        try {
            DoubleStream prices = myBooks.stream().mapToDouble(Book::getPrice);
            prices.forEach(p -> System.out.println("Price: " + p));
        } catch (IllegalStateException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void statefulIntermediateOperations() {
        System.out.println("\n------ Stateful Intermediate Operations ------");

        myBooks.stream().map(Book::getAuthor).distinct().forEach(System.out::println);
    }

    private static void shortCircuitingOperations() {
        System.out.println("\n------ Short-Circuiting Operations ------");

        Author rp = new Author("Rosamunde Pilcher");
        boolean phew = myBooks.stream()
                .map(Book::getAuthor)
                .noneMatch(isEqual(rp));
        System.out.println("Am I safe? " + phew);
    }

    private static void collectorExamples() {
        System.out.println("\n------ Collector Examples ------");

        List<Author> authors = myBooks.stream().map(Book::getAuthor).collect(Collectors.toList());
        System.out.println("Authors: " + authors);

        double averagePages = myBooks.stream().collect(Collectors.averagingInt(Book::getPages));
        System.out.println("Average pages per book: " + averagePages);
    }

    private static void joiningCollector() {
        System.out.println("\n------ Joining Collector ------");

        String titleList = myBooks.stream().map(Book::getTitle).reduce("", (t1, t2) -> t1 + t2);
        System.out.println("Title List:\n" + titleList);

        titleList = myBooks.stream().map(Book::getTitle).reduce("", (t1, t2) -> t1 + "\n" + t2);
        System.out.println("Title List:\n" + titleList);

        titleList = myBooks.stream().map(Book::getTitle).collect(Collectors.joining("\n"));
        System.out.println("Title List:\n" + titleList);
    }

}
