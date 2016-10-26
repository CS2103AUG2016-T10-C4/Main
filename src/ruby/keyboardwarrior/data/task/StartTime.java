package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

//@@author A0139820E
/**
 * Represents start time for event tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class StartTime {

    public static final String EXAMPLE = "130816/2310";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time must be in the proper format DDMMYY/HHMM";
    public static final String TIME_VALIDATION_REGEX = "[0-3][0-9][0-1][0-9][/][0-2][0-9][0-5][0-9]";

    public final String starttime;

    /**
     * Validates given time.
     *
     * @throws IllegalValueException if given time is invalid.
     */
    public StartTime(String starttime) throws IllegalValueException {
        if (!isValidTime(starttime)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.starttime = starttime;
    }

    /**
     * Returns true if a given integer is a valid date.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return starttime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.starttime.equals(((StartTime) other).starttime)); // state check
    }

    @Override
    public int hashCode() {
        return starttime.hashCode();
    }
    
}
