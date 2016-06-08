package org.interfacedesign.auth.domain.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lishaohua on 16-6-7.
 */
public interface UsersService {
    Page<Users> getUsers(String name, Pageable pageable);
    Page<Users> getAllUsers(Pageable pageable);
    void addUsers(String name, String password, List<String> roles);
    void addRoleToUser(Long usersId, List<String> roles);
    void addRole(String name, String description);
    void addUsersToRole(String roleName, List<Long> users);
}
