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

import java.util.Random;
import java.util.stream.IntStream;

/**
 * An GUIDE prefix owner may generate GUIDEs for their own prefix.
 * <p>
 * Usage of this class reduces the need to handled checked exceptions.
 *
 * @author steve_siebert
 */
public class GUIDEOwner {

    private final int prefix;
    private static final int TEST_PREFIX_MIN = 999000;
    private static final int TEST_PREFIX_MAX = 999999;
    private static final IntStream TEST_PREFIXES
            = new Random().ints(TEST_PREFIX_MIN, TEST_PREFIX_MAX + 1);

    public GUIDEOwner(int prefix) throws InvalidGUIDEException {
        if (prefix < 0) {
            throw new InvalidGUIDEException("GUIDE prefix must be a positive "
                    + "integer.");
        }
        this.prefix = prefix;
    }

    /**
     * Returns a GUIDEOwner with a prefix designated for testing.
     *
     * @return testing organization
     */
    public static GUIDEOwner testOrg() {
        try {
            return new GUIDEOwner(TEST_PREFIXES.findFirst().getAsInt());
        } catch (InvalidGUIDEException ex) {
            //we won't get here
            assert false : "GUIDE test prefix is not valid!";
            throw new RuntimeException("Error generating GUIDE test prefix.");
        }
    }

    /**
     * Creates a new, random, GUIDE ID for this organization.
     *
     * @return GUIDE random GUIDE for this prefix
     */
    public GUIDE randomGUIDE() {
        try {
            return GUIDE.randomGUIDE(prefix);
        } catch (InvalidGUIDEException ex) {
            //we won't get here, because the prefix was already validated
            assert false : "GUIDE prefix is invalid!";
            throw new RuntimeException("GUIDE prefix is unexpectidly invalid.");
        }
    }

    /**
     * Create a new GUIDE with the provided suffix.
     *
     * @param suffix guide suffix
     * @return GUIDE GUIDE with the org prefix and specified suffix
     * @throws InvalidGUIDEException thrown if the suffix is not valid
     */
    public GUIDE withSuffix(String suffix) throws InvalidGUIDEException {
        return new GUIDE(prefix, suffix);
    }

}
