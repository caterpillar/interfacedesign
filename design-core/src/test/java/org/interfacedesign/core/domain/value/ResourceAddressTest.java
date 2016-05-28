package org.interfacedesign.core.domain.value;

import org.interfacedesign.core.domain.model.ResourceAddress;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceAddressTest {

    @Test
    public void testToString() throws Exception {
        ResourceAddress resourceAddress = ResourceAddress.buildImageResourceAddress("static/img/test.gif");

        assertEquals("http://127.0.0.1:8000/helloworld/static/img/test.gif", resourceAddress.toString());
    }

//    @Test
//    public void testToStringNoPort() throws Exception {
//        ResourceAddress resourceAddress = new ResourceAddress(TransferProtocol.HTTP1,
//                "127.0.0.1", null, "static/img/test.gif");
//
//        assertEquals("http://127.0.0.1/static/img/test.gif", resourceAddress.toString());
//    }

}