package org.interfacedesign.core.domain.model.role;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.crypto.Des;

import java.util.Collection;

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
//    @Transactional
    public void testAddDesigner() throws Exception {
        Admin admin = new Admin("Steven", "Lee", "13062705747", "lishaohua@123.com", "hhlucky", "http:///");
        admin.addDesigner(new Designer("Steven", "Lee", "13062705747", "lishaohua@123.com", "hhlucky", "http:///"));
        admin.addDesigner(new Designer("Steven2", "Lee2", "13062705747", "lishaohua@123.com", "hhlucky", "http:///"));
        adminRepository.save(admin);
        assertNotNull(admin.getId());
    }

    @Test
//    @Transactional
    public void testDeleteDesigner() throws Exception {
        Admin admin = new Admin("Steven", "Lee", "13062705747", "lishaohua@123.com", "hhlucky", "http:///");
        Designer designer = new Designer("Steven", "Lee", "13062705747", "lishaohua@123.com", "hhlucky", "http:///");
        admin.addDesigner(designer);
        adminRepository.save(admin);
        assertNotNull(designer.getId());
        assertNotNull(designer.getVersion());
        admin.deleteDesigner(designer);
        adminRepository.save(admin);
        adminRepository.save(admin);
    }

    @Test
    @Transactional
    public void testDeleteDesigner2() throws Exception {
        Admin admin = adminRepository.findOne(1L);
        Collection<Designer> designers = admin.getDesigners();
        admin.deleteDesigner(designers.iterator().next());
        adminRepository.delete(admin);
    }
}