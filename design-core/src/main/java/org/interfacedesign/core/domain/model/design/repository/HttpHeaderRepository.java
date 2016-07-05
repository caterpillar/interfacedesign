package org.interfacedesign.core.domain.model.design.repository;

import org.interfacedesign.core.domain.model.design.value.HttpHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-7-5.
 */
@Repository
public interface HttpHeaderRepository extends JpaRepository<HttpHeader, String> {
}
