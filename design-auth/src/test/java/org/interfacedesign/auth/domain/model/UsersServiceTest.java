package org.interfacedesign.auth.domain.model;

import junit.framework.TestCase;
import org.interfacedesign.auth.domain.model.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by lishaohua on 16-6-7.
 *
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void testGetAllUsers() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<Users> users = usersService.getAllUsers(pageable);
        assertNotNull(users);
    }

    @Test
    public void testGetUsers() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<Users> users = usersService.getUsers("%h%", pageable);
        assertNotNull(users);
    }

    public void testAddUsers() throws Exception {

    }

    public void testAddRoleToUser() throws Exception {

    }

    public void testAddRole() throws Exception {

    }

    public void testAddUsersToRole() throws Exception {

    }
}