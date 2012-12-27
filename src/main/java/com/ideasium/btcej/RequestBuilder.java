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

import com.ideasium.btcej.common.BtceException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author pepyakin
 */
public class RequestBuilder<T> {

    private Class<T> resultClass;
    private RequestTemplate params;
    private String methodName;

    public RequestBuilder(
            @NotNull RequestTemplate params,
            @NotNull String methodName,
            @NotNull Class<T> resultClass) {
        this.params = params;
        this.resultClass = resultClass;
        this.methodName = methodName;
    }


    protected void setParam(@NotNull String name, @NotNull String value) {
        params.setParam(name, value);
    }

    protected void preExecute() {
    }

    public T execute() {
        preExecute();

        try {
            return params.makeRequest(methodName, resultClass);
        } catch (IOException e) {
            throw new BtceException(e);
        }
    }
}
