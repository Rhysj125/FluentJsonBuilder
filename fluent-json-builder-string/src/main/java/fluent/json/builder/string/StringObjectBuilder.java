package fluent.json.builder.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import fluent.json.builder.core.JsonObjectBuilder;

public class StringObjectBuilder implements JsonObjectBuilder<String> {
    private static final String STRING_TEMPLATE = "\"%s\":\"%s\"";
    private static final String NON_STRING_TEMPLATE = "\"%s\":%s";
    private static final String ARRAY_TEMPLATE = "\"%s\":[%s]";

    private final StringBuilder root;

    private StringObjectBuilder() {
        this.root = new StringBuilder();
        root.append("{");
    }

    public static StringObjectBuilder builder() {
        return new StringObjectBuilder();
    }

    @Override
    public JsonObjectBuilder<String> addObject(String propertyName, UnaryOperator<JsonObjectBuilder<String>> objectFactory) {
        var childObjectFactory = StringObjectBuilder.builder();
        var createdChildObject = objectFactory.apply(childObjectFactory).build();
        root.append(NON_STRING_TEMPLATE.formatted(propertyName, createdChildObject));
        return this;
    }

    @Override
    public JsonObjectBuilder<String> addArray(String propertyName, String... values) {
        var arrayString = "\"" + String.join("\",\"", values) + "\"";
        root.append(ARRAY_TEMPLATE.formatted(propertyName, arrayString));
        return this;
    }
    
    @Override
    public JsonObjectBuilder<String> addArray(String propertyName, Integer... values) {
        var arrayString = String.join(",", Arrays.toString(values));
        root.append(ARRAY_TEMPLATE.formatted(propertyName, arrayString));
        return this;
    }
    
    @Override
    public JsonObjectBuilder<String> addArray(String propertyName, Boolean... values) {
        var arrayString = String.join(",", Arrays.toString(values));
        root.append(ARRAY_TEMPLATE.formatted(propertyName, arrayString));
        return this;
    }
    
    @SafeVarargs
    @Override
    public final JsonObjectBuilder<String> addArray(String propertyName, UnaryOperator<JsonObjectBuilder<String>>... values) {
        List<String> objects = new ArrayList<>();

        for(var value : values) {
            StringObjectBuilder builder = StringObjectBuilder.builder();
            var obj = value.apply(builder).build();
            objects.add(obj);
        }

        root.append(ARRAY_TEMPLATE.formatted(propertyName, String.join(",", objects)));
        return this;
    }
    
    @Override
    public JsonObjectBuilder<String> addProperty(String propertyName, String value) {
        root.append(STRING_TEMPLATE.formatted(propertyName, value));
        return this;
    }

    @Override
    public JsonObjectBuilder<String> addProperty(String propertyName, Integer value) {
        root.append(NON_STRING_TEMPLATE.formatted(propertyName, value));
        return this;
    }

    @Override
    public JsonObjectBuilder<String> addProperty(String propertyName, Boolean value) {
        root.append(NON_STRING_TEMPLATE.formatted(propertyName, value));
        return this;
    }

    @Override
    public String build() {
        root.append("}");
        return root.toString();
    }
}