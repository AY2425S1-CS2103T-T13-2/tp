package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * This constructor takes a list of keywords and replaces any keyword that matches an alias
     * in the {@code Tag.getAliasToTagNameMapping()} with its corresponding full tag name.
     * If no match is found, the keyword remains unchanged. The modified list is then assigned to the
     * {@code keywords} field.
     *
     * @param keywords A list of keywords to be processed and matched.
     */
    public TagsContainsKeywordsPredicate(List<String> keywords) {
        List<String> mappedKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            // Check if the keyword is an alias and map it to the full tag name, otherwise use it as-is
            TagName mappedTagName = Tag.getAliasToTagNameMapping()
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().toString().equalsIgnoreCase(keyword))
                    .map(entry -> entry.getValue())
                    .findFirst()
                    .orElse(new TagName(keyword)); // If no alias found, use keyword as tag name
            mappedKeywords.add(mappedTagName.toString());
        }
        this.keywords = mappedKeywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> tags = person.getTags();

        // Handling case where keywords are empty
        if (keywords.isEmpty()) {
            return false;
        }

        // Convert all keywords to lowercase
        List<String> lowerCaseKeywords = keywords.stream()
                .map(String::toLowerCase)
                .toList();

        // Stream through the tags and check if any tag matches a keyword (case-insensitive)
        return tags.stream().anyMatch(tag ->
                lowerCaseKeywords.stream()
                        .anyMatch(keyword -> tag.getTagName().toString().toLowerCase().contains(keyword))
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainsKeywordsPredicate)) {
            return false;
        }

        TagsContainsKeywordsPredicate otherPredicate = (TagsContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return TagsContainsKeywordsPredicate.class.getCanonicalName() + String.format("[keywords=%s]", keywords);
    }
}
