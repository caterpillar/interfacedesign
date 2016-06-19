package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.base.util.Assert;
import org.interfacedesign.core.domain.model.utils.HttpMethod;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "http_interface")
public class HttpInterface extends AbstractInterface {
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "request_id")
    private HttpRequest httpRequest;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "response_id")
    private HttpResponse httpResponse;



    public HttpInterface() {
        super.setTransferProtocol(TransferProtocol.HTTP1);
        super.setResponseMessageProtocol(MessageProtocol.JSON);
    }

    public HttpInterface(NameDescriptionEntity nameDescription, TransferProtocol transferProtocol,
                         MessageProtocol responseMessageProtocol, HttpMethod httpMethod) {
        super(nameDescription, transferProtocol, responseMessageProtocol);
        super.setTransferProtocol(TransferProtocol.HTTP1);
        httpRequest = new HttpRequest(httpMethod);
    }




}
