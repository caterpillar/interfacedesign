package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.MappedSuperclass;

/**
 * Created by lishaohua on 16-6-18.
 */
@MappedSuperclass
public abstract class AbstractResponse extends LongIdEntity implements Response {

}
