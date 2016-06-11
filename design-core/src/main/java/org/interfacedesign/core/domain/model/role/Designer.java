package org.interfacedesign.core.domain.model.role;

import javax.persistence.Entity;
import javax.persistence.Table;

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

    public Designer(){}

    public Designer(String firstName, String lastName, String mobilePhone, String email, String nickName, String photograph) {
        super(firstName, lastName, mobilePhone, email, nickName, photograph);
    }



}
