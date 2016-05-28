package org.interfacedesign.core.domain.model.role;

import org.interfacedesign.core.domain.model.project.Interface;
import org.interfacedesign.core.domain.model.project.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:43
 * 实现业务需求
 */
@Entity
public class Designer extends TeamMember {
    @Autowired
    private InterfaceRepository interfaceRepository;

    public Designer(){}

    public Designer(String firstName, String lastName, String mobilePhone, String email, String nickName, String resourceAddress) {
        super(firstName, lastName, mobilePhone, email, nickName, resourceAddress);
    }

    public void addInterface(Interface interf) {
        interfaceRepository.save(interf);
    }


}
