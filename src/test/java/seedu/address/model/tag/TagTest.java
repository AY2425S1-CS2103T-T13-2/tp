package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(new Alias(invalidTagName)));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void hashMapSuccess() {
        assertEquals(new Tag(new Alias("v")).toString(), "[Vegan]");
        assertEquals(new Tag(new Alias("vg")).toString(), "[Vegetarian]");
    }

    @Test
    public void equalsSuccess() {
        //testing shortcut
        assertTrue(new Tag(new Alias("v")).equals(new Tag(new Alias("Vegan"))));
        //testing custom tags
        assertTrue(new Tag(new Alias("No Pork")).equals(new Tag(new Alias("No Pork"))));
        //testing against null
        assertFalse(new Tag(new Alias("v")).equals(null));
    }

    @Test
    public void addShortCutSuccess() {
        Tag.addAliasTagNameMapping(new Alias("np"), new TagName("No Pork"));
        assertTrue(Tag.getAliasToTagNameMapping().containsKey(new Alias("np")));
    }



}
