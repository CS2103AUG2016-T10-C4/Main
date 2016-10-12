package ruby.keyboardwarrior.data.task;

import java.util.Objects;

/**
 * Represents a Task in the address book.
 * Guarantees: field values are validated.
 */
public class TodoTask {

    private TaskDetails details;

    /**
     * Assumption: Every field must be present and not null.
     */
    public TodoTask(TaskDetails details) {
        this.details = details;
    }

    public TaskDetails getDetails() {
        return details;
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details);
    }

    public String toString() {
        return details.toString();
    }
    
}
