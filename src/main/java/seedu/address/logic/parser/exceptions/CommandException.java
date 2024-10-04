package seedu.address.logic.parser.exceptions;

/**
 * Represents an error which occurs during command execution.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
