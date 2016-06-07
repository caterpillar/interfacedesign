package org.interfacedesign.auth.domain.model;

import org.interfacedesign.auth.domain.model.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lishaohua on 16-6-7.
 */
@ContextConfiguration(
        locations = {"classpath:config/applicationContext-jpa.xml", "classpath:config/applicationContext-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RolesRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transactional
    public void testRoleSave() {
        Role role = new Role("UNIT_TEST", "the role to design the interface");
        roleRepository.save(role);
    }

    @Test
    @Transactional
    public void testGetRoleByName() {
        Role role = new Role("UNIT_TEST", "the role to design the interface");
        roleRepository.save(role);

        Role designer = roleRepository.findRoleByName("UNIT_TEST");

        assertNotNull(designer);
        assertEquals(designer.getName(), "UNIT_TEST");
    }

}
