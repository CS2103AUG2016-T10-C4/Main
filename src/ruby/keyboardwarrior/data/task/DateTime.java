package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents start time for event tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(Integer)}
 */
public class DateTime {

    public static final String EXAMPLE = "130816 2310";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date and Time must be in the proper format DDMMYY HHMM";
    public static final String DATE_TIME_VALIDATION_REGEX = "[0-3][0-9][0-1][0-9][0-9][0-9]\\s[0-2][0-9][0-5][0-9]";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm");
    public static final SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat("E dd.MM.yyyy");

    public final Calendar dateTime;

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
     * Returns true if a given integer is a valid date and time.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_TIME_VALIDATION_REGEX);
    }

    public String getDate() {
        return DATE_FORMAT.format(dateTime);
    }
    
    public String getTime() {
        return TIME_FORMAT.format(dateTime);
    }
    
    public String getDayAndDate() {
        return DAY_DATE_FORMAT.format(dateTime);
    }
    
    @Override
    public String toString() {
    	java.util.Date properDateTime = dateTime.getTime();
        return DATE_FORMAT.format(properDateTime) + " " +TIME_FORMAT.format(properDateTime);
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
    
}
