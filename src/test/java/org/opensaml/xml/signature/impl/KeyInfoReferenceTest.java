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
import org.opensaml.xml.signature.KeyInfoReference;

/**
 *
 */
public class KeyInfoReferenceTest extends XMLObjectProviderBaseTestCase {
    
    private String expectedID;
    private String expectedURI;
    
    /**
     * Constructor
     *
     */
    public KeyInfoReferenceTest() {
        singleElementFile = "/data/org/opensaml/xml/signature/impl/KeyInfoReference.xml";
        singleElementOptionalAttributesFile = "/data/org/opensaml/xml/signature/impl/KeyInfoReferenceOptionalAttributes.xml"; 
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();

        expectedID = "bar";
        expectedURI = "#foo";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        KeyInfoReference ref = (KeyInfoReference) unmarshallElement(singleElementFile);
        
        assertNotNull("KeyInfoReference", ref);
        assertEquals("URI attribute", expectedURI, ref.getURI());
    }
    
    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        KeyInfoReference ref = (KeyInfoReference) unmarshallElement(singleElementOptionalAttributesFile);
        
        assertNotNull("KeyInfoReference", ref);
        assertEquals("Id attribute", expectedID, ref.getID());
        assertEquals("URI attribute", expectedURI, ref.getURI());
        assertEquals(ref.resolveIDFromRoot(expectedID), ref);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        KeyInfoReference ref = (KeyInfoReference) buildXMLObject(KeyInfoReference.DEFAULT_ELEMENT_NAME);
        
        ref.setURI(expectedURI);
        
        assertEquals(expectedDOM, ref);
    }
    
    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        KeyInfoReference ref = (KeyInfoReference) buildXMLObject(KeyInfoReference.DEFAULT_ELEMENT_NAME);

        ref.setID(expectedID);
        ref.setURI(expectedURI);
        
        assertEquals(expectedOptionalAttributesDOM, ref);
        assertEquals(ref.resolveIDFromRoot(expectedID), ref);
    }

}