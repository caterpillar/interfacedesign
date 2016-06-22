package org.interfacedesign.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Created by lishaohua on 16-6-10.
 */
@MappedSuperclass
public class LongIdEntity implements IdEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public Long getVersion() {return version;}
}
