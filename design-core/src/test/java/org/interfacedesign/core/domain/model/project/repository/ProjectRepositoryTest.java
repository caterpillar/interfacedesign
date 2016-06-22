package org.interfacedesign.core.domain.model.project.repository;

import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Transactional
    public void testSave() {
        Project project = new Project("test project", "test description");
        projectRepository.save(project);
    }

    @Test
    @Transactional
    public void testGet() {
        Project project = new Project("test project", "test description");
        projectRepository.save(project);
        Project project1 = projectRepository.getOne(project.getId());
        assertNotNull(project1);
    }
}