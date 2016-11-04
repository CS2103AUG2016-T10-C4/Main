package ruby.keyboardwarrior.commands;


/**
 * Represents an incorrect command.
 * Upon execution, produces some feedback and display message usage to the user.
 */
public class IncorrectCommand extends Command{

    public final String feedbackToUser;
    public final String displayToUser;

    /**
     * Represents an incorrect command. Upon execution, produces some feedback to the user.
     */
    public IncorrectCommand(String feedbackToUser, String displayToUser){
        this.feedbackToUser = feedbackToUser;
        this.displayToUser = displayToUser;
    }

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser, displayToUser);
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
