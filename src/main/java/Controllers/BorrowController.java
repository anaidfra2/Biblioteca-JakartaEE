package Controllers;

import Dto.BorrowDTO;
import Services.BorrowService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
@DeclareRoles({"administrator", "manager", "user"})
public class BorrowController {
    @Inject
    private BorrowService borrowService;

    private List<BorrowDTO> borrows;


    public String borrowBook(String bookName) {
        int userID = (int) jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        String userName = (String) jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");

        BorrowDTO borrow = new BorrowDTO();
        borrow.setBookName(bookName);
        borrow.setBorrowerName(userName);
        borrowService.borrowBook(borrow);
        return "indexLoggedUser.xhtml?faces-redirect=true";
    }
}
