package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

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
public class AbstractInterface extends LongIdEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "description")
    private TransferProtocol transferProtocol;
    @Column(name = "response_message_protocol")
    private MessageProtocol responseMessageProtocol;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransferProtocol getTransferProtocol() {
        return transferProtocol;
    }

    public void setTransferProtocol(TransferProtocol transferProtocol) {
        if(transferProtocol == null) {
            throw new IllegalArgumentException("Transfer Protocol can't be null");
        }
        this.transferProtocol = transferProtocol;
    }

    public MessageProtocol getResponseMessageProtocol() {
        return responseMessageProtocol;
    }

    public void setResponseMessageProtocol(MessageProtocol responseMessageProtocol) {
        if(responseMessageProtocol == null) {
            throw new IllegalArgumentException("response message protocol can't be null");
        }
        this.responseMessageProtocol = responseMessageProtocol;
    }
}
