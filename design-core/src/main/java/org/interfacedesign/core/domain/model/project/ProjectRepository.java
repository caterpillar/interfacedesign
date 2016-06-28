package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.core.domain.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by lishaohua on 16-6-10.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT * FROM project WHERE create_admin_id = :adminId AND name = :projectName")
    Collection<Project> findMyProjectByName(@Param("adminId")Long adminId, @Param("projectName")String projectName);

}
