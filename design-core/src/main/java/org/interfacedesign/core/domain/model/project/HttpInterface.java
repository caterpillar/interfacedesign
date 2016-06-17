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
    @Column(name = "http_method")
    private HttpMethod httpMethod;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "interface")
    private List<HttpParameter> httpParameterList = new ArrayList<HttpParameter>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "interface")
    private List<HttpHeaderValue> httpHeaderValueList;



    public HttpInterface() {
        super.setTransferProtocol(TransferProtocol.HTTP1);
    }

    public HttpInterface(NameDescriptionEntity nameDescription, TransferProtocol transferProtocol,
                         MessageProtocol responseMessageProtocol, HttpMethod httpMethod) {
        super(nameDescription, transferProtocol, responseMessageProtocol);
        super.setTransferProtocol(TransferProtocol.HTTP1);
        this.httpMethod = httpMethod;
    }

    public boolean addParameter(HttpParameter httpParameter) {
        Assert.notNull(httpParameter, "不能添加空参数");
        return httpParameterList.add(httpParameter);
    }

    public List<HttpParameter> getParameters() {
        return new ArrayList<HttpParameter>(this.httpParameterList);
    }

    @Override
    public void setResponseMessageProtocol(MessageProtocol responseMessageProtocol) {
        if(responseMessageProtocol == null) {
            responseMessageProtocol = MessageProtocol.JSON;
        }
        super.setResponseMessageProtocol(responseMessageProtocol);
    }
}
