package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class UserJDBC implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;
    @Override
    public void create(User user) {
        if(!userExists(user)){
            String SQLuser = "insert into account (username, password, email_address, first_name, last_name) values (?, ?, ?, ?, ?)";
            String SQLrole = "insert into roles (username, role) values (?, ?)";
            jdbcTemplateObject.update( SQLuser, user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), user.getEmailAddress(), user.getFirstName(), user.getLastName());
            jdbcTemplateObject.update( SQLrole, user.getUsername(), "USER");
            System.out.println("Created User = " +  user.getUsername());
        }
        else System.out.println("User name already exists! = " + user.getUsername());
    }
    @Override
    public void create(String username, String password, String email, String firstName, String lastName) {
        if(!userExists(username)){
            String SQLuser = "insert into account (username, password, email_address, first_name, last_name) values (?, ?, ?, ?, ?)";
            String SQLrole = "insert into roles (username, role) values (?, ?)";
            jdbcTemplateObject.update( SQLuser, username, bCryptPasswordEncoder.encode(password), email, firstName, lastName);
            jdbcTemplateObject.update( SQLrole, username, "USER");
            System.out.println("Created User = " + username);
        }
        else System.out.println("User name already exists! = " + username);
    }
    @Override
    public boolean userExists(Integer id) {
        String SQL = "select * from account where id = ?";
        return id != null && id != 0 && validate(SQL, id);
    }
    @Override
    public boolean userExists(String username) {
        String SQL = "select * from account where username = ?";
        return username != null && validate(SQL, username);
    }
    @Override
    public boolean userExists(User user) {
        String SQL = "select * from account where username = ?";
        return user.getUsername() != null && validate(SQL, user.getUsername());
    }
    private boolean validate(String SQL, Object o){
        try {
            jdbcTemplateObject.queryForObject(SQL, new Object[]{o}, new UserMapper());
            return true;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
    @Override
    public User getUser(Integer id) {
        String SQL = "select * from account where id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new UserMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public User getUser(String username) {
        String SQL = "select * from account where username = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{username}, new UserMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer getIdByUsername(String name) {
        int userId = jdbcTemplateObject.queryForObject("select id from account where account.username = ?", Integer.class, name);
        return userId;
    }

    @Override
    public String getUsernameById(Integer id) {
        String username = jdbcTemplateObject.queryForObject("select username from account where account.id = ?", String.class, id);
        return username;
    }

    @Override
    public User getUser(User user) {
        String SQL = "select * from account where username = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{user.getUsername()}, new UserMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<User> listAllUsers() {
        String SQL = "select * from account";
        return jdbcTemplateObject.query(SQL, new UserMapper());
    }
    @Override
    public void delete(Integer id) {
        String SQL = "delete from account where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
    }
    @Override
    public void delete(String username) {
        String SQL = "delete from account where username = ?";
        jdbcTemplateObject.update(SQL, username);
        System.out.println("Deleted Record with username = " + username );
    }
    @Override
    public void updateRole(String username, Integer role){
        String SQL = "update account set user_role = ? where id = ?";
        jdbcTemplateObject.update(SQL, role, username);
        System.out.println("Updated role with username = " + username );
    }
    @Override
    public void updateInfo(String username, Date dateOfBirth, String phoneNumber, Integer gender, String nationality){
        String SQL = "update account set date_of_birth = ?, phone_number = ?, gender = ?, nationality = ? where username = ?";
        jdbcTemplateObject.update(SQL, dateOfBirth, phoneNumber, gender, nationality, username);
        System.out.println("Updated info with username = " + username );
    }
    @Override
    public void updateEverything(User user){
        String SQL = "update account set username = ?, email_address= ?, first_name = ?," +
                " last_name = ?, date_of_birth = ?, phone_number = ?, gender = ?, nationality = ?," +
                " work_status = ?, enabled = ?, fk_Team = ? where id = ?";
        jdbcTemplateObject.update(SQL, user.getUsername(), user.getEmailAddress(), user.getFirstName(),
                user.getLastName(), user.getDateOfBirth(), user.getPhoneNumber(), user.getGender(),
                user.getNationality(), user.getWorkStatus(), user.getEnabled(), user.getFkTeam(), user.getId());
        System.out.println("Updated user info with id: " + user.getId() );
    }
}