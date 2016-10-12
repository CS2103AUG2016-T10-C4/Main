package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a task to the keyboard warrior. "
            + "Only supports task details, can be enter after the command word seperated by a space. \n\t"
            + "Example: " + COMMAND_WORD
            + " do something";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the keyboard warrior";

    private final Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */

    public AddCommand(String taskdetails) throws IllegalValueException {
        this.toAdd = new Task(new TaskDetails(taskdetails));
    }

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() throws Exception{
        try {
            tasksList.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (TasksList.DuplicateTaskException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
