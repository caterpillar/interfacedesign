package org.interfacedesign.core.domain.model.role;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:43
 * 实现业务需求
 */
@Entity
@Table(name = "designer")
public class Designer extends TeamMember {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Designer(){}

    public Designer(String firstName, String lastName, String mobilePhone, String email, String nickName, String photograph) {
        super(firstName, lastName, mobilePhone, email, nickName, photograph);
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
