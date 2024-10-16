package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.ALIAS_DESC_VEGAN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ALIAS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_VEGAN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DelShortTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Alias;

public class DelShortTagCommandParserTest {

    private DelShortTagCommandParser parser = new DelShortTagCommandParser();

    @Test
    public void parse_validArgs_returnsDelShortTagCommand() throws Exception {
        Alias expectedAlias = new Alias(VALID_ALIAS_VEGAN);

        // Test for valid input
        DelShortTagCommand command = parser.parse(ALIAS_DESC_VEGAN);
        assertEquals(new DelShortTagCommand(expectedAlias), command);
    }

    @Test
    public void parse_missingAlias_throwsParseException() {
        // Missing alias
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidAlias_throwsParseException() {
        // Invalid alias format
        assertThrows(ParseException.class, () -> parser.parse(INVALID_ALIAS_DESC));
    }

    @Test
    public void parse_extraArguments_throwsParseException() {
        // Extra arguments before  the valid alias
        assertThrows(ParseException.class, () -> parser.parse("extra " + ALIAS_DESC_VEGAN));
    }

    @Test
    public void parse_duplicateAlias_throwsParseException() {
        // Duplicate alias in the input
        assertThrows(ParseException.class, () -> parser.parse(ALIAS_DESC_VEGAN + ALIAS_DESC_VEGAN));
    }
}
