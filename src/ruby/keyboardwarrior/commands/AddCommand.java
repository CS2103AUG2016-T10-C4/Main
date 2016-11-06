package ruby.keyboardwarrior.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;

//@@author A0144665Y

/**
 * Adds a task to Keyboard Warrior.
 */
public class AddCommand extends Command {

    /**
     * Command words and identifier.
     */
    public static final String COMMAND_WORD = "add";
    public static final String DEADLINE_IDENTIFIER = " by ";
    public static final String EVENT_IDENTIFIER = " from ";
    public static final String TAG_IDENTIFIER = " #";

    /**
     * Messages to be displayed to the user.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a task to the Keyboard Warrior."
            + "Only supports task details which can be enter after; the command word seperated by a space. \n\t"
            + "To add a Todo: \n\t\t"
            + "Format: add TASK [#TAG] \n\t\t"
            + "Example: " + COMMAND_WORD + " do something" + TAG_IDENTIFIER + "leisure\n\t"
            + "To add a Deadline: \n\t\t"
            + "Format: add TASK by DATE [TIME] [#TAG] \n\t\t"
            + "Example: " + COMMAND_WORD + " do something" + DEADLINE_IDENTIFIER + "120416 1800\n\t"
            + "To add an Event: \n\t\t"
            + "Format: add EVENT from STARTTIME [ENDTIME] [#TAG]\n\t\t"
            + "Example: " + COMMAND_WORD + " some event" + EVENT_IDENTIFIER + "221016 0900 221016 1700" + TAG_IDENTIFIER + "school\n\t\t";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Keyboard Warrior";

    private Task toAdd;

    /**
     * Obtain Tags from arguments
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
    	if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" #", "").split(" #"));
        return new HashSet<>(tagStrings);
    }
    
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String details) throws IllegalValueException {
    	String tagArguments = "";
    	String lowerCaseDetails = details.toLowerCase();
		int byExist = lowerCaseDetails.lastIndexOf(DEADLINE_IDENTIFIER);
		int fromExist = lowerCaseDetails.lastIndexOf(EVENT_IDENTIFIER);
		int tagExist = lowerCaseDetails.indexOf(TAG_IDENTIFIER);
		
		if(tagExist != -1){
			tagArguments = lowerCaseDetails.substring(tagExist);
		}
		
		Set<String> tags = getTagsFromArgs(tagArguments);
    	final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
         
		try{				
			if(byExist != -1 && Integer.parseInt(lowerCaseDetails.substring(byExist+4,byExist+10)) > 0){
				if(Integer.parseInt(lowerCaseDetails.substring(byExist+11,byExist+15)) > 0){
					this.toAdd = new Task(new TaskDetails(details.substring(0,byExist)), new DateTime(details.substring(byExist+4, byExist+15)), new UniqueTagList(tagSet));
				}
			}
			else if(fromExist != -1){
				this.toAdd = new Task(new TaskDetails(details.substring(0,fromExist)), new DateTime(details.substring(fromExist+6,fromExist+17)), new DateTime(details.substring(fromExist+18, fromExist+29)), new UniqueTagList(tagSet));
			}
			else if(tagExist != -1){
				this.toAdd = new Task(new TaskDetails(details.substring(0, tagExist)), new UniqueTagList(tagSet));
			}
			else{
				this.toAdd = new Task(new TaskDetails(details), new UniqueTagList(tagSet));
			}
		}
		catch(NumberFormatException | StringIndexOutOfBoundsException siobe){
			try{
				if(byExist != -1 && Integer.parseInt(lowerCaseDetails.substring(byExist+4,byExist+10)) > 0){
					this.toAdd = new Task(new TaskDetails(details.substring(0,byExist)), new Date(details.substring(byExist+4, byExist+10)), new UniqueTagList(tagSet));
				}
			}
			catch(NumberFormatException | StringIndexOutOfBoundsException obe){
				if(tagExist != -1){
					this.toAdd = new Task(new TaskDetails(details.substring(0, tagExist)), new UniqueTagList(tagSet));
				}
				else{
					this.toAdd = new Task(new TaskDetails(details), new UniqueTagList(tagSet));
				}
			}
		}
		
    }

    /**
     * Convenience constructor
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() throws Exception{
        try {
            tasksList.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
				            		 getMessageForTasksList(tasksList.getAllTasks(), "4"),
					 				 tasksList.getAllTasks());
        } catch (TasksList.DuplicateTaskException dpe){
            return new CommandResult(MESSAGE_DUPLICATE_TASK, tasksList.getAllTasks());
        }
    }
    
    /**
     * Method to get Task that is to be added
     */
    public Task getTask(){
        return toAdd;
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
