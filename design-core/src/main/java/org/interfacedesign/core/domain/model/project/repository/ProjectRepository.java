package org.interfacedesign.core.domain.model.project.repository;

import org.interfacedesign.core.domain.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-6-10.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
