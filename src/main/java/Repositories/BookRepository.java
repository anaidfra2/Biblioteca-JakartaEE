package Repositories;

import Entities.Book;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> getAllBooks() {
        return entityManager.createQuery("SELECT p FROM Book p", Book.class)
                .getResultList();
    }

    public void addBook(Book book) {

        entityManager.persist(book);
    }

    @PermitAll
    public void deleteBook(int bookId) {

        Book book = entityManager.find(Book.class, bookId);
        if (book != null) {

            entityManager.remove(book);
        }
    }

    public Book getProductById(int productId) {

        return entityManager.find(Book.class, productId);
    }

    public void updateBook(Book book) {

        entityManager.merge(book);
    }
}
