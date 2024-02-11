package Repositories;

import Entities.Book;
import Entities.Borrow;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BorrowRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public void createBorrow(Borrow borrow) {
        // Verifică dacă utilizatorul a împrumutat deja cartea
        if (entityManager.createQuery("SELECT b FROM Borrow b WHERE b.borrowerName = :userName AND b.bookName = :bookName", Borrow.class)
                .setParameter("userName", borrow.getBorrowerName())
                .setParameter("bookName", borrow.getBookName())
                .getResultList().size() > 0) {
            return;
        }

        // Verifică dacă cartea este disponibilă
        if (entityManager.createQuery("SELECT b FROM Book b WHERE b.title= :bookName AND b.copiesAvailable > 0", Book.class)
                .setParameter("bookName", borrow.getBookName())
                .getResultList().size() > 0) {
            entityManager.createQuery("UPDATE Book b SET b.copiesAvailable = b.copiesAvailable - 1 WHERE b.title = :bookName")
                    .setParameter("bookName", borrow.getBookName())
                    .executeUpdate();
            // Creează o nouă împrumutare și setează detaliile
            Borrow borrowEntity = new Borrow();
            borrowEntity.setBookName(borrow.getBookName());
            borrowEntity.setBorrowerName(borrow.getBorrowerName()); // Setează numele împrumutatorului
            entityManager.persist(borrowEntity); // Persistă entitatea Borrow în baza de date

        }
    }
}
