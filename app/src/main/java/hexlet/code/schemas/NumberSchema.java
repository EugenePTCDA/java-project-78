package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidator("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addValidator("positive", value -> value == null || value > 0);
        return this;
    }

    public void range(int min, int max) {
        addValidator("range", value -> value == null || (value >= min && value <= max));
    }
}
