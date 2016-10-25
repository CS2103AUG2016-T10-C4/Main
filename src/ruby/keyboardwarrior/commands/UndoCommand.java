package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.TasksList.DuplicateTaskException;
import ruby.keyboardwarrior.data.TasksList.TaskNotFoundException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.TodoTask;
import ruby.keyboardwarrior.data.task.TaskDetails;

/**
 * Undo the last command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n";

    public static final String MESSAGE_UNDO_TASK_SUCCESS = "Undo Item: %1$s";

    private String toUndo;
    
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */

    public UndoCommand(String toUndo) {
        this.toUndo = toUndo;
    }


    @Override
    public CommandResult execute() throws DuplicateTaskException, TaskNotFoundException {
		int space = toUndo.indexOf(' ');
    	String command = toUndo.substring(0, space);
    	String task = toUndo.substring(space+1);
    	
    	if(command.equals("add")){
    		tasksList.removeTask(new TodoTask(new TaskDetails(task)));
    	}
    	
    	if(command.equals("delete")){
    		tasksList.addTask(deletedList.pop());
    	}
    	
    	if(command.equals("edit")){
    		int whiteSpace = task.indexOf(' ');
        	String index = task.substring(0, whiteSpace);
    		tasksList.setTask(Integer.parseInt(index)-1,deletedList.pop());
    	}
    	
        return new CommandResult(String.format(MESSAGE_UNDO_TASK_SUCCESS, toUndo));
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
