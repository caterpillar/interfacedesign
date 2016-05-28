package org.interfacedesign.core.domain.model.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/25
 * Time: 10:06
 * 实现业务需求
 */
@Repository
public interface InterfaceRepository extends JpaRepository<Interface, Long> {
}
