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

    public HttpInterface(String name, String description, String requestUrl, Project project) {
        this(name, description, requestUrl, TransferProtocol.HTTP1, MessageProtocol.JSON, project);
    }

    public HttpInterface(String name, String description, String requestUrl, TransferProtocol transferProtocol, MessageProtocol responseMessageProtocol, Project project) {
        super(name, description, transferProtocol, responseMessageProtocol, project);
        this.httpRequest = new HttpRequest(requestUrl, this);
        this.httpResponse = new HttpResponse(this);
    }

    public void modifyRequestUrl(String url) {
        this.httpRequest.setRequestUrl(url);
    }

    public void modifRequestMethod(HttpMethod httpMethod) {
        this.httpRequest.setHttpMethod(httpMethod);
    }

    public void addRequestParameter(HttpParameter httpParameter) {
        this.httpRequest.addParameter(httpParameter);
    }

    public void addRequestHeaderValue(HttpRequestHeaderValue httpRequestHeaderValue) {
        this.httpRequest.addHttpHeaderValue(httpRequestHeaderValue);
    }
}
