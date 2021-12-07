package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(query = "Select u from User u", name = "User.findAll"),
    @NamedQuery(query = "Select u from User u where u.name = :username and u.password = :password", name = "User.findLogin")
})
public class User implements Serializable {

    public enum UserType {
        BASIC,
        REVIEWER,
        AUTHOR,
        ADMIN;

        public boolean isAdmin() {
            return this == ADMIN;
        }

        public boolean isAuthor() {
            return this == AUTHOR;
        }

        public boolean isReviewer() {
            return this == REVIEWER;
        }

        public boolean isBasic() {
            return this == BASIC;
        }
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rights")
    @Enumerated(EnumType.STRING)
    private UserType rights;

    @Column(name = "password")
    private String password;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getRights() {
        return rights;
    }

    public void setRights(UserType rights) {
        this.rights = rights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
