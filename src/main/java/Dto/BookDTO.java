package Dto;

public class BookDTO {

    private int id;
    private String title;

    private String author;

    private int copiesAvailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public BookDTO(int id, String name, String author, int copiesAvailable) {
        this.id = id;
        this.title = name;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public BookDTO() {
    }
}
