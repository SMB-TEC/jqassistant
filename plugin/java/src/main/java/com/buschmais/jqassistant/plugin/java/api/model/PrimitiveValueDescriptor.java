package com.buschmais.jqassistant.plugin.java.api.model;

import com.buschmais.jqassistant.plugin.common.api.model.ValueDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Property;

/**
 * Represents a primitive value.
 */
@Label("Primitive")
public interface PrimitiveValueDescriptor extends TypedDescriptor, ValueDescriptor<Object> {

    @Property("value")
    @Override
    Object getValue();

    @Override
    void setValue(Object value);
}
