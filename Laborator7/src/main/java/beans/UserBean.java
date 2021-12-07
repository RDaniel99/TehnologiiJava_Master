package beans;

import models.User;
import repo.UserRepository;
import utils.SessionUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserBean implements Serializable {

    @Inject
    UserRepository repo;

    public UserBean() {}
    public UserBean(UserRepository repo) {
        this.repo = repo;
    }

    public boolean validate(String username, String password) {

        return repo.validate(username, password);
    }

    public User.UserType getUserTypeForUserInSession() {

        String username = SessionUtils.getUsername();
        if(username == null || username.isEmpty()) return null;
        return repo.getTypeOfUser(username);
    }

    public void save(CreateUserInput input) {

        User user = new User();
        user.setName(input.getName());
        user.setPassword(input.getPassword());
        user.setRights(input.getRights());

        repo.save(user);

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "User Created",
                        "The user with the above credentials was created"));
    }
}
