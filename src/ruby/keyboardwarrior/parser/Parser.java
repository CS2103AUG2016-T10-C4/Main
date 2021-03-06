package ruby.keyboardwarrior.parser;

import ruby.keyboardwarrior.commands.*;
import ruby.keyboardwarrior.data.exception.IllegalValueException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ruby.keyboardwarrior.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

//@@author A0139716X
/**
 * Parses user input.
 */
public class Parser {

	/**
	 * Formats for the different types of arguments
	 */
    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");
    public static final Pattern TASK_DATA_ARGS_FORMAT = Pattern.compile("(?<mainTaskDetails>[^/]+)");               
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    
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
     * No-argument constructor for JAXB use.
     */
    public Parser() {}

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws IllegalValueException 
     */
    public Command parseCommand(String userInput) throws IllegalValueException {
    	// Checks the input string to see if it matches a particular format
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        }
        
        // Checks to see if item should be saved into the stack
        if(shouldInputBeSaved(userInput))
        	allInputs.push(userInput);
        
        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");
        
        // Checks the inputed Command Word to see if matches any commands
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
     * Checks if the command entered should be saved for the undo command.
     * 
     * @returns true if it is not a list command or help command
     */
	private boolean shouldInputBeSaved(String userInput){
		if(userInput.length() > 3){
			boolean inputIsListCommand = ListCommand.COMMAND_WORD.equalsIgnoreCase(userInput.substring(0,4));
			boolean inputIsHelpCommand = HelpCommand.COMMAND_WORD.equalsIgnoreCase(userInput);
			return !inputIsListCommand && !inputIsHelpCommand;
		}
		return false;
	}

	/**
     * Parses arguments in the context of the add task command.
     * 
     * @param arguments in the string is to be parsed a a string
     * @return the prepared command
     */
    private Command prepareAdd(String args){
        // Checks if the arguments are in the correct format
    	final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        }
        
        // Attempts to add a new command
        try {
            return new AddCommand(matcher.group("mainTaskDetails"));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage(), AddCommand.MESSAGE_USAGE);
        }
    }
    
    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param arguments in the string is to be parsed as an index
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        }
    }
    
    /**
     * Parses arguments in the context of the edit task command.
     *
     * @param arguments in the string is to be parsed as an index
     * @return the prepared command
     * @throws IllegalValueException 
     */
    private Command prepareEdit(String args) throws IllegalValueException {
        try {
        	args = args.trim();
        	String index = args.substring(0,args.indexOf(' '));
        	String editTask = args.substring(args.indexOf(' '));
        	
            final int targetIndex = parseArgsAsDisplayedIndex(index);

            return new EditCommand(targetIndex, editTask);

        } catch (ParseException | NumberFormatException | StringIndexOutOfBoundsException siobe) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        }
	}

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param arguments in the string is to be parsed as index number
     * @return the parsed index number
     * @throws ParseException if an index cannot be found in the string
     * @throws NumberFormatException if an invalid number is not found in the string region
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param arguments in the string is to be parsed for a find command
     * @return the prepared command
     */
    private Command prepareFind(String args) {
    	// Checks to see if the argument matches the correct format
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }
    
    /**
     * Parses arguments in the context of undoing the previous command.
     *
     * @return the prepared command
     */
    private Command prepareUndo() {
    	allInputs.pop();
    	String previous = allInputs.pop();
        return new UndoCommand(previous);
    }

}