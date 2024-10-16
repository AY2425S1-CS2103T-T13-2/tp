package seedu.address.storage;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Alias;
import seedu.address.model.tag.TagName;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private static final String ALIAS_MAPPING_FILE_PATH = "data/aliasmappings.json";
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }


    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    /**
     * Saves the given CSV content to a file.
     *
     * @param csvContent CSV content to be saved.
     */
    public static void saveCsvToFile(String csvContent) {
        File file = new File("data/exported.csv");

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(csvContent);
            System.out.println("CSV file saved to: data/exported.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================ Alias Mapping methods ==============================

    /**
     * Reads the alias mappings from a JSON file.
     *
     * @return A map containing the alias to tag name mappings.
     */
    public static HashMap<Alias, TagName> readAliasMappings() {
        HashMap<Alias, TagName> aliasToTagNameMapping = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ALIAS_MAPPING_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"") && line.contains(":")) {
                    String[] parts = line.replace("\"", "").replace(",", "").split(":");
                    if (parts.length == 2) {
                        aliasToTagNameMapping.put(new Alias(parts[0].trim()), new TagName(parts[1].trim()));
                    }
                }
            }
            System.out.println("Alias mappings loaded from: " + ALIAS_MAPPING_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Alias mappings file not found or couldn't be read. Initializing empty mappings.");
        }
        return aliasToTagNameMapping;
    }

    /**
     * Saves the alias mappings to a JSON file.
     *
     * @param aliasToTagNameMapping The map containing the alias to tag name mappings.
     */
    public static void saveAliasMappings(HashMap<Alias, TagName> aliasToTagNameMapping) {
        File file = new File(ALIAS_MAPPING_FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("{\n"); // Start of JSON object
            for (HashMap.Entry<Alias, TagName> entry : aliasToTagNameMapping.entrySet()) {
                writer.write("  \"" + entry.getKey().toString() + "\": \"" + entry.getValue().toString() + "\",\n");
            }
            writer.write("}\n"); // End of JSON object
            System.out.println("Alias mappings saved to: " + ALIAS_MAPPING_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
