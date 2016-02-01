/*
 * Copyright 2016 geoint.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geoint.guide;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steve_siebert
 */
public class GUIDETest {

    private static final int VALID_PREFIX = 123;
    private static final String VALID_SUFFIX = "abc";
    private static final String VALID_FORMATTED
            = formatGUIDE(VALID_PREFIX, VALID_SUFFIX);
    private static final int INVALID_PREFIX = -10;
    private static final String INVALID_SUFFIX = "&&&";

    @Test
    public void testValidGuide() throws Exception {

        GUIDE guide = new GUIDE(VALID_PREFIX, VALID_SUFFIX);
        assertEquals(VALID_PREFIX, guide.getPrefix());
        assertEquals(VALID_SUFFIX, guide.getSuffix());
    }

    @Test(expected = InvalidGUIDEException.class)
    public void testNegativePrefixGuide() throws Exception {
        GUIDE guide = new GUIDE(INVALID_PREFIX, VALID_SUFFIX);
    }

    @Test(expected = InvalidGUIDEException.class)
    public void testInvalidSuffixGuide() throws Exception {
        GUIDE guide = new GUIDE(VALID_PREFIX, INVALID_SUFFIX);
    }

    @Test
    public void testParseValidGuide() throws Exception {
        GUIDE guide = GUIDE.valueOf(VALID_FORMATTED);
        assertEquals(VALID_PREFIX, guide.getPrefix());
        assertEquals(VALID_SUFFIX, guide.getSuffix());
    }

    @Test(expected = InvalidGUIDEException.class)
    public void testParseNegativePrefixGuide() throws Exception {
        GUIDE guide = GUIDE.valueOf(formatGUIDE(INVALID_PREFIX, VALID_SUFFIX));
    }

    @Test(expected = InvalidGUIDEException.class)
    public void testParseNaNPrefixGuide() throws Exception {
        GUIDE guide = GUIDE.valueOf(String.format("guide://fdsfdsjf/abc"));
    }

    @Test(expected = InvalidGUIDEException.class)
    public void testParseInvalidSuffixGuide() throws Exception {
        GUIDE guide = GUIDE.valueOf(formatGUIDE(VALID_PREFIX, INVALID_SUFFIX));
    }

    @Test
    public void testRandomGUIDE() throws Exception {
        GUIDE guide = GUIDE.randomGUIDE(VALID_PREFIX);
        assertEquals(VALID_PREFIX, guide.getPrefix());
    }

    @Test
    public void testGUIDEStringFormat() throws Exception {
        GUIDE guide = GUIDE.randomGUIDE(VALID_PREFIX);
        assertEquals(VALID_PREFIX, guide.getPrefix());
        final String FORMATTED = guide.asString();

        GUIDE parsed = GUIDE.valueOf(FORMATTED);

        assertEquals(guide, parsed);
    }

    private static String formatGUIDE(int prefix, String suffix) {
        return String.format("guide://%d/%s", prefix, suffix);
    }
}
