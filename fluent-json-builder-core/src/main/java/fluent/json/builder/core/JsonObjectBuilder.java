package fluent.json.builder.core;

import java.util.function.UnaryOperator;

public interface JsonObjectBuilder<T> {   
    JsonObjectBuilder<T> addObject(String propertyName, UnaryOperator<JsonObjectBuilder<T>> objectFactory);

    JsonObjectBuilder<T> addArray(String propertyName, String... values);
    JsonObjectBuilder<T> addArray(String propertyName, Integer... values);
    JsonObjectBuilder<T> addArray(String propertyName, Boolean... values);
    JsonObjectBuilder<T> addArray(String propertyName, UnaryOperator<JsonObjectBuilder<T>>... values);

    JsonObjectBuilder<T> addProperty(String propertyName, String value);
    JsonObjectBuilder<T> addProperty(String propertyName, Integer value);
    JsonObjectBuilder<T> addProperty(String propertyName, Boolean value);
    
    T build();
}