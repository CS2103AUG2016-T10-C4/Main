package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList.TaskNotFoundException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the item identified by the index number used in the last item listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1 I am going to change to this";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edit Item: %1$s";

    private Task editTask;
    
    public EditCommand(String targetVisibleIndex, String newTask) throws IllegalValueException {
        super(targetVisibleIndex);
		try{
	    	String lowerCaseDetails = newTask.toLowerCase();
			int byExist = lowerCaseDetails.lastIndexOf(DEADLINE_WORD);
			int fromExist = lowerCaseDetails.lastIndexOf(EVENT_WORD);
				
			if(byExist != -1 && Integer.parseInt(lowerCaseDetails.substring(byExist+4,byExist+10)) > 0){
				if(newTask.length() > byExist+10)
					this.editTask = new Task(new TaskDetails(newTask.substring(0,byExist)), new DateTime(newTask.substring(byExist+4)));
				else
					this.editTask = new Task(new TaskDetails(newTask.substring(0,byExist)), new Date(newTask.substring(byExist+4)));
			}
			else if(fromExist != -1){
				this.editTask = new Task(new TaskDetails(newTask.substring(0,fromExist)), new DateTime(newTask.substring(fromExist+6,fromExist+17)), new DateTime(newTask.substring(fromExist+18, fromExist+29)));
			}
			else{
				this.editTask = new Task(new TaskDetails(newTask));
			}
		}
		catch (NumberFormatException | StringIndexOutOfBoundsException siobe){
			this.editTask = new Task(new TaskDetails(newTask));
		}
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
