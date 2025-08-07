package Intern;
import java.util.*;

// 1. Book class
class Book {
    private int id;
    private String title;
    private String author;
    private String edition;
    private boolean isIssued;

    public Book(int id, String title, String author, String edition) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getEdition() { return edition; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }

    @Override
    public String toString() {
        return String.format("%-10d %-25s %-15s %-10s %-10s", id, title, author, edition, isIssued ? "Issued" : "Available");
    }
}

// 2. User class
class User {
    private int userId;
    private String name;
    private List<Integer> issuedBookIds;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBookIds = new ArrayList<>();
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public List<Integer> getIssuedBookIds() { return issuedBookIds; }

    public void issueBook(int bookId) {
        issuedBookIds.add(bookId);
    }

    public void returnBook(int bookId) {
        issuedBookIds.remove(Integer.valueOf(bookId));
    }

    @Override
    public String toString() {
        return userId + " - " + name + " | Issued Books: " + issuedBookIds;
    }
}

// 3. Library class
class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void issueBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            System.out.println("Invalid Book or User ID.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.setIssued(true);
            user.issueBook(bookId);
            System.out.println("Book issued successfully.");
        }
    }

    public void returnBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            System.out.println("Invalid Book or User ID.");
            return;
        }

        if (book.isIssued()) {
            book.setIssued(false);
            user.returnBook(bookId);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book was not issued.");
        }
    }

    public void displayBooks() {
        System.out.printf("%-10s %-25s %-15s %-10s %-10s%n", "ID", "Title", "Author", "Edition", "Status");
        for (Book b : books.values()) {
            System.out.println(b);
        }
    }

    public void displayUsers() {
        for (User u : users.values()) {
            System.out.println(u);
        }
    }
}

// 4. Main function
public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();

        // Sample books and users
        library.addBook(new Book(101, "The Alchemist", "Paulo Coelho", "1st"));
        library.addBook(new Book(102, "Atomic Habits", "James Clear", "2nd"));
        library.addUser(new User(1, "Alice"));
        library.addUser(new User(2, "Bob"));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---- Library Menu ----");
            System.out.println("1. Display Books");
            System.out.println("2. Display Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    library.issueBook(issueBookId, issueUserId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUserId = sc.nextInt();
                    library.returnBook(returnBookId, returnUserId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
