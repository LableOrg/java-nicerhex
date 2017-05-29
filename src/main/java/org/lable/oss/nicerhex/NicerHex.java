/*
 * Copyright (C) 2017 Lable (info@lable.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lable.oss.nicerhex;

/**
 * Conversion of byte arrays to and from Base16 string representations. The characters used in the string
 * representation are a selection of sixteen lowercase consonants, suitable for use in user-visible strings.
 * Ambiguous characters such as 'i' and 'j' are not used.
 */
public class NicerHex {
    /**
     * The sixteen characters used by this codec, mapped to their four bit value.
     * <p>
     * Note that 'b' and 'x' are swapped. This is done to have byte 0 map to "xx" — purely for aesthetic reasons.
     */
    static final char[] ENCODING_CHAR_POOL = new char[]{
            'x', 'c', 'd', 'f', 'g', 'k', 'm', 'p',
            'q', 'r', 's', 't', 'v', 'w', 'b', 'z'
    };

    /**
     * The four bit value represented by each character in {@link #ENCODING_CHAR_POOL} mapped to that character's
     * position (offset by 98).
     * <p>
     * The first value in this array is for 'b', the next for 'c' and so on. Unused letters such as 'e' are mapped to
     * -1.
     */
    static final byte[] DECODING_CHAR_POOL = new byte[]{
            // b, c, d, e,
            14, 1, 2, -1,
            // f, g, h, i,
            3, 4, -1, -1,
            // j, k, l, m,
            -1, 5, -1, 6,
            // n, o, p, q,
            -1, -1, 7, 8,
            // r, s, t, u,
            9, 10, 11, -1,
            // v, w, x, y, z.
            12, 13, 0, -1, 15
    };

    /**
     * Convert a byte array to a NicerHex string representation.
     *
     * @param input Input bytes, may not be null.
     * @return A string.
     */
    public static String encode(byte[] input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null.");

        StringBuilder buffer = new StringBuilder(input.length * 2);

        for (byte b : input) {
            // Find the two characters that together represent one single byte.
            char second = ENCODING_CHAR_POOL[b & 0x0f];
            b >>= 4;
            char first = ENCODING_CHAR_POOL[b & 0x0f];
            buffer.append(first).append(second);
        }

        return buffer.toString();
    }

    /**
     * Convert a NicerHex string representation into the data it represents.
     *
     * @param input Input string, must contain valid NicerHex notation, and may not be null.
     * @return A byte array.
     */
    public static byte[] decode(String input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null.");
        if (input.length() % 2 == 1) throw new IllegalArgumentException("Input string of uneven length.");

        byte[] out = new byte[input.length() / 2];

        for (int i = 0; i < input.length(); i += 2) {
            char first = input.charAt(i);
            char second = input.charAt(i + 1);

            // Check if characters are within bounds ([b-z]).
            if (first < 98 || first > 122) throw new IllegalArgumentException("Illegal character at pos. " + i);
            if (second < 98 || second > 122) throw new IllegalArgumentException("Illegal character at pos. " + i + 1);

            byte firstValue = DECODING_CHAR_POOL[first - 98];
            byte secondValue = DECODING_CHAR_POOL[second - 98];

            // Check if valid characters were found.
            if (firstValue == -1) throw new IllegalArgumentException("Illegal character at pos. " + i);
            if (secondValue == -1) throw new IllegalArgumentException("Illegal character at pos. " + i + 1);

            // Combine the two four bit values represented by the two characters into one byte.
            byte val = (byte) ((firstValue << 4) | secondValue);

            out[i / 2] = val;
        }

        return out;
    }
}
