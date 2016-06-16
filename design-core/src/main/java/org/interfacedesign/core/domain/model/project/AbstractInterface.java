package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "description", column = @Column(name = "description"))
    }
    )
    protected NameDescriptionEntity nameDescription;
    @Column(name = "description")
    protected TransferProtocol transferProtocol;
    @Column(name = "response_message_protocol")
    protected MessageProtocol responseMessageProtocol;

    public AbstractInterface() {
    }

    public AbstractInterface(NameDescriptionEntity nameDescription, TransferProtocol transferProtocol, MessageProtocol responseMessageProtocol) {
        this.nameDescription = nameDescription;
        this.transferProtocol = transferProtocol;
        this.responseMessageProtocol = responseMessageProtocol;
    }

    public String getName() {
        if(this.nameDescription == null) {
            throw new IllegalStateException("获得接口名称出错");
        }
        return this.nameDescription.getName();
    }

    public String getDescription() {
        if(this.nameDescription == null) {
            throw new IllegalStateException("获得接口描述出错");
        }
        return this.nameDescription.getDescription();
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
