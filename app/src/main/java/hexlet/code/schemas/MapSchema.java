package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {

    public MapSchema required() {
        addValidator("required", value -> value != null);
        return this;
    }

    public void sizeof(int size) {
        addValidator("sizeof", value -> value == null || value.size() == size);
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        addValidator("shape", value -> {
            if (value == null) {
                return false;
            }
            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                if (!schema.isValid(value.get(key))) {
                    return false;
                }
            }
            return true;
        });
    }
}
