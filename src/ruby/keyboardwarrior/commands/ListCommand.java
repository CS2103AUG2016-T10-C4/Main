package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.task.Task;

import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<Task> allTasks = tasksList.getAllTasks();
        return new CommandResult(getMessageForPersonListShownSummary(allTasks), allTasks);
    }
    
    @Override
    public boolean isMutating() {
    	return false;
    }
}
