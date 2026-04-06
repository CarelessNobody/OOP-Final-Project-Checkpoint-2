package oop.project.library.argument;

import java.util.List;
import java.util.function.Function;

public class ParseArgType {
    private ParseArgType() {}

    public static <T> T parse(String input, Function<String, T> parser) {
        try {
            return parser.apply(input);
        } catch (Exception e) {
            throw new ArgParseException(e.getMessage(), input);
        }
    }

    public static <T> T parse(String input, Function<String, T> parser, List<T> choices) {
        try {
            T result = parser.apply(input);

            if (!choices.contains(result))
                throw new ArgParseException("Invalid choice for input", input);

            return result;
        } catch (Exception e) {
            throw new ArgParseException(e.getMessage(), input);
        }
    }
}