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
