package ruby.keyboardwarrior.storage.jaxb;

import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;
import ruby.keyboardwarrior.data.task.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

//@@author A0139820E
/**
 * JAXB-friendly adapted person data holder class.
 */
public class AdaptedTask {

    @XmlElement(required = true)
    private Integer taskType;
    @XmlElement(required = true)
    private TaskDetails taskDetails;
    @XmlElement(required = false)
    private Date date;
    @XmlElement(required = false)
    private DateTime startTime;
    @XmlElement(required = false)
    private DateTime endTime;
    
    @XmlElement
    private List<AdaptedTag> tagged = new ArrayList<>();
    
    
    private static final String DEADLINE_WORD = " by ";
    private static final String EVENT_WORD = " from ";
    
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
    	this.taskDetails = source.getDetails();
    	this.taskType = source.getTaskType();
    	this.date = source.getDate();
    	this.startTime = source.getStartTime();
    	this.endTime = source.getEndTime();
        this.tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            this.tagged.add(new AdaptedTag(tag));
        }
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
        return Utils.isAnyNull(taskDetails) && Utils.isAnyNull(taskType);
    }

    /**
     * Converts this jaxb-friendly adapted task object into the Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */

    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (AdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        
        UniqueTagList tags = new UniqueTagList(personTags);
        
        if(this.taskType == 0){
        	return new Task(this.taskDetails, tags);
        } else if(this.taskType == 1){
        	if(this.date == null)
        		return new Task(this.taskDetails, this.endTime, tags);
        	else
        	    return new Task(this.taskDetails, this.date, tags);
        } else {
        	return new Task(this.taskDetails, this.startTime, this.endTime, tags);
        }
    }
}
