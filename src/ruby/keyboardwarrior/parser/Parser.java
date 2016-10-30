package ruby.keyboardwarrior.parser;

import ruby.keyboardwarrior.commands.*;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.data.task.TaskDetails;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ruby.keyboardwarrior.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?p{Alpha}<targetIndex>.+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<taskdetails>[^/]+)");
    
    public static final String TODO_TYPE = "todo";
    public static final String DEADLINE_TYPE = "deadline";
    public static final String EVENT_TYPE = "event";

    public static Stack<String> allInputs = new Stack<String>(); 

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Parser() {}

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws IllegalValueException 
     */
    public Command parseCommand(String userInput) throws IllegalValueException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        
        if(userInput.length() > 3 && userInput.substring(0,4).equals("list") && !userInput.equals("help"))
        	allInputs.push(userInput);
        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");
        switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return prepareAdd(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
                
            case EditCommand.COMMAND_WORD:
                return prepareEdit(arguments);
                
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
                
            case UndoCommand.COMMAND_WORD:
            	return prepareUndo();

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }

	/**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args){
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            return new AddCommand(matcher.group("taskdetails"));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final String targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }
    
    /**
     * Parses arguments in the context of the edit task command.
     *
     * @param args full command args string
     * @return the prepared command
     * @throws IllegalValueException 
     */
    private Command prepareEdit(String args) throws IllegalValueException {
        try {
        	args = args.trim();
        	String index = args.substring(0,args.indexOf(' '));
        	String editTask = args.substring(args.indexOf(' '));
        	
            final String targetIndex = parseArgsAsDisplayedIndex(index);
            return new EditCommand(targetIndex, editTask);
        } catch (ParseException | NumberFormatException | StringIndexOutOfBoundsException siobe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
	}

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private String parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return matcher.group("targetIndex");
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }
    
    /**
     * Parses arguments in the context of undoing the previous command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareUndo() {
    	allInputs.pop();
    	String previous = allInputs.pop();
        return new UndoCommand(previous);
    }

}