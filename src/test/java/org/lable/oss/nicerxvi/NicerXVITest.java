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
package org.lable.oss.nicerxvi;

import org.junit.Test;
import org.lable.oss.bitsandbytes.Binary;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.lable.oss.nicerxvi.NicerXVI.decode;
import static org.lable.oss.nicerxvi.NicerXVI.encode;

public class NicerXVITest {
    @Test
    public void usageExampleTest() {
        byte[] input = "TEST1234".getBytes();

        assertThat(encode(input), is("kggkkfkgfcfdfffg"));
        assertThat(decode("kggkkfkgfcfdfffg"), is(input));
    }

    @Test
    public void encodeBasicTest() {
        assertThat(encode(Binary.decode("11110000")), is("zx"));
        assertThat(encode(Binary.decode("11100001")), is("bc"));
        assertThat(encode(Binary.decode("11010010")), is("wd"));
        assertThat(encode(Binary.decode("11000011")), is("vf"));
        assertThat(encode(Binary.decode("10110100")), is("tg"));
        assertThat(encode(Binary.decode("10100101")), is("sk"));
        assertThat(encode(Binary.decode("10010110")), is("rm"));
        assertThat(encode(Binary.decode("10000111")), is("qp"));
        assertThat(encode(Binary.decode("01111000")), is("pq"));
        assertThat(encode(Binary.decode("01101001")), is("mr"));
        assertThat(encode(Binary.decode("01011010")), is("ks"));
        assertThat(encode(Binary.decode("01001011")), is("gt"));
        assertThat(encode(Binary.decode("00111100")), is("fv"));
        assertThat(encode(Binary.decode("00101101")), is("dw"));
        assertThat(encode(Binary.decode("00011110")), is("cb"));
        assertThat(encode(Binary.decode("00001111")), is("xz"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEncodeTest() {
        encode(null);
    }

    @Test
    public void decodeBasicTest() {
        assertThat(Binary.encode(decode("zx")), is("11110000"));
        assertThat(Binary.encode(decode("bc")), is("11100001"));
        assertThat(Binary.encode(decode("wd")), is("11010010"));
        assertThat(Binary.encode(decode("vf")), is("11000011"));
        assertThat(Binary.encode(decode("tg")), is("10110100"));
        assertThat(Binary.encode(decode("sk")), is("10100101"));
        assertThat(Binary.encode(decode("rm")), is("10010110"));
        assertThat(Binary.encode(decode("qp")), is("10000111"));
        assertThat(Binary.encode(decode("pq")), is("01111000"));
        assertThat(Binary.encode(decode("mr")), is("01101001"));
        assertThat(Binary.encode(decode("ks")), is("01011010"));
        assertThat(Binary.encode(decode("gt")), is("01001011"));
        assertThat(Binary.encode(decode("fv")), is("00111100"));
        assertThat(Binary.encode(decode("dw")), is("00101101"));
        assertThat(Binary.encode(decode("cb")), is("00011110"));
        assertThat(Binary.encode(decode("xz")), is("00001111"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDecodeTest() {
        decode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unevenDecodeTest() {
        decode("b");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCharacterDecodeTestA() {
        decode("ba");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCharacterDecodeTestB() {
        decode("ab");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCharacterDecodeTestC() {
        decode("be");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCharacterDecodeTestD() {
        decode("eb");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCharacterDecodeTestE() {
        decode("b{");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCharacterDecodeTestF() {
        decode("{b");
    }
}