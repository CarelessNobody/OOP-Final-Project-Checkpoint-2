package oop.project.library.command;

import java.util.ArrayList;
import java.util.List;

public class CommSpec {

    private final String name;
    private final List<ArgSpec> positionalArgs;
    private final List<ArgSpec> namedArgs;

    public CommSpec(String name) {
        this.name = name;
        this.positionalArgs = new ArrayList<ArgSpec>();
        this.namedArgs = new ArrayList<ArgSpec>();
    }

    public String getName() {
        return name;
    }

    public List<ArgSpec> getPositionalArgs() {
        return positionalArgs;
    }

    public List<ArgSpec> getNamedArgs() {
        return namedArgs;
    }

    public CommSpec addPositional(String name, Parser parser) {
        positionalArgs.add(new ArgSpec(name, parser, false));
        return this;
    }

    public CommSpec addNamed(String name, Parser parser) {
        namedArgs.add(new ArgSpec(name, parser, true));
        return this;
    }

    public ArgSpec findNamedArg(String name) {
        for (ArgSpec arg : namedArgs) {
            if (arg.getName().equals(name)) {
                return arg;
            }
        }
        return null;
    }

    @FunctionalInterface
    public interface Parser {
        Object parse(String input) throws Exception;
    }

    public static class ArgSpec {
        private final String name;
        private final Parser parser;
        private final boolean named;

        public ArgSpec(String name, Parser parser, boolean named) {
            this.name = name;
            this.parser = parser;
            this.named = named;
        }

        public String getName() {
            return name;
        }

        public Parser getParser() {
            return parser;
        }

        public boolean isNamed() {
            return named;
        }
    }
}