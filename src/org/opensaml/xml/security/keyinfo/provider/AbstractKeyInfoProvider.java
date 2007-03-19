/*
 * Copyright [2007] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.xml.security.keyinfo.provider;

import java.security.Key;

import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialContext;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.security.keyinfo.KeyInfoProvider;
import org.opensaml.xml.signature.KeyInfo;

/**
 * Abstract super class for {@link KeyInfoProvider} implementations.
 */
public abstract class AbstractKeyInfoProvider implements KeyInfoProvider {
    
    /**
     * Build a new credential context based on the KeyInfo being processed.
     * 
     * @param keyInfo the KeyInfo element being processed
     * @param resolver the KeyInfoCredentialResolver that is invoking the provider
     * @return a new KeyInfoCredentialContext 
     * @throws SecurityException if the new credential context could not be built
     */
    protected KeyInfoCredentialContext buildContext(KeyInfo keyInfo, KeyInfoCredentialResolver resolver) 
        throws SecurityException {
        if (resolver == null) {
            throw new SecurityException("Can't create new KeyInfo credential context" 
                    + " because invoking resolver reference was null");
        }
        KeyInfoCredentialContext context = resolver.newCredentialContext();
        context.setKeyInfo(keyInfo);
        return context;
    }
    
    /**
     * Utility method to extract any key that might be present in the specified Credential.
     * 
     * @param cred the Credential to evaluate
     * @return the Key contained in the credential, or null if it does not contain a key.
     */
    protected Key extractKeyValue(Credential cred) {
        if (cred == null) {
            return null;
        }
        if (cred.getPublicKey() != null) {
            return cred.getPublicKey();
        } 
        // This could happen if key is derived, e.g. key agreement, etc
        if (cred.getSecretKey() != null) {
            return cred.getSecretKey();
        }
        // Perhaps unlikely, but go ahead and check
        if (cred.getPrivateKey() != null) {
            return cred.getPrivateKey(); 
        }
        return null;
    }

}
