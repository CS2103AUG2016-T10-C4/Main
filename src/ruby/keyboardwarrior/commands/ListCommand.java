package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.task.TodoTask;

import java.util.List;


/**
 * Lists all items in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all items in the task manager as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<TodoTask> allTasks = tasksList.getAllTasks();
        return new CommandResult(getMessageForTasksListShownSummary(allTasks), allTasks);
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
