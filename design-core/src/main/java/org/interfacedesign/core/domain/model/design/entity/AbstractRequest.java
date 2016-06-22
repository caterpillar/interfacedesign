package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;

import javax.persistence.MappedSuperclass;

/**
 * Created by lishaohua on 16-6-18.
 */
@MappedSuperclass
public abstract class AbstractRequest extends LongIdEntity implements Request {
}
