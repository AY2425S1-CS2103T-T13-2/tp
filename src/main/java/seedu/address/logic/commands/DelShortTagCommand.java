package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Alias;

/**
 * DelShortTagCommand to delete a shortcut when tagging
 */
public class DelShortTagCommand extends Command {
    public static final String COMMAND_WORD = "delShortTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a shortcut when tagging.\n"
            + "Usage: delShortTag al/v";
    public static final String MESSAGE_SUCCESS = "Shortcut removed: %1$s";
    public static final String MESSAGE_ALIAS_NOT_FOUND = "This alias does not exist: %1$s";

    private Alias alias;

    /**
     * Creates an instance of DelShortTagCommand using alias
     */
    public DelShortTagCommand(Alias alias) {
        requireAllNonNull(alias);
        this.alias = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getAliasToTagNameMapping().containsKey(alias)) {
            throw new CommandException(String.format(MESSAGE_ALIAS_NOT_FOUND, alias));
        }

        // Remove the alias-to-TagName mapping
        model.delShortTag(alias);
        return new CommandResult(String.format(MESSAGE_SUCCESS, alias));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DelShortTagCommand)) {
            return false;
        }

        DelShortTagCommand otherDelShortTagCommand = (DelShortTagCommand) other;
        return alias.equals(otherDelShortTagCommand.alias);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("alias", alias)
                .toString();
    }
}
