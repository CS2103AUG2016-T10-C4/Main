package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList.TaskNotFoundException;
import ruby.keyboardwarrior.data.task.Task;

//@author A0139716X
/**
 * Deletes a item identified using it's last displayed index from the task manager.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Deletes the item identified by the index number used in the last item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Item: %1$s";


    /**
     * Constructor for a Delete Command.
     */
    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() throws TaskNotFoundException {
        try {
            final Task target = getTargetTask();
            tasksList.removeTask(target);
            deletedList.push(target);
            return new CommandResult(String.format(MESSAGE_SUCCESS, target),
            						 getMessageForTasksList(tasksList.getAllTasks(), "4"),
            						 tasksList.getAllTasks());
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE);
        }
    }
    
    /**
     * Method to determine if there are changes to the task.
     * 
     * @return true if there are changes
     * @Override
     */
    public boolean isMutating() {
    	return true;
    }
}
