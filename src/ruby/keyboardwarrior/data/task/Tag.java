package ruby.keyboardwarrior.data.task;

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
    
    public List<String> getWordsInTagName() {
        return Arrays.asList(tagName.split("\\s+"));
    }
    
    @Override
    public String toString() {
        return '[' + tagName + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }
    
}
