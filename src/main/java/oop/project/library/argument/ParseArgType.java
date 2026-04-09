package oop.project.library.argument;

import java.util.List;
import java.util.function.Function;

public final class ParseArgType {
    private ParseArgType() {}

    public static <T> T parse(String input, Function<String, T> parser) throws ArgParseException {
        try {
            return parser.apply(input);
        } catch (Exception e) {
            throw new ArgParseException(e.getMessage(), input);
        }
    }

    public static <T> T parse(String input, Function<String, T> parser, List<T> choices) throws ArgParseException {
        T result = parse(input, parser);

        if (choices.isEmpty())
            return result;

        if (!choices.contains(result))
            throw new ArgParseException(input + " could not be parsed into one of the choice", input);

        return result;
    }

    public static <T extends Comparable<T>> T parse(String input, Function<String, T> parser, T min, T max) throws ArgParseException {
        T result = parse(input, parser);

        //Inclusive
        if (min.compareTo(result) > 0)
            throw new  ArgParseException(input + " is less than the minimum", input);
        if (max.compareTo(result) < 0)
            throw new  ArgParseException(input + " is greater than the maximum", input);

        return result;
    }

    public static String parseString(String input) throws ArgParseException {
        return parse(input, String::valueOf);
    }

    public static Integer parseInt(String input) throws ArgParseException {
        return parse(input, Integer::valueOf);
    }

    public static Integer parseInt(String input, Integer min, Integer max) throws ArgParseException {
        return parse(input, Integer::valueOf, min, max);
    }

    public static Double parseDouble(String input) throws ArgParseException {
        return parse(input, Double::valueOf);
    }

    public static Double parseDouble(String input, Double min, Double max) throws ArgParseException {
        return parse(input, Double::valueOf, min, max);
    }

    public static boolean parseBoolean(String input) throws ArgParseException {
        //Does not use the built-in boolean parser
        return parse(input, (s) -> {
            if (s.equals("true")) {
                return true;
            } else if (s.equals("false")) {
                return false;
            } else {
                throw new IllegalArgumentException("Input is not a boolean");
            }
        });
    }
}