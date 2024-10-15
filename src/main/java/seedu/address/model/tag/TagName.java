package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of a tag
 */
public class TagName {
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} ]*[\\p{Alnum}]?$";
    private String tagName;

    /**
     * Creates instance of new TagName
     * @param tagName
     */
    public TagName(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagName)) {
            return false;
        }

        TagName otherTagName = (TagName) other;
        return tagName.equals(otherTagName.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }
    @Override
    public String toString() {
        return tagName;
    }
}
