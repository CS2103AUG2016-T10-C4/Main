package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents start time for event tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class StartTime {

    public static final String EXAMPLE = "13-08-16/2310";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time must be in the proper format DD-MM-YY/HHMM";
    public static final String TIME_VALIDATION_REGEX = "[0-3][0-9]-[0-1][0-9]-(\\d{2})[/][0-2][0-9][0-5][0-9]";

    public final String startTime;

    /**
     * Validates given start time.
     *
     * @throws IllegalValueException if given start time is invalid.
     */
    public StartTime(String startTime) throws IllegalValueException {
        if (!isValidTime(startTime)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.startTime = startTime;
    }
    
    public StartTime(){
    	startTime = "";
    }

    /**
     * Returns true if a given integer is a valid start time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return startTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.startTime.equals(((StartTime) other).startTime)); // state check
    }

    @Override
    public int hashCode() {
        return startTime.hashCode();
    }
    
}
