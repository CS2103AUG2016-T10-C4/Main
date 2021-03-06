package ruby.keyboardwarrior.logic;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ruby.keyboardwarrior.commands.CommandResult;
import ruby.keyboardwarrior.commands.*;
import ruby.keyboardwarrior.common.Messages;
import ruby.keyboardwarrior.data.TasksList;
import ruby.keyboardwarrior.data.tag.Tag;
import ruby.keyboardwarrior.data.tag.UniqueTagList;
import ruby.keyboardwarrior.data.task.*;
import ruby.keyboardwarrior.storage.StorageFile;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static ruby.keyboardwarrior.common.Messages.*;

//@@author A0144665Y
public class LogicTest {

    /**
     * See https://github.com/junit-team/junit4/wiki/rules#temporaryfolder-rule
     */
    @Rule
    public TemporaryFolder saveFolder = new TemporaryFolder();

    private StorageFile saveFile;
    private TasksList tasksList;
    private Logic logic;

    @Before
    public void setup() throws Exception {
        saveFile = new StorageFile(saveFolder.newFile("testSaveFile.txt").getPath());
        tasksList = new TasksList();
        saveFile.save(tasksList);
        logic = new Logic(saveFile, tasksList);
    }

    @Test
    public void constructor() {
        //Constructor is called in the setup() method which executes before every test, no need to call it here again.

        //Confirm the last shown list is empty
        assertEquals(Collections.emptyList(), logic.getLastShownList());
    }

    /**
     * Executes the command and confirms that the result message is correct.
     * Both the 'tasks list' and the 'last shown list' are expected to be empty.
     * @see #assertCommandBehavior(String, String, TasksList, boolean, List)
     */
    private void assertCommandBehavior(String inputCommand, String expectedMessage) throws Exception {
        assertCommandBehavior(inputCommand, expectedMessage, TasksList.empty(),false, Collections.emptyList());
    }

    /**
     * Executes the command and confirms that the result message is correct and
     * also confirms that the following three parts of the Logic object's state are as expected:<br>
     *      - the internal address book data are same as those in the {@code expectedTasksList} <br>
     *      - the internal 'last shown list' matches the {@code lastShownList} <br>
     *      - the storage file content matches data in {@code expectedTasksList} <br>
     */
    private void assertCommandBehavior(String inputCommand,
                                      String expectedMessage,
                                      TasksList expectedTasksList,
                                      boolean isRelevantTasksExpected,
                                      List<Task> lastShownList) throws Exception {

        //Execute the command
        CommandResult r = logic.execute(inputCommand);

        //Confirm the result contains the right data
        assertEquals(expectedMessage, r.feedbackToUser);
        assertEquals(r.getRelevantTasks().isPresent(), isRelevantTasksExpected);
        if(isRelevantTasksExpected){
            assertEquals(lastShownList, r.getRelevantTasks().get());
        }

        //Confirm the state of data is as expected
        assertEquals(expectedTasksList, tasksList);
        assertEquals(lastShownList, logic.getLastShownList());
    }
//@@author A0144665Y
    
//@@author A0124453M
    @Test
    public void execute_invalid() throws Exception {
        String invalidCommand = "       ";
        assertCommandBehavior(invalidCommand,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
    
    @Test
    public void execute_unknownCommandWord() throws Exception {
        String unknownCommand = "uicfhmowqewca";
        assertCommandBehavior(unknownCommand, HelpCommand.COMMAND_WORD);
    }

    @Test
    public void execute_help() throws Exception {
        assertCommandBehavior("help", HelpCommand.COMMAND_WORD);
    }

    @Test
    public void execute_exit() throws Exception {
        assertCommandBehavior("exit", ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }
    
    
    /**
     * Confirms the 'invalid argument index number behaviour' for the given command
     * targeting a single task in the last shown list, using visible index.
     * @param commandWord to test assuming it targets a single task in the last shown list based on visible index.
     */
    private void assertInvalidIndexBehaviorForCommand(String commandWord) throws Exception {
        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        TestDataHelper helper = new TestDataHelper();
        Task p1 = helper.generateTask("1");
        Task p2 = helper.generateTask("2");
        List<Task> lastShownList = helper.generateTaskList(p1, p2);

        logic.setLastShownList(lastShownList);

        assertCommandBehavior(commandWord + " -1", expectedMessage, TasksList.empty(), false, lastShownList);
        assertCommandBehavior(commandWord + " 0", expectedMessage, TasksList.empty(), false, lastShownList);
        assertCommandBehavior(commandWord + " 3", expectedMessage, TasksList.empty(), false, lastShownList);

    }
//@@author A0124453M

//@@author A0139716X
    @Test
    public void execute_delete_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        assertCommandBehavior("delete ", expectedMessage);
        assertCommandBehavior("delete arg not number", expectedMessage);
    }

    @Test
    public void execute_delete_invalidIndex() throws Exception {
        assertInvalidIndexBehaviorForCommand("delete");
    }

    @Test
    public void execute_delete_missingInTasksList() throws Exception {

        TestDataHelper helper = new TestDataHelper();
        Task p1 = helper.generateTask("1");
        Task p2 = helper.generateTask("2");
        Task p3 = helper.generateTask("3");

        List<Task> threeTasks = helper.generateTaskList(p1, p2, p3);

        TasksList expectedTL = helper.generateTasksList(threeTasks);
        expectedTL.removeTask(p2);

        helper.addToTasksList(tasksList, threeTasks);
        tasksList.removeTask(p2);
        logic.setLastShownList(threeTasks);

        assertCommandBehavior("delete 4",
                                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX,
                                expectedTL,
                                false,
                                threeTasks);
    }

    @Test
    public void execute_find_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
        assertCommandBehavior("find ", expectedMessage);
    }

    @Test
    public void execute_find_onlyMatchesFullWordsInNames() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task pTarget1 = helper.generateTaskWithDetails("bla bla KEY bla");
        Task pTarget2 = helper.generateTaskWithDetails("bla KEY bla bceofeia");
        Task p1 = helper.generateTaskWithDetails("KE Y");
        Task p2 = helper.generateTaskWithDetails("KEYKEYKEY sduauo");

        List<Task> fourTasks = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
        TasksList expectedTL = helper.generateTasksList(fourTasks);
        List<Task> expectedList = helper.generateTaskList(pTarget1, pTarget2);
        helper.addToTasksList(tasksList, fourTasks);

        assertCommandBehavior("find KEY",
                                Command.getMessageForTasksList(expectedList, "key"),
                                expectedTL,
                                true,
                                expectedList);
    }

