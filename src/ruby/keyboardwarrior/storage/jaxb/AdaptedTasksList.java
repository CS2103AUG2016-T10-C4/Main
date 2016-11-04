package ruby.keyboardwarrior.storage.jaxb;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;
import ruby.keyboardwarrior.data.task.Task;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//@@author A0139820E
/**
 * JAXB-friendly adapted KeyboardWarrior data holder class.
 */
@XmlRootElement(name = "KeyboardWarrior")
public class AdaptedTasksList {

    @XmlElement
    private ArrayList<AdaptedTask> tasks = new ArrayList<>();
    @XmlElement
    private List<AdaptedTag> tags = new ArrayList<>();

    /**
     * No-argument constructor for JAXB use.
     */
    public AdaptedTasksList() {}

    /**
     * Converts a given Keyboard Warrior into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedTaskList
     */
    public AdaptedTasksList(TasksList source) {
        tasks = new ArrayList<>();
        tags = new ArrayList<>();
        for (Task task : source.getAllTasks()) {
            tasks.add(new AdaptedTask(task));
        }
        for (Tag tag : source.getAllTags()) {
            tags.add(new AdaptedTag(tag));
        }
    }


    /**
     * Check if any of the required filed is missing.
     * 
     * @return true if missing
     */
    public boolean isAnyRequiredFieldMissing() {
        for (AdaptedTask task : tasks) {
            if (task.isAnyRequiredFieldMissing()) {
                return true;
            }
        }
        for (AdaptedTag tag : tags) {
            if (tag.isAnyRequiredFieldMissing()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Converts this jaxb-friendly {@code AdaptedTasksList} object into the corresponding(@code TasksList} object.
     * 
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public TasksList toModelType() throws IllegalValueException {
        final ArrayList<Task> tasksList = new ArrayList<>();
        final List<Tag> tagList = new ArrayList<>();
        for (AdaptedTask task : tasks) {
            tasksList.add(task.toModelType());
        }
        for (AdaptedTag tag : tags) {
            tagList.add(tag.toModelType());
        }
        return new TasksList(tasksList, new UniqueTagList(tagList));
    }
}
