package fluent.json.builder.gson;

import com.google.gson.JsonObject;
import fluent.json.builder.core.JsonObjectBuilderTest;

class GsonObjectBuilderTest extends JsonObjectBuilderTest<JsonObject> {
    public GsonObjectBuilderTest() {
        super(GsonObjectBuilder.builder());
    }
}