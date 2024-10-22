package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PostalContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        List<String> phoneKeywords = Arrays.stream(keywords)
                .filter(this::isNumeric)
                .filter(keyword -> keyword.length() != 6)
                .collect(Collectors.toList());

        List<String> nameKeywords = Arrays.stream(keywords)
                .filter(keyword -> !isNumeric(keyword) && !isPostalCode(keyword))
                .collect(Collectors.toList());

        List<String> postalKeywords = Arrays.stream(keywords)
                .filter(this::isPostalCode)
                .collect(Collectors.toList());

        Predicate<Person> namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        Predicate<Person> phonePredicate = new PhoneContainsKeywordsPredicate(phoneKeywords);
        Predicate<Person> postalPredicate = new PostalContainsKeywordsPredicate(postalKeywords);

        if (!nameKeywords.isEmpty() && !phoneKeywords.isEmpty() && !postalKeywords.isEmpty()) {
            return new FindCommand(namePredicate.or(phonePredicate).or(postalPredicate));
        } else if (!nameKeywords.isEmpty() && !phoneKeywords.isEmpty()) {
            return new FindCommand(namePredicate.or(phonePredicate));
        } else if (!nameKeywords.isEmpty() && !postalKeywords.isEmpty()) {
            return new FindCommand(namePredicate.or(postalPredicate));
        } else if (!phoneKeywords.isEmpty() && !postalKeywords.isEmpty()) {
            return new FindCommand(phonePredicate.or(postalPredicate));
        } else if (!nameKeywords.isEmpty()) {
            return new FindCommand(namePredicate);
        } else if (!phoneKeywords.isEmpty()) {
            return new FindCommand(phonePredicate);
        } else {
            return new FindCommand(postalPredicate);
        }
    }

    /**
     * Utility method to check if a string is numeric (i.e., contains only phone digits and not 6 digits long).
     * @param str The string to check.
     * @return True if the string is numeric, false otherwise.
     */
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    /**
     * Utility method to check if a string is a postal code (i.e., starts with 'S' and is followed by 6 digits).
     * @param str The string to check.
     * @return True if the string is a postal code, false otherwise.
     */
    private boolean isPostalCode(String str) {
        // checks that the postal code is exactly 6 digits
        return str.matches("\\d{6}");
    }
}
