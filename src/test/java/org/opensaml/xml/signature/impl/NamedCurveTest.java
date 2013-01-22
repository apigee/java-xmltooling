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
import org.opensaml.xml.signature.NamedCurve;

public class NamedCurveTest extends XMLObjectProviderBaseTestCase {
    
    private String expectedURI;
    
    /**
     * Constructor.
     *
     */
    public NamedCurveTest() {
        singleElementFile = "/data/org/opensaml/xml/signature/impl/NamedCurve.xml";
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();
        
        expectedURI = "urn:oid:1.1.1.1";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        NamedCurve nc = (NamedCurve) unmarshallElement(singleElementFile);
        
        assertNotNull("NamedCurve", nc);
        assertEquals("URI attribute", expectedURI, nc.getURI());
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        NamedCurve nc = (NamedCurve) buildXMLObject(NamedCurve.DEFAULT_ELEMENT_NAME);
        
        nc.setURI(expectedURI);
        
        assertEquals(expectedDOM, nc);
    }
    
}