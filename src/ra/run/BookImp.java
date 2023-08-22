package ra.run;

import ra.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImp {
    private static final String FILE_NAME = "demo.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();

        while (true) {
            System.out.println("*************************MENU************************");
            System.out.println("1. Nhập thông tin các sách");
            System.out.println("2. In thông tin các sách ra file demo.txt");
            System.out.println("3. Đọc file demo.txt và in ra các sách có giá trong khoảng 10000 đến 20000");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc newline

            switch (choice) {
                case 1:
                    inputBooks(scanner, books);
                    break;
                case 2:
                    writeBooksToFile(books);
                    break;
                case 3:
                    List<Book> filteredBooks = readDataFromFile();
                    printBooks(filteredBooks);
                    break;
                case 4:
                    System.out.println("Kết thúc chương trình.");
                    return;
                default:
                    System.out.println("Chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    private static void inputBooks(Scanner scanner, List<Book> books) {
        System.out.print("Nhập số lượng sách: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Đọc newline

        for (int i = 0; i < count; i++) {
            System.out.println("Nhập thông tin sách thứ " + (i + 1) + ":");
            System.out.print("Mã sách: ");
            String bookId = scanner.nextLine();
            System.out.print("Tên sách: ");
            String bookName = scanner.nextLine();
            System.out.print("Giá sách: ");
            Float price = scanner.nextFloat();
            scanner.nextLine(); // Đọc newline

            Book book = new Book(bookId, bookName, price);
            books.add(book);
        }
        System.out.println("Nhập thông tin sách thành công!");
    }

    private static void writeBooksToFile(List<Book> books) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Book book : books) {
                oos.writeObject(book);
            }
            System.out.println("Ghi thông tin sách vào file thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public static List<Book> readDataFromFile() {
        List<Book> bookListRead = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Book book = (Book) ois.readObject();
                    if (book.getPrice() >= 10000 && book.getPrice() <= 20000) {
                        bookListRead.add(book);
                    }
                } catch (EOFException e) {
                    break; // Đọc đến cuối file thì thoát khỏi vòng lặp
                } catch (ClassNotFoundException | ClassCastException ex) {
                    System.err.println("Lỗi khi đọc đối tượng từ file: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        } catch (FileNotFoundException ex1) {
            System.err.println("Không tồn tại file");
            ex1.printStackTrace();
        } catch (IOException ex2) {
            System.err.println("Lỗi khi đọc file");
            ex2.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
            ex.printStackTrace();
        }

        return bookListRead;
    }

    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Không có sách nào trong khoảng giá từ 10000 đến 20000.");
            return;
        }

        System.out.println("Danh sách sách có giá từ 10000 đến 20000:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
