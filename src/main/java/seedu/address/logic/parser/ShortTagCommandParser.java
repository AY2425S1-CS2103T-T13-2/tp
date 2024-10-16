package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGNAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.ShortTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Alias;
import seedu.address.model.tag.TagName;

/**
 * ShortTagCommand Parser to parse the arguments of the short tag command
 */
public class ShortTagCommandParser implements Parser<ShortTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShortTagCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShortTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_TAGNAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS, PREFIX_TAGNAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShortTagCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ALIAS, PREFIX_TAGNAME);
        TagName tagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAGNAME).get());
        Alias alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());
        return new ShortTagCommand(alias, tagName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
