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
    public List<Role> listAllRoles() {
        String SQL = "select * from roles";
        return jdbcTemplateObject.query(SQL, new RoleMapper());
    }
    @Override
    public void delete(Integer id) {
        String SQL = "delete from roles where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted role with ID = " + id );
    }

    @Override
    public void updateEverything(Role role){
        String SQL = "update roles set username = ?, role= ? where id = ?";
        jdbcTemplateObject.update(SQL, role.getUsername(), role.getRole(), role.getId());
        System.out.println("Updated user info with id: " + role.getId() );
    }
}