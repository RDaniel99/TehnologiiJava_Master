package beans;

import models.User;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name = "createUserInput")
@ViewScoped
public class CreateUserInput implements Serializable {

    @Inject
    private UserBean bean;

    private String name;
    private User.UserType rights;
    private String password;

    public CreateUserInput() {}
    public CreateUserInput(UserBean bean) {
        this.bean = bean;
    }

    public UserBean getBean() {
        return bean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User.UserType getRights() {
        return rights;
    }

    public void setRights(User.UserType rights) {
        this.rights = rights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
