package org.interfacedesign.core.application.design;

import org.interfacedesign.core.domain.model.project.Project;

/**
 * Created by lishaohua on 16-6-26.
 */
public interface ProjectManageService {
    public Project createProject(Long adminId, String name, String description);
}
