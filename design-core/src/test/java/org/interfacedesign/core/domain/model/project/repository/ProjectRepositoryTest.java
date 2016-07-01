package org.interfacedesign.core.domain.model.project.repository;

import org.interfacedesign.core.BaseRolesInitTest;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lishaohua on 16-6-10.
 */
public class ProjectRepositoryTest extends BaseRolesInitTest {
    @Autowired
    private ProjectRepository projectRepository;


    @Test
    @Transactional
    public void tetFindMyProjectByName() {
        Project project = admin.createProject("test project", "测试用项目");
        projectRepository.save(project);
        final List<Project> myProjectByName = projectRepository.findMyProjectByName("test project");
        assertNotNull(myProjectByName);
        assertNotNull(myProjectByName.get(0));
    }
}