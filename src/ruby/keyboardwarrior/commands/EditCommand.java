package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList.TaskNotFoundException;
import ruby.keyboardwarrior.data.task.Task;

//@@author A0144665Y

/**
 * Edit an item identified using it's last displayed index from the task manager.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the item identified by the index number used in the last item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1 I am going to change to this";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edit Item: %1$s";

    private Task editTask;
    
    public EditCommand(int targetVisibleIndex, Task editTask) {
        super(targetVisibleIndex);
        this.editTask = editTask;
    }


    @Override
    public CommandResult execute() throws Exception{
        try {
            final Task target = getTargetTask();
            deletedList.push(target);
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
