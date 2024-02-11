package Dto;

public class BorrowDTO {

    private int borrowId;

    private String borrowerName;

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

    public BorrowDTO(int borrowId, String borrowerName, String bookName) {
        this.borrowId = borrowId;
        this.borrowerName = borrowerName;
        this.bookName = bookName;
    }

    public BorrowDTO() {
    }
}
