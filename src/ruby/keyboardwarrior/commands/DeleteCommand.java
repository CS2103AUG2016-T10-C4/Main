package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.TodoTask;
import ruby.keyboardwarrior.data.task.TodoTask.TaskNotFoundException;

/**
 * Deletes a item identified using it's last displayed index from the task manager.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Deletes the item identified by the index number used in the last item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";


    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() throws TaskNotFoundException {
        try {
            final TodoTask target = getTargetTask();
            tasksList.removeTask(target);
            deletedList.push(target);
            return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
