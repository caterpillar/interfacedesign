package org.interfacedesign.core.application.design.impl;

import org.interfacedesign.core.BaseTest;
import org.interfacedesign.core.application.design.ProjectManageService;
import org.interfacedesign.core.application.role.BaseAdminInitTest;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.role.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-6-28.
 */
public class ProjectManageServiceImplTest extends BaseAdminInitTest {
    @Autowired
    private ProjectManageService projectManageService;

    @Test
    @Transactional
    public void testCreateProject() throws Exception {
        Project project = projectManageService.createProject(admin.getId(), "测试项目", "测试项目aaa");
        assertNotNull(project);
        assertNotNull(project.getId());
        assertEquals("测试项目", project.getName());
        assertEquals("测试项目aaa", project.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testCreateDuplicateNameProject() {
        Project project1 = projectManageService.createProject(admin.getId(), "测试项目", "测试项目aaa");
        Project project2 = projectManageService.createProject(admin.getId(), "测试项目", "测试项目bbb");
    }

    @Test
    @Transactional
    public void testModifyProject() throws Exception {
        Project project1 = projectManageService.createProject(admin.getId(), "测试项目", "测试项目aaa");
        Project project2 = projectManageService.modifyProject(admin.getId(), project1.getId(), "测试项目2", "测试项目bbb");
        assertEquals("测试项目2", project2.getName());
        assertEquals("测试项目bbb", project2.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testModifyDuplicateNameProject() throws Exception {
        Project project1 = projectManageService.createProject(admin.getId(), "测试项目1", "测试项目aaa");
        Project project2 = projectManageService.createProject(admin.getId(), "测试项目2", "测试项目aaa");
        Project project3 = projectManageService.modifyProject(admin.getId(), project1.getId(), "测试项目2", "测试项目bbb");
    }

    @Test()
    @Transactional
    public void testModifyProjectModifyDescription() throws Exception {
        Project project1 = projectManageService.createProject(admin.getId(), "测试项目1", "测试项目aaa");
        Project project2 = projectManageService.createProject(admin.getId(), "测试项目2", "测试项目aaa");
        Project project3 = projectManageService.modifyProject(admin.getId(), project1.getId(), "测试项目1", "测试项目bbb aaa");
    }
}