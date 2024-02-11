package Entities;

import jakarta.persistence.*;

@Table(name = "borrowedbooks")
@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int borrowId;

    @Column(name="borrowerName")
    private String borrowerName;

    @Column(name="borrowedBook")
    private String bookName;

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Borrow(int borrowId, String borrowerName, String bookName) {
        this.borrowId = borrowId;
        this.borrowerName = borrowerName;
        this.bookName = bookName;
    }

    public Borrow() {
    }
}
