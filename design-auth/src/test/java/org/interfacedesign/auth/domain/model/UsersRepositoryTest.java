package org.interfacedesign.auth.domain.model;

import org.interfacedesign.auth.domain.model.repository.RoleRepository;
import org.interfacedesign.auth.domain.model.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-6-6.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transactional
    public void saveUsers() {
        Users users = new Users("lishaohua");
        usersRepository.save(users);

        Users usersFromDb = usersRepository.getOne(users.getId());

        assertNotNull(usersFromDb);
        assertSame(users, usersFromDb);
    }

    @Test
    @Transactional
    public void saveUsersAndRole() {
        Users users = new Users("lishaohua");
        Role designer = roleRepository.findRoleByName("DESIGNER");
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(designer);
        users.addRole(designer);
        usersRepository.save(users);

        Users usersFromDb = usersRepository.getOne(users.getId());

        assertNotNull(usersFromDb);
        assertSame(users, usersFromDb);
        assertNotNull(users.getRoles());
    }




    @Test
    @Transactional
    public void getUsers() {
        Users users = usersRepository.getOne(2L);
        System.out.println(users);
    }
}
