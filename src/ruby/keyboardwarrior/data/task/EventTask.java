package ruby.keyboardwarrior.data.task;

import java.util.Objects;
import java.util.StringJoiner;

//@@author A0139820E
/**
 * Represents a EventTask in the Keyboard Warrior.
 * Guarantees: field values are validated.
 */
public class EventTask{

    private TaskDetails details;
    private StartTime startTime;
    private EndTime endTime;

    public EventTask(TaskDetails details, StartTime startTime, EndTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
    }
    
    public TaskDetails getDetails() {
        return details;
    }
    
    public StartTime getStartTime() {
        return startTime;
    }
    
    public EndTime getEndTime() {
        return endTime;
    }


    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details, startTime, endTime);
    }

    public String toString() {
        final StringJoiner joiner = new StringJoiner(" ");
        joiner.add(details.toString()).add("from").add(startTime.toString()).add("to").add(endTime.toString());
        
        return joiner.toString();
    }
    
}
