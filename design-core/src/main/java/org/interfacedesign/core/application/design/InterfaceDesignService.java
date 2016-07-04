package org.interfacedesign.core.application.design;

import org.interfacedesign.core.domain.model.design.entity.HttpInterface;
import org.interfacedesign.core.domain.model.design.entity.HttpRequestParameter;
import org.interfacedesign.core.domain.model.design.entity.HttpRequestHeaderValue;

/**
 * Created by lishaohua on 16-6-28.
 */
public interface InterfaceDesignService {
    HttpInterface createDefaultSimpleInterface(Long designerId, Long projectId, String name, String description, String requestUrl);
    HttpRequestParameter addRequestParameter(Long designerId, Long interfaceId, String name, String description, String exampleValue, String dataType);
    HttpRequestHeaderValue addRequestHeaderValue(Long designerId, Long interfaceId, String name, String exampleValue);
}
