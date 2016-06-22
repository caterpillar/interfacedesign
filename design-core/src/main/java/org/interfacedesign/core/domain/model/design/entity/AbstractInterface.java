package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:26
 * 实现业务需求
 */
//@MappedSuperclass
@Entity(name = "interface")
@Inheritance(strategy=InheritanceType.JOINED)
public class AbstractInterface extends LongIdEntity {
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", length = 50, nullable = false)),
            @AttributeOverride(name = "description", column = @Column(name = "description", length = 255))
    }
    )
    protected NameDescriptionEntity nameDescription;
    @Enumerated(EnumType.STRING)
    @Column(name = "transfer_protocol", length = 10)
    protected TransferProtocol transferProtocol;
    @Enumerated(EnumType.STRING)
    @Column(name = "response_message_protocol", length = 10)
    protected MessageProtocol responseMessageProtocol;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    protected Project project;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "abstractInterface")
    protected Set<DesignStage> designStages;
    @Column(name = "create_time")
    protected Date createTime;

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
