package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; alias is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = Alias.MESSAGE_CONSTRAINTS;
    private static HashMap<Alias, TagName> aliasToTagNameMapping = new HashMap<>();

    private static String allMappings;

    static {
        aliasToTagNameMapping.put(new Alias("v"), new TagName("Vegan"));
        aliasToTagNameMapping.put(new Alias("vg"), new TagName("Vegetarian"));
        aliasToTagNameMapping.put(new Alias("gf"), new TagName("Gluten free"));
        aliasToTagNameMapping.put(new Alias("l"), new TagName("Lactose Intolerant"));
        aliasToTagNameMapping.put(new Alias("na"), new TagName("Nut Allergy"));
        aliasToTagNameMapping.put(new Alias("sa"), new TagName("Soy Allergy"));
        aliasToTagNameMapping.put(new Alias("p"), new TagName("Pescatarian"));

        StringBuilder mappingsBuilder = new StringBuilder("Current Dietary Restriction Tags:\n");
        for (Map.Entry<Alias, TagName> entry : aliasToTagNameMapping.entrySet()) {
            mappingsBuilder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        allMappings = mappingsBuilder.toString();
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

        // Update the mappings display
        StringBuilder mappingsBuilder = new StringBuilder("Current Dietary Restriction Tags:\n");
        for (Map.Entry<Alias, TagName> entry : aliasToTagNameMapping.entrySet()) {
            mappingsBuilder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        allMappings = mappingsBuilder.toString();
    }
}