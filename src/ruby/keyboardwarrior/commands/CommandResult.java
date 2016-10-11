package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.task.ReadOnlyTask;

import java.util.List;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;

    /** The list of persons that was produced by the command */
    private final List<? extends ReadOnlyTask> relevantPersons;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantPersons = null;
    }

    public CommandResult(String feedbackToUser, List<? extends ReadOnlyTask> relevantPersons) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = relevantPersons;
    }

    /**
     * Returns list of persons relevant to the command command result, if any.
     */
    public Optional<List<? extends ReadOnlyTask>> getRelevantPersons() {
        return Optional.ofNullable(relevantPersons);
    }

}
