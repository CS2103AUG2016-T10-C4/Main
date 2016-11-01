package ruby.keyboardwarrior.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList.TaskNotFoundException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;
import ruby.keyboardwarrior.data.task.Date;
import ruby.keyboardwarrior.data.task.DateTime;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.data.task.TaskDetails;

//@@author A0144665Y

/**
 * Edit an item identified using it's last displayed index from the task manager.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String DEADLINE_WORD = " by ";
    public static final String EVENT_WORD = " from ";
    public static final String TAG_WORD = " #";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the item identified by the index number used in the last item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1 I am going to change to this";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edit Item: %1$s";

    private Task editTask;
    
    public EditCommand(int targetVisibleIndex, String newTask) throws IllegalValueException {
        super(targetVisibleIndex);
        String tagArguments = "";
    	String lowerCaseDetails = newTask.toLowerCase();
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
					this.editTask = new Task(new TaskDetails(newTask.substring(0,byExist)), new DateTime(newTask.substring(byExist+4, byExist+15)), new UniqueTagList(tagSet));
				}
			}
			else if(fromExist != -1){
				this.editTask = new Task(new TaskDetails(newTask.substring(0,fromExist)), new DateTime(newTask.substring(fromExist+6,fromExist+17)), new DateTime(newTask.substring(fromExist+18, fromExist+29)), new UniqueTagList(tagSet));
			}
			else if(tagExist != -1){
				this.editTask = new Task(new TaskDetails(newTask.substring(0, tagExist)), new UniqueTagList(tagSet));
			}
			else{
				this.editTask = new Task(new TaskDetails(newTask), new UniqueTagList(tagSet));
			}
		}
		catch(NumberFormatException | StringIndexOutOfBoundsException siobe){
			try{
				if(byExist != -1 && Integer.parseInt(lowerCaseDetails.substring(byExist+4,byExist+10)) > 0){
					this.editTask = new Task(new TaskDetails(newTask.substring(0,byExist)), new Date(newTask.substring(byExist+4, byExist+10)), new UniqueTagList(tagSet));
				}
			}
			catch(NumberFormatException | StringIndexOutOfBoundsException obe){
				if(tagExist != -1){
					this.editTask = new Task(new TaskDetails(newTask.substring(0, tagExist)), new UniqueTagList(tagSet));
				}
				else{
					this.editTask = new Task(new TaskDetails(newTask), new UniqueTagList(tagSet));
				}
			}
		}
    }
    
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" #", "").split(" #"));
        return new HashSet<>(tagStrings);
    }


    @Override
    public CommandResult execute() throws Exception{
        try {
            final Task target = getTargetTask();
            deletedList.push(target);
            tasksList.setTask(target,editTask);
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (TaskNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKSLIST);
        }
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
