package ruby.keyboardwarrior.commands;

//@@author A0124453M
/**
 * Clears all the items in the Keyboard Warrior.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Clears all items in Keyboard Warrior permanently.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "All items have been cleared!";

    public ClearCommand() {}

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        tasksList.clear();
        return new CommandResult(MESSAGE_SUCCESS,tasksList.getAllTasks());
    }
    
    /**
     * Method to determine if there are changes to the task.
     * 
     * @return true if there are changes
     */
    @Override
    public boolean isMutating() {
    	return true;
    }
}
