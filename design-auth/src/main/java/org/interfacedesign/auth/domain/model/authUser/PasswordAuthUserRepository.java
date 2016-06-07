package org.interfacedesign.auth.domain.model.authUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lishaohua on 16-5-25.
 */
@Repository
public interface PasswordAuthUserRepository extends JpaRepository<PasswordAuthUser, Long> {

    PasswordAuthUser findByUserNameAndPassword(String userName, String password);

    PasswordAuthUser findByUserName(String userName);

}
