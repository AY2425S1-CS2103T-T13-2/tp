package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Alias;
import seedu.address.model.tag.TagName;

public class DelShortTagCommandTest {

    private static final Alias VALID_ALIAS = new Alias("v");
    private static final TagName VALID_TAG_NAME = new TagName("Vegan");
    private static final Alias NON_EXISTENT_ALIAS = new Alias("gf"); // Alias not initially in the model

    @Test
    public void constructor_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DelShortTagCommand(null));
    }

    @Test
    public void execute_aliasExists_removesAliasSuccessful() throws Exception {
        ModelStubWithAliasMapping modelStub = new ModelStubWithAliasMapping();
        DelShortTagCommand delShortTagCommand = new DelShortTagCommand(VALID_ALIAS);

        CommandResult commandResult = delShortTagCommand.execute(modelStub);

        assertEquals(String.format(DelShortTagCommand.MESSAGE_SUCCESS, VALID_ALIAS), commandResult.getFeedbackToUser());
        assertFalse(modelStub.aliasToTagMappingAdded.containsKey(VALID_ALIAS)); // Ensure alias was removed
    }

    @Test
    public void execute_aliasDoesNotExist_throwsCommandException() {
        ModelStubWithAliasMapping modelStub = new ModelStubWithAliasMapping();
        DelShortTagCommand delShortTagCommand = new DelShortTagCommand(NON_EXISTENT_ALIAS);

        assertThrows(CommandException.class,
                String.format(DelShortTagCommand.MESSAGE_ALIAS_NOT_FOUND, NON_EXISTENT_ALIAS), (
                ) -> delShortTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        DelShortTagCommand deleteFirstAliasCommand = new DelShortTagCommand(VALID_ALIAS);
        DelShortTagCommand deleteSecondAliasCommand = new DelShortTagCommand(NON_EXISTENT_ALIAS);

        // same object -> returns true
        assertTrue(deleteFirstAliasCommand.equals(deleteFirstAliasCommand));

        // same values -> returns true
        DelShortTagCommand deleteFirstAliasCommandCopy = new DelShortTagCommand(VALID_ALIAS);
        assertTrue(deleteFirstAliasCommand.equals(deleteFirstAliasCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstAliasCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstAliasCommand.equals(null));

        // different alias -> returns false
        assertFalse(deleteFirstAliasCommand.equals(deleteSecondAliasCommand));
    }

    /**
     * A Model stub that contains the alias-to-tagName mapping.
     */
    private class ModelStubWithAliasMapping extends ModelStub {
        private HashMap<Alias, TagName> aliasToTagMappingAdded = new HashMap<>();

        public ModelStubWithAliasMapping() {
            // Add initial mappings
            aliasToTagMappingAdded.put(VALID_ALIAS, VALID_TAG_NAME);
        }

        @Override
        public void delShortTag(Alias alias) {
            requireNonNull(alias);
            aliasToTagMappingAdded.remove(alias);
        }

        @Override
        public boolean hasAlias(Alias alias) {
            return aliasToTagMappingAdded.containsKey(alias);
        }

        @Override
        public HashMap<Alias, TagName> getAliasToTagNameMapping() {
            return aliasToTagMappingAdded;
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

        @Override
        public void setShortTag(Alias alias, TagName tagName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public HashMap<Alias, TagName> getAliasToTagNameMapping() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void delShortTag(Alias alias) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
