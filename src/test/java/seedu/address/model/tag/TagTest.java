package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.storage.StorageManager;


/**
 * Unit tests for {@link Tag} class.
 */
public class TagTest {

    private static final Alias VALID_ALIAS = new Alias("v");
    private static final TagName VALID_TAG_NAME = new TagName("Vegan");
    private static final Alias ANOTHER_ALIAS = new Alias("np");
    private static final TagName ANOTHER_TAG_NAME = new TagName("No Pork");

    private static final String ALIAS_MAPPING_FILE_PATH = "data/aliasMappings.json";

    @BeforeEach
    public void setUp() {
        // Clear alias mappings and set defaults before each test
        Tag.addAliasTagNameMapping(new Alias("v"), new TagName("Vegan"));
        Tag.addAliasTagNameMapping(new Alias("vg"), new TagName("Vegetarian"));
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test by deleting the aliasMappings.json file to ensure no persistence between tests
        File file = new File(ALIAS_MAPPING_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void constructor_validAlias_returnsCorrectTag() {
        Tag tag = new Tag(VALID_ALIAS);
        assertEquals(VALID_TAG_NAME, tag.getTagName());
    }

    @Test
    public void addAliasTagNameMapping_addsAliasCorrectly() {
        // Add new alias
        Tag.addAliasTagNameMapping(ANOTHER_ALIAS, ANOTHER_TAG_NAME);

        // Ensure it was added correctly
        Map<Alias, TagName> aliasToTagNameMapping = Tag.getAliasToTagNameMapping();
        assertTrue(aliasToTagNameMapping.containsKey(ANOTHER_ALIAS));
        assertEquals(ANOTHER_TAG_NAME, aliasToTagNameMapping.get(ANOTHER_ALIAS));
    }

    @Test
    public void removeAliasTagNameMapping_removesAliasCorrectly() {
        // Add and then remove the alias
        Tag.addAliasTagNameMapping(ANOTHER_ALIAS, ANOTHER_TAG_NAME);
        Tag.removeAliasTagNameMapping(ANOTHER_ALIAS);

        // Ensure the alias was removed
        Map<Alias, TagName> aliasToTagNameMapping = Tag.getAliasToTagNameMapping();
        assertFalse(aliasToTagNameMapping.containsKey(ANOTHER_ALIAS));
    }

    @Test
    public void toString_returnsCorrectString() {
        Tag tag = new Tag(VALID_ALIAS);
        assertEquals("[Vegan]", tag.toString());
    }

    @Test
    public void equals_verifiesEquality() {
        Tag tag1 = new Tag(VALID_ALIAS);
        Tag tag2 = new Tag(VALID_ALIAS);
        Tag tag3 = new Tag(new Alias("vg")); // Vegetarian

        // Test for equality
        assertTrue(tag1.equals(tag2));
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void hashCode_returnsConsistentHashCode() {
        Tag tag1 = new Tag(VALID_ALIAS);
        Tag tag2 = new Tag(VALID_ALIAS);

        // Ensure hash codes are the same for equal objects
        assertEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void loadMappings_loadsMappingsFromFile() {
        // Add a new alias and save to the file
        Tag.addAliasTagNameMapping(ANOTHER_ALIAS, ANOTHER_TAG_NAME);

        // Clear the in-memory alias mappings and load from file
        HashMap<Alias, TagName> loadedMappings = new HashMap<>(Tag.getAliasToTagNameMapping());
        loadedMappings.clear(); // Simulate clearing the in-memory mappings

        // Reload the mappings from file
        loadedMappings = StorageManager.readAliasMappings();

        // Ensure the previously added alias is loaded correctly
        assertTrue(loadedMappings.containsKey(ANOTHER_ALIAS));
        assertEquals(ANOTHER_TAG_NAME, loadedMappings.get(ANOTHER_ALIAS));
    }

    @Test
    public void saveMappings_savesMappingsToFile() {
        // Add a new alias and save to the file
        Tag.addAliasTagNameMapping(ANOTHER_ALIAS, ANOTHER_TAG_NAME);

        // Read from file and check if the new alias exists
        HashMap<Alias, TagName> loadedMappings = new HashMap<>(StorageManager.readAliasMappings());

        assertTrue(loadedMappings.containsKey(ANOTHER_ALIAS));
        assertEquals(ANOTHER_TAG_NAME, loadedMappings.get(ANOTHER_ALIAS));
    }
}
