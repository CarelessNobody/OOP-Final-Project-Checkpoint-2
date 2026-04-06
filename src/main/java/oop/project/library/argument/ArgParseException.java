package oop.project.library.argument;

public class ArgParseException extends RuntimeException {

    String input;

    public ArgParseException(String message, String input) {
        super(message);
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
