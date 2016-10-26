package ruby.keyboardwarrior.storage.jaxb;

import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.*;

import javax.xml.bind.annotation.XmlElement;

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
    public AdaptedTask(TodoTask source) {
        taskdetails = source.getDetails().details;
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
     * Converts this jaxb-friendly adapted person object into the Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public TodoTask toModelType() throws IllegalValueException {
        final TaskDetails task = new TaskDetails(this.taskdetails);
        return new TodoTask(task);
    }
}
