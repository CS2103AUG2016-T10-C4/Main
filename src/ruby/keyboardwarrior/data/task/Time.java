package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents a task's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class Time {

    public static final String EXAMPLE = "0010";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time must be in the proper format HHMM";
    public static final String TIME_VALIDATION_REGEX = "\\d+";

    public final Integer time;

    /**
     * Validates given time.
     *
     * @throws IllegalValueException if given time is invalid.
     */
    public Time(Integer time) throws IllegalValueException {
        if (!isValidTime(time)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.time = time;
    }

    /**
     * Returns true if a given integer is a valid date.
     */
    public static boolean isValidTime(Integer test) {
        return Integer.toString(test).matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && this.time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
    
}
