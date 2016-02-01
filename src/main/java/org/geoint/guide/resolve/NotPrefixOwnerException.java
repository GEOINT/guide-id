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

import org.geoint.guide.GUIDEException;

/**
 * Thrown if an operation is not authorized because client is not the prefix
 * owner.
 *
 * @author steve_siebert
 */
public class NotPrefixOwnerException extends GUIDEException {

    public NotPrefixOwnerException() {
    }

    public NotPrefixOwnerException(String message) {
        super(message);
    }

    public NotPrefixOwnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPrefixOwnerException(Throwable cause) {
        super(cause);
    }

}
