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

package com.ideasium.btcej.common;

/**
 * @author pepyakin
 */
public class BtceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7930546232987642967L;

    public BtceException() {
    }

    public BtceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BtceException(String message) {
        super(message);
    }

    public BtceException(Throwable throwable) {
        super(throwable);
    }
}
