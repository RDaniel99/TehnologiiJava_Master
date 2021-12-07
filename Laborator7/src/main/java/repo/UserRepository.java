package repo;

import logger.Logger;
import models.User;


import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named
@Stateless
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public UserRepository() {}

    @Interceptors(Logger.class)
    public void save(User user) {

    }

    public boolean validate(String username, String password) {

        Query query = em.createNamedQuery("User.findLogin");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return !query.getResultList().isEmpty();
    }
}
