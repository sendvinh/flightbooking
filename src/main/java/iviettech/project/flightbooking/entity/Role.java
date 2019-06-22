/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.RoleType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleType roleName;

    @ManyToMany(mappedBy = "listRole")
    private List<User> listAccount;

    public Role() {
    }

    public Role(int id, RoleType roleName, List<User> listAccount) {
        this.id = id;
        this.roleName = roleName;
        this.listAccount = listAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }

    public List<User> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<User> listAccount) {
        this.listAccount = listAccount;
    }

}
