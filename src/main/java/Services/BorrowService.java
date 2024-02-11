package Services;

import Dto.BorrowDTO;
import Entities.Book;
import Entities.Borrow;
import Repositories.BorrowRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BorrowService {

    @Inject
    private BorrowRepository borrowRepository;

    @PersistenceContext
    private EntityManager entityManager;
    private List<BorrowDTO> borrows;

    @PermitAll
    public void borrowBook(BorrowDTO borrowDTO) {
        Borrow borrow = convertToEntity(borrowDTO);
        borrowRepository.createBorrow(borrow);
    }

    private Borrow convertToEntity(BorrowDTO borrowDTO) {
        Borrow borrow = new Borrow();
        borrow.setBorrowId(borrowDTO.getBorrowId());
        borrow.setBorrowerName(borrowDTO.getBorrowerName());
        borrow.setBookName(borrowDTO.getBookName());
        return borrow;
    }


}
