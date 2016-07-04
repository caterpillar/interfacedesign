package org.interfacedesign.core.domain.model.design.repository;

import org.interfacedesign.core.domain.model.design.entity.HttpRequestParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-7-4.
 */
@Repository
public interface HttpParameterRepository extends JpaRepository<HttpRequestParameter, Long> {
}
