package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.utils.HttpMethod;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

import javax.persistence.*;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "http_interface")
@PrimaryKeyJoinColumn(name = "interface_id")
public class HttpInterface extends AbstractInterface {
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "request_id")
    private HttpRequest httpRequest;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "response_id")
    private HttpResponse httpResponse;


    HttpInterface() {
        super.setTransferProtocol(TransferProtocol.HTTP1);
        super.setResponseMessageProtocol(MessageProtocol.JSON);
    }

    public HttpInterface(String name, String description, TransferProtocol transferProtocol, MessageProtocol responseMessageProtocol, Project project) {
        super(name, description, transferProtocol, responseMessageProtocol, project);
    }
}
