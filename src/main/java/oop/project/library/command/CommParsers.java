package oop.project.library.command;

import oop.project.library.input.BasicArgs;

import java.util.List;
import java.util.Map;

public final class CommParsers {

    private CommParsers() {}

    public static ParsedComm parsePositional(CommSpec spec, BasicArgs args) {
        ParsedComm result = new ParsedComm();
        List<String> positional = args.positional();

        if (positional.size() != spec.getPositionalArgs().size()) {
            throw new IllegalArgumentException("incorrect # of positional arguments.");
        }

        for (int i = 0; i < spec.getPositionalArgs().size(); i++) {
            CommSpec.ArgSpec arg = spec.getPositionalArgs().get(i);

            try {
                Object parsedValue = arg.getParser().parse(positional.get(i));
                result.put(arg.getName(), parsedValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public static ParsedComm parseNamed(CommSpec spec, BasicArgs args) {
        ParsedComm result = new ParsedComm();
        Map<String, String> named = args.named();

        if (named.size() != spec.getNamedArgs().size()) {
            throw new IllegalArgumentException("incorrect # of named arguments.");
        }

        for (CommSpec.ArgSpec arg : spec.getNamedArgs()) {
            if (!named.containsKey(arg.getName())) {
                throw new IllegalArgumentException("missing named argument: " + arg.getName());
            }

            String rawValue = named.get(arg.getName());

            try {
                Object parsedValue = arg.getParser().parse(rawValue);
                result.put(arg.getName(), parsedValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}