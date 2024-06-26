package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public final void setUp() {
        Validator validator = new Validator();
        schema = validator.number();
    }

    @Test
    public final void testIsValid() {
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
    }

    @Test
    public final void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    public final void testPositive() {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    public final void testRange() {
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public final void testCombined() {
        schema.required();
        schema.positive();
        schema.range(5, 10);
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(-1));
    }
}
