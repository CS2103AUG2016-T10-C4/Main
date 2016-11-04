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
 * Represents the entire Tasks List. Contains the data of the Keyboard Warrior.
 */
public class TasksList {


    private final ArrayList<Task> allTasks;
    private final UniqueTagList allTags;
    
    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate task");
        }
    }
    
    /**
     * Signals that the Task given is not found.
     */
    public static class TaskNotFoundException extends IllegalValueException {
        public TaskNotFoundException() {
            super("No such Task can be found.");
        }
    }

    /**
     * Creates an empty Task List.
     */
    public TasksList() {
        this.allTasks = new ArrayList<Task>();
        this.allTags = new UniqueTagList();
    }

    /**
     * Constructs a Tasks List with the given data.
     */
    public TasksList(ArrayList<Task> tasks, UniqueTagList tags) {
        this.allTasks = new ArrayList<Task>(tasks);
        this.allTags = new UniqueTagList(tags);
        for (Task task : allTasks) {
            syncTagsWithMasterList(task);
        }
    }
    
    /**
     * Constructs a Tasks List with the given data.
     */
    public static TasksList empty() {
        return new TasksList();
    }
    
    /**
     * Ensures that every tag in this Task:
     *  - exists in the master list {@link #allTags}
     *  - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Task task) {
        final UniqueTagList taskTags = task.getTags();
        this.allTags.mergeFrom(taskTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : this.allTags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of Task tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : taskTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        task.setTags(new UniqueTagList(commonTagReferences));
    }

    /**
     * Adds a Task to the Tasks List.
     *
     * @throws DuplicateTaskException if an equivalent task already exists
     */
    public void addTask(Task toAdd) throws DuplicateTaskException{
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        this.allTasks.add(toAdd);
    }
    
    /**
     * Inserts a Task to the Tasks List.
     *
     * @throws DuplicateTaskException if an equivalent task already exists
     */
    public void addTask(int index, Task toAdd) throws DuplicateTaskException{
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        this.allTasks.add(index, toAdd);
    }

    /**
     * Checks if an equivalent Task exists in the Keyboard Warrior.
     */
    public boolean containsTask(Task key) {
        return this.allTasks.contains(key);
    }

    /**
     * Removes the equivalent Task from the Task List.
     *
     * @throws TaskNotFoundException if no such task could be found
     */
    public void removeTask(Task toRemove) throws TaskNotFoundException{
        if (!containsTask(toRemove)) {
            throw new TaskNotFoundException();
        }
        this.allTasks.remove(toRemove);
    }
    
    /**
     * Sets the equivalent Task from the Task List given a task.
     *
     * @throws TaskNotFoundException if no such Task could be found
     */
    public void setTask(Task toChange, Task editTask) throws TaskNotFoundException{
        if (!containsTask(toChange)) {
            throw new TaskNotFoundException();
        }
        this.allTasks.set(this.allTasks.indexOf(toChange), editTask);
    }
    
    /**
     * Sets the equivalent Task from the Task List given an Index.
     *
     * @throws TaskNotFoundException if no such Task could be found
     */
    public void setTask(Integer toChangeIndex, Task editTask){
        this.allTasks.set(toChangeIndex, editTask);
    }
  
    /**
     * Get method for the entire Task List.
     */
    public ArrayList<Task> getAllTasks(){
        return this.allTasks;
    }
    
    /**
     * Adds a Tag to the list of Tags present in the Task List.
     *
     * @throws DuplicateTagException if an equivalent Tag already exists
     */
    public void addTag(Tag toAdd) throws DuplicateTagException {
        allTags.add(toAdd);
    }
    
    /**
     * Checks if an equivalent Task exists in the Task List.
     */
    public boolean containsTag(Tag key) {
        return allTags.contains(key);
    }
    
    /**
     * Removes the equivalent Tag from the Task List.
     *
     * @throws TagNotFoundException if no such Tag could be found
     */
    public void removeTag(Tag toRemove) throws TagNotFoundException {
        this.allTags.remove(toRemove);
    }
    
    /**
     * Defensively copied UniqueTagList of all Tags in the Task List at the time of the call.
     */
    public UniqueTagList getAllTags() {
        return new UniqueTagList(this.allTags);
    }

    /**
     * Clears all tasks from the Tasks List.
     */
    public void clear() {
        allTasks.clear();
    }

    /**
     * Overrides the equals method for the Task list
     *
     * @Override
     */
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TasksList
                && this.allTasks.equals(((TasksList) other).allTasks)
                && this.allTags.equals(((TasksList) other).allTags));
    }

    /**
     * Overrides the hash code method for the Task list
     *
     * @Override
     */
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.allTasks);
    }
}
