package org.interfacedesign.auth.domain.model.repository;

import org.interfacedesign.auth.domain.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lishaohua on 16-6-6.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByName(String name);

    Page<Users> findByNameLike(String name, Pageable pageable);
}
