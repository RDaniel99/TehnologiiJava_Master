package repo;

import logger.Logger;
import models.User;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Stateless
@LocalBean
public class UserRepository implements RepositoryBase {

    @PersistenceContext
    private EntityManager em;

    public UserRepository() {}

    @Transactional
    @Interceptors(Logger.class)
    @Override
    public void save(User user) {

        em.persist(user);
/*        Query query = em.createNativeQuery("INSERT INTO users(name, password, rights) VALUES (?, ?, ?)");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getRights().toString());

        query.executeUpdate();*/
    }

    public boolean validate(String username, String password) {

        Query query = em.createNamedQuery("User.findLogin");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return !query.getResultList().isEmpty();
    }

    public User.UserType getTypeOfUser(String username) {

        Query query = em.createNamedQuery("User.findByName");
        query.setParameter("username", username);

        List<User> users = query.getResultList();

        if(users.isEmpty()) return null;
        if(users.get(0) == null) return null;

        return users.get(0).getRights();
    }
}
