package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import seedu.address.storage.StorageManager;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; alias is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = Alias.MESSAGE_CONSTRAINTS;
    private static HashMap<Alias, TagName> aliasToTagNameMapping;

    private static String allMappings;

    static {
        // Load alias-to-tagName mappings from file
        aliasToTagNameMapping = new HashMap<>(StorageManager.readAliasMappings());
        if (aliasToTagNameMapping.isEmpty()) {
            initializeDefaultMappings();
        }
        updateMappings();
    }

    private final TagName tagName;
    /**
     * Constructs a {@code Tag} with the provided alias.
     *
     * @param alias A valid alias.
     */
    public Tag(Alias alias) {
        requireNonNull(alias);
        // Look up the tag name using the alias or use the alias as the tag name if not found
        this.tagName = aliasToTagNameMapping.getOrDefault(alias, new TagName(alias.toString()));
    }

    /**
     * Initializes default mappings if the file is empty or doesn't exist.
     */
    private static void initializeDefaultMappings() {
        aliasToTagNameMapping.put(new Alias("v"), new TagName("Vegan"));
        aliasToTagNameMapping.put(new Alias("vg"), new TagName("Vegetarian"));
        aliasToTagNameMapping.put(new Alias("gf"), new TagName("Gluten free"));
        aliasToTagNameMapping.put(new Alias("l"), new TagName("Lactose Intolerant"));
        aliasToTagNameMapping.put(new Alias("na"), new TagName("Nut Allergy"));
        aliasToTagNameMapping.put(new Alias("sa"), new TagName("Soy Allergy"));
        aliasToTagNameMapping.put(new Alias("p"), new TagName("Pescatarian"));
        updateMappings();
        // Save the default mappings to the file
        StorageManager.saveAliasMappings(aliasToTagNameMapping);
    }
    /**
     * Returns true if a given string is a valid alias.
     */
    public static boolean isValidTagName(String test) {
        return Alias.isValidAlias(test);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagName.equals(otherTag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    public TagName getTagName() {
        return tagName;
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + tagName.toString() + ']';
    }

    /**
     * @return string representation of all mappings
     */
    public static String getStringMappings() {
        return allMappings;
    }

    /**
     * @return HashMap of all aliases and tag names
     */
    public static HashMap<Alias, TagName> getAliasToTagNameMapping() {
        return aliasToTagNameMapping;
    }

    /**
     * Adds a new dietary restriction mapping.
     *
     * @param alias The shortcut users would want to use for a dietary restriction.
     * @param tagName The actual value to be displayed.
     */
    public static void addAliasTagNameMapping(Alias alias, TagName tagName) {
        aliasToTagNameMapping.put(alias, tagName);
        updateMappings();
        StorageManager.saveAliasMappings(aliasToTagNameMapping); // Save mappings after adding
    }

    /**
     * Removes an alias-to-tagName mapping if it exists.
     *
     * @param alias The alias to be removed.
     */
    public static void removeAliasTagNameMapping(Alias alias) {
        aliasToTagNameMapping.remove(alias);
        updateMappings();
        StorageManager.saveAliasMappings(aliasToTagNameMapping); // Save mappings after removing
    }

    /**
     * Updates the string representation of all mappings.
     */
    private static void updateMappings() {
        StringBuilder mappingsBuilder = new StringBuilder("Current Dietary Restriction Tags:\n");
        for (Map.Entry<Alias, TagName> entry : aliasToTagNameMapping.entrySet()) {
            mappingsBuilder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        allMappings = mappingsBuilder.toString();
    }
}
