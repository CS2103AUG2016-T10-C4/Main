package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author A0144665Y
/**
 * Represents the Date and Time for tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class DateTime {

    public static final String EXAMPLE = "130816 2310";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date and Time must be in the proper format DDMMYY HHMM";
    public static final String DATE_TIME_VALIDATION_REGEX = "[0-3][0-9][0-1][0-9][0-9][0-9]\\s[0-2][0-9][0-5][0-9]";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E dd-MMM-yyyy ");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat("E dd-MMM-yyyy ");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("E HH:mm dd-MMM-yyyy");

    public final Calendar dateTime;

    /**
     * Validates given Date and Time.
     *
     * @throws IllegalValueException if given date and time is invalid.
     * @Deprecated
     */
    public DateTime() {
    	this.dateTime = null;
    }
    
    /**
     * Validates given Date and Time.
     *
     * @throws IllegalValueException if given date and time is invalid.
     * @Deprecated
     */
    public DateTime(String dateTime) throws IllegalValueException {
        if (!isValidDate(dateTime)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.dateTime = Calendar.getInstance();
        int year = Integer.parseInt(dateTime.substring(4,6))+ 2000;
        int month = Integer.parseInt(dateTime.substring(2, 4)) - 1;
        int day = Integer.parseInt(dateTime.substring(0,2));
        int hours = Integer.parseInt(dateTime.substring(7,9));
        int mins = Integer.parseInt(dateTime.substring(9));
        this.dateTime.set(year,month,day,hours,mins);
    }

    /**
     * Returns true if a given integer is a valid Date and Time.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_TIME_VALIDATION_REGEX);
    }

    /**
     * Get method for Date
     */
    public String getDate() {
        return DATE_FORMAT.format(dateTime);
    }
    
    /**
     * Get method for Time
     */
    public String getTime() {
        return TIME_FORMAT.format(dateTime);
    }
    
    /**
     * Get method for Day and Date
     */
    public String getDayAndDate() {
        return DAY_DATE_FORMAT.format(dateTime);
    }
    
    /**
     * Overrides the toString method for a Date
     * 
     * @Override
     */
    public String toString() {
    	java.util.Date properDateTime = dateTime.getTime();
        return DATE_TIME_FORMAT.format(properDateTime);
    }

    /**
     * Overrides the hash code method for a Date
     * 
     * @Override
     */
    public int hashCode() {
        return dateTime.hashCode();
    }
    
}
