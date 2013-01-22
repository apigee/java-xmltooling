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
import org.opensaml.xml.signature.DEREncodedKeyValue;

/**
 *
 */
public class DEREncodedKeyValueTest extends XMLObjectProviderBaseTestCase {
    
    private String expectedID;
    
    private String expectedStringContent;
    
    /**
     * Constructor
     *
     */
    public DEREncodedKeyValueTest() {
        singleElementFile = "/data/org/opensaml/xml/signature/impl/DEREncodedKeyValue.xml";
        singleElementOptionalAttributesFile = "/data/org/opensaml/xml/signature/impl/DEREncodedKeyValueOptionalAttributes.xml"; 
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();

        expectedID = "bar";
        expectedStringContent = "someDEREncodedKey";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        DEREncodedKeyValue der = (DEREncodedKeyValue) unmarshallElement(singleElementFile);
        
        assertNotNull("DEREncodedKeyValue", der);
        assertEquals("DEREncodedKeyValue value", der.getValue(), expectedStringContent);
    }
    
    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        DEREncodedKeyValue der = (DEREncodedKeyValue) unmarshallElement(singleElementOptionalAttributesFile);
        
        assertNotNull("DEREncodedKeyValue", der);
        assertEquals("Id attribute", expectedID, der.getID());
        assertEquals("DEREncodedKeyValue value", der.getValue(), expectedStringContent);
        assertEquals(der.resolveIDFromRoot(expectedID), der);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        DEREncodedKeyValue der = (DEREncodedKeyValue) buildXMLObject(DEREncodedKeyValue.DEFAULT_ELEMENT_NAME);
        
        der.setValue(expectedStringContent);
        
        assertEquals(expectedDOM, der);
    }
    
    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        DEREncodedKeyValue der = (DEREncodedKeyValue) buildXMLObject(DEREncodedKeyValue.DEFAULT_ELEMENT_NAME);

        der.setID(expectedID);
        der.setValue(expectedStringContent);
        
        assertEquals(expectedOptionalAttributesDOM, der);
        assertEquals(der.resolveIDFromRoot(expectedID), der);
    }

}