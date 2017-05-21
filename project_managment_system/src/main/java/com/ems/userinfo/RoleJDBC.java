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
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleJDBC implements RoleService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public Role getRole(Integer id) {
        String SQL = "select * from roles where id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new RoleMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public Role getRole(String username, String role) {
        String SQL = "select * from roles where username = ? AND role = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{username, role}, new RoleMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Role> listAllRoles() {
        String SQL = "select * from roles";
        return jdbcTemplateObject.query(SQL, new RoleMapper());
    }
    @Override
    public List<String> getRoles(String username) {
        String SQL = "select role from roles where username = ?";
        try {
            return jdbcTemplateObject.queryForList(SQL, new Object[]{username}, String.class);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void delete(Integer id) {
        String SQL = "delete from roles where id = ?";
        jdbcTemplateObject.update(SQL, id);
    }
    @Override
    public void delete(String username, String role) {
        String SQL = "delete from roles where username = ? AND role = ?";
        jdbcTemplateObject.update(SQL, username, role);
    }

    @Override
    public void updateEverything(Role role){
        String SQL = "update roles set username = ?, role= ? where id = ?";
        jdbcTemplateObject.update(SQL, role.getUsername(), role.getRole(), role.getId());
    }
    @Override
    public void updateEverything(Integer id, String username, String role){
        String SQL = "update roles set username = ?, role= ? where id = ?";
        jdbcTemplateObject.update(SQL, username, role,id);
    }
    @Override
    public void createRole(Role role){
        String SQL = "insert into roles (username, role) values (?, ?)";
        jdbcTemplateObject.update( SQL, role.getUsername(),role.getRole());
    }
    @Override
    public void createRole(String username, String role){
        String SQL = "insert into roles (username, role) values (?, ?)";
        jdbcTemplateObject.update( SQL, username, role);
    }
    @Override
    public boolean roleExists(String username, String role) {
        String SQL = "select * from roles where username = ? AND role = ?";
        return username != null && role != null && validate(SQL, username, role);
    }
    private boolean validate(String SQL, Object o, Object b){
        try {
            jdbcTemplateObject.queryForObject(SQL, new Object[]{o,b}, new RoleMapper());
            return true;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}