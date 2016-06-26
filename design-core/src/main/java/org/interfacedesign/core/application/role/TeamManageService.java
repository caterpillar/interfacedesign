package org.interfacedesign.core.application.role;

import org.interfacedesign.core.domain.model.role.Admin;
import org.interfacedesign.core.domain.model.role.Designer;
import org.interfacedesign.core.domain.model.role.Team;

import java.util.Collection;
import java.util.List;

/**
 * Created by lishaohua on 16-6-22.
 */
public interface TeamManageService {
    void addDesigner(Long adminId, Designer designer);
    void addAdmin(Admin admin);
    Team createTeam(Long adminId, String teamName, String description);
    void assignTeam(Long designerId, Long teamId);
    Collection<Designer> addDesignerToTeam(List<Long> designerIdList, Long teamId);
}
