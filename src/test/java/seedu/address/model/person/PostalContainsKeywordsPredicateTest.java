package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PostalContainsKeywordsPredicateTest {

    @Test
    public void test_postalContainsKeywords_returnsTrue() {
        PostalContainsKeywordsPredicate predicate =
                new PostalContainsKeywordsPredicate(Collections.singletonList("S123456"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("S123456").build()));

        predicate = new PostalContainsKeywordsPredicate(Arrays.asList("S123", "456"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("S123456").build()));

        predicate = new PostalContainsKeywordsPredicate(Arrays.asList("S123456"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("S123456").build()));
    }

    @Test
    public void test_postalDoesNotContainKeywords_returnsFalse() {
        PostalContainsKeywordsPredicate predicate =
                new PostalContainsKeywordsPredicate(Collections.singletonList("S654321"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("S123456").build()));

        predicate = new PostalContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("S123456").withName("Alice").build()));
    }
    @Test
    public void test_emptyKeywords_returnsFalse() {
        PostalContainsKeywordsPredicate predicate = new PostalContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAddress("S123456").build()));
    }
    @Test
    public void equals_sameObject_returnsTrue() {
        PostalContainsKeywordsPredicate predicate =
                new PostalContainsKeywordsPredicate(Collections.singletonList("S123456"));
        assertTrue(predicate.equals(predicate));
    }
    @Test
    public void equals_differentType_returnsFalse() {
        PostalContainsKeywordsPredicate predicate =
                new PostalContainsKeywordsPredicate(Collections.singletonList("S123456"));
        assertFalse(predicate.equals("some string"));
    }
}
