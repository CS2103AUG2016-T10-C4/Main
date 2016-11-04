package ruby.keyboardwarrior;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;
import ruby.keyboardwarrior.logic.Logic;
import ruby.keyboardwarrior.ui.Gui;
import ruby.keyboardwarrior.ui.Stoppable;

/**
 * Main entry point to the application.
 */
public class Main extends Application implements Stoppable{

    /**
     * Name and Version of the program.
     */
    public static final String VERSION = "Keyboardwarrior - Version 0.5";

    private Gui gui;

    /**
     * Launches the Graphical user interface.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        gui = new Gui(new Logic(), VERSION);
        gui.start(primaryStage, this);
    }

    /**
     * Closes the program.
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    /**
     * Launches the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}


