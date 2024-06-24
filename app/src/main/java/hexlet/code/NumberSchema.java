package hexlet.code;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        validators.add(value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        validators.add(value -> value == null || value > 0);
        return this;
    }

    public void range(int min, int max) {
        validators.add(value -> value == null || (value >= min && value <= max));
    }
}
