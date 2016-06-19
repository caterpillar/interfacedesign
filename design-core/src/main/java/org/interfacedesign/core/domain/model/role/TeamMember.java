package org.interfacedesign.core.domain.model.role;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.ResourceAddress;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:44
 * 实现业务需求
 */
@MappedSuperclass
public class TeamMember extends LongIdEntity {
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", length = 30)),
            @AttributeOverride(name = "secondName", column = @Column(name = "second_name", length = 30))
    }
    )
    @Embedded
    private PersonName name;
    @Column(name = "mobile_phone", length = 20)
    private String mobilePhone;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "nick_name", length = 50)
    private String nickName;
    @AttributeOverride(
            name = "resourceLocation", column = @Column(name = "photograph")
    )
    @Embedded
    private ResourceAddress photograph;

    public TeamMember() {
    }

    public TeamMember(String firstName, String lastName, String mobilePhone, String email, String nickName, String resourceAddress) {
        this.name = new PersonName(firstName, lastName);
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.nickName = nickName;
        if (StringUtils.isNoneEmpty(resourceAddress)) {
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
        if(photograph != null) {
            return this.photograph.toString();
        } else {
            return null;
        }
    }
}
