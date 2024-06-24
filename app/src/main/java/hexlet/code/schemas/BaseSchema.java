package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> validators = new HashMap<>();

    public final void addValidator(String key, Predicate<T> validator) {
        validators.put(key, validator);
    }

    public final boolean isValid(Object value) {
        for (Predicate<T> validator : validators.values()) {
            if (!validator.test((T) value)) {
                return false;
            }
        }
        return true;
    }
}
