package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJDBC implements UserDAO {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;
    @Override
    public void create(User user) {
        if(!userExists(user)){
            String SQLuser = "insert into account (username, password, email_address, first_name, last_name) values (?, ?, ?, ?, ?)";
            String SQLrole = "insert into roles (username, role) values (?, ?)";
            jdbcTemplateObject.update( SQLuser, user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), user.getEmail_address(), user.getFirst_name(), user.getLast_name());
            jdbcTemplateObject.update( SQLrole, user.getUsername(), "USER");
            System.out.println("Created User = " +  user.getUsername());
        }
        else System.out.println("User name already exists! = " + user.getUsername());
    }
    @Override
    public void create(String username, String email, String password, String first_name, String last_name) {
        if(!userExists(username)){
            String SQLuser = "insert into account (username, password, email_address, first_name, last_name) values (?, ?, ?, ?, ?)";
            String SQLrole = "insert into roles (username, role) values (?, ?)";
            jdbcTemplateObject.update( SQLuser, username, bCryptPasswordEncoder.encode(password), email, first_name, last_name);
            jdbcTemplateObject.update( SQLrole, username, "USER");
            System.out.println("Created User = " + username);
        }
        else System.out.println("User name already exists! = " + username);
    }
    @Override
    public boolean userExists(Integer user_id) {
        String SQL = "select * from account where user_id = ?";
        return user_id != null && user_id != 0 && validate(SQL, user_id);
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
    public User getUser(Integer user_id) {
        String SQL = "select * from account where user_id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{user_id}, new UserMapper());
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
    public List<User> listUsers() {
        String SQL = "select * from account";
        return jdbcTemplateObject.query(SQL, new UserMapper());
    }
    @Override
    public void delete(Integer id) {
        String SQL = "delete from account where user_id = ?";
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
        String SQL = "update account set user_role = ? where user_id = ?";
        jdbcTemplateObject.update(SQL, role, username);
        System.out.println("Updated role with username = " + username );
    }
    @Override
    public void updateInfo(String username, String date_of_birth, String phone_number, int gender, String nationality){
        String SQL = "update account set date_of_birth = ?, phone_number = ?, gender = ?, nationality = ? where username = ?";
        jdbcTemplateObject.update(SQL, date_of_birth, phone_number, gender, nationality, username);
        System.out.println("Updated info with username = " + username );
    }
    @Override
    public void updateEmployee(String username, Integer employee){
        String SQL = "update account set fk_Employee = ? where username = ?";
        jdbcTemplateObject.update(SQL, employee, username);
        System.out.println("Updated fk_employee with username = " + username );
    }
}