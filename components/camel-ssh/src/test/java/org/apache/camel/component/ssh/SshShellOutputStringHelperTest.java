/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.ssh;

import junit.framework.TestCase;

public class SshShellOutputStringHelperTest extends TestCase {
    
    public void testBeforeLast() {
        assertEquals("Hello ", SshShellOutputStringHelper.beforeLast("Hello World", "World"));
        assertEquals("Hello World ", SshShellOutputStringHelper.beforeLast("Hello World World", "World"));
        assertEquals("Hello ", SshShellOutputStringHelper.beforeLast("Hello World Again", "World"));
        assertEquals(null, SshShellOutputStringHelper.beforeLast("Hello Again", "Foo"));

        assertTrue(SshShellOutputStringHelper.beforeLast("mykey:ignore:hello", ":", "mykey:ignore"::equals).orElse(false));
        assertFalse(SshShellOutputStringHelper.beforeLast("ignore:ignore:world", ":", "mykey"::equals).orElse(false));
    }
    
    
    public void testBetweenBeforeLast() {
        assertEquals("foo bar' how are", SshShellOutputStringHelper.betweenBeforeLast("Hello 'foo bar' how are' you", "'", "'"));
        assertEquals("foo bar", SshShellOutputStringHelper.betweenBeforeLast("Hello ${foo bar} how are you", "${", "}"));
        assertEquals(null, SshShellOutputStringHelper.betweenBeforeLast("Hello ${foo bar} how are you", "'", "'"));

        assertTrue(SshShellOutputStringHelper.betweenBeforeLast("begin:mykey:end:end", "begin:", ":end", "mykey:end"::equals).orElse(false));
        assertFalse(SshShellOutputStringHelper.betweenBeforeLast("begin:ignore:end:end", "begin:", ":end", "mykey"::equals).orElse(false));
    }
    
}