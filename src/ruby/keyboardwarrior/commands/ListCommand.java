package ruby.keyboardwarrior.commands;


import ruby.keyboardwarrior.data.task.Task;

import static ruby.keyboardwarrior.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;


/**
 * Lists all items in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String TODO_TYPE = "todo";
    public static final String DEADLINE_TYPE = "deadline";
    public static final String EVENT_TYPE = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all items in the task manager as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD + "\n"
		    + "Displays Todos in the task manager as a list with index numbers.\n\t"
		    + "Example: " + COMMAND_WORD + "\n"
		    + "Displays Deadlines in the task manager as a list with index numbers.\n\t"
		    + "Example: " + COMMAND_WORD + "\n"
		    + "Displays Events in the task manager as a list with index numbers.\n\t"
		    + "Example: " + COMMAND_WORD + "\n";
    
    public static final String MESSAGE_LIST_TASK_SUCCESS = "List Item: ";
    
    public ListCommand(){
	    execute();
	}   
    
    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = tasksList.getAllTasks();
        return new CommandResult(getMessageForTasksListShownSummary(allTasks), allTasks);
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
