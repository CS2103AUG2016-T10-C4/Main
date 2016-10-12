package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents a task's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Date {

    public static final String EXAMPLE = "030617";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date must be in the proper format DDMMYY";
    public static final String DATE_VALIDATION_REGEX = "\\d+";

    public final Integer date;

    /**
     * Validates given date.
     *
     * @throws IllegalValueException if given Date integer is invalid.
     */
    public Date(Integer date) throws IllegalValueException {
        if (!isValidDate(date)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.date = date;
    }

    /**
     * Returns true if a given Integer is a valid date.
     */
    public static boolean isValidDate(Integer test) {
        return Integer.toString(test).matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && this.date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
    
}
