package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;

/**
 * Adds a task to Keyboard Warrior.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a task to the Keyboard Warrior. "
            + "Only supports task details which can be enter after the command word seperated by a space. \n\t"
            + "Example: " + COMMAND_WORD
            + " do something";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Keyboard Warrior";

    private final TodoTask toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */

    public AddCommand(String taskdetails, String startTime, String endTime) throws IllegalValueException {
    	if((endTime == null) && (startTime == null)){
    		this.toAdd = new TodoTask(new TaskDetails(taskdetails), new StartTime(), new EndTime());	//To-do
    	}
    	else if((endTime != null) && (startTime == null)){
    		this.toAdd = new TodoTask(new TaskDetails(taskdetails), new StartTime(), new EndTime(endTime));	//Deadline
    	}
    	else if((endTime == null) && (startTime != null)){
    		this.toAdd = new TodoTask(new TaskDetails(taskdetails), new StartTime(startTime), new EndTime()); //Event with no end time
    	}
    	else{
    		this.toAdd = new TodoTask(new TaskDetails(taskdetails), new StartTime(startTime), new EndTime(endTime)); //Event with end time
    	}
    }

    public AddCommand(TodoTask toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() throws Exception{
        try {
            tasksList.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (TasksList.DuplicateTaskException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
    
    public TodoTask getTask(){
        return toAdd;
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
