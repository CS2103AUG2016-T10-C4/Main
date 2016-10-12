package ruby.keyboardwarrior.data.task;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task{

    private TaskDetails details;
    
    public static class TaskNotFoundException extends Exception {}
    
    /**
     * Assumption: Every field must be present and not null.
     */
    public Task(TaskDetails details) {
        this.details = details;
    }

    public TaskDetails getDetails() {
        return details;
    }

    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && this.equals((Task) other));
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details);
    }

    public String toString() {
        return details.toString();
    }

}
