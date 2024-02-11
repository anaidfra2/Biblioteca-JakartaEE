package Services;

import Dto.AuthDTO;
import Entities.Auth;
import Repositories.AuthRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class AuthService {

    @Inject
    private AuthRepository accountRepository;

    @PermitAll
    public AuthDTO loginUser(String username, String password) {
        Auth account = accountRepository.loginUser(username, password);
        return convertToDTO(account);
    }

    @PermitAll
    public void addAccount(AuthDTO accountDTO) {
        Auth account = convertToEntity(accountDTO);
        accountRepository.addAccount(account);
    }

    @PermitAll
    private AuthDTO convertToDTO(Auth account) {
        AuthDTO accountDTO = new AuthDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(account.getPassword());
        //accountDTO.setBorrowedBooks(account.getBorrowedBooks());
        accountDTO.setType(account.getType());
        return accountDTO;
    }

    @PermitAll
    private Auth convertToEntity(AuthDTO accountDTO) {
        Auth account = new Auth();
        account.setId(accountDTO.getId());
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        //account.setBorrowedBooks(accountDTO.getBorrowedBooks());
        account.setType(accountDTO.getType());
        return account;
    }
}
