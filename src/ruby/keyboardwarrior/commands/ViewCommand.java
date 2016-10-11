package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.ReadOnlyTask;


/**
 * Shows details of the person identified using the last displayed index.
 * Private contact details are not shown.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Shows the non-private details of the person "
            + "identified by the index number in the last shown person listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing person: %1$s";


    public ViewCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyTask target = getTargetPerson();
            if (!tasksList.containsTask(target)) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextHidePrivate()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean isMutating() {
    	return false;
    }
}
