package org.interfacedesign.core.domain.model.role;

import org.apache.commons.lang3.Validate;

import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:41
 * 实现业务需求
 */
@Embeddable
public class PersonName {
    private String firstName;
    private String secondName;

    public PersonName() {
    }

    public PersonName(String firstName, String secondName) {
        Validate.notEmpty(firstName, "个人姓名不能为空");
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return firstName() + secondName();
    }

    public String firstName() {
        return this.firstName;
    }

    public String secondName() {
        return this.secondName;
    }

}
