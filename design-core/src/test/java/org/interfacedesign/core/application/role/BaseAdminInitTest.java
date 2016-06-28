package org.interfacedesign.core.application.role;

import org.interfacedesign.core.BaseTest;
import org.interfacedesign.core.domain.model.role.Admin;
import org.interfacedesign.core.domain.model.role.AdminRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-6-28.
 */
public class BaseAdminInitTest extends BaseTest {
    @Autowired
    protected TeamManageService teamManageService;
    @Autowired
    protected AdminRepository adminRepository;
    protected Admin admin;
    private final static Long INIT_ADMIN_ID = 1L;

    @Before
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setUp() {
        this.admin = adminRepository.findOne(INIT_ADMIN_ID);
    }


}
