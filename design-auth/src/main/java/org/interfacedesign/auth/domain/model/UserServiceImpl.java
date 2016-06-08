package org.interfacedesign.auth.domain.model;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.auth.domain.model.repository.RoleRepository;
import org.interfacedesign.auth.domain.model.repository.UsersRepository;
import org.interfacedesign.base.exception.DesignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by lishaohua on 16-6-7.
 */
@Service
public class UserServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;


    public Page<Users> getUsers(String name, Pageable pageable) {
        return usersRepository.findByNameLike(name, pageable);
    }

    @Transactional
    public Page<Users> getAllUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    @Transactional
    public void addUsers(String name, String password, List<String> roles) {
        if(CollectionUtils.isEmpty(roles)) {
            throw new IllegalArgumentException("添加用户至少包含一个角色");
        }
        Users usersByName = usersRepository.findByName(name);
        if(usersByName != null) {
            throw new IllegalArgumentException("添加的用户已经存在");
        }
        Users users = new Users(name, password);
        addRoleToUser(users, roles);
    }

    @Transactional
    public void addRoleToUser(Long usersId,  List<String> roles) {
        Users users = usersRepository.findOne(usersId);
        if(users == null) {
            throw new IllegalArgumentException("需要添加角色的用户不存在");
        }
        addRoleToUser(users, roles);
    }

    @Transactional
    public void addRole(String name, String description) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("添加的角色名不能为空");
        }
        Role role = roleRepository.findByName(name);
        if(role != null) {
            logger.warn("role{}已经存在", name);
            return;
        }
        role = new Role(name, description);
        roleRepository.save(role);
    }

    @Transactional
    public void addUsersToRole(String roleName, List<Long> users) {
        if(StringUtils.isEmpty(roleName)) {
            throw new IllegalArgumentException("需要添加用户的角色不能为空");
        }
        Role roleByName = roleRepository.findByName(roleName);
        if(roleByName == null) {
            throw new IllegalArgumentException("需要添加用户的角色不存在");
        }
        List<Users> all = usersRepository.findAll(users);
        roleByName.addUsers(all);
    }

    private void addRoleToUser(Users users, List<String> roles) {
        for(String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if(role != null) {
                users.addRole(role);
            }
        }
        usersRepository.save(users);
    }

}
