package org.interfacedesign.core.domain.model.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-6-22.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Designer> {
}
