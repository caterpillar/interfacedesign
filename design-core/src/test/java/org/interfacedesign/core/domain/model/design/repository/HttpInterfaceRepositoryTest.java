package org.interfacedesign.core.domain.model.design.repository;

import org.interfacedesign.core.BaseRolesInitTest;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-7-2.
 */
public class HttpInterfaceRepositoryTest extends BaseRolesInitTest {
    @Autowired
    private HttpInterfaceRepository interfaceRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testFindByHttpRequestUrl() throws Exception {
        admin.createProject("测试项目", "用户测试接口");
        interfaceRepository.findByHttpRequestUrl("http://www.baidu.com", 1L);
    }
}