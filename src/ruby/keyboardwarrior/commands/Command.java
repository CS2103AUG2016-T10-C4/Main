package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.task.Task;

import java.util.List;
import java.util.Stack;

import static ruby.keyboardwarrior.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TasksList tasksList;
    protected static Stack<Task> deletedList = new Stack<Task>();
    protected List<Task> relevantTasks;
    private int targetIndex = -1;
 
    /**
     * @param targetIndex last visible listing Index of the target Task
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute() throws Exception;
    
    /**
     * Check if there are changes to the Task inside Keyboard Warrior
     */
    public abstract boolean isMutating();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TasksList tasksList, List<Task> relevantTasks) {
        this.tasksList = tasksList;
        this.relevantTasks = relevantTasks;
    }
    
    /**
     * Constructs a feedback message to summarize an operation that displayed a listing of tasks.
     *
     * @param tasksDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForTasksList(List<Task> tasksDisplayed, String type) {
    	try{
    		Integer taskType = Integer.parseInt(type);
    		switch(taskType){
    			case 0: return String.format(Messages.MESSAGE_TODO_LIST, tasksDisplayed.size());
    				
    			case 1: return String.format(Messages.MESSAGE_DEADLINE_LIST, tasksDisplayed.size());
    				
    			case 2: return String.format(Messages.MESSAGE_EVENT_LIST, tasksDisplayed.size());
    			
    			default: return String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, tasksDisplayed.size());
    		}
    	} catch (NumberFormatException | StringIndexOutOfBoundsException siobe) {
    		return String.format(Messages.MESSAGE_TASK_FOUND + type, tasksDisplayed.size());
    	}
    }

    /**
     * Method to set the target Index.
     */
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
    
    /**
     * Extracts the the target Task in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return relevantTasks.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Method to get the target Index.
     */
    public int getTargetIndex() {
        return targetIndex;
    }
}
