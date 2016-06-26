package org.interfacedesign.core.application.role.impl;

import javafx.print.Collation;
import org.apache.commons.lang3.Validate;
import org.interfacedesign.core.application.role.TeamManageService;
import org.interfacedesign.core.domain.model.role.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lishaohua on 16-6-25.
 */
@Service
public class TeamManageServiceImpl implements TeamManageService {
    private static final Logger logger = LoggerFactory.getLogger(TeamManageServiceImpl.class);
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DesignerRepository designerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public void addDesigner(Long adminId, Designer designer) {
        Admin admin = findAdmin(adminId);
        designerRepository.save(designer);
        admin.addDesigner(designer);
        logger.info("%s添加类设计员%s", admin, designer);
    }

    public Team createTeam(Long adminId, String teamName, String description) {
        Admin admin = findAdmin(adminId);
        Team team = admin.buildTeam(teamName, description);
        adminRepository.save(admin);
        logger.info("%s创建了小组%s", admin, team);
        return team;
    }

    public void assignTeam(Long designerId, Long teamId) {
        Designer designer = designerRepository.findOne(designerId);
        Validate.notNull(designer, "要分配的设计人员不存在");
        Team team = teamRepository.findOne(teamId);
        designer.assignTeam(team);
        designerRepository.save(designer);
    }

    public Collection<Designer> addDesignerToTeam(List<Long> designerIdList, Long teamId) {
        Team team = teamRepository.findOne(teamId);
        Validate.notNull(team, "设计人员所要分配的小组不存在");
        List<Designer> successAddDesigners = new ArrayList<Designer>();
        for (Long designerId : designerIdList) {
            Designer designer = designerRepository.findOne(designerId);
            if(designer == null) {
                logger.warn("要分配的设计员(%s)不存在", designerId);
            } else {
                team.addDesigner(designer);
                successAddDesigners.add(designer);
            }
        }
        teamRepository.save(team);
        return successAddDesigners;
    }

    public void addAdmin(Admin admin) {
        Validate.notNull(admin, "要添加的管理员不能为空");
        adminRepository.save(admin);
    }

    private Admin findAdmin(Long adminId) {
        Admin admin = adminRepository.findOne(adminId);
        Validate.notNull(admin, "管理员不存在");
        return admin;
    }


}
