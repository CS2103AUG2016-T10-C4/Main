package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDetails(String)}
 */
public class TaskDetails {

    public static final String EXAMPLE = "Do something";
    public static final String MESSAGE_DETAILS_CONSTRAINTS = "Details should be spaces or alphanumeric characters";
    public static final String DETAILS_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String details;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public TaskDetails(String taskdetails) throws IllegalValueException {
        taskdetails = taskdetails.trim();
        if (!isValidDetails(taskdetails)) {
            throw new IllegalValueException(MESSAGE_DETAILS_CONSTRAINTS);
        }
        this.details = taskdetails;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    public static boolean isValidDetails(String test) {
        return test.matches(DETAILS_VALIDATION_REGEX);
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInDetails() {
        return Arrays.asList(details.split("\\s+"));
    }

    @Override
    public String toString() {
        return details;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDetails // instanceof handles nulls
                && this.details.equals(((TaskDetails) other).details)); // state check
    }

    @Override
    public int hashCode() {
        return details.hashCode();
    }

}
