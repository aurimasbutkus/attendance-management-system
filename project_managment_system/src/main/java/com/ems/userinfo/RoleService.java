package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */
import java.sql.Date;
import java.util.List;

public interface RoleService {

    Role getRole(Integer id);
    List<Role> listAllRoles();

    void delete(Integer id);
    void updateEverything(Role role);
}