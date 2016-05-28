package org.interfacedesign.auth.domain.model;

import org.interfacedesign.auth.domain.exception.AuthException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-5-25.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthUserServiceImplTest {
    @Autowired
    private AuthUserService authUserService;

    @Test
    public void testGetPasswordAuthUser() throws Exception {
        String userName = "test2";
        String password = "passw0rd";
        PasswordAuthUser passwordAuthUser = authUserService.getPasswordAuthUser(userName, password);
        assertNotNull(passwordAuthUser);
    }

    @Test
//    @Transactional
    public void testAddAuthUser() throws Exception {
        authUserService.addAuthUser("hh_lucky", "111111");
    }

    @Test(expected = AuthException.class)
    @Transactional
    public void testAddAuthUserDuplicateName() throws Exception {
        authUserService.addAuthUser("hh_lucky", "111111");
        authUserService.addAuthUser("hh_lucky", "111111");
    }


    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testAddAuthUserInvalidateStr1() throws Exception {
        authUserService.addAuthUser("hh_lu", "111111");
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testAddAuthUserInvalidateStr2() throws Exception {
        authUserService.addAuthUser("h1111111111111111", "111111");
    }

    @Test
    @Transactional
    public void testDeactivate() {
        String userName = "hh_lucky";
        authUserService.addAuthUser(userName, "111111");
        authUserService.deactivate(userName);
        PasswordAuthUser passwordAuthUser = authUserService.getPasswordAuthUser(userName);
        assertFalse(passwordAuthUser.isActive());
    }

    @Test
    @Transactional
    public void testActivate() {
        String userName = "hh_lucky";
        authUserService.addAuthUser(userName, "111111");
        authUserService.deactivate(userName);
        authUserService.activate(userName);
        PasswordAuthUser passwordAuthUser = authUserService.getPasswordAuthUser(userName);
        assertTrue(passwordAuthUser.isActive());
    }



}