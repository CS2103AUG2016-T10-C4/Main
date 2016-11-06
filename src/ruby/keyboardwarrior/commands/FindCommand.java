package ruby.keyboardwarrior.commands;

import ruby.keyboardwarrior.data.task.Task;

import java.util.*;

/**
 * Finds and lists all tasks in Keyboard Warrior whose details contains any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all items whose details contain any of "
            + "the specified keywords (not case sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private Set<String> keywords;

    /**
     * Constructor for a Find Command.
     */
    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
        this.keywords = toLowerCase(keywords);
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        final List<Task> itemsFound = getItemsWithDetailsContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForTasksList(itemsFound, getInput(keywords)), itemsFound);
    }

    /**
     * Method to determine if there are changes to the task.
     * 
     * @return true if there are changes
     */
    @Override
    public boolean isMutating() {
    	return false;
    }
    
    //@@author A0139716X
    /**
     * Retrieve all items in Keyboard Warrior whose details contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of items found
     */
    private List<Task> getItemsWithDetailsContainingAnyKeyword(Set<String> keywords) {
        return getMatchedItems();
    }
    
    /**
     * Method to obtain the matched items that is not case sensitive.
     */
    private ArrayList<Task> getMatchedItems(){
    	final ArrayList<Task> matchedItems = new ArrayList<>();
    	for (Task Task : tasksList.getAllTasks()) {
            final Set<String> wordsInTask = new HashSet<>(Task.getDetails().getWordsInDetails());
            Set<String> lowerCaseWordsInTask = toLowerCase(wordsInTask);
            if (!Collections.disjoint(lowerCaseWordsInTask, this.keywords)) {
                matchedItems.add(Task);
            }
        }
    	return matchedItems;
    }
    
    /**
     * Method to make the keywords lower case for non-case-sensitive searching.
     */
    private Set<String> toLowerCase(Set<String> words){
    	Set<String> lowerCaseKeywords = new HashSet<String>();
        Iterator<String> keywordsItr = keywords.iterator(); 
        while (keywordsItr.hasNext()){
            lowerCaseKeywords.add(keywordsItr.next().toLowerCase());
        }
    	return lowerCaseKeywords;
    }
    
    /**
     * Method to get the user input.
     */
    private String getInput(Set<String> keywords){
        StringBuilder sb = new StringBuilder();
        Iterator<String> keywordsItr = keywords.iterator();
        while (keywordsItr.hasNext()) {
        	sb.append(keywordsItr.next() + " ");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

}
