package org.interfacedesign.core.domain.model.role;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-6-22.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testAddDesigner() throws Exception {
        Admin admin = new Admin("Steven", "Lee", "13062705747", "lishaohua@123.com", "hhlucky", "http:///");
        admin.addDesigner(new Designer("Steven", "Lee", "13062705747", "lishaohua@123.com", "hhlucky", "http:///"));
        admin.addDesigner(new Designer("Steven2", "Lee2", "13062705747", "lishaohua@123.com", "hhlucky", "http:///"));
        adminRepository.save(admin);
    }
}