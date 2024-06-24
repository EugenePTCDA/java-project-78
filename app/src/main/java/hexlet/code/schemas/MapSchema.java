package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {

    private Map<String, BaseSchema<String>> shapeSchemas = new HashMap<>();

    public MapSchema required() {
        addValidator("required", value -> value != null);
        return this;
    }

    public void sizeof(int size) {
        addValidator("sizeof", value -> value == null || value.size() == size);
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        this.shapeSchemas = schemas;
        addValidator("shape", value -> {
            if (value == null) {
                return false;
            }
            for (Map.Entry<String, BaseSchema<String>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!schema.isValid(value.get(key))) {
                    return false;
                }
            }
            return true;
        });
    }
}
