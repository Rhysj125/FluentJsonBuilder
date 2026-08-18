package fluent.json.builder.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class JsonObjectBuilderTest<T> {
    private final JsonObjectBuilder<T> builder;

    public JsonObjectBuilderTest(JsonObjectBuilder<T> builder) {
        this.builder = builder;
    }

    @Test
    void buildReturnsEmptyRootObject() {
        var obj = builder.build();

        assertThat(obj).isNotNull().hasToString("{}");
    }

    @Test
    void buildReturnsObjectWithChildWhenAdded() {
        var objBuidlder = builder.addObject("testObject", factory -> factory);
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"testObject\":{}}");
    }

    @Test
    void buildReturnsObjectWithChildAndGrandChild() {
        var objBuidlder = builder
                .addObject("testObject",
                        factory -> factory
                                .addObject("propertyName", objectFactory -> objectFactory)
                );
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"testObject\":{\"propertyName\":{}}}");
    }

    @Test
    void buildReturnsObjectWithStringPropertyWhenAdded() {
        var objBuidlder = builder.addProperty("stringKey", "stringValue");
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"stringKey\":\"stringValue\"}");
    }

    @Test
    void buildReturnsObjectWithChildWithStringProperty() {
        var objBuidlder = builder.addObject("child", objectFactory -> objectFactory.addProperty("stringKey", "stringValue"));
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"child\":{\"stringKey\":\"stringValue\"}}");
    }

    @Test
    void buildReturnsObjectWithChildWithArrayProperty() {
        var objBuidlder = builder.addArray("array", "one", "two");
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"array\":[\"one\",\"two\"]}");
    }

    @Test
    void buildReturnsObjectWithChildWithArrayOfObjectsProperty() {
        var objBuidlder = builder.addArray("array", factory -> factory.addProperty("child", "value"));
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"array\":[{\"child\":\"value\"}]}");
    }
}
