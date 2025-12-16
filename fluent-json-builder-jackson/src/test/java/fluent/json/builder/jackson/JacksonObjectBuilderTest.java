package fluent.json.builder.jackson;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class JacksonObjectBuilderTest {
    @Test
    void buildReturnsEmptyRootObject() {
        var objBuidlder = JacksonObjectBuilder.builder();
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{}");
    }

    @Test
    void buildReturnsObjectWithChildWhenAdded() {
        var objBuidlder = JacksonObjectBuilder.builder().addObject("testObject", factory -> factory);
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"testObject\":{}}");
    }

    @Test
    void buildReturnsObjectWithChildAndGrandChild() {
        var objBuidlder = JacksonObjectBuilder.builder().addObject("testObject", factory -> factory.addObject("propertyName", objectFactory -> objectFactory));
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"testObject\":{\"propertyName\":{}}}");
    }

    @Test
    void buildReturnsObjectWithStringPropertyWhenAdded() {
        var objBuidlder = JacksonObjectBuilder.builder().addProperty("stringKey", "stringValue");
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"stringKey\":\"stringValue\"}");
    }

    @Test
    void buildReturnsObjectWithChildWithStringProperty() {
        var objBuidlder = JacksonObjectBuilder.builder().addObject("child", objectFactory -> objectFactory.addProperty("stringKey", "stringValue"));
        var obj = objBuidlder.build();

        assertThat(obj).isNotNull().hasToString("{\"child\":{\"stringKey\":\"stringValue\"}}");
    }
}