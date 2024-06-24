package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private Validator validator;
    private MapSchema schema;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    public void testIsValid() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSizeof() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");

        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testCombined() {
        schema.required().sizeof(2);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }
}
