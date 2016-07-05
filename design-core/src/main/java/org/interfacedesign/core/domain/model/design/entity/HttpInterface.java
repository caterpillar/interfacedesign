package org.interfacedesign.core.domain.model.design.entity;

import org.interfacedesign.core.domain.model.project.Project;
import org.interfacedesign.core.domain.model.utils.HttpMethod;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;
import org.interfacedesign.core.domain.model.utils.TransferProtocol;

import javax.persistence.*;
import java.util.Date;

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
        super.setTransferProtocol(TransferProtocol.HTTP);
        super.setResponseMessageProtocol(MessageProtocol.JSON);
    }

    public HttpInterface(String name, String description, String requestUrl, Project project) {
        this(name, description, requestUrl, TransferProtocol.HTTP, MessageProtocol.JSON, project);
    }

    public HttpInterface(String name, String description, String requestUrl, TransferProtocol transferProtocol, MessageProtocol responseMessageProtocol, Project project) {
        super(name, description, transferProtocol, responseMessageProtocol, project);
        this.lastModifyTime = new Date();
        this.httpRequest = new HttpRequest(requestUrl, this);
        this.httpResponse = new HttpResponse(this);
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void modifyRequestUrl(String url) {
        this.httpRequest.setRequestUrl(url);
    }

    public void modifRequestMethod(HttpMethod httpMethod) {
        this.httpRequest.setHttpMethod(httpMethod);
    }

    public void addRequestParameter(HttpRequestParameter httpRequestParameter) {
        this.httpRequest.addParameter(httpRequestParameter);
    }

    public void addRequestHeaderValue(HttpRequestHeader httpRequestHeader) {
        this.httpRequest.addHttpHeaderValue(httpRequestHeader);
    }

}
