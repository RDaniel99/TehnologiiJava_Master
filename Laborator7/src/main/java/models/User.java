package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(query = "Select u from User u", name = "User.findAll"),
    @NamedQuery(query = "Select u from User u where u.name = :username and u.password = :password", name = "User.findLogin"),
    @NamedQuery(query = "Select u from User u where u.name = :username", name = "User.findByName")
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

        public boolean canPublish() {return this == ADMIN || this == AUTHOR;}
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Column(name = "rights")
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserType rights;

    @Column(name = "password")
    @NotNull
    @Size(max = 50, message = "Password must be at most 50 characters")
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
