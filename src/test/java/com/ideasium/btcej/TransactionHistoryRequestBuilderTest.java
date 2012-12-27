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

import com.ideasium.btcej.common.SortOrder;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * @author Sergey
 */
public class TransactionHistoryRequestBuilderTest {

    private TransactionHistoryRequestBuilder requestBuilder;
    private RequestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = mock(RequestTemplate.class);
        requestBuilder = new TransactionHistoryRequestBuilder(template);
    }

    @Test
    public void testFrom() throws Exception {
        requestBuilder.from(10);
        verify(template).setParam(eq("from"), eq("10"));
    }

    @Test
    public void testOrder() throws Exception {
        requestBuilder.sortBy(SortOrder.ASCENDING);
        verify(template).setParam(eq("order"), eq("ASC"));
    }

    @Test
    public void testExecute() throws Exception {
        requestBuilder.execute();
        verify(template).makeRequest(eq("TransHistory"), eq(TransactionHistory.class));
    }
}
