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
            + "Displays items in the task manager as a list with index numbers.\n\t"
            + "To list all items:\n\t\t"
            + COMMAND_WORD + "\n\t"
            + "To list todos:\n\t\t"
		    + COMMAND_WORD + " " + TODO_TYPE + "\n\t"
		    + "To list deadlines:\n\t\t"
		    + COMMAND_WORD + " " + DEADLINE_TYPE + "\n\t"
		    + "To list events:\n\t\t"
		    + COMMAND_WORD + " " + EVENT_TYPE;
    
    public static final String MESSAGE_LIST_TASK_SUCCESS = "List Item: ";
    
    private Integer taskType;
    
    public ListCommand(String arg){
    	if(arg.length() != 0){
    		if(TODO_TYPE.equalsIgnoreCase(arg.trim()))
	    		this.taskType = 0;
	    	else if(DEADLINE_TYPE.equalsIgnoreCase(arg.trim()))
	    		this.taskType = 1;
	    	else if(EVENT_TYPE.equalsIgnoreCase(arg.trim()))
	    		this.taskType = 2;
	    	else
	    		this.taskType = 3;
	    } else {
	    	this.taskType = 4;
	    }
	}   
    
    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = tasksList.getAllTasks();
        ArrayList<Task> certainTasks = new ArrayList<Task>();
    	if(this.taskType != 4){
	        if(this.taskType != 3){
		    	for(Task task : tasksList.getAllTasks()) {
		                if(task.getTaskType() == this.taskType)
		                	certainTasks.add(task);
	        }
		    	return new CommandResult(getMessageForTasksListShownSummary(certainTasks,String.valueOf(this.taskType)), certainTasks);
	    	} else {
	    		return new CommandResult(MESSAGE_USAGE);
	    	}
    	} else {
    		return new CommandResult(getMessageForTasksListShownSummary(allTasks, String.valueOf(this.taskType)));
    	}
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
