/*
 * Copyright (C) 2014 Marand
 */

package org.openehr.adl.parser.adl14;

import org.openehr.adl.ParserTestBase;
import org.openehr.jaxb.am.Archetype;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BasicGenericTypeTest extends ParserTestBase {
    @Test
    public void testParse() throws Exception {
        Archetype archetype = parseArchetype("adl14/adl-test-SOME_TYPE.generic_type_basic.draft.adl");
        assertThat(archetype).isNotNull();
    }
}