package com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "permission")
    private Long permission;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
//    private Set<User> userSet;

    public Role() {
    }

    public Role(Long roleId) {
        this.id = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public Set<User> getUserSet() {
//        return userSet;
//    }
//
//    public void setUserSet(Set<User> userSet) {
//        this.userSet = userSet;
//    }

    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }

    //    @Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(permission, role.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, permission);
    }
//    public int hashCode() {
//        return Objects.hash(id, permission, userSet);
//    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", permission=" + permission +
                '}';
    }
}
