package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
public class Book extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookTitle;
    private double price;

    @ManyToOne
    private Bookstore bookstore;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Buyer buyer;

    public Book() {

    }

    public Book(String bookTitle, double price, Bookstore bookstore, Author author) {
        this.bookTitle = bookTitle;
        this.price = price;
        this.bookstore = bookstore;
        this.author = author;
    }

    //region Getter & Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bookstore getBookstore() {
        return bookstore;
    }

    public void setBookstore(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    //endregion


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", price=" + price +
                ", bookstore=" + bookstore +
                ", author=" + author +
                '}';
    }
}
