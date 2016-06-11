package org.interfacedesign.core.domain.model.project.repository;

import org.interfacedesign.core.domain.model.role.Designer;
import org.interfacedesign.core.domain.model.role.repository.DesignerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by lishaohua on 16-6-11.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DesignerRepositoryTest {
    @Autowired
    private DesignerRepository designerRepository;

    @Test
    @Transactional
    public void testSave() {
        Designer designer = new Designer("Steve", "Lee", "13062705747", "hhlucky@126.com", "hhlucky", "/static/safa/sdadsa");
        designerRepository.save(designer);
        assertNotNull(designer.getId());
    }

}