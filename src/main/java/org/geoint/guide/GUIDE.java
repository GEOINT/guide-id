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

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A GUIDE (Globally Unique IDentifiers for Everything) is specification for
 * creating and managing globally unique identifiers for any resource.
 * <p>
 * GUIDE's are assigned to resources that are permanent and published and may
 * not be reused.
 * <p>
 * GUIDE identifiers are intended to serialized as a String in the following
 * format: {@code guide://prefix/suffix}
 * <p>
 * GUIDE ID's must be UNCLASSIFIED.
 *
 * @author steve_siebert
 */
public class GUIDE {

    private final int prefix;
    private final String suffix;

    private static final String GUIDE_SCHEME = "guide";
    private static final Pattern GUIDE_SUFFIX_PATTERN
            = Pattern.compile("[A-Za-z0-9_\\-\\.]+");
    private static final Pattern GUIDE_PATTERN
            = Pattern.compile("guide://(\\d+)/([A-Za-z0-9_\\-\\.]+)");

    public GUIDE(int prefix, String suffix) throws InvalidGUIDEException {
        if (prefix < 0) {
            throw new InvalidGUIDEException("GUIDE prefix must be a positive "
                    + "integer.");
        }
        this.prefix = prefix;
        if (suffix == null || suffix.isEmpty()) {
            throw new InvalidGUIDEException("GUIDE must have a defined suffix.");
        }
        Matcher suffixMatcher = GUIDE_SUFFIX_PATTERN.matcher(suffix);
        if (!suffixMatcher.find()) {
            throw new InvalidGUIDEException(String.format("Invalid GUIDE suffix "
                    + "'%s'.  Suffix must only contain the following characers: "
                    + "A-Z, a-z, 0-9, underscore, hyphen, and period",
                    suffix));
        }
        this.suffix = suffix;
    }

    /**
     * Parses the value of the provided String, returning a GUIDE.
     *
     * @param guideString GUIDE in string format
     * @return GUIDE GUIDE instance
     * @throws InvalidGUIDEException thrown if the String is not a valid GUIDE
     */
    public static GUIDE valueOf(String guideString)
            throws InvalidGUIDEException {
        Matcher m = GUIDE_PATTERN.matcher(guideString);
        if (!m.find() || m.group(1) == null || m.group(2) == null) {
            throw new InvalidGUIDEException(String.format("'%s' is not a "
                    + "valid GUIDE. GUIDE must be in the following format: "
                    + "guide://prefix/suffix", guideString));
        }
        try {
            return new GUIDE(Integer.valueOf(m.group(1)), m.group(2));
        } catch (NumberFormatException ex) {
            throw new InvalidGUIDEException(String.format("Invalid GUIDE "
                    + "'%s', prefix must be a valid positive integer.",
                    guideString), ex);
        }
    }

    /**
     * Generate a new, random, GUIDE with the provided prefix.
     *
     * @param prefix guide prefix
     * @return new, random, guide
     * @throws InvalidGUIDEException thrown if the provided prefix is invalid
     */
    public static GUIDE randomGUIDE(int prefix)
            throws InvalidGUIDEException {
        return new GUIDE(prefix, UUID.randomUUID().toString());
    }

    /**
     * According to the GUIDE lexicon, this is a positive integer (base 10).
     *
     * @return GUIDE prefix
     */
    public int getPrefix() {
        return prefix;
    }

    /**
     * According to the GUIDE lexicon, this is a <i>case insensitive</i>
     * alphanumeric string.
     * <p>
     * Allowed characters include A-Z, a-z, 0-9, underscore, hyphen, and period.
     * <p>
     * It is recommended that this value is a UUID (see RFC 4122), though other
     * application specific technics may be used.
     *
     * @return GUIDE suffix
     */
    public String getSuffix() {
        return suffix;
    }

    public String asString() {
        return String.format("%s://%d/%s", GUIDE_SCHEME, prefix, suffix);
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.prefix;
        hash = 37 * hash + Objects.hashCode(this.suffix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GUIDE other = (GUIDE) obj;
        if (this.prefix != other.prefix) {
            return false;
        }
        return Objects.equals(this.suffix, other.suffix);
    }

}
