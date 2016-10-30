package ruby.keyboardwarrior.ui;

import ruby.keyboardwarrior.data.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for formatting text for display. e.g. for adding text decorations.
 */
public class Formatter {

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$s) %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    private static final int DISPLAYED_INDEX_OFFSET = 1;


    /** Formats the given strings for displaying to the user. */
    public String format(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String m : messages) {
        	sb.append(m + "\n");
        }
        return sb.toString();
    }

    /** Formats the given list of persons for displaying to the user. */
    public String format(List<Task> Tasks) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task Task : Tasks) {
            formattedTasks.add(Task.toString());
        }
        return format(asIndexedList(formattedTasks));
    }
    
    /** Formats the given list of persons for displaying to the user. */
    public String format(List<Task> Tasks, Integer taskType) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task Task : Tasks) {
            formattedTasks.add(Task.toString());
        }
        return format(asIndexedList(formattedTasks,taskType));
    }

    /** Formats a list of strings as an indexed list. */
    private static String asIndexedList(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
        	if(displayIndex == listItems.size())
        		formatted.append(getIndexedListItem(Integer.toString(displayIndex), listItem));
        	else
        		formatted.append(getIndexedListItem(Integer.toString(displayIndex), listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }
    
    /** Formats a list of strings as an indexed list. */
    private static String asIndexedList(List<String> listItems, Integer taskType) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
        	if(displayIndex == listItems.size())
        		formatted.append(getIndexedListItem(getPrefix(taskType) + Integer.toString(displayIndex), listItem));
        	else
        		formatted.append(getIndexedListItem(getPrefix(taskType) + Integer.toString(displayIndex), listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }
    
    private static String getPrefix(Integer taskType){
    	switch(taskType){
    		case 0: return "T";
    		case 1: return "D";
    		case 2: return "E";
    		default: return"";
    	}
    }

    /**
     * Formats a string as an indexed list item.
     *
     * @param  index for this listing
     */
    private static String getIndexedListItem(String index, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, index, listItem);
    }

}
