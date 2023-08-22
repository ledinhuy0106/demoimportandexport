package ra.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookId;
    private String bookName;
    private Float price;

    public Book(String bookId, String bookName, Float price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }
}
