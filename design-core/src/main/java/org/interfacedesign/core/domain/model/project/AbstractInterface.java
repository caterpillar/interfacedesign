package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.core.domain.model.TransferProtocol;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:26
 * 实现业务需求
 */
@MappedSuperclass
public class AbstractInterface implements Interface {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private TransferProtocol transferProtocol;
    @Column
    private MessageProtocol responseMessageProtocol;


    public String getDescription() {
       return description;
    }




}
