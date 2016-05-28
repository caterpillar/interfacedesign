package org.interfacedesign.core.domain.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lishaohua on 16-5-25.
 */
@MappedSuperclass
public class IdEntity {
    @Id
    private Long id;
}
