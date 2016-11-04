package ruby.keyboardwarrior.commands;

import java.util.List;
import java.util.Optional;

import ruby.keyboardwarrior.data.task.Task;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user in the label.
     */
    public final String feedbackToUser;
    
    /**
     * The message displayed to the user shown in the output window.
     */
    public final String displayToUser;

    /**
     * The list of items that was produced by the command
     */
    private final List<Task> relevantTasks;

    /**
     * The command result used for an invalid command, help 
     */
    public CommandResult(String feedbackToUser, String displayToUser) {
        this.feedbackToUser = feedbackToUser;
        this.displayToUser = displayToUser;
        this.relevantTasks = null;
    }
    
    /**
     * The command result used when the user add, edit, delete and undo.
     */
    public CommandResult(String feedbackToUser, List<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.displayToUser = "";
        this.relevantTasks = relevantTasks;
    }
    
    /**
     * The command result used to list or find items
     */
    public CommandResult(String feedbackToUser, String displayToUser, List<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.displayToUser = displayToUser;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Returns list of items relevant to the command command result, if any.
     */
    public Optional<List<Task>> getRelevantTasks() {
        return Optional.ofNullable(relevantTasks);
    }

}
