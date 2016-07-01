package org.interfacedesign.core.domain.model.design.repository;

import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-7-1.
 */
@Repository
public interface HttpInterfaceRepository extends JpaRepository<HttpInterface, Long> {
}
