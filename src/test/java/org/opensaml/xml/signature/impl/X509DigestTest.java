/*
 * Licensed to the University Corporation for Advanced Internet Development, 
 * Inc. (UCAID) under one or more contributor license agreements.  See the 
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache 
 * License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.xml.signature.impl;


import org.opensaml.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.signature.X509Digest;

public class X509DigestTest extends XMLObjectProviderBaseTestCase {
    
    private String expectedAlgorithm;
    
    private String expectedStringContent;
    
    /**
     * Constructor.
     *
     */
    public X509DigestTest() {
        singleElementFile = "/data/org/opensaml/xml/signature/impl/X509Digest.xml";
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();
        
        expectedAlgorithm = SignatureConstants.ALGO_ID_DIGEST_SHA1;
        expectedStringContent = "someX509Digest";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        X509Digest digest = (X509Digest) unmarshallElement(singleElementFile);
        
        assertNotNull("X509Digest", digest);
        assertEquals("Algorithm attribute", expectedAlgorithm, digest.getAlgorithm());
        assertEquals("X509Digest value", digest.getValue(), expectedStringContent);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        X509Digest digest = (X509Digest) buildXMLObject(X509Digest.DEFAULT_ELEMENT_NAME);
        
        digest.setValue(expectedStringContent);
        digest.setAlgorithm(expectedAlgorithm);
        
        assertEquals(expectedDOM, digest);
    }
    
}