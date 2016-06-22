package org.interfacedesign.core.domain.model.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 20:23
 * 实现业务需求
 */
@Entity
@Table(name = "admin")
public class Admin extends TeamMember {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
    private Set<Designer> designers;
    @Autowired
    @Transient
    private DesignerRepository designerRepository;


    protected Admin(){

    }
    public Admin(String firstName, String lastName, String mobilePhone, String email, String nickName, String resourceAddress) {
        super(firstName, lastName, mobilePhone, email, nickName, resourceAddress);
    }

    @Transactional
    public void addDesigner(Designer designer) {
        if(CollectionUtils.isEmpty(designers)) {
            designers = new HashSet<Designer>();
        }
        designer.setAdmin(this);
        designers.add(designer);
    }



}
