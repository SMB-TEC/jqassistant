package com.buschmais.jqassistant.plugin.java.impl.store.descriptor;

import com.buschmais.jqassistant.core.store.api.descriptor.FileDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Relation;

import java.util.Set;

/**
 * A descriptor representing a property file.
 */
@Label(value = "PROPERTIES", usingIndexedPropertyOf = FileDescriptor.class)
public interface PropertyFileDescriptor extends FileDescriptor {

    @Relation("HAS")
    public Set<PropertyDescriptor> getProperties();

}
