package com.ems.userinfo;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Marius on 2017-04-08.
 */

@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Length(min = 5, message = "*Your username must have at least 5 characters")
    @NotEmpty(message = "*Please provide your username")
    private String username;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email_address;
    @NotEmpty(message = "*Please provide your first name")
    private String first_name;
    @NotEmpty(message = "*Please provide your last name")
    private String last_name;
    @Nullable
    private String gender;
    @Nullable
    private String nationality;
    @Nullable
    private String date_of_birth;
    @Nullable
    private String phone_number;
    @Nullable
    private String work_status;
    @Column(name = "enabled")
    private int enabled;
    @Nullable
    private Integer fk_Team;

    public User() {
    }

    public User(String username, String email, String password, String first_name, String last_name) {
        this.username = username;
        this.email_address = email;
        this.password = password;
        this.first_name = first_name;;
        this.last_name = last_name;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email_address = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Integer getFk_Team() {
        return fk_Team;
    }

    public void setFk_Team(Integer fk_Team) {
        this.fk_Team = fk_Team;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", username='" + username + '\'' +
                ", email_address='" + email_address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
