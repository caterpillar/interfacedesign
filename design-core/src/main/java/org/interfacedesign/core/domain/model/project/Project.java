package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.core.domain.model.IdEntity;

import javax.persistence.Column;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:24
 * Project
 */
public class Project extends IdEntity {
    @Column
    private String name;
    @Column
    private String description;



}
