package ruby.keyboardwarrior.data.task;

import java.util.Objects;
import java.util.Set;

import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;

//@@author A0144665Y

/**
 * Represents a Task in the task manager.
 * Guarantees: field values are validated.
 */
public class Task {

    private TaskDetails details;
    private Integer taskType; // 0 for To-do, 1 for Deadline, 2 for Event
    private Date date;
    private DateTime startTime;
    private DateTime endTime;   
    private UniqueTagList tags;
    
    public Task(TaskDetails details, UniqueTagList tags) {
    	this.taskType = 0;
        this.details = details;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }
    
    public Task(TaskDetails details, DateTime dateTime, UniqueTagList tags) {
    	this.taskType = 1;
        this.details = details;
        this.endTime = dateTime;
        this.tags = new UniqueTagList(tags);
    }
    
    public Task(TaskDetails details, Date date, UniqueTagList tags) {
    	this.taskType = 1;
        this.details = details;
        this.date = date;
        this.tags = new UniqueTagList(tags);
    }
    
    public Task(TaskDetails details, DateTime startTime, DateTime endTime, UniqueTagList tags) {
    	this.taskType = 2;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = new UniqueTagList(tags);
    }
    
    public TaskDetails getDetails() {
        return this.details;
    }
    
    public Date getDate(){
    	return this.date;
    }
    
    public DateTime getStartTime(){
    	return this.startTime;
    }
    
    public DateTime getEndTime(){
    	return this.endTime;
    }
    
    public Integer getTaskType(){
    	return this.taskType;
    }
    
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    /**
     * Replaces this item's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }
    
    public boolean equals(Object other) {
        if (other instanceof Task){
            Task task = (Task) other;
            return this.getDetails().equals(task.getDetails());
        }
        else
            return false;
    }
    
    @Override
    public String toString(){
    	
    	//gets string of tags (to be modularized and put into UniqueTagList class)
    	final StringBuilder builder = new StringBuilder();
    	for(Tag t : getTags().getInternalList()){
    		String stringTag = t.toString();
    		builder.append(stringTag);
    	}
    	
    	if(taskType == 0)
    		return details.toString() + "\n\t" + builder.toString() + "\n";
    	else if (taskType == 1) {
    		if(endTime == null)
    			return details.toString() + "\n\t Deadline:\t" + date.toString() + "\n\t" + builder.toString() + "\n";
    		else
    			return details.toString() + "\n\t Deadline:\t" + endTime.toString() + "\n\t" + builder.toString() + "\n";	
    	} else {
    		return details.toString() + "\n\t Start time:\t" + startTime.toString() + "\n\t End time:\t" + endTime.toString() + "\n\t" + builder.toString() + "\n"; 
    	}
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details, taskType, date, startTime, endTime, tags);
    }

}
