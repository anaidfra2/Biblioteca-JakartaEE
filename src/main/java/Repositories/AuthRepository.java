package Repositories;

import Entities.Auth;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.List;


@DeclareRoles({"administrator", "manager", "user"})
@Stateless
public class AuthRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @PermitAll
    public Auth loginUser(String username, String password) {
        if (password == null) {
            return new Auth();
        }
       String hashedPassword = DigestUtils.sha256Hex(password);
        String jpql = "SELECT a FROM Auth a WHERE a.username = :username AND a.password = :password";
        List<Auth> accounts = entityManager.createQuery(jpql, Auth.class)
                .setParameter("username", username)
                .setParameter("password", hashedPassword)
                //.setParameter("password", password)
                .getResultList();

        if (accounts.size() > 0) {
            return accounts.get(0);
        } else {
            return new Auth();
        }
    }

    @PermitAll
    public void addAccount(Auth account) {
        account.setPassword(DigestUtils.sha256Hex(account.getPassword()));
       // account.setPassword(account.getPassword());
        entityManager.persist(account);
        String sql = "INSERT INTO grup (username, groupname) VALUES ('"+account.getUsername()+"', 'group3')";
        entityManager.createNativeQuery(sql).executeUpdate();
    }


    public AuthRepository() {
    }
}
