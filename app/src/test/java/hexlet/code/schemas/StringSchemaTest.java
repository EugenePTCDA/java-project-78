package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private StringSchema schema;

    @BeforeEach
    public void setUp() {
        Validator validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void testIsValid() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    public void testMinLength() {
        schema.minLength(5);
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
    }

    @Test
    public void testContains() {
        schema.contains("wh");
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("the fox"));

        schema.contains("whatthe");
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void testCombined() {
        schema.required();
        schema.minLength(10);
        schema.minLength(5);
        schema.contains("wh");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("the fox"));
    }
}
