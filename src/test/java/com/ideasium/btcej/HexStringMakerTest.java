/*
 * ideasium (c) 2012.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty.  In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */

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
