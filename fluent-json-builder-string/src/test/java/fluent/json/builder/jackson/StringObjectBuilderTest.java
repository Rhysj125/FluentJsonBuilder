package fluent.json.builder.jackson;

import fluent.json.builder.core.JsonObjectBuilderTest;
import fluent.json.builder.string.StringObjectBuilder;

class StringObjectBuilderTest extends JsonObjectBuilderTest<String> {
    public StringObjectBuilderTest() {
        super(StringObjectBuilder.builder());
    }
}