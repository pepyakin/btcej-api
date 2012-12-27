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
public class RequestParamsTest {

    private RequestParams params;


    @Before
    public void setUp() {
        params = new RequestParams();
    }

    @Test
    public void testSetMethod() throws Exception {
        params.setMethod("someMethod");

        String queryString = params.buildQuery();

        assertEquals("method=someMethod", queryString);
    }

    @Test
    public void testSetNonce() throws Exception {
        params.setNonce(42);

        String queryString = params.buildQuery();

        assertEquals("nonce=42", queryString);
    }

    @Test
    public void testSetParam() throws Exception {
        params.setParam("foo", "true");

        String queryString = params.buildQuery();

        assertEquals("foo=true", queryString);
    }

    @Test
    public void testSetParam_multi() throws Exception {
        params.setParam("foo", "true");
        params.setParam("bar", "42");

        String queryString = params.buildQuery();

        assertParams(queryString, "foo=true", "bar=42");
    }

    @Test
    public void testSetParam_togetherMethodAndNonce() throws Exception {
        params.setMethod("baz");
        params.setNonce(42);
        params.setParam("foo", "true");

        String queryString = params.buildQuery();

        assertParams(queryString, "method=baz", "nonce=42", "foo=true");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetParam_notValidMethodParam() {
        params.setParam("method", "baz");
    }

    private void assertParams(String actual, String ... expectedParams) {
        String[] actualParams = actual.split("&");

        assertEquals(actualParams.length, expectedParams.length);

        for (String expectedParam : expectedParams) {
            assertTrue(containsIn(actualParams, expectedParam));
        }
    }

    private boolean containsIn(String[] array, String item) {
        for (String arrayItem : array) {
            if (arrayItem.equals(item)) {
                return true;
            }
        }

        return false;
    }
}
