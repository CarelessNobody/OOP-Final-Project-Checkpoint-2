package oop.project.library.scenarios;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import oop.project.library.argument.ParseArgType;
import oop.project.library.command.CommParsers;
import oop.project.library.command.CommSpec;
import oop.project.library.command.ParsedComm;
import oop.project.library.input.BasicArgs;
import oop.project.library.input.Input;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public final class ArgumentScenarios {

    public static Map<String, Object> add(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (PoC)");
    }

    public static Map<String, Object> sub(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (PoC)");
    }

    public static Map<String, Object> fizzbuzz(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (PoC)");
    }

    public static Map<String, Object> difficulty(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (PoC)");
    }

    public static Map<String, Object> date(String arguments) throws RuntimeException {
        try {
            BasicArgs args = new Input(arguments).parseBasicArgs();

            CommSpec spec = new CommSpec("date")
                    .addPositional("date", s -> ParseArgType.parse(s, LocalDate::parse));

            ParsedComm result = CommParsers.parsePositional(spec, args);
            return result.asMap();
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid date command", e);
        }
    }

}
