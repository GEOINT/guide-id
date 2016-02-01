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
package org.geoint.guide.resolve;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import org.geoint.guide.GUIDE;

/**
 * Resolves a GUIDE to a temporal URL.
 *
 * @author steve_siebert
 */
public interface GUIDEResolver {

    /**
     * Lookup the temporal URL.
     *
     * @param guide identifier
     * @return resolved URL
     * @throws UnknownGUIDEException if the resolver could not find a temporal
     * URL
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     */
    URL lookup(GUIDE guide) throws UnknownGUIDEException, IOException;

    /**
     * Lookup the temporal URL.
     * <p>
     * Functionally equivalent to {@link #lookup(org.geoint.guide.GUIDE) } but
     * does not throw a checked exception if the resolver does not know the URL.
     *
     * @param guide identifier
     * @return resolver url or null
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     */
    Optional<URL> find(GUIDE guide) throws IOException;

    /**
     * Lookup a GUIDE by its temporal URL (reverse-lookup).
     *
     * @param url temporal url
     * @return GUIDE identifier
     * @throws UnknownGUIDEException if the resolver could not find a temporal
     * URL
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     */
    GUIDE lookup(URL url) throws UnknownGUIDEException, IOException;

    /**
     * Lookup a GUIDE by its temporal URL (reverse-lookup).
     * <p>
     * Functionally equivilant to {@link #lookup(java.net.URL) } but does not
     * throw a checked exception.
     *
     * @param url temporal url
     * @return GUIDE identifier
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     */
    Optional<GUIDE> find(URL url) throws IOException;

    /**
     * Attempt to register a GUIDE with the provided temporal URL.
     *
     * @param guide identifier
     * @param url temporal url
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     * @throws GUIDEAlreadyExistsException if the GUIDE is already registered
     * @throws NotPrefixOwnerException if the GUIDE cannot be registered because
     * this is not the valid GUIDEOwner
     */
    void register(GUIDE guide, URL url)
            throws IOException, GUIDEAlreadyExistsException,
            NotPrefixOwnerException;

    /**
     * Attempt to update the register with a new temporal URL.
     *
     * @param guide identifier
     * @param url temporal url
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     * @throws UnknownGUIDEException if the provided GUIDE is unknown to the
     * register
     * @throws NotPrefixOwnerException if the GUIDE cannot be registered because
     * this is not the valid GUIDEOwner
     */
    void update(GUIDE guide, URL url) throws IOException, UnknownGUIDEException,
            NotPrefixOwnerException;

    /**
     * Attempt to delete a GUIDE with the register.
     *
     * @param guide identifier to delete
     * @throws IOException if there were connectivity problems with the GUIDE
     * resolver
     * @throws UnknownGUIDEException if the provided GUIDE is unknown to the
     * register
     * @throws NotPrefixOwnerException if the GUIDE cannot be registered because
     * this is not the valid GUIDEOwner
     */
    void delete(GUIDE guide) throws IOException, UnknownGUIDEException,
            NotPrefixOwnerException;
}
