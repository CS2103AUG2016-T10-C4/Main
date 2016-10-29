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
    
    public ListCommand(String arg){
    	if(arg != null){
    		if(TODO_TYPE.equalsIgnoreCase(arg.trim()))
	    		execute(0);
	    	else if(DEADLINE_TYPE.equalsIgnoreCase(arg.trim()))
	    		execute(1);
	    	else
	    		execute(2);
	    } else {
	    	execute();
	    }
	}   
    
    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = tasksList.getAllTasks();
        return new CommandResult(getMessageForTasksListShownSummary(allTasks), allTasks);
    }
    
    public CommandResult execute(Integer type) {
    	ArrayList<Task> certainTask = new ArrayList<Task>();
    	for(Task task : tasksList.getAllTasks()) {
                if(task.getTaskType() == type)
                	certainTask.add(task);
        }
    	return new CommandResult(getMessageForTasksListShownSummary(certainTask), certainTask);
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
