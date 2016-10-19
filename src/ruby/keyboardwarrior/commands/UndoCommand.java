package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.TasksList.DuplicateTaskException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.TodoTask;
import ruby.keyboardwarrior.data.task.TodoTask.TaskNotFoundException;
import ruby.keyboardwarrior.data.task.TaskDetails;
import ruby.keyboardwarrior.data.task.TodoTask;

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
    public CommandResult execute() throws DuplicateTaskException, ruby.keyboardwarrior.data.task.TodoTask.TaskNotFoundException {
		int space = toUndo.indexOf(' ');
    	String command = toUndo.substring(0, space);
    	String task = toUndo.substring(space);
    	
    	if(command.equals("add")){
    		tasksList.removeTask(new TodoTask(new TaskDetails(task)));
    	}
    	
    	if(command.equals("delete")){
    		tasksList.addTask(deletedList.pop());
    	}
    	
        return new CommandResult(String.format(MESSAGE_UNDO_TASK_SUCCESS, toUndo));
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
