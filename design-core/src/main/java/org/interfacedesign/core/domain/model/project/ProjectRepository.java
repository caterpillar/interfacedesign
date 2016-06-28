package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.role.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by lishaohua on 16-6-10.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p FROM Project p WHERE p.nameDescription.name = :projectName")
    List<Project> findMyProjectByName(@Param("projectName")String projectName);

}
