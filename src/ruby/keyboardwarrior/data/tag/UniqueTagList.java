package ruby.keyboardwarrior.data.tag;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.DuplicateDataException;

import java.util.*;

//@@author A0139716X
/**
 * A list of tags that enforces no nulls and uniqueness between its elements.
 *
 * Supports minimal set of list operations for the app's features.
 *
 * @see Tag#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTagList implements Iterable<Tag> {
	
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTagException extends DuplicateDataException {
        protected DuplicateTagException() {
            super("Operation would result in duplicate tags");
        }
    }
    
    /**
     * Signals that an operation targeting a specified Tag in the list would fail because
     * there is no such matching Tag in the list.
     */
    public static class TagNotFoundException extends Exception {
        protected TagNotFoundException() {
            super("Tag is not found inside the list");
        }
    }

    /**
     * Constructs empty TagList.
     */
    public UniqueTagList() {}

    /**
     * Varargs/array constructor for the Tags, it enforces no nulls or duplicates.
     */
    public UniqueTagList(Tag... tags) throws DuplicateTagException {
        assert !Utils.isAnyNull((Object[]) tags);
        final List<Tag> initialTags = Arrays.asList(tags);
        if (!Utils.elementsAreUnique(initialTags)) {
            throw new DuplicateTagException();
        }
        this.internalList.addAll(initialTags);
    }
    
    /**
     * Java collections constructor, enforces no null or duplicate elements.
     */
    public UniqueTagList(Collection<Tag> tags) throws DuplicateTagException {
        Utils.assertNoNullElements(tags);
        if (!Utils.elementsAreUnique(tags)) {
            throw new DuplicateTagException();
        }
        this.internalList.addAll(tags);
    }

    /**
     * Java set constructor, enforces no nulls.
     */
    public UniqueTagList(Set<Tag> tags) {
    	Utils.assertNoNullElements(tags);
        this.internalList.addAll(tags);
    }

    /**
     * Copy constructor, insulates from changes in source.
     */
    public UniqueTagList(UniqueTagList source) {
    	// Insulate internal list from changes in argument
        this.internalList.addAll(source.internalList); 
    }

    /**
     * All Tags in this list as a Set. This set is mutable and change-insulated against the internal list.
     */
    public Set<Tag> toSet() {
        return new HashSet<>(this.internalList);
    }

    /**
     * Replaces the Tags in this list with those in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        this.internalList.clear();
        this.internalList.addAll(replacement.internalList);
    }

    /**
     * Adds every Tag from the argument list that does not yet exist in this list.
     */
    public void mergeFrom(UniqueTagList tags) {
        final Set<Tag> alreadyInside = this.toSet();
        for (Tag tag : tags) {
            if (!alreadyInside.contains(tag)) {
                this.internalList.add(tag);
            }
        }
    }

    /**
     * Returns true if the list contains an equivalent Tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        assert toCheck != null;
        return this.internalList.contains(toCheck);
    }

    /**
     * Adds a Tag to the list.
     *
     * @throws DuplicateTagException if the Tag to add is a duplicate of an existing Tag in the list.
     */
    public void add(Tag toAdd) throws DuplicateTagException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        this.internalList.add(toAdd);
    }
    
    /**
     * Removes the equivalent Tag from the list.
     *
     * @throws TagNotFoundException if no such Tag could be found in the list.
     */
    public void remove(Tag toRemove) throws TagNotFoundException {
        final boolean TagFoundAndDeleted = this.internalList.remove(toRemove);
        if (!TagFoundAndDeleted) {
            throw new TagNotFoundException();
        }
    }

    /**
     * Returns an iterator for the list of Tags
     */
    @Override
    public Iterator<Tag> iterator() {
        return this.internalList.iterator();
    }

    /**
     * Get method for an internal list of Tags
     */
    public ObservableList<Tag> getInternalList() {
        return this.internalList;
    }
    
    /**
     * Overrides the to String method for the Tag list
     */
    @Override
    public String toString() {
    	final StringBuilder builder = new StringBuilder();
    	for(Tag t : this.internalList){
    		String stringTag = t.toString();
    		builder.append(stringTag);
    	}
    	return builder.toString();
    }

    /**
     * Overrides the equals method for the Tag list
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instance of handles nulls
                && this.internalList.equals(
                ((UniqueTagList) other).internalList));
    }
    
    /**
     * Overrides the hash code method for the Tag list
     */
    @Override
    public int hashCode() {
        return this.internalList.hashCode();
    }
}
