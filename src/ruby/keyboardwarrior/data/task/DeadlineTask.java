package ruby.keyboardwarrior.data.task;

import java.util.Objects;
import java.util.StringJoiner;

//@@author A0139820E
/**
 * Represents a Deadline Task in the Keyboard Warrior.
 * Guarantees: field values are validated.
 */
public class DeadlineTask {

    private TaskDetails details;
    private EndTime endtime;

    public DeadlineTask(TaskDetails details, EndTime endtime) {
        this.details = details;
        this.endtime = endtime;
    }

    public TaskDetails getDetails() {
        return details;
    }

    public EndTime getEndTime() {
        return endtime;
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details, endtime);
    }

    public String toString() {
        final StringJoiner joiner = new StringJoiner(" ");
        joiner.add(details.toString()).add("by").add(endtime.toString());
        
        return joiner.toString();
    }
    
}
