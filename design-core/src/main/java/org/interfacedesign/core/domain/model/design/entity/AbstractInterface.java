package org.interfacedesign.core.domain.model.design.entity;

import org.apache.commons.lang3.Validate;
import org.interfacedesign.base.entity.LongIdEntity;
import org.interfacedesign.base.util.Assert;
import org.interfacedesign.core.domain.model.design.value.InterfaceVersion;
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
@Inheritance(strategy = InheritanceType.JOINED)
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
    @Column(name = "last_modify_time")
    protected Date lastModifyTime;
    @AttributeOverride(
            name = "version", column = @Column(name = "interface_version", length = 7)
    )
    protected InterfaceVersion interfaceVersion;
    @Column(name = "is_release")
    protected Boolean isRelease;

    public AbstractInterface() {
    }

//    public AbstractInterface(NameDescriptionEntity nameDescription, TransferProtocol transferProtocol, MessageProtocol responseMessageProtocol) {
//        this.nameDescription = nameDescription;
//        this.transferProtocol = transferProtocol;
//        this.responseMessageProtocol = responseMessageProtocol;
//    }

    public AbstractInterface(String name, String description,
                             TransferProtocol transferProtocol, MessageProtocol responseMessageProtocol,
                             Project project) {
        setNameAndDescription(name, description);
        Validate.notNull(transferProtocol);
        setTransferProtocol(transferProtocol);
        setResponseMessageProtocol(responseMessageProtocol);
        setProject(project);
    }

    private void setProject(Project project) {
        Validate.notNull(project);
        this.project = project;
    }

    private void setNameAndDescription(String name, String description) {
        Validate.notNull(name, "接口名称不能为空");
        Assert.lengthNoGreater(name, 50, "接口名称长度不能大于50个字符");
        Assert.lengthNoGreater(description, 255, "接口描述长度不能大于255个字符");
        this.nameDescription = new NameDescriptionEntity(name, description);
    }


    public String getName() {
        if (this.nameDescription == null) {
            throw new IllegalStateException("获得接口名称出错");
        }
        return this.nameDescription.getName();
    }

    public String getDescription() {
        if (this.nameDescription == null) {
            throw new IllegalStateException("获得接口描述出错");
        }
        return this.nameDescription.getDescription();
    }

    public TransferProtocol getTransferProtocol() {
        return transferProtocol;
    }

    public void setTransferProtocol(TransferProtocol transferProtocol) {
        Validate.notNull(transferProtocol, "接口传输协议不能为空");
        this.transferProtocol = transferProtocol;
    }

    public MessageProtocol getResponseMessageProtocol() {
        return responseMessageProtocol;
    }

    public void setResponseMessageProtocol(MessageProtocol responseMessageProtocol) {
        Validate.notNull(responseMessageProtocol, "返回报文协议不能为空");
        this.responseMessageProtocol = responseMessageProtocol;
    }
}
