package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Alias;
import seedu.address.model.tag.TagName;

public class ShortTagCommandTest {

    private static final Alias VALID_ALIAS = new Alias("v");
    private static final TagName VALID_TAG_NAME = new TagName("Vegan");
    private static final Alias ANOTHER_ALIAS = new Alias("gf");
    private static final TagName ANOTHER_TAG_NAME = new TagName("Gluten Free");

    @Test
    public void constructor_nullAliasOrTagName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShortTagCommand(null, VALID_TAG_NAME));
        assertThrows(NullPointerException.class, () -> new ShortTagCommand(VALID_ALIAS, null));
    }

    @Test
    public void execute_aliasAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAliasMapping modelStub = new ModelStubAcceptingAliasMapping();
        ShortTagCommand shortTagCommand = new ShortTagCommand(VALID_ALIAS, VALID_TAG_NAME);

        CommandResult commandResult = shortTagCommand.execute(modelStub);

        assertEquals(String.format(ShortTagCommand.MESSAGE_SUCCESS, Messages.formatTag(VALID_ALIAS, VALID_TAG_NAME)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.aliasToTagMappingAdded.containsKey(VALID_ALIAS));
    }

    @Test
    public void equals() {
        ShortTagCommand addFirstAliasCommand = new ShortTagCommand(VALID_ALIAS, VALID_TAG_NAME);
        ShortTagCommand addSecondAliasCommand = new ShortTagCommand(ANOTHER_ALIAS, ANOTHER_TAG_NAME);

        // same object -> returns true
        assertTrue(addFirstAliasCommand.equals(addFirstAliasCommand));

        // same values -> returns true
        ShortTagCommand addFirstAliasCommandCopy = new ShortTagCommand(VALID_ALIAS, VALID_TAG_NAME);
        assertTrue(addFirstAliasCommand.equals(addFirstAliasCommandCopy));

        // different types -> returns false
        assertFalse(addFirstAliasCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstAliasCommand.equals(null));

        // different alias and tagName -> returns false
        assertFalse(addFirstAliasCommand.equals(addSecondAliasCommand));
    }

    /**
     * A Model stub that accepts the alias to tagName mapping.
     */
    private class ModelStubAcceptingAliasMapping extends ModelStub {
        private HashMap<Alias, TagName> aliasToTagMappingAdded = new HashMap<>();

        @Override
        public void addAliasTagNameMapping(Alias alias, TagName tagName) {
            requireNonNull(alias);
            requireNonNull(tagName);
            aliasToTagMappingAdded.put(alias, tagName);
        }

        @Override
        public boolean hasAlias(Alias alias) {
            return aliasToTagMappingAdded.containsKey(alias);
        }
        public HashMap<Alias, TagName> getAliasTagNameMapping() {
            return aliasToTagMappingAdded;
        }
    }

    /**
     * A Model stub that contains an existing alias.
     */
    private class ModelStubWithExistingAlias extends ModelStub {
        private final Alias alias;
        private final TagName tagName;

        ModelStubWithExistingAlias(Alias alias, TagName tagName) {
            requireNonNull(alias);
            requireNonNull(tagName);
            this.alias = alias;
            this.tagName = tagName;
        }

        @Override
        public boolean hasAlias(Alias alias) {
            requireNonNull(alias);
            return this.alias.equals(alias);
        }
    }

    /**
     * A default model stub that has all of the methods failing or returning default values.
     */
    private class ModelStub implements Model {
        public void addAliasTagNameMapping(Alias alias, TagName tagName) {
            throw new AssertionError("This method should not be called.");
        }
        public boolean hasAlias(Alias alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList();
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
