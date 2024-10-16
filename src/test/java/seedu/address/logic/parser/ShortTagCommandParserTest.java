package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.ALIAS_DESC_VEGAN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ALIAS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAGNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAGNAME_DESC_VEGAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_VEGAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGNAME_VEGAN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShortTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Alias;
import seedu.address.model.tag.TagName;

public class ShortTagCommandParserTest {

    private ShortTagCommandParser parser = new ShortTagCommandParser();

    @Test
    public void parse_validArgs_returnsShortTagCommand() throws Exception {
        Alias expectedAlias = new Alias(VALID_ALIAS_VEGAN);
        TagName expectedTagName = new TagName(VALID_TAGNAME_VEGAN);

        assertParseSuccess(parser, ALIAS_DESC_VEGAN + TAGNAME_DESC_VEGAN,
                            new ShortTagCommand(expectedAlias, expectedTagName));
    }

    @Test
    public void parse_missingAlias_throwsParseException() {
        // Missing alias
        assertThrows(ParseException.class, () -> parser.parse(TAGNAME_DESC_VEGAN));
    }

    @Test
    public void parse_missingTagName_throwsParseException() {
        // Missing tag name
        assertThrows(ParseException.class, () -> parser.parse(ALIAS_DESC_VEGAN));
    }

    @Test
    public void parse_invalidAlias_throwsParseException() {
        // Invalid alias
        assertThrows(ParseException.class, () -> parser.parse(INVALID_ALIAS_DESC + TAGNAME_DESC_VEGAN));
    }

    @Test
    public void parse_invalidTagName_throwsParseException() {
        // Invalid tag name
        assertThrows(ParseException.class, () -> parser.parse(ALIAS_DESC_VEGAN + INVALID_TAGNAME_DESC));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        // No arguments provided
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_extraArguments_throwsParseException() {
        // Extra arguments before valid args
        assertThrows(ParseException.class, () -> parser.parse("extra " + ALIAS_DESC_VEGAN + TAGNAME_DESC_VEGAN));
    }

    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        // Duplicate prefixes for alias or tagName
        assertThrows(ParseException.class, () -> parser.parse(ALIAS_DESC_VEGAN + ALIAS_DESC_VEGAN
                                                                + TAGNAME_DESC_VEGAN));
        assertThrows(ParseException.class, () -> parser.parse(ALIAS_DESC_VEGAN + TAGNAME_DESC_VEGAN
                                                                + TAGNAME_DESC_VEGAN));
    }
}
