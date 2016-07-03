package org.interfacedesign.core.domain.model.design.repository;

import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.interfacedesign.core.domain.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lishaohua on 16-7-1.
 */
@Repository
public interface HttpInterfaceRepository extends JpaRepository<HttpInterface, Long> {

    @Query(value = "select i from HttpInterface i where httpRequest.requestUrl = :requestUrl and project.id = :projectId")
    public List<HttpInterface> findByHttpRequestUrl(@Param("requestUrl")String requestUrl, @Param("projectId")Long projectId);
}
