package org.interfacedesign.core.application.design.impl;

import org.interfacedesign.core.BaseRolesInitTest;
import org.interfacedesign.core.application.design.InterfaceDesignService;
import org.interfacedesign.core.application.design.ProjectManageService;
import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lishaohua on 16-7-1.
 */
public class InterfaceDesignServiceImplTest extends BaseRolesInitTest {
    @Autowired
    private InterfaceDesignService interfaceDesignService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectManageService projectManageService;

    @Test
    @Transactional
    public void testCreateDefaultSimpleInterface() throws Exception {
        Project project = projectManageService.createProject(admin.getId(), "测试项目", "用于测试项目");
        HttpInterface defaultSimpleInterface = interfaceDesignService.createDefaultSimpleInterface(designer.getId(), project.getId(),
                "测试接口", "测试接口用于测试简单接口的创建", "http://www.baidu.com");
        assertNotNull(defaultSimpleInterface);
        assertNotNull(defaultSimpleInterface.getId());
        assertEquals(defaultSimpleInterface.getName(), "测试接口");
        assertEquals(defaultSimpleInterface.getDescription(), "测试接口用于测试简单接口的创建");
        assertEquals(defaultSimpleInterface.getTransferProtocol(), TransferProtocol.HTTP);
        assertNotNull(defaultSimpleInterface.getHttpRequest());
        assertNotNull(defaultSimpleInterface.getHttpRequest().getId());
        assertNotNull(defaultSimpleInterface.getHttpRequest().getRequestUrl(), "http://www.baidu.com");
        assertNotNull(defaultSimpleInterface.getHttpResponse());
        assertNotNull(defaultSimpleInterface);
    }
}