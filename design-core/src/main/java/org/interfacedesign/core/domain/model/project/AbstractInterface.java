package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.core.domain.model.IdEntity;
import org.interfacedesign.core.domain.model.TransferProtocol;
import org.interfacedesign.core.domain.model.project.Interface;
import org.interfacedesign.core.domain.model.project.MessageProtocol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:26
 * 实现业务需求
 */
@Entity
public class AbstractInterface extends IdEntity implements Interface {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private TransferProtocol transferProtocol;
    @Column
    private MessageProtocol responseMessageProtocol;



}
