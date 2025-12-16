package fluent.json.builder.gson;

import java.util.function.UnaryOperator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fluent.json.builder.core.JsonObjectBuilder;

public class GsonObjectBuilder implements JsonObjectBuilder<JsonObject> {
    private final JsonObject root;

    public GsonObjectBuilder() {
        this.root = new JsonObject();
    }

    public static GsonObjectBuilder builder() {
        return new GsonObjectBuilder();
    }

    @Override
    public JsonObjectBuilder<JsonObject> addObject(String propertyName, UnaryOperator<JsonObjectBuilder<JsonObject>> objectFactory) {
        var childObjectFactory = GsonObjectBuilder.builder();
        var createdChildObject = objectFactory.apply(childObjectFactory).build();
        root.add(propertyName, createdChildObject);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addArray(String propertyName, String... values) {
        var array = new JsonArray();
        for(var value : values) {
            array.add(value);
        }
        
        root.add(propertyName, array);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addArray(String propertyName, Integer... values) {
        var array = new JsonArray();
        for(var value : values) {
            array.add(value);
        }
        
        root.add(propertyName, array);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addArray(String propertyName, Boolean... values) {
        var array = new JsonArray();
        for(var value : values) {
            array.add(value);
        }
        
        root.add(propertyName, array);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addArray(String propertyName, UnaryOperator<JsonObjectBuilder<JsonObject>>... factories) {
        var array = new JsonArray();
        for(var factory : factories) {
            var builder = GsonObjectBuilder.builder();
            var obj = factory.apply(builder).build();
            array.add(obj);
        }
        
        root.add(propertyName, array);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addProperty(String propertyName, String value) {
        root.addProperty(propertyName, value);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addProperty(String propertyName, Integer value) {
        root.addProperty(propertyName, value);
        return this;
    }

    @Override
    public JsonObjectBuilder<JsonObject> addProperty(String propertyName, Boolean value) {
        root.addProperty(propertyName, value);
        return this;
    }

    @Override
    public JsonObject build() {
        return root;
    }
}