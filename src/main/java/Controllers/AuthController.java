package Controllers;

import Dto.AuthDTO;
import Services.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class AuthController implements Serializable {

    @Inject
    private AuthService authService;
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String email;
    private String password;



    public AuthController(int id,String username, String email, String password) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AuthController() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
        } catch(ServletException ex) {
            context.addMessage(null, new FacesMessage("Login Failed with "+ex.getMessage()));
            return "error.xhtml";
        }
        AuthDTO account = authService.loginUser(username, password);
        externalContext.getSessionMap().put("userid", account.getId());
        externalContext.getSessionMap().put("username", account.getUsername());
        externalContext.getSessionMap().put("type", account.getType());
        String accountType = account.getType();
        if ("user".equals(accountType)) {
            return "indexLoggedUser.xhtml";
        } else if ("manager".equals(accountType) || "administrator".equals(accountType)) {
            return "indexLoggedAdmin.xhtml";
        } else {
            return "index.xhtml";
        }
    }

    @PermitAll
    public String register() throws IOException {
        AuthDTO account = new AuthDTO(id,username,email,password,"user");
        authService.addAccount(account);
        return "login.xhtml";
    }

    @PermitAll
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        //return "/index.xhtml";
    }
}
