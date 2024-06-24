package hexlet.code;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema schema = v.string();
    }
}
