package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    protected final List<Predicate<String>> validators = new ArrayList<>();

    public StringSchema required() {
        validators.add(s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        validators.add(s -> s != null && s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        validators.add(s -> s != null && s.contains(substring));
        return this;
    }

    public boolean isValid(String data) {
        for (Predicate<String> validator : validators) {
            if (!validator.test(data)) {
                return false;
            }
        }
        return true;
    }
}
