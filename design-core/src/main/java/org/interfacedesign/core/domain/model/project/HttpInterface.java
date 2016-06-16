package org.interfacedesign.core.domain.model.project;

import org.interfacedesign.core.domain.model.utils.HttpMethod;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by lishaohua on 16-6-13.
 */
@Entity
@Table(name = "http_interface")
public class HttpInterface extends AbstractInterface {
    @Column(name = "http_method")
    private HttpMethod httpMethod;
    private List<HttpParameter> httpParameterList;

    public HttpInterface() {
        super.setTransferProtocol(TransferProtocol.HTTP1);
    }

    @Override
    public void setResponseMessageProtocol(MessageProtocol responseMessageProtocol) {
        if(responseMessageProtocol == null) {
            responseMessageProtocol = MessageProtocol.JSON;
        }
        super.setResponseMessageProtocol(responseMessageProtocol);
    }
}
