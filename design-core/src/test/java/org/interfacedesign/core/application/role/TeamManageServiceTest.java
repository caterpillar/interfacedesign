package org.interfacedesign.core.application.role;

import org.interfacedesign.core.domain.model.role.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-6-25.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamManageServiceTest {
    @Autowired
    private TeamManageService teamManageService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private DesignerRepository designerRepository;

    @Test
    @Transactional
    public void testAddAdmin() throws Exception {
        Admin admin = new Admin("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addAdmin(admin);
        assertNotNull(admin.getId());
    }

    @Test
    @Transactional
    public void testAddDesigner() throws Exception {
        Admin admin = new Admin("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addAdmin(admin);
        Designer designer = new Designer("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addDesigner(admin.getId(), designer);
        assertNotNull(designer.getId());
        assertNotNull(designer.getAdmin());
        assertEquals(admin, designer.getAdmin());
    }



    @Test
    public void testCreateTeam() throws Exception {

    }

    @Test
    public void testAssignTeam() throws Exception {

    }

    @Test
    public void testAddDesignerToTeam() throws Exception {

    }
}