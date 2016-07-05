package org.interfacedesign.core.application.design.impl;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.application.design.InterfaceDesignService;
import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.interfacedesign.core.domain.model.design.entity.HttpRequestHeader;
import org.interfacedesign.core.domain.model.design.entity.HttpRequestParameter;
import org.interfacedesign.core.domain.model.design.repository.DataTypeRepository;
import org.interfacedesign.core.domain.model.design.repository.HttpHeaderRepository;
import org.interfacedesign.core.domain.model.design.repository.HttpInterfaceRepository;
import org.interfacedesign.core.domain.model.design.repository.HttpParameterRepository;
import org.interfacedesign.core.domain.model.design.value.DataType;
import org.interfacedesign.core.domain.model.design.value.HttpHeader;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.interfacedesign.core.domain.model.role.Designer;
import org.interfacedesign.core.domain.model.role.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

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
    @Autowired
    private DataTypeRepository dataTypeRepository;
    @Autowired
    private HttpParameterRepository httpParameterRepository;
    @Autowired
    private HttpHeaderRepository httpHeaderRepository;


    @Transactional
    public HttpInterface createDefaultSimpleInterface(Long designerId, Long projectId, String name, String description,
                                                      String requestUrl) {
        Project project = getValidateProject(projectId);
        final List<HttpInterface> projectList = interfaceRepository.findByHttpRequestUrl(requestUrl, projectId);
        if(!CollectionUtils.isEmpty(projectList)) {
            throw new IllegalArgumentException(String.format("%s 项目中有已经存在url为%s的接口", project.getName(), requestUrl));
        }
        HttpInterface httpInterface = new HttpInterface(name, description, requestUrl, project);
        Designer designer = getValidateDesigner(designerId);
        httpInterface.startDesignStage(designer);
        interfaceRepository.save(httpInterface);
        return httpInterface;
    }

    @Transactional
    public HttpRequestParameter addRequestParameter(Long designerId, Long interfaceId, String name, String description,
                                             String exampleValue, String type) {
//        Designer designer = getValidateDesigner(designerId);
        HttpInterface httpInterface = getValidateInterface(interfaceId);
        DataType dataType = dataTypeRepository.findOne(type);
        HttpRequestParameter httpRequestParameter = new HttpRequestParameter(name, description, dataType, exampleValue, httpInterface.getHttpRequest());
        httpInterface.addRequestParameter(httpRequestParameter);
        httpParameterRepository.save(httpRequestParameter);
        return httpRequestParameter;
    }

    public HttpRequestParameter addRequestParameter(Long designerId, Long interfaceId, String name, String description,
                                                    Set<HttpRequestParameter.EnumParameterValue> enumExampleValue, String type) {
        HttpInterface httpInterface = getValidateInterface(interfaceId);
        DataType dataType = dataTypeRepository.findOne(type);
        Validate.notNull(dataType, "%s 数据类型不存在", type);
        HttpRequestParameter httpRequestParameter = new HttpRequestParameter(name, description, dataType, enumExampleValue, httpInterface.getHttpRequest());
        httpInterface.addRequestParameter(httpRequestParameter);
        httpParameterRepository.save(httpRequestParameter);
        return httpRequestParameter;
    }

    public HttpRequestHeader addRequestHeaderValue(Long designerId, Long interfaceId, String headName, String exampleValue) {
        HttpInterface httpInterface = getValidateInterface(interfaceId);
        HttpHeader httpHeader = httpHeaderRepository.findOne(headName);
        Validate.notNull(httpHeader, "%s http header不存在", headName);
        HttpRequestHeader httpRequestHeader = new HttpRequestHeader(httpHeader, exampleValue, httpInterface.getHttpRequest());
        httpInterface.addRequestHeaderValue(httpRequestHeader);
        return httpRequestHeader;
    }

    private Project getValidateProject(Long projectId) {
        Project project = projectRepository.findOne(projectId);
        Validate.notNull(project, "创建的接口所归属的项目不能为空");
        return project;
    }

    private HttpInterface getValidateInterface(Long interfaceId) {
        HttpInterface httpInterface = interfaceRepository.findOne(interfaceId);
        Validate.notNull(httpInterface, "编辑的接口不存在");
        return httpInterface;
    }

    private Designer getValidateDesigner(Long designerId) {
        Designer designer = designerRepository.findOne(designerId);
        Validate.notNull(designer, "设计者不存在");
        return designer;
    }


}
