package org.interfacedesign.core.application.design.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.application.design.ProjectManageService;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.project.ProjectRepository;
import org.interfacedesign.core.domain.model.role.Admin;
import org.interfacedesign.core.domain.model.role.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-6-26.
 */
@Service
public class ProjectManageServiceImpl implements ProjectManageService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public Project createProject(Long adminId, String name, String description) {
        Validate.notNull(adminId, "创建项目adminId不能为null");
        Admin admin = adminRepository.findOne(adminId);
        Validate.notNull(admin, "创建项目的admin不存在");
        Project project = admin.createProject(name, description);
        return projectRepository.save(project);
    }

    @Transactional
    public Project modifyProject(Long adminId, Long projectId, String name, String description) {
        Validate.notNull(adminId, "adminId不能为空");
        Validate.notNull(projectId, "要修改的项目id不能为null");
        Project project = projectRepository.findOne(projectId);
        Validate.notNull(project, "需要修改的项目不存在");
        Admin admin = adminRepository.findOne(adminId);
        Validate.notNull(admin, "管理员不存在");
        if(!admin.getMyProject().contains(project)) {
            throw new IllegalArgumentException("该项目不是自己的项目不能修改");
        }
        for(Project pro : admin.getMyProject()) {
            if(!pro.equals(project) && pro.getName().equals(name)) {
                throw new IllegalArgumentException("要修改的项目名称与已有的项目名称重复");
            }
        }
        if(StringUtils.isNotEmpty(name)) {
            project.setName(name);
        }
        if(StringUtils.isNotEmpty(description)) {
            project.setDescription(description);
        }
        return project;
    }

}
