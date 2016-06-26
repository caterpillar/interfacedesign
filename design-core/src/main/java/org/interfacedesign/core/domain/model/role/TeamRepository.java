package org.interfacedesign.core.domain.model.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-6-25.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
