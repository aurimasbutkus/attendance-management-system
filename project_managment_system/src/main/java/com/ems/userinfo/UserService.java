package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */
import java.sql.Date;
import java.util.List;

public interface UserService {

    void create(String username, String email, String password, String firstName, String lastName);
    void create(User user);

    User getUser(Integer id);
    User getUser(String username);
    User getUser(User user);
    Integer getIdByUsername(String name);
    String getUsernameById(Integer id);

    List<User> listAllUsers();

    void delete(Integer id);
    void delete(String username);

    void updateRole(String username, Integer role);
    void updateInfo(String username, Date dateOfBirth, String phoneNumber, Integer gender, String nationality);
    void updateEverything(User user);
    boolean userExists(Integer id);
    boolean userExists(String username);
    boolean userExists(User user);
}