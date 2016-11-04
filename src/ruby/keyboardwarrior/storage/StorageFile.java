package ruby.keyboardwarrior.storage;

import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.storage.jaxb.AdaptedTasksList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

//@@author A0139820E
/**
 * Represents the file used to store address book data.
 */
public class StorageFile {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "keyboardwarrior.txt";

    /**
     * Signals that the given file path does not fulfill the storage file path constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    private final JAXBContext jaxbContext;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageFile() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageFile(String filePath) throws InvalidStorageFilePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedTasksList.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }

        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
    	String pathName = filePath.toString();
    	String extension = pathName.substring(pathName.lastIndexOf(".") + 1, pathName.length());
    	
    	if(extension.equals("txt"))
    		return true;
    	else
    		return false;
    }

    /**
     * Saves all data to this storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(TasksList tasksList) throws StorageOperationException {

        try (final Writer fileWriter = new BufferedWriter(new FileWriter(path.toFile()))) {
            final AdaptedTasksList toSave = new AdaptedTasksList(tasksList);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(toSave, fileWriter);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path + " error: " + ioe.getMessage());
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException("Error converting Keyboard Warrior into storage format");
        }
    }

    /**
     * Loads data from this storage file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public TasksList load() throws StorageOperationException {
        try (final Reader fileReader = new BufferedReader(new FileReader(path.toFile()))) {
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final AdaptedTasksList loaded = (AdaptedTasksList) unmarshaller.unmarshal(fileReader);
            
            // A Manual check for missing elements
            if (loaded.isAnyRequiredFieldMissing()) {
                throw new StorageOperationException("File data missing some elements");
            }
            return loaded.toModelType();

        // Create empty file if not found
        } catch (FileNotFoundException fnfe) {
            final TasksList empty = new TasksList();
            save(empty);
            return empty;

        // Other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException("Error parsing file data format");
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    /**
     * Get method for file path.
     */
    public String getPath() {
        return path.toString();
    }

}
