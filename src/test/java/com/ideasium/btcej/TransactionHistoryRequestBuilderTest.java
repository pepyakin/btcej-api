/*
 * ${NAME}
 * ${PROJECT_NAME}
 *
 * Created by ${USER} on ${DAY}.${MONTH}.${YEAR}.
 * Copyright (c) ${YEAR} ideasium. All rights reserved.
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
