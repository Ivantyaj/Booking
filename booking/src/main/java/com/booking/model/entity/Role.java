package com.booking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission")
    private Long permission;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> userSet;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(permission, role.permission) &&
                Objects.equals(userSet, role.userSet);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, permission, userSet);
//    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", permission=" + permission +
                ", userSet=" + userSet +
                '}';
    }
}
