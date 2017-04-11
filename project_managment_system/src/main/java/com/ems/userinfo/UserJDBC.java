package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJDBC implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void create(User user) {
        if(!userExists(user)){
            String SQL = "insert into account (username, password, email_address, user_role) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, user.getUsername(), user.getPassword(), user.getEmail_address(), user.getUser_role());
            System.out.println("Created User = " +  user.getUsername());
        }
        else System.out.println("User name already exists! = " + user.getUsername());
    }
    @Override
    public void create(String username, String email, String password) {
        if(!userExists(username)){
            String SQL = "insert into account (username, password, email_address, user_role) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, username, password, email, 3);
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
    public void updateEmployee(String username, Integer employee){
        String SQL = "update account set fk_Employee = ? where username = ?";
        jdbcTemplateObject.update(SQL, employee, username);
        System.out.println("Updated fk_employee with username = " + username );
    }
}