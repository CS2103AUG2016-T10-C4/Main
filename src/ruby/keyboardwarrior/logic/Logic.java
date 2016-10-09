package ruby.keyboardwarrior.logic;

import ruby.keyboardwarrior.data.Todolist;
import ruby.keyboardwarrior.data.tasks.Todo;
//import seedu.addressbook.commands.Command;
//import seedu.addressbook.commands.CommandResult;
//import seedu.addressbook.data.AddressBook;
//import seedu.addressbook.data.person.ReadOnlyPerson;
//import seedu.addressbook.parser.Parser;
import ruby.keyboardwarrior.storage.StorageFile;

import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;

/**
 * Represents the main Logic of the AddressBook.
 */
public class Logic {


    private StorageFile storage;
    private Todolist todolist;

    /** The list of person shown to the user most recently.  */
//    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setTodolist(StorageFile.loadTodoFromFile("todolist.txt"));
    }

    public Logic(StorageFile storageFile, ArrayList<String> todolist){
        setStorage(storageFile);
        setTodolist(todolist);
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setTodolist(ArrayList<String> todoString){
        for (String todo : todoString){
            todolist.add(new Todo(todo));
        }
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage() throws StorageFile.InvalidStorageFilePathException {
        return new StorageFile();
    }

    public String getStorageFilePath() {
        return storage.getPath();
    }

    /**
     * Unmodifiable view of the current last shown list.
     */
/*    public List<ReadOnlyPerson> getLastShownList() {
        return Collections.unmodifiableList(lastShownList);
    }

    protected void setLastShownList(List<? extends ReadOnlyPerson> newList) {
        lastShownList = newList;
    }
*/
    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
/*    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        recordResult(result);
        return result;
    }
*/
    /**
     * Executes the command, updates storage, and returns the result.
     *
     * @param command user command
     * @return result of the command
     * @throws Exception if there was any problem during command execution.
     */
/*    private CommandResult execute(Command command) throws Exception {
        command.setData(addressBook, lastShownList);
        CommandResult result = command.execute();
        storage.save(addressBook);
        return result;
    }
*/
    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
/*    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }
*/
}
