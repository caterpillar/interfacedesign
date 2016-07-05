package org.interfacedesign.core.application.design;

import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.interfacedesign.core.domain.model.design.entity.HttpRequestParameter;
import org.interfacedesign.core.domain.model.design.entity.HttpRequestHeader;
import org.interfacedesign.core.domain.model.utils.MessageProtocol;

import java.util.Set;

/**
 * Created by lishaohua on 16-6-28.
 */
public interface InterfaceDesignService {
    HttpInterface createDefaultSimpleInterface(Long designerId, Long projectId, String name, String description, String requestUrl);
    HttpRequestParameter addRequestParameter(Long designerId, Long interfaceId, String name, String description, String exampleValue, String dataType);
    HttpRequestParameter addRequestParameter(Long designerId, Long interfaceId, String name, String description, Set<HttpRequestParameter.EnumParameterValue> enumExampleValue, String dataType);

    void updateResponseBody(Long designerId, Long interfaceId, String alps, MessageProtocol responseBodyExampleProtocol);


//    HttpRequestHeader addRequestHeaderValue(Long designerId, Long interfaceId, String name, String exampleValue);

}
