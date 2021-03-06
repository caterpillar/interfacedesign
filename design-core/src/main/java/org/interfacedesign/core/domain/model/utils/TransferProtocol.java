package org.interfacedesign.core.domain.model.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Li Shaohua
 * Date: 2016/5/23
 * Time: 15:31
 * 实现业务需求
 */
public enum TransferProtocol {
    HTTP("http1", "http"),
    HTTPS("https", "https"),
    FTP("ftp", "ftp"),
    TCP("tcp", "tcp"),
    EMAIL("email", "email");

    private final static List<TransferProtocol> resourceProtocol = new ArrayList<TransferProtocol>()
    {
        {
            add(HTTP);
            add(HTTPS);
            add(FTP);
        }
    };

    private String name;
    private String value;

    /**
     * 判断该协议是否是
     * @param name
     * @param value
     */
    private TransferProtocol(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public boolean isResourceProtocol() {
        return resourceProtocol.contains(this);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static void main(String[] args) {
        System.out.println(TransferProtocol.HTTP);
    }
}
