package org.interfacedesign.core.domain.value;

import org.interfacedesign.core.domain.model.TransferProtocol;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransferProtocolTest {

    @Test
    public void testIsResourceProtocol() throws Exception {
        assertTrue(TransferProtocol.HTTP1.isResourceProtocol());
        assertTrue(TransferProtocol.HTTP2.isResourceProtocol());
        assertTrue(TransferProtocol.HTTPS.isResourceProtocol());
        assertTrue(TransferProtocol.FTP.isResourceProtocol());
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals("http", TransferProtocol.HTTP1.getValue());
    }
}