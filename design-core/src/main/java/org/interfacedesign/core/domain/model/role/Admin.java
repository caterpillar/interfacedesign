package org.interfacedesign.core.domain.model.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.*;
import java.util.*;

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
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "admin")
    private Set<Designer> designers;
    @Autowired
    @Transient
    private DesignerRepository designerRepository;


    protected Admin(){

    }
    public Admin(String firstName, String lastName, String mobilePhone, String email, String nickName, String resourceAddress) {
        super(firstName, lastName, mobilePhone, email, nickName, resourceAddress);
    }

    public Collection<Designer> getDesigners() {
        return new ArrayList<Designer>(designers);
    }

    public void addDesigner(Designer designer) {
        if(CollectionUtils.isEmpty(designers)) {
            designers = new HashSet<Designer>();
        }
        designer.setAdmin(this);
        designers.add(designer);
    }

    public boolean deleteDesigner(Designer designer) {
        if(designer == null) {
            return false;
        }
        if(CollectionUtils.isEmpty(designers)) {
            return false;
        }
        designerRepository.delete(designer);
    }



}
