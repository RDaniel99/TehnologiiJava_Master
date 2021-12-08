package repo;

import models.User;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class UserRepositoryDecorator implements RepositoryBase {

    @Delegate
    @Inject
    UserRepository repo;


    @Override
    public void save(User user) {

        repo.save(user);
    }
}
