package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private final Validator validator = new Validator();
    private MapSchema schema;

    @BeforeEach
    public final void setUp() {
        schema = validator.map();
    }

    @Test
    public final void testIsValid() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public final void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    public final void testSizeof() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");

        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public final void testCombined() {
        schema.required();
        schema.sizeof(2);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public final void testShape() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
