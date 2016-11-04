package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author A0144665Y

/**
 * Represents the date for tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class Date {

    public static final String EXAMPLE = "130816";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date must be in the proper format DDMMYY";
    public static final String DATE_VALIDATION_REGEX = "[0-3][0-9][0-1][0-9][0-9][0-9]";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E dd-MMM-yyyy");
    public static final SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat("E dd-MMM-yyyy");

    public final Calendar date;

    /**
     * Validates given Date.
     *
     * @throws IllegalValueException if given time is invalid.
     * @Deprecated
     */
    public Date() {
    	this.date = null;
    }
    
    /**
     * Validates given Date.
     *
     * @throws IllegalValueException if given time is invalid.
     * @Deprecated
     */
    public Date(String date) throws IllegalValueException {
        if (!isValidDate(date)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.date = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(4,6))+2000;
        int month = Integer.parseInt(date.substring(2, 4)) - 1;
        int day = Integer.parseInt(date.substring(0,2));
        this.date.set(year,month,day);
    }

    /**
     * Returns true if a given integer is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Get method for Date
     */
    public String getDate() {
        return DATE_FORMAT.format(date);
    }
    
    /**
     * Get method for Day and Date
     */
    public String getDayAndDate() {
        return DAY_DATE_FORMAT.format(date);
    }
    
    /**
     * Overrides the toString method for a Date
     * 
     * @Override
     */
    public String toString() {
    	java.util.Date properDate = date.getTime();
    	return DATE_FORMAT.format(properDate);
    }

    /**
     * Overrides the hash code method for a Date
     * 
     * @Override
     */
    public int hashCode() {
        return date.hashCode();
    }
    
}
