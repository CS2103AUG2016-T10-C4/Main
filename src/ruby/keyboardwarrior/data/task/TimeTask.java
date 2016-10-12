package ruby.keyboardwarrior.data.task;

//import ruby.keyboardwarrior.data.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Task in the Keyboard Warrior.
 * Guarantees: field values are validated.
 */
public class TimeTask{

    private Date date;
    private Time startTime;
    private Time endTime;
    private TaskDetails details;
    private Venue venue;

    /**
     * Assumption: The user at least typed the date, startTime and task details
     */
    public TimeTask(Date date, Time startTime, Time endTime, TaskDetails details, Venue venue) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }
    
    public Time getEndTime() {
        return endTime;
    }
    
    public TaskDetails getDetails() {
        return details;
    }

    public Venue getVenue() {
        return venue;
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, startTime, endTime, details, venue);
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate()).append(" ");
        builder.append(getStartTime()).append(" ");        
        if (getEndTime() != null) {
            builder.append("to ").append(getEndTime()).append(" ");
        }
        builder.append(getDetails()).append(" ").append(getVenue());
        return builder.toString();
    }
    
}
