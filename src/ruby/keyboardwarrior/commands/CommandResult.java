package ruby.keyboardwarrior.commands;

import java.util.List;
import java.util.Optional;

import ruby.keyboardwarrior.data.task.TodoTask;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;

    /** The list of items that was produced by the command */
    private final List<TodoTask> relevantTasks;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantTasks = null;
    }

    public CommandResult(String feedbackToUser, List<TodoTask> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Returns list of items relevant to the command command result, if any.
     */
    public Optional<List<TodoTask>> getRelevantTasks() {
        return Optional.ofNullable(relevantTasks);
    }

}
