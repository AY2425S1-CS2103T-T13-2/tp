package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;


/**
 * Finds and lists all persons in address book whose name or phone number contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose names or phone numbers contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... or [p/PHONE [MORE_PHONE_NUMBERS]...]\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie or " + COMMAND_WORD + " 8123 4567";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = requireNonNull(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;

//        // Compare the predicates (which should be named classes like NameContainsKeywordsPredicate)
//        if (predicate instanceof NameContainsKeywordsPredicate && otherFindCommand.predicate instanceof NameContainsKeywordsPredicate) {
//            return predicate.equals(otherFindCommand.predicate);
//        }
//
//        if (predicate instanceof PhoneContainsKeywordsPredicate && otherFindCommand.predicate instanceof PhoneContainsKeywordsPredicate) {
//            return predicate.equals(otherFindCommand.predicate);
//        }
//
//        // Return false if predicates are not the same type
//        return false;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
