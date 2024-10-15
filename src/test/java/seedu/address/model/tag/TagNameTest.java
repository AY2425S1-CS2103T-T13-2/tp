package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class TagNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTagName = "**#";
        assertThrows(IllegalArgumentException.class, () -> new TagName(invalidTagName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TagName.isValidTagName(null));

        // invalid name
        assertFalse(TagName.isValidTagName("")); // empty string
        assertFalse(TagName.isValidTagName(" ")); // spaces only
        assertFalse(TagName.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(TagName.isValidTagName("soy*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TagName.isValidTagName("No Pork")); // alphabets only
        assertTrue(TagName.isValidTagName("12345")); // numbers only
        assertTrue(TagName.isValidTagName("Soy 2nd Type")); // alphanumeric characters
        assertTrue(TagName.isValidTagName("Capital Tan")); // with capital letters
        assertTrue(TagName.isValidTagName("j4xJfyjXMv0NDv1C0jCtgquHRgf46YVJ3ey0PBFWEfy87BTxrRyP1JBhbKU8gaVhpkBKeQ"));
        assertTrue(TagName.isValidTagName("soy allergies"));
    }

    @Test
    public void equals() {
        TagName tagName = new TagName("Valid Name");

        // same values -> returns true
        assertTrue(tagName.equals(new TagName("Valid Name")));

        // same object -> returns true
        assertTrue(tagName.equals(tagName));

        // null -> returns false
        assertFalse(tagName.equals(null));

        // different types -> returns false
        assertFalse(tagName.equals(5.0f));

        // different values -> returns false
        assertFalse(tagName.equals(new TagName("Other Valid Name")));
    }
}
