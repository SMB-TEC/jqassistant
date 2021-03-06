package com.buschmais.jqassistant.plugin.java.test.scanner;

import static com.buschmais.jqassistant.plugin.java.test.matcher.TypeDescriptorMatcher.typeDescriptor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.Test;

import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import com.buschmais.jqassistant.plugin.java.test.set.scanner.innerclass.AnonymousInnerClass;

/**
 * Contains test on relations between outer and inner classes.
 */
public class AnonymousInnerClassIT extends AbstractJavaPluginIT {

    private static final String INNERCLASS_NAME = AnonymousInnerClass.class.getName() + "$1";

    /**
     * Scans an outer class.
     * 
     * @throws IOException
     *             If the test fails.
     */
    @Test
    public void outerClass() throws IOException {
        scanClasses(AnonymousInnerClass.class);
        assertOuterClassContainsInnerClass();
    }

    /**
     * Scans an inner class.
     * 
     * @throws IOException
     *             If the test fails.
     */
    @Test
    public void innerClass() throws IOException, ClassNotFoundException {
        scanInnerClass(AnonymousInnerClass.class, "1");
        assertOuterClassContainsInnerClass();
    }

    /**
     * Asserts that the outer class can be fetched and contains a relation to
     * the inner class.
     */
    private void assertOuterClassContainsInnerClass() {
        store.beginTransaction();
        TestResult testResult = query("MATCH (outerClass:Type)-[:DECLARES]->(innerClass:Type) RETURN outerClass");
        assertThat(testResult.getRows().size(), equalTo(1));
        Map<String, Object> row = testResult.getRows().get(0);
        TypeDescriptor outerClass = (TypeDescriptor) row.get("outerClass");
        assertThat(outerClass, typeDescriptor(AnonymousInnerClass.class));
        Matcher<Iterable<? super TypeDescriptor>> matcher = hasItem(typeDescriptor(INNERCLASS_NAME));
        assertThat(outerClass.getDeclaredInnerClasses(), matcher);
        store.commitTransaction();
    }

}
