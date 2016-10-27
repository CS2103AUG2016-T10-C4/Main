package ruby.keyboardwarrior.storage.jaxb;

import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.task.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

//@@author A0139820E
/**
 * JAXB-friendly adapted person data holder class.
 */
public class AdaptedTask {

    @XmlElement(required = true)
    private String taskdetails;
 
    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedTask() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedPerson
     */
    public AdaptedTask(Task source) {
        this.taskdetails = source.toString();
    }

    /**
     * Returns true if any required field is missing.
     *
     * JAXB does not enforce (required = true) without a given XML schema.
     * Since we do most of our validation using the data class constructors, the only extra logic we need
     * is to ensure that every xml element in the document is present. JAXB sets missing elements as null,
     * so we check for that.
     */
    public boolean isAnyRequiredFieldMissing() {
        return Utils.isAnyNull(taskdetails);
    }

    /**
     * Converts this jaxb-friendly adapted task object into the Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Task toModelType() throws IllegalValueException {
        final TaskDetails task = new TaskDetails(this.taskdetails);
        final Set<Tag> tagSet = new HashSet<>();
        return new Task(task, tagSet);
    }
}
