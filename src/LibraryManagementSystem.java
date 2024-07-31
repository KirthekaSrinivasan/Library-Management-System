import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Model Classes
class Book {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }
}

class Member {
    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// DAO Classes
class BookDAO {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public Book getBook(String id) {
        return books.get(id);
    }

    // Other methods like removeBook, updateBook, etc., can be added here
}

class MemberDAO {
    private Map<String, Member> members = new HashMap<>();

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    public Member getMember(String id) {
        return members.get(id);
    }

    // Other methods like removeMember, updateMember, etc., can be added here
}

// Service Class
class LibraryService {
    private BookDAO bookDAO = new BookDAO();
    private MemberDAO memberDAO = new MemberDAO();

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void addMember(Member member) {
        memberDAO.addMember(member);
    }

    public boolean issueBook(String bookId, String memberId) {
        Book book = bookDAO.getBook(bookId);
        if (book != null && !book.isIssued()) {
            book.setIssued(true);
            return true;
        }
        return false;
    }

    // Other methods like returnBook, listBooks, listMembers, etc., can be added here
}

// User Interface Class
public class LibraryManagementSystem {
    private LibraryService libraryService = new LibraryService();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Book ID, Title, Author:");
                    String bookId = scanner.nextLine();
                    String title = scanner.nextLine();
                    String author = scanner.nextLine();
                    libraryService.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    System.out.println("Enter Member ID, Name:");
                    String memberId = scanner.nextLine();
                    String name = scanner.nextLine();
                    libraryService.addMember(new Member(memberId, name));
                    break;
                case 3:
                    System.out.println("Enter Book ID, Member ID:");
                    bookId = scanner.nextLine();
                    memberId = scanner.nextLine();
                    boolean issued = libraryService.issueBook(bookId, memberId);
                    System.out.println(issued ? "Book issued" : "Issue failed");
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem().start();
    }
}
