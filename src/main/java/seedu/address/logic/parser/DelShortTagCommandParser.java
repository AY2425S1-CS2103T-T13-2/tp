package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;

import java.util.stream.Stream;

import seedu.address.logic.commands.DelShortTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Alias;

/**
 * DelShortTagCommandParser to parse the arguments of the del short tag command.
 */
public class DelShortTagCommandParser implements Parser<DelShortTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DelShortTagCommand
     * and returns a DelShortTagCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DelShortTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelShortTagCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ALIAS);
        Alias alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());
        return new DelShortTagCommand(alias);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
