package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        PhoneContainsKeywordsPredicate predicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("91234567"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("9123", "4567"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("9123", "4567"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        PhoneContainsKeywordsPredicate predicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("98765432"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("91234567").withName("Alice").build()));
    }
}
