package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents end time for deadline and event tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class EndTime {

    public static final String EXAMPLE = "130816/2310";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time must be in the proper format DDMMYY/HHMM";
    public static final String TIME_VALIDATION_REGEX = "[0-3][0-9][0-1][0-9][/][0-2][0-9][0-5][0-9]";

    public final String endtime;

    /**
     * Validates given time.
     *
     * @throws IllegalValueException if given time is invalid.
     */
    public EndTime(String endtime) throws IllegalValueException {
        if (!isValidTime(endtime)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.endtime = endtime;
    }

    /**
     * Returns true if a given integer is a valid date.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return endtime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && this.endtime.equals(((EndTime) other).endtime)); // state check
    }

    @Override
    public int hashCode() {
        return endtime.hashCode();
    }
    
}
