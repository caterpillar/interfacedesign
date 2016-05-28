package org.interfacedesign.core.domain.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 16:01
 * 实现业务需求
 */
public class ResourceAddress {
    private static final String RESOURCE_SPLITTER = "/";
    private final ResourceRoot resourceRoot;
    private final String resourceLocation;

    private static final ResourceRoot IMAGE_RESOURCE = new ResourceRoot(TransferProtocol.HTTP1, "127.0.0.1", 8000, "helloworld");

    public static ResourceAddress buildImageResourceAddress(String resourceLocation) {
        return new ResourceAddress(IMAGE_RESOURCE, resourceLocation);
    }

    private ResourceAddress(ResourceRoot resourceRoot, String resourceLocation) {
        if (resourceRoot == null) {
            throw new IllegalArgumentException("resource root can't be null");
        }
        this.resourceRoot = resourceRoot;
        if (StringUtils.isEmpty(resourceLocation)) {
            throw new IllegalArgumentException("resource address can't be null");
        }
        this.resourceLocation = resourceLocation;
    }

    @Override
    public String toString() {
        return new StringBuffer(this.resourceRoot.toString())
                .append(RESOURCE_SPLITTER).append(resourceLocation).toString();
    }

    public static class ResourceRoot {
        private final TransferProtocol protocol;
        private final String host;
        private final Integer port;
        private final String appName;

        private String stringValue;


        public ResourceRoot(TransferProtocol protocol, String host, Integer port, String appName) {
            if (protocol == null) {
                throw new IllegalArgumentException("protocol argument can't null");
            }
            if (!protocol.isResourceProtocol()) {
                throw new IllegalArgumentException("protocol ");
            }
            this.protocol = protocol;
            if (StringUtils.isEmpty(host)) {
                throw new IllegalArgumentException("host argument can't null");
            }
            this.host = host;
            if (port != null) {
                if (port < 0) {
                    throw new IllegalArgumentException("port argument can't null");
                }
                this.port = port;
            } else {
                this.port = null;
            }
            this.appName = appName;
        }

        @Override
        public String toString() {
            if (StringUtils.isEmpty(this.stringValue)) {
                StringBuilder sb = new StringBuilder();
                sb.append(protocol).append("://").append(host);
                if (this.port != null) {
                    sb.append(":");
                    sb.append(this.port);
                }
                sb.append(RESOURCE_SPLITTER);
                if (StringUtils.isNotEmpty(appName)) {
                    sb.append(appName);
                }
                this.stringValue = sb.toString();
            }
            return this.stringValue;
        }
    }
}


