package fluent.json.builder.jackson;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fluent.json.builder.core.JsonObjectBuilderTest;

class JacksonObjectBuilderTest extends JsonObjectBuilderTest<ObjectNode> {
    public JacksonObjectBuilderTest() {
        super(JacksonObjectBuilder.builder());
    }
}