package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {

    public MapSchema required() {
        validators.add(value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        validators.add(value -> value == null || value.size() == size);
        return this;
    }
}
