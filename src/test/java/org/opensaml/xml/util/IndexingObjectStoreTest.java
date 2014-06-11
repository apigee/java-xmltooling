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

package org.opensaml.xml.util;

import junit.framework.TestCase;

/** Unit test for {@link IndexingObjectStore }. */
public class IndexingObjectStoreTest extends TestCase {

    public void testIndexingObjectStore() {
        IndexingObjectStore<String> store = new IndexingObjectStore<String>();

        String str1 = new String("foo");
        String str2 = new String("bar");

        assertTrue(store.isEmpty());
        assertEquals(0, store.size());

        String nullIndex = store.put(null);
        assertNull(nullIndex);
        assertTrue(store.isEmpty());
        assertEquals(0, store.size());

        String str1Index = store.put(str1);
        assertTrue(store.contains(str1Index));
        assertFalse(store.isEmpty());
        assertEquals(1, store.size());
        assertEquals(str1, store.get(str1Index));

        String index1 = store.put("foo");
        assertTrue(store.contains(index1));
        assertFalse(store.isEmpty());
        assertEquals(1, store.size());
        assertEquals(str1Index, index1);
        assertEquals(str1, store.get(index1));

        store.remove(str1Index);
        assertTrue(store.contains(index1));
        assertFalse(store.isEmpty());
        assertEquals(1, store.size());
        assertEquals(str1Index, index1);
        assertEquals(str1, store.get(index1));

        String str2Index = store.put(str2);
        assertTrue(store.contains(str2Index));
        assertFalse(store.isEmpty());
        assertEquals(2, store.size());
        assertEquals(str2, store.get(str2Index));

        store.remove(str1Index);
        assertFalse(store.contains(str1Index));
        assertFalse(store.isEmpty());
        assertEquals(1, store.size());
        assertNull(store.get(str1Index));

        store.clear();
        assertTrue(store.isEmpty());
        assertEquals(0, store.size());
    }
    
    public void testCollision() {
        /*
         * These String values have the same hashCode() value, using the
         * algorithm that is documented as part of the Java 6 API. As it
         * is part of the API, we do not expect it to vary between
         * implementations.
         */
        final String s1 = "FB";
        final String s2 = "Ea";
        assertEquals(s1.hashCode(), s2.hashCode());

        final IndexingObjectStore<String> store = new IndexingObjectStore<String>();

        final String s1index = store.put(s1);
        final String s2index = store.put(s2);
        assertFalse(s1index.equals(s2index));
        assertTrue(store.contains(s1index));
        assertTrue(store.contains(s2index));
        assertEquals(s1, store.get(s1index));
        assertEquals(s2, store.get(s2index));
    }
}