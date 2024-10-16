package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Alias;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;

/**
 * ShortTagCommand to create a shortcut when tagging
 */
public class ShortTagCommand extends Command {
    public static final String COMMAND_WORD = "shortTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a shortcut when tagging.\n"
                                                + "Usage: shortTag al/v tn/Vegan";
    public static final String MESSAGE_SUCCESS = "New Shortcut added: %1$s";
    private Alias alias;
    private TagName tagName;

    /**
     * to create an instance of ShortTagCommand using alias and tagName
     */
    public ShortTagCommand(Alias alias, TagName tagName) {
        requireAllNonNull(alias, tagName);
        this.alias = alias;
        this.tagName = tagName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Tag.addAliasTagNameMapping(alias, tagName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatTag(alias, tagName)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShortTagCommand)) {
            return false;
        }

        ShortTagCommand otherShortTagCommand = (ShortTagCommand) other;
        return alias.equals(otherShortTagCommand.alias) && tagName.equals(otherShortTagCommand.tagName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("alias", alias)
                .add("tag name", tagName)
                .toString();
    }
}