    @Test
    public void execute_find_isNonCaseSensitive() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task pTarget1 = helper.generateTaskWithDetails("bla bla KEY bla");
        Task pTarget2 = helper.generateTaskWithDetails("bla KEY bla bceofeia");
        Task p1 = helper.generateTaskWithDetails("key key");
        Task p2 = helper.generateTaskWithDetails("KEy sduauo");

        List<Task> fourTasks = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
        TasksList expectedTL = helper.generateTasksList(fourTasks);
        List<Task> expectedList = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
        helper.addToTasksList(tasksList, fourTasks);

        assertCommandBehavior("find KEY",
                                Command.getMessageForTasksList(expectedList, "key"),                            
                                expectedTL,
                                true,
                                expectedList);
    }

    @Test
    public void execute_find_matchesIfAnyKeywordPresent() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task pTarget1 = helper.generateTaskWithDetails("bla bla KEY bla");
        Task pTarget2 = helper.generateTaskWithDetails("bla rAnDoM bla bceofeia");
        Task p1 = helper.generateTaskWithDetails("key key");
        Task p2 = helper.generateTaskWithDetails("KEy sduauo");

        List<Task> fourTasks = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
        TasksList expectedTL = helper.generateTasksList(fourTasks);
        List<Task> expectedList = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
        helper.addToTasksList(tasksList, fourTasks);

        assertCommandBehavior("find KEY rAnDoM",
                                Command.getMessageForTasksList(expectedList, "random key"),
                                expectedTL,
                                true,
                                expectedList);
    }
//@@author A0139716X
   
//@@author A0139820E
    /**
     * A utility class to generate test data.
     */
    class TestDataHelper{

        Task aTasks() throws Exception {
            TaskDetails taskdetails = new TaskDetails("This is a task");
            return new Task(taskdetails);
        }

        /**
         * Generates a valid task using the given seed.
         * Running this function with the same parameter values guarantees the returned task will have the same state.
         * Each unique seed will generate a unique Task object.
         *
         * @param seed used to generate the person data field values
         */
        Task generateTask(String seed) throws Exception {
            return new Task(new TaskDetails("Task " + seed), new UniqueTagList(new Tag(seed)));
        }

        /** Generates the correct add command based on the task given */
        String generateAddCommand(Task p) {
            StringJoiner cmd = new StringJoiner(" ");

            cmd.add("add");

            cmd.add(p.getDetails().toString());
            return cmd.toString();
        }

        /**
         * Generates an TasksList based on the list of Tasks given.
         */
        TasksList generateTasksList(List<Task> todoTasks) throws Exception{
            TasksList tasksList = new TasksList();
            addToTasksList(tasksList, todoTasks);
            return tasksList;
        }

        /**
         * Adds the given list of Tasks to the given TasksList
         */
        void addToTasksList(TasksList tasksList, List<Task> tasksToAdd) throws Exception{
            for(Task p: tasksToAdd){
                tasksList.addTask(p);
            }
        }

        /**
         * Creates a list of Tasks based on the given Task objects.
         */
        List<Task> generateTaskList(Task... tasks) throws Exception{
            List<Task> taskList = new ArrayList<>();
            for(Task p: tasks){
                taskList.add(p);
            }
            return taskList;
        }

        /**
         * Generates a Task object with given name. Other fields will have some dummy values.
         */
         Task generateTaskWithDetails(String taskdetails) throws Exception {
            return new Task(
                    new TaskDetails(taskdetails));
         }
    }

}
