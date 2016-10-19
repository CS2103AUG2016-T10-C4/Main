package ruby.keyboardwarrior.data;

import ruby.keyboardwarrior.data.exception.DuplicateDataException;
import ruby.keyboardwarrior.data.task.*;
import ruby.keyboardwarrior.data.task.TodoTask.TaskNotFoundException;

import java.util.*;

/**
 * Represents the entire address book. Contains the data of the address book.
 *
 * Guarantees:
 *  - Every tag found in every person will also be found in the tag list.
 *  - The tags in each person point to tag objects in the master list. (== equality)
 */
public class TasksList {

    private final ArrayList<TodoTask> allTasks;
    
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate task");
        }
    }

    public static TasksList empty() {
        return new TasksList();
    }

    /**
     * Creates an empty address book.
     */
    public TasksList() {
        allTasks = new ArrayList<TodoTask>();
    }

    /**
     * Constructs an address book with the given data.
     * Also updates the tag list with any missing tags found in any person.
     *
     * @param tasks external changes to this will not affect this address book
     * @param tags external changes to this will not affect this address book
     */
    public TasksList(ArrayList<TodoTask> tasks) {
        this.allTasks = new ArrayList<TodoTask>(tasks);
    }

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #allTags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #allTags}.
     *
     * @throws DuplicateTaskException if an equivalent person already exists.
     */
    public void addTask(TodoTask toAdd) throws DuplicateTaskException{
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        allTasks.add(toAdd);
    }

    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsTask(TodoTask key) {
        return allTasks.contains(key);
    }

    /**
     * Removes the equivalent person from the address book.
     *
     * @throws TaskNotFoundException if no such Person could be found.
     */
    public void removeTask(TodoTask toRemove) throws TaskNotFoundException{
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
    public void setTask(TodoTask toChange, TodoTask editTask) throws TaskNotFoundException{
        if (!containsTask(toChange)) {
            throw new TaskNotFoundException();
        }
        allTasks.set(allTasks.indexOf(toChange), editTask);
    }
    
    public void setTask(int toChangeIndex, TodoTask editTask){
        allTasks.set(toChangeIndex, editTask);
    }
    
    public ArrayList<TodoTask> getAllTasks(){
        return allTasks;
    }

    /**
     * Clears all persons and tags from the address book.
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
