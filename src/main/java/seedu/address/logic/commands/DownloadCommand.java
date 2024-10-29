package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.storage.StorageManager;


/**
 * Downloads the csv file.
 */
public class DownloadCommand extends Command {

    public static final String COMMAND_WORD = "download";
    public static final String MESSAGE_SUCCESS = "Downloaded the csv file.";
    public static final String MESSAGE_NO_ROWS = "No rows to download.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports JSON data to CSV format. "
            + "If no tags are specified, all rows are downloaded. "
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example (download all rows): " + COMMAND_WORD + "\n"
            + "Example (filter by tags): " + COMMAND_WORD + " "
            + PREFIX_TAG + "tag1 "
            + PREFIX_TAG + "tag2 "
            + "..."
            + "[" + PREFIX_TAG + "tagN]";

    private final Set<Tag> tagList;

    /**
     * Creates a DownloadCommand to download the specified {@code Tag} rows.
     */
    public DownloadCommand(Set<Tag> tags) {
        requireNonNull(tags);
        this.tagList = tags;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> addressBookJson = model.getFilteredPersonListFromAddressBook(this.tagList);
        String addressBookCsv = CsvUtil.convertObservableListToCsv(addressBookJson);
        System.out.println(addressBookJson);
        StorageManager.saveCsvToFile(addressBookCsv);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DownloadCommand otherDownloadCommand)) {
            return false;
        }

        return tagList.equals(otherDownloadCommand.tagList);
    }

}
