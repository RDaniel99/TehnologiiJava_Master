package beans;

import repo.UserRepository;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
}
