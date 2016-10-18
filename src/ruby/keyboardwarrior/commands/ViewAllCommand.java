package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.Task;


/**
 * Shows all details of the item identified using the last displayed index.
 * Private contact details are shown.
 */
public class ViewAllCommand extends Command {

    public static final String COMMAND_WORD = "viewall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Shows all details of the item "
            + "identified by the index number in the last shown item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing Item: %1$s";


    public ViewAllCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            if (!tasksList.containsTask(target)) {
                return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKSLIST);
            }
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_DETAILS, target.toString()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
