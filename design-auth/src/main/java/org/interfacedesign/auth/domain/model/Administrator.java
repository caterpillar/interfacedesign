package org.interfacedesign.auth.domain.model;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lishaohua on 16-6-7.
 */
public interface Administrator {
    void addUsers(String name, String password, List<String> roles);
}
