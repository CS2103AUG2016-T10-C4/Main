package ruby.keyboardwarrior.commands;

//@@author A0139820E
import ruby.keyboardwarrior.data.task.Task;

import static ruby.keyboardwarrior.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

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
    
    /**
     * Constructor to initialize the Task Type
     */
    public ListCommand(String arg){
    	this.taskType = determineTaskType(arg);
	}
    
    /**
     * Method to execute the command input
     * 
     */
    @Override
    public CommandResult execute() {
    	ArrayList<Task> allTasks = tasksList.getAllTasks();
    	if(this.taskType == 4){ // List all Task
    		return new CommandResult(COMMAND_WORD,getMessage(allTasks),allTasks);
    	} else {
	        if(this.taskType != 3){ // List a Specific kind of task
	        	ArrayList<Task> certainTasks = getCertainTask();
	        	return new CommandResult(feedbackToUser(), certainTasks);
	        } else { // Invalid Input
	    		return new CommandResult(MESSAGE_INVALID_COMMAND_FORMAT,MESSAGE_USAGE);
	    	}
    	}
    }
    
    /**
     * Method to get message depending on task Type
     */
    private String getMessage(ArrayList<Task> tasks){
    	return getMessageForTasksList(tasks,String.valueOf(this.taskType));
    }
    
    /**
     * Method to obtain a list of certain task
     */
    private ArrayList<Task> getCertainTask(){
    	ArrayList<Task> tasks = new ArrayList<Task>();
    	for(Task task : tasksList.getAllTasks()) {
                if(task.getTaskType() == this.taskType)
                	tasks.add(task);
    	}
        return tasks; 
    }
    
    /**
     * Method to determine the Task Type depending on the arguments
     */
	private Integer determineTaskType(String arg) {
		if(arg.length() != 0){
    		if(TODO_TYPE.equalsIgnoreCase(arg.trim()))
	    		return 0; // Todo
	    	else if(DEADLINE_TYPE.equalsIgnoreCase(arg.trim()))
	    		return 1; // Deadline
	    	else if(EVENT_TYPE.equalsIgnoreCase(arg.trim()))
	    		return 2; // Event
	    	else
	    		return 3; // Invalid
	    } else {
	    	return 4; // List All
	    }
	}
	
    /**
     * Method switch the corresponding task type with its feedback command
     */
	private String feedbackToUser() {
		switch(taskType){
			case 0:
				return COMMAND_WORD + " " + TODO_TYPE;
			case 1:
				return COMMAND_WORD + " " + DEADLINE_TYPE;
			case 2:
				return COMMAND_WORD + " " + EVENT_TYPE;
			case 3: // Fall Through
			default:
				return MESSAGE_INVALID_COMMAND_FORMAT;
		}
	}
    
    /**
     * Method to determine if there are changes to the task.
     * 
     * @return true if there are changes
     */
	@Override
    public boolean isMutating() {
    	return false;
    }
}