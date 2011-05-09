/*
 * This file is provided to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.basho.riak.client.cap;

import com.basho.riak.client.util.CharsetUtils;

/**
 * @author russell
 * 
 */
public class BasicVClock implements VClock {

    private final byte[] value;

    public BasicVClock(final byte[] value) {
        if (value == null) {
            throw new IllegalArgumentException("VClock value cannot be null");
        }
        this.value = value.clone();
    }

    public byte[] getBytes() {
        return value.clone();
    }

    public String asString() {
        return CharsetUtils.asUTF8String(value);
    }
}