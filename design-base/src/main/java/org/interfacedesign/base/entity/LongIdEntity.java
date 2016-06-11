package org.interfacedesign.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lishaohua on 16-6-10.
 */
@MappedSuperclass
public class LongIdEntity implements IdEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
}
