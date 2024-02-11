package Controllers;

import Dto.BookDTO;
import Services.AuthService;
import Services.BookService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
@DeclareRoles({"administrator", "manager", "user"})
public class BookController implements Serializable {

    @Inject
    private BookService bookService;

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;
    private String author;
    private int copiesAvailable;

    private BookDTO selectedBook;
    private List<BookDTO> books;

    @PostConstruct
    public void init() {
        books = bookService.getAllBooks();
        selectedBook = new BookDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public BookDTO getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookDTO selectedBook) {
        this.selectedBook = selectedBook;
    }

    public BookController(int id, String name, String author, int copiesAvailable) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public BookController() {
    }

    //add book
    public String addBook() {
        bookService.addBook(selectedBook);
        books = bookService.getAllBooks();
        selectedBook = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public String startCreate(){
        return "/createBook.xhtml?faces-redirect=true";
    }

    //edit book

    public String selectBookEdit(String bookIDparam) {
        int bookID = Integer.parseInt(bookIDparam);
        selectedBook = bookService.getBookById(bookID);
        return "editBook.xhtml";
    }



    public String updateBook() {
        bookService.updateBook(selectedBook);
        books = bookService.getAllBooks();
        selectedBook = null;

        return "/index.xhtml";
    }

    //delete book

    public String deleteBook() {
        bookService.deleteBook(selectedBook.getId());
        selectedBook=null;
        return "indexLoggedAdmin.xhtml";
    }

    public String selectBookDelete(String bookIDparam){
        int bookID=Integer.parseInt(bookIDparam);
        selectedBook=bookService.getBookById(bookID);
        return "deleteBook.xhtml";
    }
}
