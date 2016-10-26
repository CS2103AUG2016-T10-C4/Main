package ruby.keyboardwarrior.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ruby.keyboardwarrior.commands.ExitCommand;
import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.task.TodoTask;
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
    private TextArea outputConsole;
    
    @FXML
    private TextArea TasksListView;

    @FXML
    private TextField commandInput;


    @FXML
    void onCommand(ActionEvent event) {
        try {
            String userCommandText = commandInput.getText();
            CommandResult result = logic.execute(userCommandText);
            if(isExitCommand(result)){
                exitApp();
                return;
            }
            if(userCommandText.length() > 3 && (userCommandText.substring(0,4).equals("find") || userCommandText.substring(0,4).equals("list"))){
            	clearOutputConsole();
            	display(userCommandText);
            	displayAll(result);
            } else if(result.feedbackToUser.length() > 22 && result.feedbackToUser.substring(0,22).equals("Invalid command format")){
            	displayResult(result);
            } else {
            	displayResult(result);
            	displayAll(logic.execute("list"));
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

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    /** Clears the output display area */
    public void clearOutputConsole(){
        outputConsole.clear();
    }

    /** Displays the result of a command execution to the user. */
    public void displayResult(CommandResult result) {
        clearOutputConsole();
        TasksListView.clear();
        final Optional<List<TodoTask>> resultTasks = result.getRelevantTasks();
        if(resultTasks.isPresent()) {
            display(resultTasks.get());
        }
        if(result.feedbackToUser.length() > 22 && result.feedbackToUser.substring(0,22).equals("Invalid command format")){
        	display("Invalid command format!");
        	displayAll(result.feedbackToUser.substring(25));
        } else {
        	display(result.feedbackToUser);
        }
    }

    public void displayWelcomeMessage(String version, String storageFilePath) throws Exception {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        display(MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo);
        displayAll(logic.execute("list"));
    }

    /**
     * Displays the list of persons in the output display area, formatted as an indexed list.
     */
    private void display(List<TodoTask> tasks) {
        display(new Formatter().format(tasks));
    }

    /**
     * Displays the given messages on the output display area, after formatting appropriately.
     */
    private void display(String... messages) {
    	clearOutputConsole();
        outputConsole.setText(outputConsole.getText() + new Formatter().format(messages));
    }
    
    /** Displays the result of a command execution to the user. */
    public void displayAll(CommandResult result) {
    	TasksListView.clear();
    	displayAll(result.feedbackToUser);
    	final Optional<List<TodoTask>> resultTasks = result.getRelevantTasks();
    	if(resultTasks.isPresent()) {
            displayAll(resultTasks.get());
        }
    }
    
    /**
     * Displays the entire list of tasks
     */
    private void displayAll(List<TodoTask> tasks){
        displayAll(new Formatter().format(tasks));
    }
    
    private void displayAll(String... messages){
        TasksListView.setText(TasksListView.getText() + new Formatter().format(messages));
    }
}
