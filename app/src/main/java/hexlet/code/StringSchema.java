package hexlet.code;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        validators.add(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        validators.add(value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        validators.add(value -> value == null || value.contains(substring));
        return this;
    }
}
