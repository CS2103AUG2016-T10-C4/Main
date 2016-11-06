package ruby.keyboardwarrior.commands;


/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    
    public static final String TITLE_MESSAGE = "Entire List of Commands:"; 

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" +"Shows program usage instructions.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_ALL_USAGES = TITLE_MESSAGE
    		+ "\n" + AddCommand.MESSAGE_USAGE
            + "\n" + "\n" + DeleteCommand.MESSAGE_USAGE
            + "\n" + "\n" + ClearCommand.MESSAGE_USAGE
            + "\n" + "\n" + FindCommand.MESSAGE_USAGE
            + "\n" + "\n" + ListCommand.MESSAGE_USAGE
            + "\n" + "\n" + EditCommand.MESSAGE_USAGE
            + "\n" + "\n" + HelpCommand.MESSAGE_USAGE
            + "\n" + "\n" + ExitCommand.MESSAGE_USAGE;

    /**
     * Constructor.
     */
    public HelpCommand() {}

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_ALL_USAGES, COMMAND_WORD);
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
