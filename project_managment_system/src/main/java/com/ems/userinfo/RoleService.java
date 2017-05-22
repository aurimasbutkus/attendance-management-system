package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */
import java.util.List;

public interface RoleService {

    Role getRole(Integer id);

    List<String> getRoles(String username);

    Role getRole(String username, String role);

    List<Role> listAllRoles();

    void delete(Integer id);
    void delete(String username, String role);
    void updateEverything(Role role);
    void updateEverything(Integer id, String username, String role);
    void createRole(Role role);
    void createRole(String username, String role);
    boolean roleExists(String username, String role);
}