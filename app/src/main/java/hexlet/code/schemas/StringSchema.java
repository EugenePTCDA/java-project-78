package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidator("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidator("minLength", value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addValidator("contains", value -> value == null || value.contains(substring));
        return this;
    }
}
