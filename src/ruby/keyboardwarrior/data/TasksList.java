package ruby.keyboardwarrior.data;

import ruby.keyboardwarrior.data.task.*;
import ruby.keyboardwarrior.data.task.UniqueTasksList.*;
//import ruby.keyboardwarrior.data.tag.Tag;
//import ruby.keyboardwarrior.data.tag.UniqueTagList;
//import ruby.keyboardwarrior.data.tag.UniqueTagList.*;

import java.util.*;

/**
 * Represents the entire address book. Contains the data of the address book.
 *
 * Guarantees:
 *  - Every tag found in every person will also be found in the tag list.
 *  - The tags in each person point to tag objects in the master list. (== equality)
 */
public class TasksList {

    private final UniqueTasksList allTasks;
//    private final UniqueTagList allTags; // can contain tags not attached to any person

    public static TasksList empty() {
        return new TasksList();
    }

    /**
     * Creates an empty address book.
     */
    public TasksList() {
        allTasks = new UniqueTasksList();
//        allTags = new UniqueTagList();
    }

    /**
     * Constructs an address book with the given data.
     * Also updates the tag list with any missing tags found in any person.
     *
     * @param persons external changes to this will not affect this address book
     * @param tags external changes to this will not affect this address book
     */
    public TasksList(UniqueTasksList persons/*, UniqueTagList tags*/) {
        this.allTasks = new UniqueTasksList(persons);
/*        this.allTags = new UniqueTagList(tags);
        for (Person p : allPersons) {
            syncTagsWithMasterList(p);
        }*/
    }

    /**
     * Ensures that every tag in this person:
     *  - exists in the master list {@link #allTags}
     *  - points to a Tag object in the master list
     */
    /*private void syncTagsWithMasterList(Person person) {
        final UniqueTagList personTags = person.getTags();
        allTags.mergeFrom(personTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : allTags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of person tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : personTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        person.setTags(new UniqueTagList(commonTagReferences));
    }*/

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #allTags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #allTags}.
     *
     * @throws DuplicateTaskException if an equivalent person already exists.
     */
    public void addTask(Task toAdd) throws DuplicateTaskException {
//        syncTagsWithMasterList(toAdd);
        allTasks.add(toAdd);
    }

    /**
     * Adds a tag to the list of tags present in the address book.
     *
     * @throws DuplicateTagException if an equivalent tag already exists.
     */
 /*   public void addTag(Tag toAdd) throws DuplicateTagException {
        allTags.add(toAdd);
    }*/

    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsTask(ReadOnlyTask key) {
        return allTasks.contains(key);
    }

    /**
     * Checks if an equivalent person exists in the address book.
     */
    /*public boolean containsTag(Tag key) {
        return allTags.contains(key);
    }*/

    /**
     * Removes the equivalent person from the address book.
     *
     * @throws TaskNotFoundException if no such Person could be found.
     */
    public void removeTask(ReadOnlyTask toRemove) throws TaskNotFoundException {
        allTasks.remove(toRemove);
    }

    /**
     * Removes the equivalent Tag from the address book.
     *
     * @throws TagNotFoundException if no such Tag could be found.
     */
    /*public void removeTag(Tag toRemove) throws TagNotFoundException {
        allTags.remove(toRemove);
    }*/

    /**
     * Clears all persons and tags from the address book.
     */
    public void clear() {
        allTasks.clear();
//        allTags.clear();
    }

    /**
     * Defensively copied UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniqueTasksList getAllTasks() {
        return new UniqueTasksList(allTasks);
    }

    /**
     * Defensively copied UniqueTagList of all tags in the address book at the time of the call.
     */
   /* public UniqueTagList getAllTags() {
        return new UniqueTagList(allTags);
    }*/

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TasksList // instanceof handles nulls
                && this.allTasks.equals(((TasksList) other).allTasks)
    /*            && this.allTags.equals(((AddressBook) other).allTags)*/);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(allTasks/*, allTags*/);
    }
}
