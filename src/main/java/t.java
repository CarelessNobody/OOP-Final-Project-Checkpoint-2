import oop.project.library.argument.ArgParseException;
import oop.project.library.argument.ParseArgType;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

enum Types {
    GRASS,
    WATER,
    FIRE;

    @Override
    public String toString() {
        return "HELLO! I AM " + this.name();
    }
}
<T> void testCustomInputs(String[] inputs, Function<String, T> parser) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            T num = ParseArgType.parse(input, parser);
            System.out.println(num);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}

<T> void testCustomInputs(String[] inputs, Function<String, T> parser, List<T> choices) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            T num = ParseArgType.parse(input, parser, choices);
            System.out.println(num);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}

void testParseBoolean(String[] inputs) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            Boolean bool = ParseArgType.parseBoolean(input);
            System.out.println(bool);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}

void testParseInt(String[] inputs) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            int num = ParseArgType.parseInt(input);
            System.out.println(num);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}

void testParseDouble(String[] inputs) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            Double num = ParseArgType.parseDouble(input);
            System.out.println(num);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}

void testParseString(String[] inputs) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            String num = ParseArgType.parseString(input);
            System.out.println(num);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}

void testParseIntRange(String[] inputs, int min, int max) {
    System.out.println("-----------------------------");
    for (String input: inputs) {
        try {
            int num = ParseArgType.parseInt(input, min, max);
            System.out.println(num);
        } catch (ArgParseException e) {
            System.out.println(e.getMessage() + ": " + e.getInput());
        }
    }
    System.out.println("-----------------------------");
}


void main() {
    String[] booleanInputs = {"true", "false", "True", "TRUE", ""};
    String[] intInputs= {"1", "-5", "0", "4390931", "bad", "10e999999"};
    String[] doubleInputs = {"1.0", "-5.23", "0", "-2.322", "bad"};
    String[] stringInputs = {"1", "What is up!", "this\n", ""};
    String[] dateInputs = {"2024-10-23", "2024-10-32", "Tuesday"};
    String[] stringChoiceInputs = {"easy", "hard", "normal"};
    String[] customInputs = {"2", "3", "5", "20"};
    String[] enumInputs = {"GRASS", "WATER", "FIRE", "FIRE"};

    testParseBoolean(booleanInputs);
    testParseInt(intInputs);
    testParseDouble(doubleInputs);
    testParseString(stringInputs);
    testCustomInputs(dateInputs, LocalDate::parse);
    testCustomInputs(stringChoiceInputs, String::valueOf, List.of("easy", "medium", "hard")); //Should add better way for ranges of numbers
    testCustomInputs(customInputs, (s) -> {
        BigInteger num = new BigInteger(s);
        if (num.isProbablePrime(1000))
            return num;
        throw new IllegalArgumentException("Input is (probably) not a prime");
    });
    testParseIntRange(intInputs, 0, Integer.MAX_VALUE);
    testParseIntRange(intInputs, Integer.MIN_VALUE, Integer.MAX_VALUE);
    testParseIntRange(intInputs, 1, 1);
    testCustomInputs(intInputs, Integer::parseInt, List.of(1,2,3,4,5));
    testCustomInputs(enumInputs, (s) -> {
        for (Types type: Types.values()) {
            if (type.name().equals(s))
                return type;
        }
        throw new IllegalArgumentException("Input does not match existing enum");
    }, List.of(Types.GRASS));
}