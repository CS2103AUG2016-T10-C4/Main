package ruby.keyboardwarrior.ui;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import ruby.keyboardwarrior.commands.ExitCommand;
import ruby.keyboardwarrior.commands.HelpCommand;
import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.logic.Logic;
import ruby.keyboardwarrior.commands.CommandResult;

import java.util.List;
import java.util.Optional;

import static ruby.keyboardwarrior.common.Messages.*;

//@@author A0139820E
/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;

    public MainWindow(){
    }

    public void setLogic(Logic logic){
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }
    
    @FXML
    private TextArea TasksListView;

    @FXML
    private TextField commandInput;
    
    @FXML
    private Label userAction;


    @FXML
    void onCommand(ActionEvent event) {
        try {
        	TasksListView.clear();
            String userCommandText = commandInput.getText();
            String findCommand = "find";
            String listCommand = "list";
            CommandResult result = logic.execute(userCommandText);
            if(isExitCommand(result)){
                exitApp();
                return;
            }
            
            if(listCommand.equalsIgnoreCase(userCommandText)){
            	display(result.feedbackToUser);
            	listAll();
        	}else if(userCommandText.length() > 3 && (findCommand.equalsIgnoreCase(userCommandText.substring(0,4)) || listCommand.equalsIgnoreCase(userCommandText.substring(0,4)))){
            	display(userCommandText);
            	displayAll(result);
            } else if(result.feedbackToUser.length() > 22 && result.feedbackToUser.substring(0,22).equals("Invalid command format")){
            	display(result.feedbackToUser);
            } else if (result.feedbackToUser.length() > 23 && result.feedbackToUser.substring(0,24).equals(HelpCommand.TITLE_MESSAGE)) {
            	display("Invalid Command Format!");
            	displayAll(result);           
        	} else {
        		display(result.feedbackToUser);
            	listAll();
            }
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void exitApp() throws Exception {
        mainApp.stop();
    }
    
    private void listAll () throws Exception{
    	displayAll(Messages.MESSAGE_TASKS_LISTED_OVERVIEW);
    	displayAll(logic.execute("list todo"));
    	displayAll(logic.execute("list deadline"));
    	displayAll(logic.execute("list event"));
    }

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    public void displayWelcomeMessage(String version, String storageFilePath) throws Exception {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        displayAll(MESSAGE_WELCOME + version, storageFileInfo + "\n");
        listAll();
        display();
    }
    
    /** Displays the result of a command execution to the user. */
    public void displayAll(CommandResult result) {
    	displayAll(result.feedbackToUser);
    	final Optional<List<Task>> resultTasks = result.getRelevantTasks();
    	if(resultTasks.isPresent()) {
            displayAll(resultTasks.get());
        }
    }
    
    /**
     * Displays the entire list of tasks
     */
    private void displayAll(List<Task> tasks){
        displayAll(new Formatter().format(tasks));
    }
    
    private void displayAll(String... messages){
        TasksListView.setText(TasksListView.getText() + new Formatter().format(messages));
    }
   
	private void display(String displayToUser){
		userAction.setText(new Formatter().format(displayToUser));
        display();
	}
	
	private void display(){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(8), userAction);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
	}
}