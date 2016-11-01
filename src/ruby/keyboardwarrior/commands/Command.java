package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.task.Task;

import java.util.List;
import java.util.ArrayList;
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
     * @param targetIndex last visible listing index of the target task
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of tasks.
     *
     * @param tasksDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForTasksListShownSummary(List<Task> tasksDisplayed, String type) {
    	try{
    		Integer taskType = Integer.parseInt(type);
    		switch(taskType){
    			case 0: return String.format(Messages.MESSAGE_TODO_LIST, tasksDisplayed.size());
    				
    			case 1: return String.format(Messages.MESSAGE_DEADLINE_LIST, tasksDisplayed.size());
    				
    			case 2: return String.format(Messages.MESSAGE_EVENT_LIST, tasksDisplayed.size());
    			
    			default: return String.format("list");
    		}
    	} catch (NumberFormatException | StringIndexOutOfBoundsException siobe) {
    		return String.format(Messages.MESSAGE_TASK_FOUND + type, tasksDisplayed.size());
    	}
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute() throws Exception;
    
    public abstract boolean isMutating();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TasksList tasksList, List<Task> relevantTasks) {
        this.tasksList = tasksList;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return relevantTasks.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

}
