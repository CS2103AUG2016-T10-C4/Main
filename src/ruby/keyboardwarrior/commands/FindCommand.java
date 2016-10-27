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

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<Task> itemsFound = getItemsWithDetailsContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForTasksListShownSummary(itemsFound), itemsFound);
    }

    @Override
    public boolean isMutating() {
    	return false;
    }
    /**
     * Retrieve all items in Keyboard Warrior whose details contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of items found
     */

    //@@author A0139716X

    private List<Task> getItemsWithDetailsContainingAnyKeyword(Set<String> keywords) {
        final List<Task> matchedItems = new ArrayList<>();

        Set<String> lowerCaseKeywords = new HashSet<String>();
        Iterator<String> keywordsItr = keywords.iterator();
        while (keywordsItr.hasNext()){
            lowerCaseKeywords.add(keywordsItr.next().toLowerCase());	//makes it all lower case so can have non-case-sensitive searching
        }
        for (Task Task : tasksList.getAllTasks()) {
            final Set<String> wordsInName = new HashSet<>(Task.getDetails().getWordsInDetails());
            Set<String> lowerCaseWordsInName = new HashSet<String>();
            Iterator<String> nameItr = wordsInName.iterator();
            while (nameItr.hasNext())
            {
                lowerCaseWordsInName.add(nameItr.next().toLowerCase());
            }
            if (!Collections.disjoint(lowerCaseWordsInName, lowerCaseKeywords)) {
                matchedItems.add(Task);
            }
        }
        return matchedItems;
    }

}
