package org.interfacedesign.auth.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-6-6.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;


    @Test
    @Transactional
    public void getUsers() {
        Users users = usersRepository.getOne(2L);
        System.out.println(users);
    }
}
