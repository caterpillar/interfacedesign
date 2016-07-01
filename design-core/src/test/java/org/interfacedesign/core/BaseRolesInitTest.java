package org.interfacedesign.core;

import org.interfacedesign.core.domain.model.role.Admin;
import org.interfacedesign.core.domain.model.role.AdminRepository;
import org.interfacedesign.core.domain.model.role.Designer;
import org.interfacedesign.core.domain.model.role.DesignerRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lishaohua on 16-7-1.
 */
public class BaseRolesInitTest extends BaseTest {
    @Autowired
    protected DesignerRepository designerRepository;
    protected Designer designer;
    @Autowired
    protected AdminRepository adminRepository;
    protected Admin admin;
    private final static Long INIT_ADMIN_ID = 1L;
    private final static Long INIT_DESIGNER_ID = 1L;

    @Before
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setUp() {
        this.designer = designerRepository.findOne(INIT_DESIGNER_ID);
        this.admin = adminRepository.findOne(INIT_ADMIN_ID);
    }
}
