package oop.project.library.scenarios;

import oop.project.library.argument.ParseArgType;
import oop.project.library.command.CommParsers;
import oop.project.library.command.CommSpec;
import oop.project.library.command.ParsedComm;
import oop.project.library.input.BasicArgs;
import oop.project.library.input.Input;

import java.util.Map;

public final class CommandScenarios {

    private CommandScenarios() {}

    public static Map<String, Object> mul(String arguments) throws RuntimeException {
        try {
            BasicArgs args = new Input(arguments).parseBasicArgs();

            CommSpec spec = new CommSpec("mul").addPositional("left", ParseArgType::parseInt).addPositional("right", ParseArgType::parseInt);

            ParsedComm result = CommParsers.parsePositional(spec, args);
            return result.asMap();
        } catch (RuntimeException e) {
            throw new RuntimeException("invalid mul command", e);
        }
    }

    public static Map<String, Object> div(String arguments) throws RuntimeException {
        try {
            BasicArgs args = new Input(arguments).parseBasicArgs();

            String rightRaw = args.named().get("right");
            CommSpec spec = new CommSpec("div").addNamed("left", ParseArgType::parseDouble).addNamed("right", ParseArgType::parseDouble);

            ParsedComm result = CommParsers.parseNamed(spec, args);
            return result.asMap();
        } catch (RuntimeException e) {
            throw new RuntimeException("invalid div command", e);
        }
    }

    public static Map<String, Object> echo(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (MVP)");
    }

    public static Map<String, Object> search(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (MVP)");
    }

    public static Map<String, Object> dispatch(String arguments) throws RuntimeException {
        throw new UnsupportedOperationException("TODO (MVP)");
    }
}