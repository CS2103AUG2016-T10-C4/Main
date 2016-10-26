package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents end time for deadline and event tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class EndTime {

    public static final String EXAMPLE = "13-08-16/2310";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time must be in the proper format DD-MM-YY/HHMM";
    public static final String TIME_VALIDATION_REGEX = "([0-3][0-9]-[0-1][0-9]-(\\d{2})[/])?([0-2][0-9][0-5][0-9])?";

    public final String endTime;

    /**
     * Validates given end time.
     *
     * @throws IllegalValueException if given end time is invalid.
     */
    public EndTime(String endTime) throws IllegalValueException {
        if (!isValidTime(endTime)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.endTime = endTime;
    }
    
    public EndTime(){
    	endTime = "";
    }

    /**
     * Returns true if a given integer is a valid end time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && this.endTime.equals(((EndTime) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return endTime.hashCode();
    }
    
}
