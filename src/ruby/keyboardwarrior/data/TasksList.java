package ruby.keyboardwarrior.data;

import ruby.keyboardwarrior.data.exception.DuplicateDataException;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;
import ruby.keyboardwarrior.data.tag.UniqueTagList.DuplicateTagException;
import ruby.keyboardwarrior.data.tag.UniqueTagList.TagNotFoundException;
import ruby.keyboardwarrior.data.task.*;

import java.util.*;

//@@author A0139820E
/**
 * Represents the entire tasks list. Contains the data of the keyboard warrior.
 */
public class TasksList {


    private static ArrayList<Task> allTasks;
    private final UniqueTagList allTags;
    
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
        allTags = new UniqueTagList();
    }

    /**
     * Constructs a taskslist with the given data.
     */
    public TasksList(ArrayList<Task> tasks, UniqueTagList tags) {
        this.allTasks = new ArrayList<Task>(tasks);
        this.allTags = new UniqueTagList(tags);
        for (Task task : allTasks) {
            syncTagsWithMasterList(task);
        }
    }
    
    /**
     * Ensures that every tag in this person:
     *  - exists in the master list {@link #allTags}
     *  - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Task task) {
        final UniqueTagList taskTags = task.getTags();
        allTags.mergeFrom(taskTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : allTags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of person tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : taskTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        task.setTags(new UniqueTagList(commonTagReferences));
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
     * Adds a tag to the list of tags present in the address book.
     *
     * @throws DuplicateTagException if an equivalent tag already exists.
     */
    public void addTag(Tag toAdd) throws DuplicateTagException {
        allTags.add(toAdd);
    }
    
    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsTag(Tag key) {
        return allTags.contains(key);
    }
    
    /**
     * Removes the equivalent Tag from the address book.
     *
     * @throws TagNotFoundException if no such Tag could be found.
     */
    public void removeTag(Tag toRemove) throws TagNotFoundException {
        allTags.remove(toRemove);
    }
    
    /**
     * Defensively copied UniqueTagList of all tags in the address book at the time of the call.
     */
    public UniqueTagList getAllTags() {
        return new UniqueTagList(allTags);
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
                && this.allTasks.equals(((TasksList) other).allTasks)
                && this.allTags.equals(((TasksList) other).allTags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(allTasks);
    }
}
