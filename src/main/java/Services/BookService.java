package Services;

import Dto.BookDTO;
import Entities.Book;
import Repositories.BookRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BookService {

    @Inject
    private BookRepository bookRepository;

    @PermitAll
    public List<BookDTO> getAllBooks() {
        return bookRepository.getAllBooks()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PermitAll
    public void addBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        bookRepository.addBook(book);
    }

    @PermitAll
    public void deleteBook(int bookId) {

        bookRepository.deleteBook(bookId);
    }

    @PermitAll
    public BookDTO getBookById(int bookId) {
        Book book = bookRepository.getProductById(bookId);
        return convertToDTO(book);
    }
    @PermitAll
    public void updateBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        bookRepository.updateBook(book);
    }

    @PermitAll
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCopiesAvailable(book.getCopiesAvailable());
        return bookDTO;
    }

    @PermitAll
    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCopiesAvailable(bookDTO.getCopiesAvailable());
        return book;
    }
}
