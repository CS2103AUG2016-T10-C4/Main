package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;
import ruby.keyboardwarrior.data.tag.Tag;
//import ruby.keyboardwarrior.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a task into the Keyboard Warrior.\n"
            + "Parameters for Floating Task: TASK \n\t"
            + "Example: " + COMMAND_WORD
            + "add do the dishes\n"
		    + "Parameters for Deadlines: TASK by [DATE] [TIME] \n\t"
		    + "Example: " + COMMAND_WORD
		    + "add do CS2103 Tutorial by Wednesday\n"
            + "Parameters for Specific Task: DATE TIME [to TIME] TASK [@VENUE] \n\t"
            + "Example: " + COMMAND_WORD
            + "add 010616 1800 to 2000 Go to the Mall @Clementi Mall\n";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the Keyboard Warrior.\n";

    private final Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new TaskDetails(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate)/*,
                new UniqueTagList(tagSet)*/
        );
    }

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    public ReadOnlyTask getPerson() {
        return toAdd;
    }

    @Override
    public CommandResult execute() throws Exception{
        try {
            tasksList.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTasksList.DuplicateTaskException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
