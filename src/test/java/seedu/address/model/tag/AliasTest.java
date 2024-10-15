package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class AliasTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Alias(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidAlias = "**#";
        assertThrows(IllegalArgumentException.class, () -> new Alias(invalidAlias));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Alias.isValidAlias(null));

        // invalid name
        assertFalse(TagName.isValidTagName("")); // empty string
        assertFalse(TagName.isValidTagName(" ")); // spaces only
        assertFalse(TagName.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(TagName.isValidTagName("soy*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TagName.isValidTagName("np")); // alphabets only
        assertTrue(TagName.isValidTagName("12")); // numbers only
        assertTrue(TagName.isValidTagName("soy2")); // alphanumeric characters
        assertTrue(TagName.isValidTagName("Np")); // with capital letters
        assertTrue(TagName.isValidTagName("j4xJfyjXMv0NDv1C0jCtgquHRgf46YVJ3ey0PBFWEfy87BTxrRyP1JBhbKU8gaVhpkBKeQ"));
    }

    @Test
    public void equals() {
        Alias alias = new Alias("vg");

        // same values -> returns true
        assertTrue(alias.equals(new Alias("vg")));

        // same object -> returns true
        assertTrue(alias.equals(alias));

        // null -> returns false
        assertFalse(alias.equals(null));

        // different types -> returns false
        assertFalse(alias.equals(5.0f));

        // different values -> returns false
        assertFalse(alias.equals(new Alias("v")));
    }
}
