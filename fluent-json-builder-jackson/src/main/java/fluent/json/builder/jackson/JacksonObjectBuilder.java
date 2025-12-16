package fluent.json.builder.jackson;

import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fluent.json.builder.core.JsonObjectBuilder;

public class JacksonObjectBuilder implements JsonObjectBuilder<ObjectNode> {
    private final ObjectNode root;

    private JacksonObjectBuilder() {
        this.root = new ObjectNode(JsonNodeFactory.instance);
    }

    public static JacksonObjectBuilder builder() {
        return new JacksonObjectBuilder();
    }

    @Override
    public JsonObjectBuilder<ObjectNode> addObject(String propertyName, UnaryOperator<JsonObjectBuilder<ObjectNode>> objectFactory) {
        var childObjectFactory = JacksonObjectBuilder.builder();
        var createdChildObject = objectFactory.apply(childObjectFactory).build();
    
        
        root.put(propertyName, createdChildObject);
        return this;
    }

    @Override
    public JsonObjectBuilder<ObjectNode> addProperty(String propertyName, String value) {
        root.put(propertyName, value);
        return this;
    }

    @Override
    public JsonObjectBuilder<ObjectNode> addProperty(String propertyName, Integer value) {
        root.put(propertyName, value);
        return this;
    }

    @Override
    public JsonObjectBuilder<ObjectNode> addProperty(String propertyName, Boolean value) {
        root.put(propertyName, value);
        return this;
    }

    @Override
    public ObjectNode build() {
        return root;
    }
}