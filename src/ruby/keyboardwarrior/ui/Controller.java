package ruby.keyboardwarrior.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ruby.keyboardwarrior.commands.ExitCommand;
import ruby.keyboardwarrior.commands.HelpCommand;
import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.task.Task;
import ruby.keyboardwarrior.logic.Logic;
import ruby.keyboardwarrior.commands.CommandResult;

import java.util.List;
import java.util.Optional;

import static ruby.keyboardwarrior.common.Messages.*;

/**
 * The Controller of the app
 */
public class Controller {
	
	@FXML
	private TextArea outputConsole;

	@FXML
	private TextArea TasksListView;

	@FXML
	private TextField commandInput;

	public void userPressEnter(){
		outputConsole.setText("Invalid Command Format!");
	}

}
