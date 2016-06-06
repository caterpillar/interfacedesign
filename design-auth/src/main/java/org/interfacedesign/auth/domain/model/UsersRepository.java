package org.interfacedesign.auth.domain.model;

import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-6-6.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
