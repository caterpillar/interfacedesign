package org.interfacedesign.core.application.design;

import org.interfacedesign.core.domain.model.design.entity.HttpInterface;

/**
 * Created by lishaohua on 16-6-28.
 */
public interface InterfaceDesignService {
    HttpInterface createDefaultSimpleInterface(Long designerId, Long projectId, String name, String description, String requestUrl);
}
