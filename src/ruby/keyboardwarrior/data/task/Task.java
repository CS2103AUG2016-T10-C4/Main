package ruby.keyboardwarrior.data.task;

import java.util.Objects;

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
    
    /**
     * Creates a Todo Task with details and tags.
     */
    public Task(TaskDetails details) {
        this.taskType = 0;
        this.details = details;
        this.tags = null;
    }
    
    /**
     * Creates a Todo Task with details and tags.
     */
    public Task(TaskDetails details, UniqueTagList tags) {
    	this.taskType = 0;
        this.details = details;
        this.tags = new UniqueTagList(tags);
    }
    
    /**
     * Creates a Deadline Task with details, date and tags.
     */
    public Task(TaskDetails details, Date date, UniqueTagList tags) {
    	this.taskType = 1;
        this.details = details;
        this.date = date;
        this.tags = new UniqueTagList(tags);
    }
    
    /**
     * Creates a Deadline Task with details, date, time and tags.
     */
    public Task(TaskDetails details, DateTime dateTime, UniqueTagList tags) {
    	this.taskType = 1;
        this.details = details;
        this.endTime = dateTime;
        this.tags = new UniqueTagList(tags);
    }
    
    /**
     * Creates an Event Task with details, date, start time, end time and tags.
     */
    public Task(TaskDetails details, DateTime startTime, DateTime endTime, UniqueTagList tags) {
    	this.taskType = 2;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = new UniqueTagList(tags);
    }
    
    /**
     * Get method for details.
     */
    public TaskDetails getDetails() {
        return this.details;
    }
    
    /**
     * Get method for date.
     */
    public Date getDate(){
    	return this.date;
    }
    
    /**
     * Get method for start time.
     */
    public DateTime getStartTime(){
    	return this.startTime;
    }
    
    /**
     * Get method for end time
     */
    public DateTime getEndTime(){
    	return this.endTime;
    }
    
    /**
     * Get method for task type.
     */
    public Integer getTaskType(){
    	return this.taskType;
    }
    
    /**
     * Get method for tags.
     */
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    /**
     * Set method that replaces this item's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }
    
    /**
     * Overrides the toString method for a task.
     */
    @Override
    public String toString(){
    	// Adjust the string build according to the task type
    	if(taskType == 0){ // Todo Task
    	    if (tags != null)
    	        return details.toString() + "\t" + tags.toString();
    	    else
    	        return details.toString();
    	}
    	else if(taskType == 1){ // Deadline Task
    		if(endTime == null)
    			return  details.toString() + " by " + date.toString() + "\t" +  tags.toString(); // Date Only
    		else
    			return  details.toString() +" by " + endTime.toString() + "\t" + tags.toString(); // Date and Time	
    	}
    	else{ // Event Task
    		return startTime.toString() + " to " + endTime.toString() + "\t" + details.toString() + "\t" + tags.toString(); 
    	}
    }
    
    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Task){
            Task task = (Task) other;
            return this.getDetails().equals(task.getDetails());
        }
        else
            return false;
    }
    
    /**
     * Hashes the objects for a better search method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(details, taskType, date, startTime, endTime, tags);
    }

}
