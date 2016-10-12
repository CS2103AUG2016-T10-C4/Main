package ruby.keyboardwarrior.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.data.task.TaskDetails;

public class StorageFile {
    private static final String DEFAULT_STORAGE_FILEPATH = "keyboardwarrior.txt";

    private static final String MESSAGE_INVALID_STORAGE_FILE_CONTENT = null;
    
    private static String storageFilePath;

    private static ArrayList<Task> allTasks;
    
    public StorageFile (){
        setupDefaultFileForStorage();
        allTasks = new ArrayList<Task>();
    }
    
    public TasksList load(){
        ArrayList<String> fileLines = loadLinesFromFile(storageFilePath);
        for (String line : fileLines){
            allTasks.add(new Task(new TaskDetails(line)));
        }
        return new TasksList(allTasks);
    }
    /**
     * Sets up the storage based on the default file.
     * Creates file if missing.
     * Exits program if the file cannot be created.
     */
    private static void setupDefaultFileForStorage() {
        storageFilePath = DEFAULT_STORAGE_FILEPATH;
        createFileIfMissing(storageFilePath);
    }
    
    /**
     * Creates storage file if it does not exist. Shows feedback to user.
     *
     * @param filePath file to create if not present
     */
    private static void createFileIfMissing(String filePath) {
        final File storageFile = new File(filePath);
        if (storageFile.exists()) {
            return;
        }
        
        try {
            storageFile.createNewFile();
        } catch (IOException ioe) {
            exitProgram();
        }
    }
    
    /**
     * Initialises the in-memory data using the storage file.
     * Assumption: The file exists.
     */
    /*private static void loadDataFromStorage() {
        initialiseKeyboardWarriorModel(loadLinesFromFile(storageFilePath));
    }*/
    
    /**
     * Resets the internal model with the given data. Does not save to file.
     *
     * @param persons list of persons to initialise the model with
     */
    /*private static void initialiseKeyboardWarriorModel(ArrayList<String> tasks) {
        allTasks.clear();
        allTasks = tasks;
    }*/
    
    /**
     * Converts contents of a file into a list of persons.
     * Shows error messages and exits program if any errors in reading or decoding was encountered.
     *
     * @param filePath file to load from
     * @return the list of decoded persons
     */
    private static ArrayList<String> loadLinesFromFile(String filePath) {
        final ArrayList<String> loadedTasks = getLinesInFile(filePath);
        if (loadedTasks == null) {
            exitProgram();
        }
        return loadedTasks;
    }
    
    /**
     * Gets all lines in the specified file as a list of strings. Line separators are removed.
     * Shows error messages and exits program if unable to read from file.
     */
    private static ArrayList<String> getLinesInFile(String filePath) {
        ArrayList<String> lines = null;
        try {
            lines = new ArrayList(Files.readAllLines(Paths.get(filePath)));
        } catch (FileNotFoundException fnfe) {
            exitProgram();
        } catch (IOException ioe) {
            exitProgram();
        }
        return lines;
    }
    
    public void save(TasksList taskslist){
        ArrayList<String> linesinlist = null;
        for (Task task : taskslist.getAllTasks()){
            linesinlist.add(task.getDetails().details);
        }
    }
    /**
     * Saves all data to the file.
     * Exits program if there is an error saving to file.
     *
     * @param filePath file for saving
     */
    private static void saveTasksToFile(ArrayList<String> tasks, String filePath) {
        final ArrayList<String> linesToWrite = tasks;
        try {
            Files.write(Paths.get(storageFilePath), linesToWrite);
        } catch (IOException ioe) {
            exitProgram();
        }
    }
    
    public String getPath() {
        return storageFilePath;
    }
    
    /**
     * Displays the goodbye message and exits the runtime.
     */
    private static void exitProgram() {
        System.exit(0);
    }
}
