package com.ems.userinfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Marius on 2017-04-08.
 */


@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    @NotNull
    private String username;
    @NotNull
    private String email_address;
    @NotNull
    private String password;
    private Integer user_role;
    private Integer fk_Employee;

    public User() {
        this.user_role = 3;
        this.fk_Employee = 0;
    }

    public User(String username, String email, String password, Integer role, Integer employee) {
        this.username = username;
        this.email_address = email;
        this.password = password;
        this.user_role = role;
        this.fk_Employee = employee;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email_address = email;
        this.password = password;
        this.user_role = 3;
        this.fk_Employee = 0;
    }

    public Integer getUser_id() {
        return user_id;
    }

    void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }

    public Integer getFk_Employee() {
        return fk_Employee;
    }

    public void setFk_Employee(Integer fk_Employee) {
        this.fk_Employee = fk_Employee;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email_address='" + email_address + '\'' +
                ", password='" + password + '\'' +
                ", user_role=" + user_role +
                ", fk_Employee=" + fk_Employee +
                '}';
    }
}
