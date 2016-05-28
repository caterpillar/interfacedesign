package org.interfacedesign.core.domain.model.role;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.core.domain.model.IdEntity;
import org.interfacedesign.core.domain.model.ResourceAddress;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:44
 * 实现业务需求
 */
@MappedSuperclass
public class TeamMember extends IdEntity {
    @Column
    private PersonName name;
    @Column
    private String mobilePhone;
    @Column
    private String email;
    @Column
    private String nickName;
    @Column
    private ResourceAddress photograph;

    public TeamMember() {
    }

    public TeamMember(String firstName, String lastName, String mobilePhone, String email, String nickName, String resourceAddress) {
        this.name = new PersonName(firstName, lastName);
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.nickName = nickName;
        if(StringUtils.isNoneEmpty(resourceAddress)) {
            this.photograph = ResourceAddress.buildImageResourceAddress(resourceAddress);
        }
    }

    public String getName() {
        return this.name.toString();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhotograph() {
        return this.photograph.toString();
    }
}
