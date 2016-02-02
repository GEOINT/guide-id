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
public class GUIDEOwnerTest {

    @Test
    public void testTestOrgRandom() {
        GUIDEOwner owner = GUIDEOwner.testOrg();
        GUIDE id = owner.randomGUIDE();

        assertTrue(id.getPrefix() >= 999000 && id.getPrefix() <= 999999);
    }

    @Test
    public void testRandomGUIDE() throws Exception {
        GUIDEOwner owner = new GUIDEOwner(123);
        GUIDE id = owner.randomGUIDE();
        assertEquals(123, id.getPrefix());
    }

    @Test
    public void testWithSuffix() throws Exception {
        GUIDEOwner owner = new GUIDEOwner(123);
        GUIDE id = owner.withSuffix("456");
        assertEquals(123, id.getPrefix());
        assertEquals("456", id.getSuffix());
    }

}
