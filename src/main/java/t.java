import oop.project.library.argument.ArgParseException;
import oop.project.library.argument.ParseArgType;

<T> void testInputs(String[] inputs, Function<String, T> parser) {
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

<T> void testInputs(String[] inputs, Function<String, T> parser, List<T> choices) {
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

void main() {
    String[] booleanInputs = {"true", "false", "True", "TRUE", ""};
    String[] intInputs= {"1", "-5", "0", "4390931", "bad", "10e999999"};
    String[] doubleInputs = {"1.0", "-5.23", "0", "-2.322", "bad"};
    String[] stringInputs = {"1", "What is up!", "this\n", ""};
    String[] dateInputs = {"2024-10-23", "2024-10-32", "Tuesday"};
    String[] enumInputs = {"easy", "hard", "normal"};
    String[] customInputs = {"2", "3", "5", "20"};

    testInputs(booleanInputs, (s) -> {
        if (s.equals("true")) {
            return true;
        } else if (s.equals("false")) {
            return false;
        } else {
            throw new ArgParseException("Input is not a boolean", s);
        }
    });
    testInputs(intInputs, Integer::parseInt);
    testInputs(doubleInputs, Double::parseDouble);
    testInputs(stringInputs, String::valueOf);
    testInputs(dateInputs, LocalDate::parse);
    testInputs(enumInputs, String::valueOf, List.of("easy", "medium", "hard")); //Should add better way for ranges of numbers
    testInputs(customInputs, (s) -> {
        BigInteger num = new BigInteger(s);
        if (num.isProbablePrime(1000))
            return num;
        throw new ArgParseException("Input is (probably) not a prime", s);
    });
}