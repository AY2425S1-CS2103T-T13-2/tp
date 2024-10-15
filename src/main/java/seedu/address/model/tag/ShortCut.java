package seedu.address.model.tag;

/**
 * Shortcut consists of alias and tagName
 */
public class ShortCut {
    private TagName tagName;
    private Alias alias;

    /**
     * Creates an instance of a shortcut to get the tagname using an alias
     * @param tagName full description of tag
     * @param alias shortened version of the tag
     */
    public ShortCut(TagName tagName, Alias alias) {
        this.tagName = tagName;
        this.alias = alias;
    }
    public TagName getTagName() {
        return this.tagName;
    }
    public Alias getAlias() {
        return this.alias;
    }

}
