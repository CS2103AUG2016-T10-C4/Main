package ruby.keyboardwarrior.data.tag;

import java.util.Arrays;
import java.util.List;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents a Tag in the KeywordWarrior.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_TAG_CONSTRAINTS = "Tag names should be alphanumeric";
    public static final String TAG_VALIDATION_REGEX = "^[a-zA-Z0-9]+$";

    public String tagName;
    
     /**
     * Validates given tag name.
     *
     * @throws IllegalValueException if the given tag name is invalid.
     */
    public Tag(String name) throws IllegalValueException {
        assert name != null;
        name = name.trim();
        if (!isValidTagName(name)) {
            throw new IllegalValueException(MESSAGE_TAG_CONSTRAINTS);
        }
        this.tagName = name;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(TAG_VALIDATION_REGEX);
    }
    
    /**
     * Returns an array list of tags.
     */
    public List<String> getWordsInTagName() {
        return Arrays.asList(tagName.split("\\s+"));
    }
    
    /**
     * Overrides the toString method for tags
     * 
     * @Override
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    /**
     * Overrides the equals method
     * 
     * @Override
     */
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Tag
                && this.tagName.equals(((Tag) other).tagName));
    }

    /**
     * Hashes the objects for a better search method
     * 
     * @Override
     */
    public int hashCode() {
        return tagName.hashCode();
    }
    
}
