package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.data.task.Task.TaskNotFoundException;

/**
 * Edit a Task identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the person identified by the index number used in the last task listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1 I am going to change to this";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edit Task: %1$s";

    private Task editTask;
    
    public EditCommand(int targetVisibleIndex, Task editTask) {
        super(targetVisibleIndex);
        this.editTask = editTask;
    }


    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            tasksList.setTask(target,editTask);
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (TaskNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKSLIST);
        }
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
