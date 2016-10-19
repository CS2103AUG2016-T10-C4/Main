package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.task.TodoTask;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import static ruby.keyboardwarrior.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TasksList tasksList;
    protected static Stack<TodoTask> deletedList = new Stack<TodoTask>();
    protected List<TodoTask> relevantTasks;
    private int targetIndex = -1;
 
    /**
     * @param targetIndex last visible listing index of the target task
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of tasks.
     *
     * @param tasksDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForTasksListShownSummary(List<TodoTask> tasksDisplayed) {
        return String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, tasksDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute() throws Exception;
    
    public abstract boolean isMutating();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TasksList tasksList, List<TodoTask> relevantTasks) {
        this.tasksList = tasksList;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected TodoTask getTargetTask() throws IndexOutOfBoundsException {
        return relevantTasks.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
