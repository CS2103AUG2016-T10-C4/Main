package ruby.keyboardwarrior.ui;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import ruby.keyboardwarrior.commands.ExitCommand;
import ruby.keyboardwarrior.commands.ListCommand;
import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.logic.Logic;
import ruby.keyboardwarrior.commands.CommandResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ruby.keyboardwarrior.common.Messages.*;

//@@author A0144665Y
/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;

    /**
     * No-argument constructor for JAXB use.
     */
    public MainWindow(){
    }

    /**
     * Set method for Logic.
     */
    public void setLogic(Logic logic){
        this.logic = logic;
    }

    /**
     * Set method for the main Application.
     */
    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }
    
    
    /**
     * The FXML elements of the GUI.
     */
    @FXML
    private TextArea outputWindow;
    @FXML
    private TextField commandInput;
    @FXML
    private Label userAction;

    /**
     * Executes the user input when the enter button is pressed. 
     * @throws Exception 
     */
    @FXML
    void onCommand(ActionEvent event) throws Exception {
    	clearOutputWindow();
        String userCommandText = commandInput.getText();
        CommandResult result = logic.execute(userCommandText);
        
        // Execute if the user inputs exit
        if(isExitCommand(result)){
        	exitApp();
        }
        
        displayResult(result, true);
        clearCommandInput();
    }

    /**
     * Exits the application.
     */
    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /**
     * Returns true if the result given is of an exit command.
     */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /**
     * Clears the command input box.
     */
    private void clearCommandInput() {
        commandInput.clear();
    }
    
    /**
     * Clears the task list view.
     */
    private void clearOutputWindow() {
    	outputWindow.clear();
    }

    /**
     * Displays the welcome messages of the application.
     */
    public void displayWelcomeMessage(String version, String storageFilePath) throws Exception {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        displayMessages(MESSAGE_WELCOME + version, storageFileInfo + "\n");
		displayResult(logic.execute(ListCommand.COMMAND_WORD), false);
        fadingLabel();
    }
    
    /**
     * Displays the result of a command execution to the user.
     */
    public void displayResult(CommandResult result, boolean withFeedback) {
    	final Optional<List<Task>> resultTasks = result.getRelevantTasks();
    	final String resultString = result.displayToUser;
    	final String userFeedback = result.feedbackToUser;
    	if(resultString != "") {	
    		displayMessages(resultString);
        }
    	if(resultTasks.isPresent()) {
    		List<ArrayList<Task>> sortedTaskList = sortTask(resultTasks.get());
    		displayAllTask(sortedTaskList);
        }
    	if(withFeedback)
    		displayFeedback(userFeedback);
    }
    
    /**
     * Sorts the entire list of tasks into their specific category.
     */
    private List<ArrayList<Task>> sortTask(List<Task> tasks){
    	List<ArrayList<Task>> sortedTask = new ArrayList<ArrayList<Task>>();
    	sortedTask.add(obtainSpecificTaskList(tasks, 0));
    	sortedTask.add(obtainSpecificTaskList(tasks, 1));
    	sortedTask.add(obtainSpecificTaskList(tasks, 2));
    	return sortedTask;
    }
    
    /**
     * Obtain a list of one specific task type.
     */
    private ArrayList<Task> obtainSpecificTaskList(List<Task> tasks, Integer taskType){
    	ArrayList<Task> specificTaskList = new ArrayList<Task>();
    	for(Task task: tasks){
    		if(task.getTaskType() == taskType)
    			specificTaskList.add(task);
    	}
    	return specificTaskList;
    }
    
    /**
     * Displays all the types of task to the user.
     */
    private void displayAllTask(List<ArrayList<Task>> sortedTaskList){
    	displayTask(sortedTaskList.get(0), Messages.MESSAGE_TODO_LIST);
    	displayTask(sortedTaskList.get(1), Messages.MESSAGE_DEADLINE_LIST);
    	displayTask(sortedTaskList.get(2), Messages.MESSAGE_EVENT_LIST);
    }
    
    /**
     * Displays the task to the user.
     */
    private void displayTask(List<Task> tasks, String message){
    	if(!tasks.isEmpty()){
    		displayMessages(String.format(message, tasks.size()));
    	  	displayMessages(new Formatter().format(tasks));
    	}
    }
    
    /**
     * Displays the messages to the user.
     */
    private void displayMessages(String... messages){
    	outputWindow.setText(outputWindow.getText() + new Formatter().format(messages));
    }
   
    /**
     * Display a fading Label containing the feedback from the application.
     */
	private void displayFeedback(String displayToUser){
		userAction.setText(new Formatter().format(displayToUser));
        fadingLabel();
	}
	
    /**
     * Runs a fading transition for the Label.
     */
	private void fadingLabel(){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(10), userAction);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
	}
}