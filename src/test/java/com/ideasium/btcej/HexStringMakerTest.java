package com.ideasium.btcej;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author pepyakin
 */
public class HexStringMakerTest {

    private HexStringMaker maker;

    @Before
    public void setUp() {
        maker = new HexStringMaker();
    }

    @Test
    public void testGetHexString() throws Exception {
        byte[] bytes = new byte[]{(byte) 0xDE, (byte) 0xAD};

        String hexString = maker.getHexString(bytes);
        assertEquals("dead", hexString);
    }

    @Test
    public void testGetHexString_2AtTime() {
        byte[] bytes1 = new byte[]{(byte) 0xDE, (byte) 0xEF};
        byte[] bytes2 = new byte[]{(byte) 0xC0, (byte) 0xFE};

        String hexString1 = maker.getHexString(bytes1);
        assertEquals("deef", hexString1);

        String hexString2 = maker.getHexString(bytes2);
        assertEquals("c0fe", hexString2);
    }
}
