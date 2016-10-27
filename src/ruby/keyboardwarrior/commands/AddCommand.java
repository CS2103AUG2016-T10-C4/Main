package ruby.keyboardwarrior.commands;

import java.util.Scanner;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;

//@@author A0144665Y

/**
 * Adds a task to Keyboard Warrior.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String DEADLINE_WORD = " by ";
    public static final String EVENT_WORD = " from ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a task to the Keyboard Warrior. "
            + "Only supports task details which can be enter after; the command word seperated by a space. \n\t"
            + "To add a Todo: \n\t\t"
            + "Format: add TASK \n\t\t"
            + "Example: " + COMMAND_WORD + " do something\n\t"
            + "To add a Deadline: \n\t\t"
            + "Format: add TASK by DATE [TIME] \n\t\t"
            + "Example: " + COMMAND_WORD + " do something" + DEADLINE_WORD + "120416 1800\n\t"
            + "To add an Event: \n\t\t"
            + "Format: add EVENT from STARTTIME [ENDTIME] \n\t\t"
            + "Example: " + COMMAND_WORD + " some event" + EVENT_WORD + "221016 0900 221016 1700\n\t\t";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Keyboard Warrior";

    private Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */

    public AddCommand(String details) throws IllegalValueException {
    	/*
    	StringBuilder itemDetails = new StringBuilder();
     	StringBuilder startTime = new StringBuilder();
		StringBuilder endTime = new StringBuilder();
		String deadline;
		Scanner scanDetails = new Scanner(details);
		Scanner scanType = new Scanner(details);

        while(scanDetails.hasNext()) {
        	String check = scanDetails.next();
        	if(check.startsWith("by")){
        		break;
        	}
        	if(check.startsWith("from")){
        		break;
        	}
        	else{
        		itemDetails.append(" " + check);
        	}
        }
        scanDetails.close();
        
        if(scanType.findInLine("by") != null) {
        	deadline = scanType.nextLine();
        	if(deadline.length() > 10){
        		this.toAdd = new Task(new TaskDetails(itemDetails.toString().trim()), new DateTime(deadline));
        	}
        	else{
        		this.toAdd = new Task(new TaskDetails(itemDetails.toString().trim()), new Date(deadline));
        	}
        }
        else if(scanType.findInLine("from") != null){
        	startTime.append(scanType.next()).append(" " + scanType.next());
        	endTime.append(scanType.next()).append(" " + scanType.next());
        	this.toAdd = new Task(new TaskDetails(itemDetails.toString().trim()), new DateTime(startTime.toString()), new DateTime(endTime.toString()));
        }
        else{
        	this.toAdd = new Task(new TaskDetails(details));
        }
        scanType.close();
        */

		try{
	    	String lowerCaseDetails = details.toLowerCase();
			int byExist = lowerCaseDetails.lastIndexOf(DEADLINE_WORD);
			int fromExist = lowerCaseDetails.lastIndexOf(EVENT_WORD);
				
			if(byExist != -1 && Integer.parseInt(lowerCaseDetails.substring(byExist+4,byExist+10)) > 0){
				if(details.length() > byExist+10)
					this.toAdd = new Task(new TaskDetails(details.substring(0,byExist)), new DateTime(details.substring(byExist+4)));
				else
					this.toAdd = new Task(new TaskDetails(details.substring(0,byExist)), new Date(details.substring(byExist+4)));
			}
			else if(fromExist != -1){
				this.toAdd = new Task(new TaskDetails(details.substring(0,fromExist)), new DateTime(details.substring(fromExist+6,fromExist+17)), new DateTime(details.substring(fromExist+18, fromExist+29)));
			}
			else{
				this.toAdd = new Task(new TaskDetails(details));
			}
		}
		catch (NumberFormatException | StringIndexOutOfBoundsException siobe){
			this.toAdd = new Task(new TaskDetails(details));
		}
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
    
    public Task getTask(){
        return toAdd;
    }
    
    @Override
    public boolean isMutating() {
    	return true;
    }
}
