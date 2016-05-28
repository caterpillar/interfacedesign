package org.interfacedesign.auth.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-5-25.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordAuthUserRepositoryTest {
    @Autowired
    private PasswordAuthUserRepository passwordAuthUserRepository;

    @Test
    @Transactional
    public void testSave() {
        passwordAuthUserRepository.save(new PasswordAuthUser("test3", "passw0rd"));
    }

}