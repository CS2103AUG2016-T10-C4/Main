package ruby.keyboardwarrior.logic;

import ruby.keyboardwarrior.commands.Command;
import ruby.keyboardwarrior.commands.CommandResult;
import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.parser.Parser;
import ruby.keyboardwarrior.storage.StorageFile;
import ruby.keyboardwarrior.storage.StorageFile.InvalidStorageFilePathException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main Logic of the AddressBook.
 */
public class Logic {


    private StorageFile storage;
    private TasksList tasksList;

    /** The list of person shown to the user most recently.  */
    private List<Task> lastShownList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setTasksList(storage.load());
    }

    Logic(StorageFile storageFile, TasksList tasksList){
        setStorage(storageFile);
        setTasksList(tasksList);
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setTasksList(TasksList tasksList){
        this.tasksList = tasksList;
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage() throws InvalidStorageFilePathException {
        return new StorageFile();
    }

    public String getStorageFilePath() {
        return storage.getPath();
    }

    /**
     * Unmodifiable view of the current last shown list.
     */
    public List<Task> getLastShownList() {
        return Collections.unmodifiableList(lastShownList);
    }
    

    protected void setLastShownList(List<Task> newList) {
        lastShownList = newList;
    }

    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        recordResult(result);
        return result;
    }

    /**
     * Executes the command, updates storage, and returns the result.
     *
     * @param command user command
     * @return result of the command
     * @throws Exception if there was any problem during command execution.
     */
    private CommandResult execute(Command command) throws Exception {
        command.setData(tasksList, lastShownList);
        CommandResult result = command.execute();
        if (command.isMutating()) {
        	 storage.save(tasksList);
        }
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<Task>> personList = result.getRelevantTasks();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }
}
