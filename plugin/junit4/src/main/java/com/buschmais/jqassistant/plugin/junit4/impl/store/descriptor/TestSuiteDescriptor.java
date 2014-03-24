package com.buschmais.jqassistant.plugin.junit4.impl.store.descriptor;

import com.buschmais.jqassistant.core.store.api.descriptor.FileDescriptor;
import com.buschmais.jqassistant.core.store.api.descriptor.NamedDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Property;
import com.buschmais.xo.neo4j.api.annotation.Relation;

import java.util.List;

@Label("TESTSUITE")
public interface TestSuiteDescriptor extends NamedDescriptor, FileDescriptor {

    @Property("TESTS")
    int getTests();

    void setTests(int tests);

    @Property("FAILURES")
    int getFailures();

    void setFailures(int failures);

    @Property("ERRORS")
    int getErrors();

    void setErrors(int errors);

    @Property("SKIPPED")
    int getSkipped();

    void setSkipped(int skipped);

    @Property("TIME")
    float getTime();

    void setTime(float time);

    @Relation("CONTAINS")
    List<TestCaseDescriptor> getTestCases();
}
