package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringSchemaTest {

    private StringSchema schema;

    @BeforeEach
    public void setUp() {
        Validator validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void testRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testMinLength() {
        schema.minLength(5);

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hex"));
    }

    @Test
    public void testContains() {
        schema.contains("wh");

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("hexlet"));
    }

    @Test
    public void testMultipleValidators() {
        schema.required().minLength(5).contains("hex");

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("let"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testOverridingValidators() {
        schema.minLength(10);
        schema.validators.clear();
        schema.minLength(4);

        assertTrue(schema.isValid("Hexlet"));
        assertFalse(schema.isValid("Hex"));
    }
}