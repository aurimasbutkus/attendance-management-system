package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */
import java.util.List;

public interface UserDAO {

    /**
     * This is the method to be used to create
     * a record in the User table.
     */
    void create(String username, String password, String email);
    /**
     * This is the method to be used to create
     * a record in the User table.
     */
    void create(User user);
    /**
     * This is the method to be used to list down
     * a record from the User table corresponding
     * to a passed student id.
     */
    User getUser(Integer id);
    User getUser(String username);
    User getUser(User user);
    /**
     * This is the method to be used to list down
     * all the records from the User table.
     */
    List<User> listUsers();

    /**
     * This is the method to be used to delete
     * a record from the User table corresponding
     * to a passed student id.
     */
    void delete(Integer id);
    void delete(String username);

    /**
     * This is the method to be used to update
     * a record into the User table.
     */
    void updateRole(String id, Integer role);
    void updateEmployee(String id, Integer employee);

    boolean userExists(Integer user_id);
    boolean userExists(String username);
    boolean userExists(User user);
}