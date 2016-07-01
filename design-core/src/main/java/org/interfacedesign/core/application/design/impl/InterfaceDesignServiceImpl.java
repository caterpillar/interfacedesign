package org.interfacedesign.core.application.design.impl;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.application.design.InterfaceDesignService;
import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.interfacedesign.core.domain.model.design.repository.HttpInterfaceRepository;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.interfacedesign.core.domain.model.role.Designer;
import org.interfacedesign.core.domain.model.role.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-6-28.
 */
@Service
public class InterfaceDesignServiceImpl implements InterfaceDesignService {
    @Autowired
    private HttpInterfaceRepository interfaceRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DesignerRepository designerRepository;


    @Transactional
    public HttpInterface createDefaultSimpleInterface(Long designerId, Long projectId, String name, String description, String requestUrl) {
        Project project = projectRepository.findOne(projectId);
        Validate.notNull(project, "创建的接口所归属的项目不能为空");
        HttpInterface httpInterface = new HttpInterface(name, description, requestUrl, project);
        Designer designer = designerRepository.findOne(designerId);
        httpInterface.startDesignStage(designer);
        interfaceRepository.save(httpInterface);
        return httpInterface;
    }


}
