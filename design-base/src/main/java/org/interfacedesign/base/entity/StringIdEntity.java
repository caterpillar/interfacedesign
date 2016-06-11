package org.interfacedesign.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lishaohua on 16-6-10.
 */
@MappedSuperclass
public class StringIdEntity implements IdEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private String id;

    public String getId() {
        return id;
    }
}
