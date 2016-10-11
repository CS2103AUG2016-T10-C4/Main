package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.DuplicateDataException;

import java.util.*;

/**
 * A list of persons. Does not allow null elements or duplicates.
 *
 * @see Task#equals(Object)
 * @see Utils#elementsAreUnique(Collection)
 */
public class UniqueTasksList implements Iterable<Task> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicatePersonException extends DuplicateDataException {
        protected DuplicatePersonException() {
            super("Operation would result in duplicate persons");
        }
    }

    /**
     * Signals that an operation targeting a specified person in the list would fail because
     * there is no such matching person in the list.
     */
    public static class PersonNotFoundException extends Exception {}

    private final List<Task> internalList = new ArrayList<>();

    /**
     * Constructs empty person list.
     */
    public UniqueTasksList() {}

    /**
     * Constructs a person list with the given persons.
     */
    public UniqueTasksList(Task... persons) throws DuplicatePersonException {
        final List<Task> initialTags = Arrays.asList(persons);
        if (!Utils.elementsAreUnique(initialTags)) {
            throw new DuplicatePersonException();
        }
        internalList.addAll(initialTags);
    }

    /**
     * Constructs a list from the items in the given collection.
     * @param tasks a collection of persons
     * @throws DuplicatePersonException if the {@code persons} contains duplicate persons
     */
    public UniqueTasksList(Collection<Task> tasks) throws DuplicatePersonException {
        if (!Utils.elementsAreUnique(tasks)) {
            throw new DuplicatePersonException();
        }
        internalList.addAll(tasks);
    }

    /**
     * Constructs a shallow copy of the list.
     */
    public UniqueTasksList(UniqueTasksList source) {
        internalList.addAll(source.internalList);
    }

    /**
     * Unmodifiable java List view with elements cast as immutable {@link ReadOnlyTask}s.
     * For use with other methods/libraries.
     * Any changes to the internal list/elements are immediately visible in the returned list.
     */
    public List<ReadOnlyTask> immutableListView() {
        return Collections.unmodifiableList(internalList);
    }


    /**
     * Checks if the list contains an equivalent person as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        return internalList.contains(toCheck);
    }

    /**
     * Adds a person to the list.
     *
     * @throws DuplicatePersonException if the person to add is a duplicate of an existing person in the list.
     */
    public void add(Task toAdd) throws DuplicatePersonException {
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent person from the list.
     *
     * @throws PersonNotFoundException if no such person could be found in the list.
     */
    public void remove(ReadOnlyTask toRemove) throws PersonNotFoundException {
        final boolean personFoundAndDeleted = internalList.remove(toRemove);
        if (!personFoundAndDeleted) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Clears all persons in list.
     */
    public void clear() {
        internalList.clear();
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTasksList // instanceof handles nulls
                && this.internalList.equals(
                        ((UniqueTasksList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
