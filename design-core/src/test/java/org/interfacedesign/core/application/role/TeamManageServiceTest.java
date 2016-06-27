package org.interfacedesign.core.application.role;

import org.interfacedesign.core.domain.model.role.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.util.calendar.CalendarUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @Transactional
    public void testCreateTeam() throws Exception {
        Admin admin = new Admin("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addAdmin(admin);
        Long adminId = admin.getId();
        assertNotNull(adminId);
        final Team team = teamManageService.createTeam(adminId, "第一个设计小组", "这是一个测试数据");
        assertNotNull(team);
        assertEquals("第一个设计小组", team.getName());
        assertEquals("这是一个测试数据", team.getDescription());
        assertEquals(admin, team.getAdmin());
        assertEquals(team, team.getAdmin().getTeams().iterator().next());
    }

    @Test
    @Transactional
    public void testAssignTeam() throws Exception {
        Designer designer = new Designer("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        designerRepository.save(designer);
        Admin admin = new Admin("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addAdmin(admin);
        Long adminId = admin.getId();
        assertNotNull(adminId);
        Team team = teamManageService.createTeam(adminId, "第一个设计小组", "这是一个测试数据");
        teamManageService.assignTeam(designer.getId(), team.getId());
        Designer designer2 = designerRepository.findOne(designer.getId());
        assertNotNull(designer2.getTeam());
    }

    @Test
    @Transactional
    public void testAddDesignerToTeam() throws Exception {
        Admin admin = new Admin("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addAdmin(admin);
        Long adminId = admin.getId();
        assertNotNull(adminId);
        Team team = teamManageService.createTeam(adminId, "第一个设计小组", "这是一个测试数据");
        admin.addDesigner(new Designer("李", "少华", "13062705748", "lishaohua@126.com", "华华", "http://"));
        adminRepository.save(admin);
        Admin admin1 = adminRepository.findOne(adminId);
        final Collection<Designer> designers = admin1.getDesigners();
        List<Long> designersId = new ArrayList<Long>();
        for(Designer des : designers) {
            designersId.add(des.getId());
        }
        teamManageService.addDesignerToTeam(designersId, team.getId());
        for(Designer des : admin1.getDesigners()) {
            final Designer one = designerRepository.findOne(des.getId());
            assertNotNull(one.getTeam());
        }
    }

    @Test
    @Transactional
    public void testTeam() {
        Admin admin = new Admin("李2", "少华2", "13062705748", "lishaohua@126.com", "华华", "http://");
        teamManageService.addAdmin(admin);
        Long adminId = admin.getId();
        assertNotNull(adminId);
        Team team = teamManageService.createTeam(adminId, "第一个设计小组2", "这是一个测试数据2");
        teamRepository.save(team);
        Designer designer = new Designer("李2", "少华2", "13062705748", "lishaohua@126.com", "华华", "http://");
        designer.assignTeam(team);
        admin.addDesigner(designer);
        designerRepository.save(designer);
    }
}