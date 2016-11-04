package ruby.keyboardwarrior.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ruby.keyboardwarrior.logic.Logic;
import ruby.keyboardwarrior.Main;

import java.io.File;
import java.io.IOException;

/**
 * The GUI of the Application
 */
public class Gui {

    /** 
     * Offset required to convert between 1-indexing and 0-indexing.
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** 
     * The initial display size of the launch window.
     */
    public static final int INITIAL_WINDOW_WIDTH = 1200;
    public static final int INITIAL_WINDOW_HEIGHT = 600;
    
    private final Logic logic;
    private MainWindow mainWindow;
    private String version;

    /** 
     * Initialize the logic or the "brains" of the program.
     */
    public Gui(Logic logic, String version) {
        this.logic = logic;
        this.version = version;
    }

    /** 
     * Display the main window and welcome message of the application to the user.
     */
    public void start(Stage stage, Stoppable mainApp) throws Exception {
        mainWindow = createMainWindow(stage, mainApp);
        mainWindow.displayWelcomeMessage(version, logic.getStorageFilePath());
        stage.getIcons().add(new Image("file:docs/images/Keyboardwarrior.jpg"));
    }

    /** 
     * Creates a main window and initializing its logic and titles.
     */
    private MainWindow createMainWindow(Stage stage, Stoppable mainApp) throws IOException{
        // Sets stage
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ui" + File.separator + "mainwindow.fxml"));
        stage.setTitle(version);
        stage.setScene(new Scene(loader.load(), INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
        stage.show();
        
        // Create mainWindow
        MainWindow mainWindow = loader.getController();
        mainWindow.setLogic(logic);
        mainWindow.setMainApp(mainApp);
        return mainWindow;
    }

}
