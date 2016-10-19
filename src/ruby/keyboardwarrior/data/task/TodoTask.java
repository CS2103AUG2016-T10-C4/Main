package ruby.keyboardwarrior.data.task;

import java.util.Objects;

/**
 * Represents a Task in the address book.
 * Guarantees: field values are validated.
 */
public class TodoTask {

    private TaskDetails details;

    public static class TaskNotFoundException extends Exception {};
    /**
     * Assumption: Every field must be present and not null.
     */
    
    public TodoTask(){}
    
    public TodoTask(TaskDetails details) {
        this.details = details;
    }

    public TaskDetails getDetails() {
        return details;
    }
    
    public boolean equals(Object other) {
        if (other instanceof TodoTask){
            TodoTask todotask = (TodoTask) other;
            return this.getDetails().equals(todotask.getDetails());
        }
        else
            return false;
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details);
    }

    public String toString() {
        return details.toString();
    }
    
}
