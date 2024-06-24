package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> validators = new ArrayList<>();

    public boolean isValid(T value) {
        for (Predicate<T> validator : validators) {
            if (!validator.test(value)) {
                return false;
            }
        }
        return true;
    }
}