package org.interfacedesign.core.domain.model.role;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 20:23
 * 实现业务需求
 */
public class Admin extends TeamMember {

    public Admin(){

    }
    public Admin(String firstName, String lastName, String mobilePhone, String email, String nickName, String resourceAddress) {
        super(firstName, lastName, mobilePhone, email, nickName, resourceAddress);
    }



}
