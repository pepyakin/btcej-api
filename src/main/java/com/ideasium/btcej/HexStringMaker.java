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

import org.jetbrains.annotations.NotNull;

/**
 * @author pepyakin
 */
class HexStringMaker {

    private static final char[] ALPHABET = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    private StringBuilder stringBuilder;

    public String getHexString(@NotNull byte[] bytes) {
        if (stringBuilder == null) {
            final int hexStringLength = bytes.length * 2;
            stringBuilder = new StringBuilder(hexStringLength);
        }

        try {
            return convertBytesToHexString(bytes);
        } finally {
            stringBuilder.setLength(0);
        }
    }

    private String convertBytesToHexString(byte[] bytes) {
        for (byte value : bytes) {
            appendHigh(value);
            appendLow(value);
        }

        return stringBuilder.toString();
    }

    private void appendHigh(byte value) {
        int highPart = (value & 0xF0) >> 4;
        append(highPart);
    }

    private void appendLow(byte value) {
        int lowPart = value & 0x0F;
        append(lowPart);
    }

    private void append(int alphaIndex) {
        stringBuilder.append(ALPHABET[alphaIndex]);
    }
}
