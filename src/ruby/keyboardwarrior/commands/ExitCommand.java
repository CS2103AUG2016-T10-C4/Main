package ruby.keyboardwarrior.commands;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Exits the program.\n\t"
            								   + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting Keyboard Warrior as requested ...";
    public static final String MESSAGE_GOODBYE = "Good Bye! Have a nice day!";

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT, MESSAGE_GOODBYE);
    }
    
    /**
     * Method to determine if there are changes to the task.
     * 
     * @return true if there are changes
     */
    @Override
    public boolean isMutating() {
    	return false;
    }
}
