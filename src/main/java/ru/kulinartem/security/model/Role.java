package ru.kulinartem.security.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "role")
    private String role;

    @ManyToMany (
            cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.REFRESH, CascadeType.DETACH}
            , fetch = FetchType.LAZY)
    @JoinTable (name = "users_roles"
            , joinColumns = @JoinColumn (name = "role_id")
            , inverseJoinColumns = @JoinColumn (name = "user_id")
            )
    private List<User> users;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public void addUserToRole(User user) {
        if (users.isEmpty()) {
            users = new ArrayList<>();
        } else {
            users.add(user);
        }
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id && role.equals(role1.role) && Objects.equals(users, role1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, users);
    }
}
