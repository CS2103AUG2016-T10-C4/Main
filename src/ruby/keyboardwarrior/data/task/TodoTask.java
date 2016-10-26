package ruby.keyboardwarrior.data.task;

import java.util.Objects;

/**
 * Represents a Task in the task manager.
 * Guarantees: field values are validated.
 */
public class TodoTask {

    private TaskDetails details;
    private StartTime startTime;
    private EndTime endTime;
    
    public TodoTask(TaskDetails details, StartTime startTime, EndTime endTime) {
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TaskDetails getDetails() {
        return details;
    }
    
    public StartTime getStartTime() {
        return startTime;
    }
    
    public EndTime getEndTime() {
        return endTime;
    }
    
    public boolean equals(Object other) {
        if (other instanceof TodoTask){
            TodoTask todotask = (TodoTask) other;
            return this.getDetails().equals(todotask.getDetails());
        }
        else
            return false;
    }

    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(details, startTime, endTime);
    }

    public String toString() {
    	StringBuilder taskString = new StringBuilder();
       
        if(endTime.toString().equals("") && (startTime.toString().equals(""))){
        	taskString.append(details.toString());	//To-do
    	}
    	else if((!endTime.toString().equals("")) && (startTime.toString().equals(""))){
    		taskString.append(details.toString()).append(" by " + endTime.toString());	//Deadline
    	}
    	else if((endTime.toString().equals("")) && (!startTime.toString().equals(""))){
    		taskString.append(details.toString()).append(" from " + startTime.toString());	//Event with no end time
    	}
    	else{
    		taskString.append(details.toString()).append(" from " + startTime.toString()).append(" to " + endTime.toString());	//Event with end time
    	}
        
        return taskString.toString();
    }
    
}
