package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */
import java.sql.Date;
import java.util.List;

public interface UserDAO {

    /**
     * This is the method to be used to create
     * a record in the User table.
     */
    void create(String username, String email, String password, String first_name, String last_name);
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
    Integer getIdByUsername(String name);
    String getUsernameById(Integer id);
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
    void updateRole(String username, Integer role);
    void updateInfo(String username, Date date_of_birth, String phone_number, Integer gender, String nationality);
//    void updateEverything(Integer id, String username, String email, String password, String first_name, String last_name,
//                          String date_of_birth, String phone_number, Integer gender, String nationality, String work_status,
//                          Integer enabled, Integer fk_Team);
    void updateEverything(User user);
    boolean userExists(Integer user_id);
    boolean userExists(String username);
    boolean userExists(User user);
}