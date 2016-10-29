package ruby.keyboardwarrior.data;

import ruby.keyboardwarrior.data.exception.DuplicateDataException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;

import java.util.*;

//@@author A0139820E
/**
 * Represents the entire tasks list. Contains the data of the keyboard warrior.
 */
public class TasksList {


    private static ArrayList<Task> allTasks = new ArrayList<Task>();
    
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate task");
        }
    }
    
    /**
     * Signals that the task given is not found.
     */
    public static class TaskNotFoundException extends IllegalValueException {
        public TaskNotFoundException() {
            super("No such Task can be found.");
        }
    }

    public static TasksList empty() {
        return new TasksList();
    }

    /**
     * Creates an empty address book.
     */
    public TasksList() {
        allTasks = new ArrayList<Task>();
    }

    /**
     * Constructs a taskslist with the given data.
     */
    public TasksList(ArrayList<Task> tasks) {
        this.allTasks = new ArrayList<Task>(tasks);
    }

    /**
     * Adds a task to the tasks list.
     *
     * @throws DuplicateTaskException if an equivalent task already exists.
     */
    public void addTask(Task toAdd) throws DuplicateTaskException{
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        allTasks.add(toAdd);
    }
    
    /**
     * Inserts a task to the tasks list.
     *
     * @throws DuplicateTaskException if an equivalent task already exists.
     */
    public void addTask(int index, Task toAdd) throws DuplicateTaskException{
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        allTasks.add(index, toAdd);
    }

    /**
     * Checks if an equivalent task exists in the keyboard warrior.
     */
    public boolean containsTask(Task key) {
        return allTasks.contains(key);
    }

    /**
     * Removes the equivalent task from the address book.
     *
     * @throws TaskNotFoundException if no such task could be found.
     */
    public void removeTask(Task toRemove) throws TaskNotFoundException{
        if (!containsTask(toRemove)) {
            throw new TaskNotFoundException();
        }
        allTasks.remove(toRemove);
    }
    
    /**
     * Sets the equivalent Task from the Task Manager.
     *
     * @throws TaskNotFoundException if no such Task could be found.
     */
    public void setTask(Task toChange, Task editTask) throws TaskNotFoundException{
        if (!containsTask(toChange)) {
            throw new TaskNotFoundException();
        }
        allTasks.set(allTasks.indexOf(toChange), editTask);
    }
    
    public void setTask(int toChangeIndex, Task editTask){
        allTasks.set(toChangeIndex, editTask);
    }
  
    public ArrayList<Task> getAllTasks(){
        return allTasks;
    }

    /**
     * Clears all tasks from the tasks list.
     */
    public void clear() {
        allTasks.clear();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TasksList // instanceof handles nulls
                && this.allTasks.equals(((TasksList) other).allTasks));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(allTasks);
    }
}
