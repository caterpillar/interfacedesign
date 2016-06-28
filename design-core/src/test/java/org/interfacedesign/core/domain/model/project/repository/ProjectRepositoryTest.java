package org.interfacedesign.core.domain.model.project.repository;

import org.interfacedesign.core.application.role.TeamManageService;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.interfacedesign.core.domain.model.role.Admin;
import org.interfacedesign.core.domain.model.role.AdminRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lishaohua on 16-6-10.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TeamManageService teamManageService;

    private Admin admin;

    @Before
    public void setUp() {
        Admin admin = new Admin("Steven", "Lee", "13062705747", "111@mail.com", "", "");
        teamManageService.addAdmin(admin);
        this.admin = admin;
    }


    @Test
    public void tetFindMyProjectByName() {
        Project project = admin.createProject("test project", "测试用项目");
        projectRepository.save(project);
        final List<Project> myProjectByName = projectRepository.findMyProjectByName("test project");
        assertNotNull(myProjectByName);
        assertNotNull(myProjectByName.get(0));
    }
}