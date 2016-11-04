package ruby.keyboardwarrior.storage.jaxb;

import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;
import ruby.keyboardwarrior.data.task.*;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

//@@author A0139820E
/**
 * JAXB-friendly adapted Task data holder class.
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
    
    /**
     * No-argument constructor for JAXB use.
     */
    public AdaptedTask() {}


    /**
     * Converts a given Task into this class for JAXB use.
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
     * Check if any of the required filed is missing.
     * 
     * @return true if missing
     */
    public boolean isAnyRequiredFieldMissing() {
        return Utils.isAnyNull(taskDetails) && Utils.isAnyNull(taskType);
    }

    /**
     * Converts this jaxb-friendly adapted task object into the Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Task
     */
    public Task toModelType() throws IllegalValueException {
    	// Creates a new list of tags
        final List<Tag> taskTags = new ArrayList<>();
        for (AdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }
        
        // Creates a task with the unique tag list
        UniqueTagList tags = new UniqueTagList(taskTags);
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
