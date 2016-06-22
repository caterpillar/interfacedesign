package org.interfacedesign.core.domain.model.role;

import org.interfacedesign.core.domain.model.role.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-6-11.
 */
@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
}
