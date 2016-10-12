package ruby.keyboardwarrior.data.task;

import java.util.Objects;

/**
 * Represents a deadline in the Keyboard Warrior.
 * Guarantees: field values are validated.
 */
public class DeadlineTask {

    private TaskDetails details;
    private Date date;
    private Time time;

    /**
     * Assumption: Details and date or time must not be null
     */
    public DeadlineTask(TaskDetails details, Date date, Time time) {
        this.details = details;
        this.date = date;
        this.time = time;
    }

    public TaskDetails getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details, date, time);
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDetails()).append(" by ");        
        if (getDate() != null) {
            builder.append(getDate()).append(" ");
        }
        if (getTime() != null) {
            builder.append(getTime()).append(" ");
        }
        return builder.toString();
    }
    
}
