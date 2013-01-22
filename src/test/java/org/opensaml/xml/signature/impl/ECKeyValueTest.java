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
import org.opensaml.xml.signature.ECKeyValue;
import org.opensaml.xml.signature.KeyInfoReference;
import org.opensaml.xml.signature.NamedCurve;
import org.opensaml.xml.signature.PublicKey;

/**
 *
 */
public class ECKeyValueTest extends XMLObjectProviderBaseTestCase {

    private String expectedID;
    
    /**
     * Constructor
     *
     */
    public ECKeyValueTest() {
        singleElementFile = "/data/org/opensaml/xml/signature/impl/ECKeyValue.xml";
        singleElementOptionalAttributesFile = "/data/org/opensaml/xml/signature/impl/ECKeyValueOptionalAttributes.xml"; 
        childElementsFile = "/data/org/opensaml/xml/signature/impl/ECKeyValueChildElements.xml";
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();

        expectedID = "bar";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        ECKeyValue keyValue = (ECKeyValue) unmarshallElement(singleElementFile);
        
        assertNotNull("ECKeyValue", keyValue);
        assertNull("NamedCurve child element", keyValue.getNamedCurve());
        assertNull("PublicKey child element", keyValue.getPublicKey());
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        ECKeyValue keyValue = (ECKeyValue) unmarshallElement(singleElementOptionalAttributesFile);
        
        assertNotNull("ECKeyValue", keyValue);
        assertEquals("Id attribute", expectedID, keyValue.getID());
        assertEquals(keyValue.resolveIDFromRoot(expectedID), keyValue);
        assertNull("NamedCurve child element", keyValue.getNamedCurve());
        assertNull("PublicKey child element", keyValue.getPublicKey());
    }
    
    /** {@inheritDoc} */
    public void testChildElementsUnmarshall() {
        ECKeyValue keyValue = (ECKeyValue) unmarshallElement(childElementsFile);
        
        assertNotNull("ECKeyValue", keyValue);
        assertNotNull("NamedCurve child element", keyValue.getNamedCurve());
        assertNotNull("PublicKey child element", keyValue.getPublicKey());
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        ECKeyValue keyValue = (ECKeyValue) buildXMLObject(ECKeyValue.DEFAULT_ELEMENT_NAME);
        
        assertEquals(expectedDOM, keyValue);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        ECKeyValue keyValue = (ECKeyValue) buildXMLObject(ECKeyValue.DEFAULT_ELEMENT_NAME);

        keyValue.setID(expectedID);
        
        assertEquals(expectedOptionalAttributesDOM, keyValue);
        assertEquals(keyValue.resolveIDFromRoot(expectedID), keyValue);
    }
    
    /** {@inheritDoc} */
    public void testChildElementsMarshall() {
        ECKeyValue keyValue = (ECKeyValue) buildXMLObject(ECKeyValue.DEFAULT_ELEMENT_NAME);
        
        keyValue.setNamedCurve((NamedCurve) buildXMLObject(NamedCurve.DEFAULT_ELEMENT_NAME));
        keyValue.setPublicKey((PublicKey) buildXMLObject(PublicKey.DEFAULT_ELEMENT_NAME));
        
        assertEquals(expectedChildElementsDOM, keyValue);
    }

}