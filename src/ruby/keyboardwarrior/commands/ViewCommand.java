package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.TodoTask;


/**
 * Shows details of the item identified using the last displayed index.
 * Private contact details are not shown.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Shows the non-private details of the person "
            + "identified by the index number in the last shown item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_TASK_DETAILS = "Viewing Item: %1$s";


    public ViewCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() {
        try {
            final TodoTask target = getTargetTask();
            if (!tasksList.containsTask(target)) {
                return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKSLIST);
            }
            return new CommandResult(String.format(MESSAGE_VIEW_TASK_DETAILS, target.toString()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean isMutating() {
    	return false;
    }
}
