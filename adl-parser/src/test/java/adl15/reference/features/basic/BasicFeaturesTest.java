/*
 * Copyright (C) 2014 Marand
 */

package adl15.reference.features.basic;

import org.openehr.adl.am.AmQuery;
import org.openehr.adl.util.TestAdlParser;
import org.openehr.jaxb.am.*;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Marko Pipan
 */
public class BasicFeaturesTest {
    @Test
    public void testMixedTypes() {
        Archetype archetype = TestAdlParser.parseAdl(
                "adl15/reference/features/basic/openEHR-EHR-EVALUATION.mixed_types.v1.adls");

        CComplexObject cluster = AmQuery.find(archetype.getDefinition(), "/data[id14]/items[id2]");
        ArchetypeInternalRef internalRef = (ArchetypeInternalRef) cluster.getAttributes().get(0).getChildren().get(0);
        assertThat(internalRef.getTargetPath()).isEqualTo("/data[id14]/items[id11]/items[id13]");
        assertThat(internalRef.getRmTypeName()).isEqualTo("ELEMENT");
        assertThat(internalRef.getNodeId()).isEqualTo("id10");

        ArchetypeSlot slot = (ArchetypeSlot) cluster.getAttributes().get(0).getChildren().get(1);
        assertThat(slot.getIncludes().get(0).getStringExpression()).isEqualTo("archetype_id/value matches {/.*/}");

        assertThat(cluster.getAttributes().get(0).getChildren().get(2)).isInstanceOf(CComplexObject.class);

    }

    @Test
    public void testStructure() {
        Archetype archetype = TestAdlParser.parseAdl(
                "adl15/reference/features/basic/openEHR-TEST_PKG-BOOK.structure_test1.v1.adls");

        assertThat(archetype.getDefinition().getRmTypeName()).isEqualTo("BOOK");

        assertThat(((CString)AmQuery.find(archetype, "title")).getDefaultValue()).isEqualTo("Devils");
        assertThat(((CString)AmQuery.find(archetype, "author")).getDefaultValue()).isEqualTo("Fyodor Dostoyevsky");
        assertThat(((CString)AmQuery.find(archetype, "chapters[id2]/title")).getDefaultValue()).isEqualTo("By way of introductoin");
        assertThat(((CString)AmQuery.find(archetype, "chapters[id3]/title")).getDefaultValue()).isEqualTo("Prince Harry, matchmaking");
        assertThat(((CString)AmQuery.find(archetype, "chapters[id4]/title")).getDefaultValue()).isEqualTo("Another man's sins");

    }

    @Test
    public void testAssumedTypes() {
        Archetype archetype = TestAdlParser.parseAdl(
                "adl15/reference/features/basic/openEHR-TEST_PKG-WHOLE.assumed_types.v1.adls");
        assertThat(archetype.getDefinition().getAttributes().get(0).getRmAttributeName()).isEqualTo("string_attr1");
    }

    @Test
    public void testBasicTypes() {
        Archetype archetype = TestAdlParser.parseAdl(
                "adl15/reference/features/basic/openEHR-TEST_PKG-WHOLE.basic_types.v1.adls");
        assertThat(archetype.getDefinition().getAttributes().get(0).getRmAttributeName()).isEqualTo("string_attr1");

    }
    @Test
    public void testMostMinimal1() {
        Archetype archetype = TestAdlParser.parseAdl(
                "adl15/reference/features/basic/openEHR-TEST_PKG-WHOLE.most_minimal.v1.adls");
        assertThat(archetype.getArchetypeId().getValue()).isEqualTo("openehr-TEST_PKG-WHOLE.most_minimal.v1.0.0");
    }


}
