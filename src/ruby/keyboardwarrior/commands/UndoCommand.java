package ruby.keyboardwarrior.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ruby.keyboardwarrior.data.TasksList.DuplicateTaskException;
import ruby.keyboardwarrior.data.TasksList.TaskNotFoundException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.Date;
import ruby.keyboardwarrior.data.task.DateTime;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.data.task.TaskDetails;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;

//@@author A0144665Y

/**
 * Undo the last command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String DEADLINE_WORD = " by ";
    public static final String EVENT_WORD = " from ";
    public static final String TAG_WORD = " #";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n";

    public static final String MESSAGE_UNDO_TASK_SUCCESS = "Undo Item: %1$s";

    private String toUndo;
    
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public UndoCommand(String toUndo) {
        this.toUndo = toUndo;
    }

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() throws IllegalValueException {
		int space = toUndo.indexOf(' ');
    	String command = toUndo.substring(0, space);
    	String details = toUndo.substring(space+1);
    	
    	if(command.equals("add")){
    		Task toDelete = null;
        	String tagArguments = "";
        	String lowerCaseDetails = details.toLowerCase();
    		int byExist = lowerCaseDetails.lastIndexOf(DEADLINE_WORD);
    		int fromExist = lowerCaseDetails.lastIndexOf(EVENT_WORD);
    		int tagExist = lowerCaseDetails.indexOf(TAG_WORD);
    		
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
    					toDelete = new Task(new TaskDetails(details.substring(0,byExist)), new DateTime(details.substring(byExist+4, byExist+15)), new UniqueTagList(tagSet));
    				}
    			}
    			else if(fromExist != -1){
    				toDelete = new Task(new TaskDetails(details.substring(0,fromExist)), new DateTime(details.substring(fromExist+6,fromExist+17)), new DateTime(details.substring(fromExist+18, fromExist+29)), new UniqueTagList(tagSet));
    			}
    			else if(tagExist != -1){
    				toDelete = new Task(new TaskDetails(details.substring(0, tagExist)), new UniqueTagList(tagSet));
    			}
    			else{
    				toDelete = new Task(new TaskDetails(details), new UniqueTagList(tagSet));
    			}
    		}
    		catch(NumberFormatException | StringIndexOutOfBoundsException siobe){
    			try{
    				if(byExist != -1 && Integer.parseInt(lowerCaseDetails.substring(byExist+4,byExist+10)) > 0){
    					toDelete = new Task(new TaskDetails(details.substring(0,byExist)), new Date(details.substring(byExist+4, byExist+10)), new UniqueTagList(tagSet));
    				}
    			}
    			catch(NumberFormatException | StringIndexOutOfBoundsException obe){
    				if(tagExist != -1){
    					toDelete = new Task(new TaskDetails(details.substring(0, tagExist)), new UniqueTagList(tagSet));
    				}
    				else{
    					toDelete = new Task(new TaskDetails(details), new UniqueTagList(tagSet));
    				}
    			}
    		}
    		
    		tasksList.removeTask(toDelete);
    	}
    	
    	if(command.equals("delete")){
    		tasksList.addTask(Integer.parseInt(details)-1,deletedList.pop());
    	}
    	
    	if(command.equals("edit")){
    		int whiteSpace = details.indexOf(' ');
        	String index = details.substring(0, whiteSpace);
    		tasksList.setTask(Integer.parseInt(index)-1,deletedList.pop());
    	}
    	
        return new CommandResult(String.format(MESSAGE_UNDO_TASK_SUCCESS, toUndo), tasksList.getAllTasks());
    }
    
    /**
     * Method to extract the tags from the arguments
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" #", "").split(" #"));
        return new HashSet<>(tagStrings);
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
