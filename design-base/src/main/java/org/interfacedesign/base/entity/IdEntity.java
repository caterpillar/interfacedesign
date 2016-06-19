package org.interfacedesign.base.entity;

import java.io.Serializable;

/**
 * Created by lishaohua on 16-6-10.
 */
public interface IdEntity<T> extends Serializable{
    T getId();
}
